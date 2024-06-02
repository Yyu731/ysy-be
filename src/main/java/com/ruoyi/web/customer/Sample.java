package com.ruoyi.web.customer;


// This file is auto-generated, don't edit it. Thanks.
//package com.aliyun.sample;

import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.*;

public class Sample {

    /**
     * 使用AK&SK初始化账号Client
     *
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {

        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
//        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考。
//        // 建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html。
//        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
//                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
//                .setAccessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
//                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
//                .setAccessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
//        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
//        config.endpoint = "dysmsapi.aliyuncs.com";
//        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        String accessKeyId = "";
        String accessKeySecret = "";
//        com.aliyun.dysmsapi20170525.Client client = createClient(accessKeyId, accessKeySecret);
        int code = 7834;

        com.aliyun.dysmsapi20170525.Client client = Sample.createClient(accessKeyId, accessKeySecret);
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("研学园")
                .setTemplateCode("SMS_467415418")
                .setPhoneNumbers("13579030102")
                .setTemplateParam("{\"code\":\"" + code + "\"}");
//                .setTemplateParam("{\"code\":\"1234\"}");
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值

            client.sendSmsWithOptions(sendSmsRequest, runtime);


        } catch (TeaException error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }
}
