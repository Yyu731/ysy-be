package com.ruoyi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.domain.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.domain.SchoolInfo;
import com.ruoyi.domain.vo.*;

import java.util.List;

/**
* @author ASUS
* @description 针对表【post】的数据库操作Service
* @createDate 2024-05-22 15:29:49
*/
public interface PostService extends IService<Post> {
    List<HotPostVo> getHotPostList(List<Post> postList);

    List<TotalPostVo> getTotalPostList(List<Post> postList);

    DetailPostVo getDetailPostList(Post post);

    Page<Post> getPostPage(Page<Post> page, Post post);
}
