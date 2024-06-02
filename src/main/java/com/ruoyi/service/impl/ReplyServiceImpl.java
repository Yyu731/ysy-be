package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.domain.Reply;
import com.ruoyi.service.ReplyService;
import com.ruoyi.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ASUS
* @description 针对表【reply】的数据库操作Service实现
* @createDate 2024-05-22 18:09:28
*/
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply>
    implements ReplyService{

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<Reply> getReplyListByPostId(Long id) {
        return replyMapper.selectReplyListByTopicId(id);
    }
}




