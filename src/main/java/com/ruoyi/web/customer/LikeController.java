package com.ruoyi.web.customer;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.domain.PostLike;
import com.ruoyi.domain.Reply;
import com.ruoyi.domain.ReplyLike;
import com.ruoyi.dto.ChatRequestDTO;
import com.ruoyi.dto.ChatResponseVO;
import com.ruoyi.dto.MessageDTO;
import com.ruoyi.service.IAiAppService;
import com.ruoyi.service.PostLikeService;
import com.ruoyi.service.ReplyLikeService;
import com.ruoyi.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("customer/like")
public class LikeController extends BaseController {

    @Autowired
    private PostLikeService postLikeService;
    @Autowired
    private ReplyLikeService replyLikeService;

    @PostMapping("/add")
    public AjaxResult addPost(@RequestBody PostLike postLike) {
        //                LoginUser loginUser = SecurityUtils.getLoginUser();
//                postLike.setLikerId(loginUser.getUserId());

        postLike.setLikerId(1l);
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("GMT+8"));

        // 将 LocalDateTime 转换为 Date 类型
        Date currentDate = Date.from(currentDateTime.atZone(ZoneId.of("GMT+8")).toInstant());

        postLike.setLikeTime(currentDate);
        postLikeService.save(postLike);
        return success();
    }

    @PostMapping("/addreply")
    public AjaxResult addReply(@RequestBody ReplyLike replyLike) {
        //                LoginUser loginUser = SecurityUtils.getLoginUser();
//                postLike.setLikerId(loginUser.getUserId());

        replyLike.setLikerId(1l);
        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("GMT+8"));

        // 将 LocalDateTime 转换为 Date 类型
        Date currentDate = Date.from(currentDateTime.atZone(ZoneId.of("GMT+8")).toInstant());

        replyLike.setLikeTime(currentDate);
        replyLikeService.save(replyLike);
        return success();
    }

}
