package com.ruoyi.web.customer;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.domain.Reply;
import com.ruoyi.dto.ChatRequestDTO;
import com.ruoyi.dto.ChatResponseVO;
import com.ruoyi.dto.MessageDTO;
import com.ruoyi.service.IAiAppService;
import com.ruoyi.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customer/reply")
public class ReplyController extends BaseController {

    @Autowired
    private IAiAppService service;
    @Autowired
    private ReplyService replyService;

    @PostMapping("/add")
    public AjaxResult addReply(@RequestBody Reply reply) throws Exception {
        ChatRequestDTO chatRequestDTO = new ChatRequestDTO();
        List<MessageDTO> messageDTOS = new ArrayList<>();
        MessageDTO messageDTO = new MessageDTO("user", reply.getReplyContent());
        messageDTOS.add(messageDTO);
        chatRequestDTO.setMessages(messageDTOS);
        chatRequestDTO.setSystem("请你判断以上这段话的含义是否包含侮辱性骂人言论，如果是则返回数字0,如果不是则返回数字1,不要有多余的任何内容!");
        ChatResponseVO chatResponseVO = service.chatMessage("SparkDesk", chatRequestDTO);
        System.out.println(chatResponseVO.getResult());
        if (chatResponseVO.getResult().equals("1")) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            reply.setReplierId(loginUser.getUserId());
//                reply.setReplierId(1l);

            reply.setReplyTime(DateUtil.date());
            replyService.save(reply);
        } else {
            throw new ServiceException("内容违规", 440);
        }
        return success();
    }
}