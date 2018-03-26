package com.fh.util;

import java.util.Random;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fh.entity.WxUser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendMessage {

	/**
	 * @param args
	 * @throws Exception 
	 */
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIekevWZriHiiM";
    static final String accessKeySecret = "xvinY5uvoQM8uoI68vPhSelKbqS9Vm";
	public static boolean sendMessage(String code,String phone){
	    
	    boolean flag=false;
	    String url="http://gw.api.taobao.com/router/rest";
        String appkey="24663575";//必填
        String secret="406c4f1c4f584c70813b6d086cb9a978";//必填
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType("normal");//短信推送个话，就填normal
        req.setSmsFreeSignName("幼狮");
        String a="{'code':'";
        a+=code;
        a+="'}";
        req.setSmsParamString(a);
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_71300115"); //短信模板的编号
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            flag=true;
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
        return flag;
	}
	public static WxUser sendMessage2(String code,String phone) {
	    WxUser wx=new WxUser();
        //可自助调整超时时间
	    try {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        String a="{'code':'";
        a+=code;
        a+="'}";
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("亮狮网");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_118555021");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(a);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse response = acsClient.getAcsResponse(request);
        if(response.getCode() != null && response.getCode().equals("OK")) {
            wx.setFlag(true);
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
	public static QuerySendDetailsResponse querySendDetails(String bizId,String phone) throws ClientException {

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
        request.setPhoneNumber(phone);
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
	public static WxUser sendMessage3(String code,String phone){
	    WxUser wx=new WxUser();
        String body="";
        String url="http://gw.api.taobao.com/router/rest";
        String appkey="24663575";//必填
        String secret="406c4f1c4f584c70813b6d086cb9a978";//必填
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType("normal");//短信推送个话，就填normal
        req.setSmsFreeSignName("幼狮");
        String a="{'code':'";
        a+=code;
        a+="'}";
        req.setSmsParamString(a);
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_71300115"); //短信模板的编号
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            
           body = rsp.getBody();
            //  {"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"174013909763359820^0","msg":"OK","success":true},"request_id":"1649hr8lvb597"}}
          // holo.db.bean.WxUser@7e13efdf
            JSONObject json=JSONObject.parseObject(body);
            JSONObject jsonObject = json.getJSONObject("alibaba_aliqin_fc_sms_num_send_response");
            JSONObject jsonObject11 = json.getJSONObject("error_response");
            if(jsonObject11!=null){
                wx.setFlag(false);
                wx.setQxzt("短信发送错误,请检查手机号是否正确！！");
            }
            if(jsonObject!=null){
            JSONObject jsonObject2 = jsonObject.getJSONObject("result");
            String integer = jsonObject2.getString("err_code");
            wx.setQxzt(integer);
            if("0".equals(integer)){
                wx.setFlag(true); 
            }else{
                wx.setFlag(false);   
            }
            }
        } catch (ApiException e) {
             wx.setFlag(false);
             wx.setQxzt("系统异常，请稍后再试");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return wx;
    }
	
	public static WxUser sendMessage5(String code,String phone) {
	    WxUser wx=new WxUser();
        //可自助调整超时时间
	    try {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        String a="{'code':'";
        a+=code;
        a+="'}";
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("幼狮空间");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_127163884");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(a);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse response = acsClient.getAcsResponse(request);
        if(response.getCode() != null && response.getCode().equals("OK")) {
            wx.setFlag(true);
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
	public static void main(String[] args) {
	   Integer radomInt = new Random().nextInt(999999);
	    WxUser sendMessage = sendMessage5(radomInt.toString(),"15093648677");
	    System.out.println(sendMessage.getMessage());
    }

}
