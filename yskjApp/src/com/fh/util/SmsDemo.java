package com.fh.util;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.dysmsapi.transform.v20170525.SendSmsResponseUnmarshaller;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fh.entity.WxUser;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created on 17/6/7.
 * 短信API产品的DEMO程序,工程中包含了一个SmsDemo类，直接通过
 * 执行main函数即可体验短信产品API功能(只需要将AK替换成开通了云通信-短信产品功能的AK即可)
 * 工程依赖了2个jar包(存放在工程的libs目录下)
 * 1:aliyun-java-sdk-core.jar
 * 2:aliyun-java-sdk-dysmsapi.jar
 *
 * 备注:Demo工程编码采用UTF-8
 * 国际短信发送请勿参照此DEMO
 */
public class SmsDemo {

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIekevWZriHiiM";
    static final String accessKeySecret = "xvinY5uvoQM8uoI68vPhSelKbqS9Vm";

    public static WxUser sendSms(String yonghu,String leixing,String phone) throws ClientException {
    	 WxUser wx=new WxUser();
    	  try {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("亮狮网");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_138060451");
        String a="{'yonghu':'";
        a+=yonghu;
        a+="','leixing':'";
        a+=leixing;
        a+="'}";
        System.out.println(a);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(a);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse response = acsClient.getAcsResponse(request);
        if(response.getCode() != null && response.getCode().equals("OK")) {
            wx.setFlag(true);
            wx.setMessage("发送成功！！");
        }else{
            wx.setFlag(false);
            wx.setQxzt(response.getCode());
            if(response.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
            wx.setMessage("发送频繁，稍后再试！！");
            }else if(response.getCode().equals("isv.MOBILE_NUMBER_ILLEGAL")){
                wx.setMessage("非法手机号！！");
            }else if(response.getCode().equals("isv.OUT_OF_SERVICE")){
                wx.setMessage("业务停机！！");
            }else if(response.getCode().equals("isv.AMOUNT_NOT_ENOUGH")){
                wx.setMessage("账户余额不足！！");
            }
            else{
                wx.setMessage("发送失败！！");
            }
        }
	    }catch (Exception e) {
            wx.setFlag(false);
            wx.setQxzt("系统异常，请稍后再试");
            wx.setMessage("系统异常，请稍后再试");
           // TODO Auto-generated catch block
           e.printStackTrace();
       }

        return wx;
    }

    /**
     * 扫码发短信
     * @param yonghu
     * @param leixing
     * @param phone
     * @return
     * @throws ClientException
     */
    public static WxUser scanSendSms(String yonghu,String leixing,String phone) throws ClientException {
   	 WxUser wx=new WxUser();
   	  try {
       //可自助调整超时时间
       System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
       System.setProperty("sun.net.client.defaultReadTimeout", "10000");

       //初始化acsClient,暂不支持region化
       IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
       DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
       IAcsClient acsClient = new DefaultAcsClient(profile);

       //组装请求对象-具体描述见控制台-文档部分内容
       SendSmsRequest request = new SendSmsRequest();
       //必填:待发送手机号
       request.setPhoneNumbers(phone);
       //必填:短信签名-可在短信控制台中找到
       request.setSignName("亮狮网");
       //必填:短信模板-可在短信控制台中找到
       request.setTemplateCode("SMS_138079960");
       String a="{'leixing':'";
       a+=leixing;
       a+="'}";
       System.out.println(a);
       //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
       request.setTemplateParam(a);

       //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
       //request.setSmsUpExtendCode("90997");

       //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
       //request.setOutId("yourOutId");

       //hint 此处可能会抛出异常，注意catch
       SendSmsResponse response = acsClient.getAcsResponse(request);
       if(response.getCode() != null && response.getCode().equals("OK")) {
           wx.setFlag(true);
           wx.setMessage("发送成功！！");
       }else{
           wx.setFlag(false);
           wx.setQxzt(response.getCode());
           if(response.getCode().equals("isv.BUSINESS_LIMIT_CONTROL")){
           wx.setMessage("发送频繁，稍后再试！！");
           }else if(response.getCode().equals("isv.MOBILE_NUMBER_ILLEGAL")){
               wx.setMessage("非法手机号！！");
           }else if(response.getCode().equals("isv.OUT_OF_SERVICE")){
               wx.setMessage("业务停机！！");
           }else if(response.getCode().equals("isv.AMOUNT_NOT_ENOUGH")){
               wx.setMessage("账户余额不足！！");
           }
           else{
               wx.setMessage("发送失败！！");
           }
       }
	    }catch (Exception e) {
           wx.setFlag(false);
           wx.setQxzt("系统异常，请稍后再试");
           wx.setMessage("系统异常，请稍后再试");
          // TODO Auto-generated catch block
          e.printStackTrace();
      }

       return wx;
   }

    public static QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("15000000000");
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);

        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }

    public static void main(String[] args) throws ClientException, InterruptedException {
        //发短信
        WxUser response = sendSms("16601132903","业主委托","16601132903");
        System.out.println(response.getMessage());
    }
}
