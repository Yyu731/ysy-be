package com.ruoyi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.domain.Post;

import java.util.List;

/**
 * @author ASUS
 * @description 针对表【post】的数据库操作Mapper
 * @createDate 2024-05-22 15:29:49
 * @Entity com.ruoyi.domain.Post
 */
public interface PostMapper extends BaseMapper<Post> {

    List<Post> selectPostPage(Long offset, Long size, Post post);
}




