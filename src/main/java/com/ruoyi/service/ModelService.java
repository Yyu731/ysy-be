package com.ruoyi.service;

import com.ruoyi.dto.ChatRequestDTO;
import com.ruoyi.dto.ChatResponseVO;


/**
 * 模型服务
 *
 * @author wsl
 * @date 2024/2/19
 */
public interface ModelService {

    /**
     * 发起请求
     *
     * @param dto 请求参数
     * @return 返回值
     * @throws Exception 异常
     */
    ChatResponseVO chatMessage(ChatRequestDTO dto) throws Exception;

}
