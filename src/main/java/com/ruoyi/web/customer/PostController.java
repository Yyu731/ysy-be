package com.ruoyi.web.customer;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.Post;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.*;
import com.ruoyi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("customer/post")
public class PostController extends BaseController {

    @Autowired
    private PostService postService;
    @GetMapping("/hotlist")
    public TableDataInfo hotlist()
    {
        Page<Post> page = getPage();
        System.out.println(page.toString());
        postService.page(page, Wrappers.lambdaQuery(Post.class)
                .eq(Post::getReviewStatus,1));

        //10条记录里
        List<Post> records = page.getRecords();
        List<HotPostVo> hotPostVoList = postService.getHotPostList(records);
        return getDataTable(hotPostVoList);
    }

    @GetMapping("/totallist")
    public TableDataInfo totallist()
    {
        Page<Post> page = getPage();
        System.out.println(page.toString());
//        postService.page(page);
        postService.page(page, Wrappers.lambdaQuery(Post.class)
                .eq(Post::getReviewStatus,1));
        //10条记录里
        List<Post> records = page.getRecords();

        List<TotalPostVo> totalPostVoList = postService.getTotalPostList(records);
        return getDataTable(totalPostVoList);
    }
    @GetMapping("/detaillist")
    public AjaxResult detaillist(DetailPostVo detailPostVo)
    {
//        DetailPostVo detailPostList = postService.getDetailPostList(postService.getById(detailPostVo.getPostId()));
        return success(postService.getDetailPostList(postService.getById(detailPostVo.getPostId())));
    }
//    @GetMapping("/detail")
//    public AjaxResult detaillist(SchoolInfo schoolInfo) {
//        return success(schoolInfoService.getDetailSchoolVo(schoolInfoService.getById(schoolInfo.getSchoolId())));
//    }

}
