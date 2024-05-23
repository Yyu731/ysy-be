package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.Post;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.*;
import com.ruoyi.service.PostService;
import com.ruoyi.service.SchoolInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController extends BaseController {

    @Autowired
    private PostService postService;
    @GetMapping("/hotlist")
    public TableDataInfo hotlist()
    {
        Page<Post> page = getPage();
        System.out.println(page.toString());
        postService.page(page, Wrappers.lambdaQuery(Post.class)
                .in(Post::getPostId,1,2,3)
                .eq(Post::getReviewStatus,"Accepted" ));

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
                .eq(Post::getReviewStatus,"Accepted" ));
        //10条记录里
        List<Post> records = page.getRecords();

        List<TotalPostVo> totalPostVoList = postService.getTotalPostList(records);
        return getDataTable(totalPostVoList);
    }
    @GetMapping("/detaillist")
    public TableDataInfo detaillist()
    {
        Page<Post> page = getPage();
        System.out.println(page.toString());
//        postService.page(page);
        postService.page(page, Wrappers.lambdaQuery(Post.class)
                .eq(Post::getReviewStatus,"Accepted" ));
        //10条记录里
        List<Post> records = page.getRecords();

        List<DetailPostVo> detailPostList = postService.getDetailPostList(records);
        return getDataTable(detailPostList);
    }

}
