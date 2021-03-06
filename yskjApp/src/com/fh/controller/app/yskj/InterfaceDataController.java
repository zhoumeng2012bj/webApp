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
import com.fh.service.wxapp.TbCityManager;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
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
	
	@Resource(name="tbCityService")
	private TbCityManager tbCityService;
	
	
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
    	    e.printStackTrace();
            flag=false;
            message="信息处理异常！";
			logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
			//发送短信
			if(flag){
				String phone=pd.getString("phone");
				sendMess(pd.getString("uid"),"售后服务",phone,"");
			}
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
        	 e.printStackTrace();
             flag=false;
             message="信息处理异常！";
			 logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
			//发送短信
			if(flag){
				String phone=pd.getString("phone");
				sendMess(pd.getString("uid"),"业主委托",phone,"");
			}
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
        String category="";
         try {
        	String type=pd.get("type")+"";
        	if(type.equals("1")){
        		category="工商税务";
        		pd.put("oddNumbers", NumberUitl.getNumber("SW"));	
        	}else if(type.equals("2")){
        		category="增值服务";
        		pd.put("oddNumbers", NumberUitl.getNumber("ZZ"));	
        	}else if(type.equals("3")){
        		category="装修定制";
        		pd.put("oddNumbers", NumberUitl.getNumber("ZX"));	
        	}else if(type.equals("4")){
        		category="金融服务";
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
        	 e.printStackTrace();
             flag=false;
             message="信息处理异常！";
			 logAfter(logger);
        }finally{
        	map.put("message", message);
			map.put("success", flag);
			logAfter(logger);
			//发送短信
			if(flag){
				String phone=pd.getString("phone");
				sendMess(pd.getString("uid"),category,phone,"");
			}
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**管家服务(设备报修 BX、物业对接 WY、房屋凭证 PZ、房屋费用 FY、房屋变更 BG)
	 * @return 
	 */
	@RequestMapping(value="/butlerService")
	@ResponseBody
	public Object butlerService(@RequestBody PageData pd){
		logBefore(logger, "保存管家服务信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        String category="";
         try {
        	String type=pd.get("type")+"";
        	category=pd.get("category")+"";
        	int num=NumberUitl.getCategory(category);
        	if(Tools.notEmpty(type) && Tools.notEmpty(category) &&  num!=0){
        		pd.put("oddNumbers", NumberUitl.getNumber(category));	
        		if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST 
        			pd.put("category", num); 
                	pd.put("bussinessType", "3");         //1 业主委托 2商城服务 3售后服务(管家服务)
                	pd.put("createTime",Tools.date2Str(new Date()));  //创建时间
        			pd.put("status", "1");	            //1、待接单，2、已接单，3已完成
                	interfaceDataService.savebutlerInfo(pd);
                	message="信息处理成功!";
                }else{
                    flag=false;
                    message="提交请求方式错误!";
                }
        	}else{
        		 flag=false;
                 message="缺少请求参数!";
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
			//发送短信
			if(flag){
				String phone=pd.getString("phone");
				String fyId=pd.get("fyid")+"";
				sendMess(pd.getString("uid"),NumberUitl.getTypeStr(category),phone,fyId);
			}
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 房屋变更 （续租、换房、退房）
	 * @param pd
	 * @return
	 */
	@RequestMapping(value="/housingChange")
	@ResponseBody
	public Object housingChange(@RequestBody PageData pd){
		logBefore(logger, "保存房屋变更信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        String category="";
        String uid=pd.get("uid")+"";
         try {
        	String type=pd.get("type")+"";
        	category=pd.get("category")+"";
        	int num=NumberUitl.getCategory(category);
        	if(Tools.notEmpty(type) && Tools.notEmpty(category) &&  num!=0){
        		pd.put("oddNumbers", NumberUitl.getNumber(category));	
        		if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST 
        			pd.put("category", num); 
                	pd.put("bussinessType", "3");         //1 业主委托 2商城服务 3售后服务(管家服务)
                	pd.put("createTime",Tools.date2Str(new Date()));  //创建时间
                	if(Tools.notEmpty(pd.get("changeTime")+"")){
                		pd.put("changeTime",DateUtil.fomatDate(pd.get("changeTime")+""));
                	}
        			pd.put("status", "1");	            //1、待接单，2、已接单，3已完成
                	interfaceDataService.saveHousingChange(pd);
                	message="信息处理成功!";
                }else{
                    flag=false;
                    message="提交请求方式错误!";
                }
        	}else{
        		 flag=false;
                 message="缺少请求参数!";
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
			//发送短信
			if(flag){
				String phone=pd.getString("phone");
				String fyId=pd.get("fyid")+"";
				sendMess(pd.getString("uid"),NumberUitl.getTypeStr(category),phone,fyId);
			}
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
	
	/**获取更多会员企业列表
	 * @return 
	 */
	@RequestMapping(value="/listMemberEnterprise")
	@ResponseBody
	public Object getListMemberEnterprise(@RequestBody PageData pd){
		logBefore(logger, "获取更多会员企业列表信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
    			list=interfaceDataService.getListMemberEnterprise(pd);
    			map.put("data", list);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**获取会员企业详情信息
	 * @return 
	 */
	@RequestMapping(value="/getMemberEnterpriseInfo")
	@ResponseBody
	public Object getEnterMemberEnterprise(@RequestBody PageData pd){
		logBefore(logger, "获取会员企业详情信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 PageData dpData=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	dpData=interfaceDataService.getMemberEnterpriseInfo(pd);
    			map.put("data", dpData);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**判断用户是否已加入会员
	 * @return 
	 */
	@RequestMapping(value="/checkMembership")
	@ResponseBody
	public Object checkMembership(@RequestBody PageData pd){
		logBefore(logger, "判断是否已加入会员");
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pdData=null;
		boolean flag=true;
        String message="";
         try {
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	//type 审核类型：1 待审核    2审核通过   3已拒绝  4已发布   5已撤销
            	String uid=pd.get("uid")+"";
            	if(Tools.notEmpty(uid)){ 
            		pdData=interfaceDataService.getEnterMembership(pd);
            		map.put("data", pdData);
                	message="信息处理成功!";
            	}else{
            		flag=false;
            		message="缺少请求参数!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	
	/**加入会员
	 * @return 
	 */
	@RequestMapping(value="/membership")
	@ResponseBody
	public Object membership(@RequestBody PageData pd){
		logBefore(logger, "保存加入会员信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
		
        String message="";
         try {
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	
            	PageData pdInfo=interfaceDataService.getEnterEnterprise(pd);
            	if(pdInfo ==null){
            		pd.put("id", "");  //返回主键id
                	pd.put("createTime",Tools.date2Str(new Date()));  //创建时间、
        			pd.put("status", "1");	//状态(1可用 2禁用)
        			pd.put("type", "1");    //审核类型：1 待审核    2审核通过   3已拒绝  4已发布   5已撤销
                	interfaceDataService.saveMembership(pd);
                	String  id=pd.getString("id");
                	
                	//保存会员企业logo图片信息
                	PageData pdPic=new PageData();
                	pdPic.put("url", pd.getString("logoImg"));
                	pdPic.put("recordId", id);
                	pdPic.put("type", 16);
                	pdPic.put("isDelete", 0);
                	interfaceDataService.saveEnterprisePic(pdPic);
                	message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="该企业已加入会员!";
            	}
            	
            }else{
                flag=false;
                message="提交请求方式错误!";
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
			//发送短信
			if(flag){
				String phone=pd.getString("phone");
				sendMess(pd.getString("uid"),"加入会员",phone,"");
			}
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**发布服务、发布需求
	 * @return 
	 */
	@RequestMapping(value="/releaseDemand")
	@ResponseBody
	public Object releaseService(@RequestBody PageData pd){
		logBefore(logger, "保存发布服务、需求信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	pd.put("createTime",Tools.date2Str(new Date()));  //创建时间、
    			pd.put("status", "1");	//状态(1可用 2禁用)
    			pd.put("type", "1");    //状态(1、待审核，2、审核通过 3、已拒绝 4、已发布 5、达成合作
            	interfaceDataService.saveReleaseDemand(pd);
            	message="发布成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
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
			//发送短信
			if(flag){
				String category=pd.get("demandCategory")+"";
				String type="";
				if(category.equals("1")){
					type="发布服务";
				}
				if(category.equals("2")){
					type="发布需求";
				}
				String phone=pd.getString("enterPhone");
				sendMess(pd.getString("uid"),type,phone,"");
			}
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
	
	/**(2.0 版本服务、需求列表查询)
	 * @return 
	 */
	@RequestMapping(value="/listReleaseDemand")
	@ResponseBody
	public Object getReleaseDemandList(@RequestBody PageData pd){
		logBefore(logger, "获取企业需求列表信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
        	 List<PageData> list=null;
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
    			list=interfaceDataService.ReleaseDemandList(pd);
    			map.put("data", list);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
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
    	    e.printStackTrace();
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
        	 e.printStackTrace();
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
            	 if(list !=null && list.size() >0){
            		map.put("company", list.get(0).getString("companyname"));
 	    			map.put("data", list);
            	  }else{
            		  flag=false;
            	  }
	              message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	/**获取当前登陆用户的通知列表
	 * @return 
	 */
	@RequestMapping(value="/getMessageByUserId")
	@ResponseBody
	public Object getMessageByUserId(@RequestBody PageData pd){
		logBefore(logger, "根据用户id获取通知列表");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        List<PageData> list=null;
         try {
        	 
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id");
            	String page = pd.getString("page");
            	String pagesize = pd.getString("pagesize");
            	if(Tools.notEmpty(id))
            	{
            		if(Tools.notEmpty(page)&&Tools.notEmpty(pagesize)){
            			pd.put("pagestart", (Integer.parseInt(page)-1)*Integer.parseInt(pagesize)); 
            			pd.put("pageend",  Integer.parseInt(pagesize)); 
            		}
            	 list=interfaceDataService.getMessageByUserId(pd); 
    			map.put("data", list);
            	message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	
	/**获取id类型的通知详情
	 * @return 
	 */
	@RequestMapping(value="/getMessageInfoByUserId")
	@ResponseBody
	public Object getMessageInfoByUserId(@RequestBody PageData pd){
		logBefore(logger, "根据id获取通知详情");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        List<PageData> list=null;
         try {
        	 
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id");
            	String bussinessType = pd.getString("bussinessType");
            	String type = pd.getString("type");
            	if(Tools.notEmpty(id)&&Tools.notEmpty(bussinessType))
            	{
            		if(bussinessType.equals("4")){
            			if(type.equals("3")){
            				list=interfaceDataService.getEnterpriseByUserId(id);
            			}else{
            				list=interfaceDataService.getEnterpriseDemandByUserId(id);
            			}  
           		    }else{
           		    	list=interfaceDataService.getTransactionByUserId(id);	
           		    }
    			map.put("data", list);
            	message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**2.0 最新数据查询
	 * @return 
	 */
	@RequestMapping(value="/quotationData")
	@ResponseBody
	public Object getQuotationData(@RequestBody PageData pd){
		logBefore(logger, "获取2.0最新数据信息");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
         try {
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	PageData pdData=interfaceDataService.getQuotationData(pd);
    			map.put("data", pdData);
            	message="信息处理成功!";
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**
	 * 发生短信
	 */
	public void sendMess(String userId,String type,String phone,String fyId){
		 try {
			 //String phone=interfaceDataService.getUserPhone(userId);
			 PageData pd=new PageData();
			 pd.put("appPhone", phone);
			 pd.put("type", type);
			 tbCityService.getSendMsgEnterPrise(pd);
			 //管家发送短信
			 if(!fyId.equals("") && !fyId.equals("null") && fyId !=null){
				 pd.put("fyid", fyId);
				tbCityService.getSendMsg(pd);
			 }
			 //扫码发短信（userid为空 则给客户发送短信）
			 if(Tools.isEmpty(userId)){
				 tbCityService.getSendMsgService(pd);
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally{
				logAfter(logger);
			}
	}
	/**获取当前登陆用户的合同列表
	 * @return 
	 */
	@RequestMapping(value="/getContractListById")
	@ResponseBody
	public Object getContractListById(@RequestBody PageData pd){
		logBefore(logger, "根据用户id获取当前登陆用户的合同列表");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        List<PageData> list=null;
         try {
        	 
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id");
            	if(Tools.notEmpty(id))
            	{
            	 list=interfaceDataService.getContractListById(id);
            	 if(list !=null && list.size() >0){ 
 	    			map.put("data", list);
            	  }else{
            		  flag=false;
            	  }
	              message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**根据合同id及合同类型获取租金信息及付款方式
	 * @return 
	 */
	@RequestMapping(value="/getContractZjxxById")
	@ResponseBody
	public Object getContractZjxxById(@RequestBody PageData pd){
		logBefore(logger, "根据合同id及合同类型获取租金信息及付款方式");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        List<PageData> list=null;
         try {
        	 
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id"); 
            	if(Tools.notEmpty(id))
            	{
            	 list=interfaceDataService.getContractZjxxById(pd);
            	  List<PageData>fkfs=interfaceDataService.getContractFkfsById(pd);
            	  PageData ht = interfaceDataService.getContractById(pd); 
            	 if(list !=null && list.size() >0){ 
 	    			map.put("zjdata", list);
 	    			map.put("fkfsdata", fkfs);
 	    			map.put("htdata", ht);
            	  }else{
            		  flag=false;
            	  }
	              message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**根据合同id及合同类型获取合同应付账单
	 * @return 
	 */
	@RequestMapping(value="/getContractYfzdById")
	@ResponseBody
	public Object getContractYfzdById(@RequestBody PageData pd){
		logBefore(logger, "根据合同id及合同类型获取合同应付账单");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        List<PageData> list=null;
         try {
        	 
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id"); 
            	String httype= pd.getString("httype"); 
            	if(Tools.notEmpty(id))
            	{
            	if(httype=="0"){
            	    list=interfaceDataService.getContractFkById(pd);
            	}else{
            		list=interfaceDataService.getContractSkById(pd);
            	}
            	 if(list !=null && list.size() >0){ 
 	    			map.put("data", list); 
            	  }else{
            		  flag=false;
            	  }
	              message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**根据userid及付款凭证
	 * @return 
	 */
	@RequestMapping(value="/getContractFkpzById")
	@ResponseBody
	public Object getContractFkpzById(@RequestBody PageData pd){
		logBefore(logger, "根据userid及付款凭证");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        List<PageData> list=null;
         try {
        	 
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id"); 
            	if(Tools.notEmpty(id))
            	{
            	 list=interfaceDataService.getContractFkpzById(pd);
            	 if(list !=null && list.size() >0){ 
 	    			map.put("data", list); 
            	  }else{
            		  flag=false;
            	  }
	              message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**根据userid获取收款凭证
	 * @return 
	 */
	@RequestMapping(value="/getContractSkpzById")
	@ResponseBody
	public Object getContractSkpzById(@RequestBody PageData pd){
		logBefore(logger, "根据userid获取收款凭证");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        List<PageData> list=null;
         try {
        	 
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id");  
            	if(Tools.notEmpty(id))
            	{
            	 list=interfaceDataService.getContractSkpzById(pd); 
            	 if(list !=null && list.size() >0){ 
 	    			map.put("data", list); 
            	  }else{
            		  flag=false;
            	  }
	              message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**根据id获取付款凭证详情
	 * @return 
	 */
	@RequestMapping(value="/getContractFkpzxxById")
	@ResponseBody
	public Object getContractFkpzxxById(@RequestBody PageData pd){
		logBefore(logger, "根据id获取付款凭证详情");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        PageData  list=null;
         try {
        	 
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id"); 
            	if(Tools.notEmpty(id))
            	{
            	 list=interfaceDataService.getContractFkpzxxById(pd);
            	 if(list !=null && list.size() >0){ 
 	    			map.put("data", list); 
            	  }else{
            		  flag=false;
            	  }
	              message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
	/**根据id获取收款凭证详情
	 * @return 
	 */
	@RequestMapping(value="/getContractSkpzxxById")
	@ResponseBody
	public Object getContractSkpzxxById(@RequestBody PageData pd){
		logBefore(logger, "根据id获取收款凭证详情");
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag=true;
        String message="";
        PageData list=null;
         try {
        	 
            if (this.getRequest().getMethod().toUpperCase().equals("POST")) {//POST
            	String id = pd.getString("id");  
            	if(Tools.notEmpty(id))
            	{
            	 list=interfaceDataService.getContractSkpzxxById(pd); 
            	 if(list !=null && list.size() >0){ 
 	    			map.put("data", list); 
            	  }else{
            		  flag=false;
            	  }
	              message="信息处理成功!";
            	}else{
            		 flag=false;
                     message="提交请求参数错误!";
            	}
            }else{
                flag=false;
                message="提交请求方式错误!";
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
	
}
	
 