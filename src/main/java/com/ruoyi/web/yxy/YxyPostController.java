package com.ruoyi.web.yxy;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.MajorPrimary;
import com.ruoyi.domain.Post;
import com.ruoyi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: YxyPostController
 * Package: com.ruoyi.web.yxy
 * Description:
 *
 * @Author yy
 * @Create 2024-05-29 15:51
 * @Version 1.0
 */
@RestController
@RequestMapping("yxy/post")
public class YxyPostController extends BaseController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasAuthority('yxy:post:list')")
    @GetMapping("list")
    public TableDataInfo list(Post post) {
        Page<Post> page = getPage();
        Long offset = ((page.getCurrent() - 1) * page.getSize());
        Long pageSize = page.getSize();
        page.setRecords(postService.getPostPage(offset, pageSize, post));
        page.setTotal(postService.count());
        return getDataTable(page);
    }

    @PreAuthorize("hasAuthority('yxy:post:edit')")
    @PutMapping()
    public AjaxResult edit(@RequestBody Post post) {
        return success(postService.updateById(post));
    }

}
