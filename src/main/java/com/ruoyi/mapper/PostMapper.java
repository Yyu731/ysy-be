package com.ruoyi.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.domain.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author ASUS
* @description 针对表【post】的数据库操作Mapper
* @createDate 2024-05-22 15:29:49
* @Entity com.ruoyi.domain.Post
*/
public interface PostMapper extends BaseMapper<Post> {

    Page<Post> selectPostPage(Page<Post> page, Post post);
}




