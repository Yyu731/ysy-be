package com.ruoyi.mapper;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.dto.*;
//import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 聊天请求转换器
 *
 * @author wsl
 * @date 2024/2/22
 */
@Mapper
public interface ChatRequestConvert {

    ChatRequestConvert INSTANCE = Mappers.getMapper(ChatRequestConvert.class);
    /**
     * 通用请求转换为讯飞星火请求
     *
     * @param dto 通用请求
     * @return 讯飞星火请求
     */
    default SparkDeskDTO convertSparkDesk(ChatRequestDTO dto) {
        SparkDeskDTO sparkDeskDTO = new SparkDeskDTO();
        SparkDeskPayloadDTO payload = new SparkDeskPayloadDTO();
        SparkDeskPayloadMessageDTO payloadMessage = new SparkDeskPayloadMessageDTO();

        String system = dto.getSystem();
        if (StrUtil.isNotBlank(system)) {
            MessageDTO messageDTO = new MessageDTO("system", system);
            dto.getMessages().add(0, messageDTO);
        }

        payloadMessage.setText(dto.getMessages());
        payload.setMessage(payloadMessage);

        SparkDeskParameterChatDTO parameterChat = new SparkDeskParameterChatDTO();
        parameterChat.setDomain("generalv3.5");
        JSONObject parameterChatJsonObject = new JSONObject();

        BeanUtil.copyProperties(parameterChat, parameterChatJsonObject);
        BeanUtil.copyProperties(dto.getParams(), parameterChatJsonObject);

        SparkDeskParameterDTO parameter = new SparkDeskParameterDTO();
        parameter.setChat(parameterChatJsonObject);

        sparkDeskDTO.setPayload(payload);
        sparkDeskDTO.setParameter(parameter);

        return sparkDeskDTO;
    }



}
