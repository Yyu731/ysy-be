package com.ruoyi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.domain.Reply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ASUS
* @description 针对表【reply】的数据库操作Service
* @createDate 2024-05-22 18:09:28
*/
public interface ReplyService extends IService<Reply> {

    List<Reply> getReplyListByPostId(Long id);
}
