package com.fh.controller.app.yskj;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.service.system.yskj.InterfaceDataManager;
import com.fh.util.AppUtil;
import com.fh.util.NumberUitl;
import com.fh.util.PageData;
import com.fh.util.Tools;

/**
 * app 接口数据
 * @author admin
 *
 */
@Controller
@RequestMapping(value="/webApp/dataInfo")
public class InterfaceDataController extends BaseController {
    
	@Resource(name="interfacedataService")
	private InterfaceDataManager interfaceDataService;
	
	
	/**售后服务信息
	 * @return 
	 */
	@RequestMapping(value="/customerInfo")
	@ResponseBody
	public Object saveCustomerInfo(@RequestBody PageData pd){
		logBefore(logger, "保存提交售后服务信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
		
        String message="";
         try {
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	pd.put("id", "");	        //声明属性获取返回自动生成主键id
            	pd.put("bussinessType", "3");  //1 业主委托 2商城服务 3售后服务
            	pd.put("createTime",Tools.date2Str(new Date()));  //创建时间
    			pd.put("status", "1");	                          //1、待接单，2、已接单，3已完成
    			pd.put("oddNumbers", NumberUitl.getNumber("SH"));	   
            	interfaceDataService.saveTransaction(pd);
            	String  id=pd.getString("id");
            	System.out.println("返回生成主键："+id);
            	 message="信息成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**业主委托信息
	 * @return 
	 */
	@RequestMapping(value="/ownerEntrust")
	@ResponseBody
	public Object saveOwnerEntrust(@RequestBody PageData pd){
		logBefore(logger, "保存业主委托信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	pd.put("bussinessType", "1");          // 1 业主委托 2商城服务 3售后服务
            	pd.put("createTime",Tools.date2Str(new Date()));  //创建时间
    			pd.put("status", "1");	            //1、待接单，2、已接单，3已完成
    			pd.put("oddNumbers", NumberUitl.getNumber("WT"));	
            	interfaceDataService.saveTransaction(pd);
            	 message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**商城服务
	 * @return 
	 */
	@RequestMapping(value="/mallService")
	@ResponseBody
	public Object saveMallService(@RequestBody PageData pd){
		logBefore(logger, "保存商城服务信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	String type=pd.getString("type");
        	if(type.equals("1")){
        		pd.put("oddNumbers", NumberUitl.getNumber("SW"));	
        	}else if(type.equals("2")){
        		pd.put("oddNumbers", NumberUitl.getNumber("ZZ"));	
        	}else if(type.equals("3")){
        		pd.put("oddNumbers", NumberUitl.getNumber("ZX"));	
        	}else if(type.equals("3")){
        		pd.put("oddNumbers", NumberUitl.getNumber("JR"));	
        	}
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	pd.put("bussinessType", "2");         //1 业主委托 2商城服务 3售后服务
            	pd.put("createTime",Tools.date2Str(new Date()));  //创建时间
    			pd.put("status", "1");	            //1、待接单，2、已接单，3已完成
            	interfaceDataService.saveTransaction(pd);
            	 message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**获取会员企业列表
	 * @return 
	 */
	@RequestMapping(value="/memberEnterprise")
	@ResponseBody
	public Object getMemberEnterprise(){
		logBefore(logger, "获取会员企业列表信息");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
    			list=interfaceDataService.getMemberEnterprise(pd);
    			map.put("data", list);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**获取企业需求列表
	 * @return 
	 */
	@RequestMapping(value="/enterpriseDemand")
	@ResponseBody
	public Object getEnterpriseDemand(@RequestBody PageData pd){
		logBefore(logger, "获取企业需求列表信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
    			list=interfaceDataService.getEnterpriseDemand(pd);
    			map.put("data", list);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**获取幼师头条信息
	 * @return 
	 */
	@RequestMapping(value="/topNews")
	@ResponseBody
	public Object getTopNews(){
		logBefore(logger, "获取幼师头条信息");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd=new PageData();
		pd=this.getPageData();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
    			list=interfaceDataService.getTopNews(pd);
    			map.put("data", list);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**获取幼师头条详情信息
	 * @return 
	 */
	@RequestMapping(value="/topNewsDetails")
	@ResponseBody
	public Object getTopNewsInfo(@RequestBody PageData pd){
		logBefore(logger, "获取幼师头条详情信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 PageData pdData=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	pdData=interfaceDataService.getTopNewsInfo(pd);
    			map.put("data", pdData);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	/**获取app版本信息
	 * @return 
	 */
	@RequestMapping(value="/appVersionInfo")
	@ResponseBody
	public Object getAppVersionInfo(@RequestBody PageData pd){
		logBefore(logger, "获取app版本信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	PageData pdData=interfaceDataService.getAppVersion(pd);
    			map.put("data", pdData);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**获取面积接口
	 * @return 
	 */
	@RequestMapping(value="/getAreaData")
	@ResponseBody
	public Object getAreaData(){
		logBefore(logger, "获取最新数据面积列表信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
    			list=interfaceDataService.getLatestData(1);
    			map.put("data1", list);     
    			list=interfaceDataService.getLatestData(2);
    			map.put("data2", list);
    			list=interfaceDataService.getLatestData(3);
    			map.put("content", list.get(0).get("name"));
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**获取企业行业接口
	 * @return 
	 */
	@RequestMapping(value="/getEnterpriseData")
	@ResponseBody
	public Object getEnterpriseData(){
		logBefore(logger, "获取最新数据企业行业统计信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	list=interfaceDataService.getLatestData(4);
    			map.put("data", list);   
    			list=interfaceDataService.getLatestData(6);
    			map.put("content", list.get(0).get("name"));
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**获取企业规模接口
	 * @return 
	 */
	@RequestMapping(value="/getEnterpriseScaleData")
	@ResponseBody
	public Object getEnterpriseScaleData(){
		logBefore(logger, "获取最新数据企业规模统计信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	list=interfaceDataService.getEnterpriseScaleData();
            	String[] arr1 =new String[5];
            	String[] arr2 =new String[5];
            	String[] arr3 =new String[5];
            	for(int i=0;i<list.size();i++)
            	{  
            		arr1[i]= list.get(i).getString("name");
            		arr2[i]= list.get(i).get("value").toString(); 
            		arr3[i]= list.get(i).get("widthNum").toString();
            	} 
    			map.put("xdata", arr1);
    			map.put("ydata", arr2);
    			map.put("wdata", arr3);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**获取经纪人接口
	 * @return 
	 */
	@RequestMapping(value="/getBrokerData")
	@ResponseBody
	public Object getBrokerData(){
		logBefore(logger, "获取经纪人接口");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
    			list=interfaceDataService.getLatestData(7);
    			map.put("data1", list);     
    			list=interfaceDataService.getLatestData(9);
    			map.put("data2", list);
    			list=interfaceDataService.getLatestData(8);
    			map.put("content", list.get(0).get("name"));
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return  map;
	}
	
	/**获取管家接口
	 * @return 
	 */
	@RequestMapping(value="/getButlerData")
	@ResponseBody
	public Object getButlerData(){
		logBefore(logger, "获取管家接口");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message=""; 
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
    			list=interfaceDataService.getLatestData(11);
    			map.put("data1", list);   
    			list=interfaceDataService.getLatestData(10);
    			map.put("content", list.get(0).get("name"));
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return  map;
	}
	
	
	/**获取管家工龄统计接口
	 * @return 
	 */
	@RequestMapping(value="/getButlerYearData")
	@ResponseBody
	public Object getButlerYearData(){
		logBefore(logger, "获取管家工龄统计接口");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	list=interfaceDataService.getLatestData(12);
            	String[] arr1 =new String[4];
            	String[] arr2 =new String[4];
            	String[] arr3 =new String[4];
            	for(int i=0;i<list.size();i++)
            	{  
            		arr1[i]= list.get(i).getString("name");
            		arr2[i]= list.get(i).get("value").toString(); 
            		arr3[i]= list.get(i).get("widthNum").toString();  
            	} 
    			map.put("xdata", arr1);
    			map.put("ydata", arr2);
    			map.put("wdata", arr3);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**获取首页最新数据接口
	 * @return 
	 */
	@RequestMapping(value="/getNewData")
	@ResponseBody
	public Object getNewData(){
		logBefore(logger, "获取首页最新数据接口");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	list=interfaceDataService.getLatestData(13);
    			map.put("data1", list);   
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**app 安装包下载
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/appDown")
	@ResponseBody
	public Object appFileDownload(){
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
		 try {
			    PageData pd = new PageData();
				pd = this.getPageData();
			    String appType=pd.getString("appType").toUpperCase();  //IOS,Android 
				//String appVersion=pd.getString("appVersion");
				PageData pdData=null;
			    if(Tools.notEmpty(appType)){
			    	pdData=interfaceDataService.getAppVersion(pd);
			    	map.put("data", pdData);   
		            message="信息处理成功!";
		            // String url=pdData.getString("appFileUrl");
			        /*if(appType.equals("IOS")){
			        	FileDownload.fileUrlDownload(response,url,"yskj.ipa");
			        }else if(appType.equals("ANDROID")){
			        	FileDownload.fileUrlDownload(response, url,"yskj.apk");
			        }*/
			    }else{
			    	flag=false;
			    	 message="缺少参数!";
			    }
		 }catch (Exception e) {
			 e.printStackTrace();
			 flag=false;
             message="信息处理异常！";
 			 logAfter(logger);
		 }finally{
	        	map.put("message", message);
				map.put("success", flag);
				logAfter(logger);
		 }
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**获取当前登陆用户的房源及公司信息
	 * @return 
	 */
	@RequestMapping(value="/getHouseCompanyById")
	@ResponseBody
	public Object getHouseCompanyById(@RequestBody PageData pd){
		logBefore(logger, "根据用户id获取房源及公司信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        List<PageData> list=null;
         try {
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id");
            	if(Tools.notEmpty(id))
            	{
            	 list=interfaceDataService.getHouseCompanyById(id);
            	 map.put("company", list.get(0).getString("companyname"));
    			map.put("data", list);
            	message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数为空!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
            }
         }catch (Exception e) {
                flag=false;
                message="信息处理异常！";
    			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
}
	
 