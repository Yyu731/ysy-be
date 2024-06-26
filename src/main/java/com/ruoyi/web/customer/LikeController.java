package com.ruoyi.web.customer;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.domain.PostLike;
import com.ruoyi.domain.ReplyLike;
import com.ruoyi.service.PostLikeService;
import com.ruoyi.service.ReplyLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequestMapping("customer/like")
public class LikeController extends BaseController {

    @Autowired
    private PostLikeService postLikeService;
    @Autowired
    private ReplyLikeService replyLikeService;

    @PostMapping("/add")
    public AjaxResult addPost(@RequestBody PostLike postLike) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        postLike.setLikerId(loginUser.getUserId());
//        postLike.setLikerId(1l);

        if (postLikeService.getOne(Wrappers.lambdaQuery(PostLike.class).eq(PostLike::getPostId, postLike.getPostId())
                .eq(PostLike::getLikerId, postLike.getLikerId())) == null) {
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("GMT+8"));

            // 将 LocalDateTime 转换为 Date 类型
            Date currentDate = Date.from(currentDateTime.atZone(ZoneId.of("GMT+8")).toInstant());

            postLike.setLikeTime(currentDate);
            postLikeService.save(postLike);
        } else {
            postLikeService.remove(Wrappers.lambdaQuery(PostLike.class).eq(PostLike::getPostId, postLike.getPostId())
                    .eq(PostLike::getLikerId, postLike.getLikerId()));
        }
        return success();
    }


    @PostMapping("/addreply")
    public AjaxResult addReply(@RequestBody ReplyLike replyLike) {
        //                LoginUser loginUser = SecurityUtils.getLoginUser();
//                replyLike.setLikerId(loginUser.getUserId());

        replyLike.setLikerId(1l);
        if (replyLikeService.getOne(Wrappers.lambdaQuery(ReplyLike.class).eq(ReplyLike::getReplyId, replyLike.getReplyId())
                .eq(ReplyLike::getLikerId, replyLike.getLikerId())) == null) {
            LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("GMT+8"));

            // 将 LocalDateTime 转换为 Date 类型
            Date currentDate = Date.from(currentDateTime.atZone(ZoneId.of("GMT+8")).toInstant());

            replyLike.setLikeTime(currentDate);
            replyLikeService.save(replyLike);
        } else {
            replyLikeService.remove(Wrappers.lambdaQuery(ReplyLike.class).eq(ReplyLike::getReplyId, replyLike.getReplyId())
                    .eq(ReplyLike::getLikerId, replyLike.getLikerId()));
        }
        return success();
    }

}
