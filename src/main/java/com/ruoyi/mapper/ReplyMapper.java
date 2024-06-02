package com.ruoyi.mapper;

import com.ruoyi.domain.Reply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author ASUS
* @description 针对表【reply】的数据库操作Mapper
* @createDate 2024-05-22 18:09:28
* @Entity com.ruoyi.domain.Reply
*/
public interface ReplyMapper extends BaseMapper<Reply> {

    List<Reply> selectReplyListByTopicId(Long id);
}




