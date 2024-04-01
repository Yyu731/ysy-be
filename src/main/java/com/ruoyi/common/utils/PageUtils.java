package com.ruoyi.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import org.apache.poi.ss.formula.functions.T;

/**
 * 分页工具类
 * 
 * @author ruoyi
 */
public class PageUtils
{
    /**
     * 设置请求分页数据
     */
    public static Page getPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        return new Page(pageNum, pageSize);
    }

}
