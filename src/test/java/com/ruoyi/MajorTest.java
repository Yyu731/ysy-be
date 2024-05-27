package com.ruoyi;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.domain.Major;
import com.ruoyi.domain.MajorPrimary;
import com.ruoyi.domain.MajorSecondary;
import com.ruoyi.mapper.MajorMapper;
import com.ruoyi.mapper.MajorPrimaryMapper;
import com.ruoyi.mapper.MajorSecondaryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Objects;

/**
 * ClassName: MajorTest
 * Package: com.ruoyi
 * Description:
 *
 * @Author yy
 * @Create 2024-05-27 12:22
 * @Version 1.0
 */
@SpringBootTest
public class MajorTest {

    @Autowired
    private MajorPrimaryMapper majorPrimaryMapper;

    @Test
    public void test1() {
        String response = HttpUtil.get("https://static.kaoyan.cn/json/query/query_special.json");
        System.out.println(response);
        JSONObject json = new JSONObject(response);
        JSONArray first = json.getJSONObject("data").getJSONArray("first_class");
        System.out.println(first);
        for (int i = 0; i < first.size(); i++) {
            JSONObject object = first.getJSONObject(i);
            System.out.println(first.getJSONObject(i));
            MajorPrimary majorPrimary = new MajorPrimary();
            majorPrimary.setMajorCode(object.getStr("code"));
            majorPrimary.setMajorName(object.getStr("name"));
            majorPrimary.setCreateBy("admin");
            majorPrimary.setCreateTime(DateUtil.date());
            majorPrimaryMapper.insert(majorPrimary);
        }
    }

    @Autowired
    private MajorSecondaryMapper majorSecondaryMapper;

    @Test
    public void test2() {
        String response = HttpUtil.get("https://static.kaoyan.cn/json/query/query_special.json");
        JSONObject json = new JSONObject(response);
        System.out.println(json);
        JSONArray sencondClass = json.getJSONObject("data").getJSONArray("second_class");
        for (int i = 0; i < sencondClass.size(); i++) {
            JSONObject object = sencondClass.getJSONObject(i);
            Integer parentId =  object.getInt("root_id") - 300;
            MajorSecondary majorSecondary = new MajorSecondary();
            majorSecondary.setParentId(parentId);
            majorSecondary.setMajorName(object.getStr("name"));
            majorSecondary.setMajorCode(object.getStr("code"));
            majorSecondary.setDegreeType(object.getInt("degree_type"));
            majorSecondary.setCreateBy("admin");
            majorSecondary.setCreateTime(DateUtil.date());
            majorSecondaryMapper.insert(majorSecondary);

        }
    }

    @Autowired
    private MajorMapper majorMapper;

    @Test
    public void test3() {
        Map map = Map.of("limit", 20000, "page", 1);
        String response = HttpUtil.post("https://api.kaoyan.cn/h5/special/specialList",map);
        JSONObject json = new JSONObject(response);
//        System.out.println(json);
        JSONArray array = json.getJSONObject("data").getJSONArray("data");
        System.out.println(array.size());

        for (int i = 0; i < array.size(); i++) {
            JSONObject object = array.getJSONObject(i);
            String level2Code = object.getStr("level2_code");
            MajorSecondary majorSecondary = majorSecondaryMapper.selectOne(Wrappers.lambdaQuery(MajorSecondary.class).eq(MajorSecondary::getMajorCode, level2Code));
            if(majorSecondary == null) {
                continue;
            }
            Major major = new Major();
            major.setParentId(majorSecondary.getMajorSecondaryId());
            major.setMajorName(object.getStr("special_name"));
            major.setMajorCode(object.getStr("code"));
            major.setCreateBy("admin");
            major.setCreateTime(DateUtil.date());
            majorMapper.insert(major);
        }
    }
}
