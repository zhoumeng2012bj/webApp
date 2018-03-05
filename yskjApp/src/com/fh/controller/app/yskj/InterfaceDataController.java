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
	public Object getMemberEnterprise(@RequestBody PageData pd){
		logBefore(logger, "获取会员企业列表信息");
		Map<String,Object> map = new HashMap<String,Object>();
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
	
}
	
 