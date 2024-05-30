package com.ruoyi.web.customer;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.domain.OrderTable;
import com.ruoyi.domain.Reply;
import com.ruoyi.domain.vo.OrderVo;
import com.ruoyi.dto.ChatRequestDTO;
import com.ruoyi.dto.ChatResponseVO;
import com.ruoyi.dto.MessageDTO;
import com.ruoyi.dto.ReplyDto;
import com.ruoyi.service.IAiAppService;
import com.ruoyi.service.OrderTableService;
import com.ruoyi.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("customer/reply")
public class ReplyController  extends BaseController {

    @Autowired
    private IAiAppService service;
    @Autowired
    private ReplyService replyService;

    @PostMapping("/add")
    public AjaxResult addReply(@RequestBody Reply reply) throws Exception {
        ChatRequestDTO chatRequestDTO=new ChatRequestDTO();
        List<MessageDTO> messageDTOS=new ArrayList<>();
        MessageDTO messageDTO=new MessageDTO("user",reply.getReplyContent());
        messageDTOS.add(messageDTO);
        chatRequestDTO.setMessages(messageDTOS);
        chatRequestDTO.setSystem("请你判断以上这段话的含义是否是不能公开发表的侮辱性骂人言论，如果是则返回数字0，如果不是则返回数字1，只用返回一个0或者1，不要返回多余的话");
            ChatResponseVO chatResponseVO=service.chatMessage("SparkDesk", chatRequestDTO);
        System.out.println(chatResponseVO.getResult());
            if(chatResponseVO.getResult().equals("1")){
//                LoginUser loginUser = SecurityUtils.getLoginUser();
//                reply.setReplierId(loginUser.getUserId());
                reply.setReplierId(1l);
                LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("GMT+8"));

                // 将 LocalDateTime 转换为 Date 类型
                Date currentDate = Date.from(currentDateTime.atZone(ZoneId.of("GMT+8")).toInstant());

                reply.setReplyTime(currentDate);
                replyService.save(reply);
            }else {
                throw new ServiceException("内容违规",440);
            }
        return success();
    }
}