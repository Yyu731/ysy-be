package com.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.domain.*;
import com.ruoyi.domain.vo.DetailPostVo;
import com.ruoyi.domain.vo.HotPostVo;
import com.ruoyi.domain.vo.ReplyVo;
import com.ruoyi.domain.vo.TotalPostVo;
import com.ruoyi.mapper.PostMapper;
import com.ruoyi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ASUS
 * @description 针对表【post】的数据库操作Service实现
 * @createDate 2024-05-22 15:29:49
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private PostImagesService postImagesService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private PostLikeService postLikeService;
    @Autowired
    private CollectService collectService;

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<HotPostVo> getHotPostList(List<Post> postList) {

        List<HotPostVo> hotPostVos = new ArrayList<>();
        for (Post post : postList) {
            HotPostVo hotPostVo = new HotPostVo();
            hotPostVo.setPostId(post.getPostId());
            hotPostVo.setAuthorId(post.getAuthorId());
            hotPostVo.setContent(post.getContent());
            hotPostVo.setTitle(post.getTitle());
            hotPostVo.setHeadPortrait(sysUserService.getById(post.getAuthorId()).getAvatar());
            hotPostVos.add(hotPostVo);
        }

        return hotPostVos;
    }

    @Override
    public List<TotalPostVo> getTotalPostList(List<Post> postList) {

        List<TotalPostVo> totalPostVos = new ArrayList<>();
        for (Post post : postList) {
            TotalPostVo totalPostVo = new TotalPostVo();
            totalPostVo.setPostId(post.getPostId());
            totalPostVo.setAuthorId(post.getAuthorId());
            totalPostVo.setTitle(post.getTitle());
            totalPostVo.setContent(post.getContent());
            totalPostVo.setPostTime(post.getPostTime());
            totalPostVo.setPostType(post.getPostType());
            totalPostVo.setNickName(sysUserService.getById(post.getAuthorId()).getNickName());
            totalPostVo.setHeadPortrait(sysUserService.getById(post.getAuthorId()).getAvatar());
            totalPostVo.setPostImages(
                    postImagesService.list(
                                    Wrappers.lambdaQuery(PostImages.class)
                                            .eq(PostImages::getPostId, post.getPostId()))
                            .stream().map(item -> item.getImageUrl()).limit(9)
                            .collect(Collectors.toList()));
            totalPostVo.setReviewNum(replyService.list(
                    Wrappers.lambdaQuery(Reply.class)
                            .eq(Reply::getPostId, post.getPostId())).size());
            totalPostVo.setLikeNum(postLikeService.list(
                    Wrappers.lambdaQuery(PostLike.class)
                            .eq(PostLike::getPostId, post.getPostId())).size());
            totalPostVo.setCollectNum(collectService.list(
                    Wrappers.lambdaQuery(Collect.class)
                            .eq(Collect::getPostId, post.getPostId())).size());
            try {
                LoginUser loginUser = SecurityUtils.getLoginUser();
                totalPostVo.setCollectStatus(collectService.getOne(Wrappers.lambdaQuery(Collect.class)
                        .eq(Collect::getPostId, post.getPostId())
                        .eq(Collect::getUserId, loginUser.getUserId())) == null ? 0 : 1);
                totalPostVo.setLikeStatus(postLikeService.getOne(Wrappers.lambdaQuery(PostLike.class)
                        .eq(PostLike::getPostId, post.getPostId())
                        .eq(PostLike::getLikerId, loginUser.getUserId())) == null ? 0 : 1);
            } catch (ServiceException e) {
                totalPostVo.setCollectStatus(0);
                totalPostVo.setLikeStatus(0);
            }
            totalPostVos.add(totalPostVo);
        }

        return totalPostVos;
    }

    @Override
    public DetailPostVo getDetailPost(Post post) {
        DetailPostVo detailPostVo = new DetailPostVo();
        detailPostVo.setPostId(post.getPostId());
        detailPostVo.setAuthorId(post.getAuthorId());
        detailPostVo.setTitle(post.getTitle());
        detailPostVo.setContent(post.getContent());
        detailPostVo.setPostTime(post.getPostTime());
        detailPostVo.setPostType(post.getPostType());
        detailPostVo.setNickName(sysUserService.getById(post.getAuthorId()).getNickName());
        detailPostVo.setHeadPortrait(sysUserService.getById(post.getAuthorId()).getAvatar());
        detailPostVo.setPostImages(
                postImagesService.list(
                                Wrappers.lambdaQuery(PostImages.class)
                                        .eq(PostImages::getPostId, post.getPostId()))
                        .stream().map(item -> item.getImageUrl()).limit(9)
                        .collect(Collectors.toList()));
        detailPostVo.setReviewNum(replyService.list(
                Wrappers.lambdaQuery(Reply.class)
                        .eq(Reply::getPostId, post.getPostId())).size());
        detailPostVo.setLikeNum(postLikeService.list(
                Wrappers.lambdaQuery(PostLike.class)
                        .eq(PostLike::getPostId, post.getPostId())).size());
        detailPostVo.setCollectNum(collectService.list(
                Wrappers.lambdaQuery(Collect.class)
                        .eq(Collect::getPostId, post.getPostId())).size());
        try {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            detailPostVo.setCollectStatus(collectService.getOne(Wrappers.lambdaQuery(Collect.class)
                    .eq(Collect::getUserId, loginUser.getUserId())) == null ? 0 : 1);
            detailPostVo.setLikeStatus(postLikeService.getOne(Wrappers.lambdaQuery(PostLike.class)
                    .eq(PostLike::getLikerId, loginUser.getUserId())) == null ? 0 : 1);
        } catch (ServiceException e) {
            detailPostVo.setCollectStatus(0);
            detailPostVo.setLikeStatus(0);
        }
        List<Reply> replies = replyService.list(Wrappers.lambdaQuery(Reply.class)
                .eq(Reply::getPostId, post.getPostId())
                .orderByDesc(Reply::getReplyTime));

        List<ReplyVo> replyVos = replies.stream().map(reply -> {
            ReplyVo replyVo = new ReplyVo();
            BeanUtils.copyProperties(reply, replyVo);
            replyVo.setLikeStatus(1);
            replyVo.setLikeNum(100);
            replyVo.setNickName(sysUserService.getById(replyVo.getReplierId()).getNickName());
            replyVo.setHeadPortrait(sysUserService.getById(replyVo.getReplierId()).getAvatar());
            return replyVo;
        }).collect(Collectors.toList());

        detailPostVo.setAllReply(replyVos);

        return detailPostVo;

    }

    @Override
    public Page<Post> getPostPage(Page<Post> page, Post post) {

        postMapper.selectPostPage(page, post);

        return page;
    }
}




