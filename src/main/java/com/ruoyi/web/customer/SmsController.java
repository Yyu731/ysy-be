package com.ruoyi.web.customer;



import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.ruoyi.domain.vo.SmsRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    @PostMapping("/sendSms")
    public String sendSms(@RequestBody SmsRequest request) {
        String accessKeyId = "yourAccessKeyId";
        String accessKeySecret = "yourAccessKeySecret";
        int code = generateVerificationCode(); // 生成验证码

        try {
            com.aliyun.dysmsapi20170525.Client client = Sample.createClient(accessKeyId, accessKeySecret);
            com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                    .setSignName("研学园")
                    .setTemplateCode("SMS_467415418")
                    .setPhoneNumbers(request.getPhoneNumber())
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
            client.sendSms(sendSmsRequest); // 发送短信
            return "短信发送成功";
        } catch (TeaException e) {
            e.printStackTrace();
            return "短信发送失败";
        } catch (Exception e) {
            e.printStackTrace();
            return "短信发送失败";
        }
    }

    // 生成随机验证码的方法，你可以根据自己的需求实现
    private int generateVerificationCode() {
        // 实现你的验证码生成逻辑
        return 307014; // 这里只是一个示例，实际应用中需要根据实际情况生成验证码
    }
}

