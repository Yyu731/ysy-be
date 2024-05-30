package com.ruoyi.web.customer;

import com.ruoyi.dto.ChatRequestDTO;
import com.ruoyi.dto.ChatResponseVO;
import com.ruoyi.service.IAiAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@Api(tags = "AI应用")
@RequestMapping("/customer/ai")
public class AiAppController {

    @Autowired
    private IAiAppService service;

    @PostMapping("/chat-message")
    @ApiOperation("向大模型发起对话请求")
//    @ApiParam(value = "模型类型(ErnieBot/SparkDesk/ChatGlm/QianWen)", required = false) @RequestParam String modelType,
    public ChatResponseVO chatMessage(
            @ApiParam(value = "消息参数", required = true) @RequestBody ChatRequestDTO dto) {
        try {
            String modelType="SparkDesk";
            dto.setSystem("请你判断以上这段话中是否带有不能公开发表的侮辱性骂人言论，如果有则返回数字0，如果没有则返回数字1，只用返回一个0或者1，不要返回多余的话");

//            if()

            return service.chatMessage(modelType, dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
