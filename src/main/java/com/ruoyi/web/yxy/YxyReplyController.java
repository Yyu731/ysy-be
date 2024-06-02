package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.Post;
import com.ruoyi.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: YxyReplyController
 * Package: com.ruoyi.web.yxy
 * Description:
 *
 * @Author yy
 * @Create 2024-05-29 22:23
 * @Version 1.0
 */
@RestController
@RequestMapping("yxy/reply")
public class YxyReplyController extends BaseController {

    @Autowired
    private ReplyService replyService;

    @PreAuthorize("hasAuthority('yxy:post:list')")
    @GetMapping("{id}")
    public TableDataInfo list(@PathVariable Long id) {
        return getDataTable(replyService.getReplyListByPostId(id));
    }
}
