package com.fh.controller.wxapp.tbcity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.json.JSONString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.WxUser;
import com.fh.entity.system.Role;
import com.fh.entity.wxapp.TbCity;
import com.fh.service.system.fhlog.FHlogManager;
import com.fh.service.system.user.UserManager;
import com.fh.service.wxapp.TbCityManager;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;
import com.fh.util.Tools;


/**
  * 系统用户-接口类 
  * 相关参数协议：
  * 00	请求失败
  * 01	请求成功
  * 02	返回空值
  * 03	请求协议参数不完整    
  * 04  用户名或密码错误
  * 05  FKEY验证失败
 */
@Controller
@RequestMapping(value="/appYskj/V1")
public class TbCityController extends BaseController {
    
	@Resource(name="tbCityService")
	private TbCityManager tbCityService;
	@Resource(name="fhlogService")
	private FHlogManager FHLOG;
	
	/**系统用户注册接口
	 * @return
	 */
	
	@RequestMapping(value="/getTbCity")
	@ResponseBody
	public Object getTbCity()throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		TbCity tbCity =new TbCity();
		try{
			Object object = pd.getString("id");
			Integer id=Integer.parseInt(object.toString());
			if(id!=0){
			tbCity = tbCityService.getTbCity(id);
			map.put("pd", tbCity);
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 获取所有的行政区域
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getRoot")
	@ResponseBody
	public Object getRoot()throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 List<PageData> root = tbCityService.getRoot(pd);
			 if(root.size()>0){
				 Object object = root.get(0).get("fdcode");
				 String string = object.toString();
				 PageData pd1 = new PageData();
				 pd1.put("fdparentCode", string);
				 List<PageData> beijing = tbCityService.getBeijing(pd1);
				 if(beijing.size()>0){
					 Object object2 = beijing.get(0).get("fdcode");
					 String string2 = object2.toString();
					 PageData pd2 = new PageData();
					 
					 pd2.put("fdparentCode", string2);
					 List<PageData> listAll = tbCityService.listAll(pd2);
					 if(listAll.size()>0){
						 map.put("data", listAll);
					 }
					 
				 }
				 
				 
			 }
			 map.put("success", true);
			 map.put("message", "请求成功");
		}else{
			 map.put("message", "请求方式错误");
			 map.put("success", false);
		}
			
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 获取某一行政区域下的商圈
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getShq")
	@ResponseBody
	public Object getShq(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 List<PageData> listFdparentCode = tbCityService.listFdparentCode(pd);
			 map.put("data", listFdparentCode);
			 map.put("success", true);
			 map.put("message", "请求成功");
			 
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
				
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 获取所有的地铁信息
	 */
	
	/**
	 * 获取某一地铁下的站点信息
	 */
	
	
	/**
	 * 1.用户注册手机号   判断手机号有没有被注册过
	 */
	
	@RequestMapping(value="/comReg")
	@ResponseBody
	public Object comReg(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			List<PageData> compReg = tbCityService.compReg(pd);
			if(compReg.size()>0){
				map.put("success", false);
				 map.put("message","此手机号已被注册");
			}else{
				map.put("success",true);
				 map.put("message","可以注册");
			}
			 
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 2. 发送短信验证码
	 */
	
	@RequestMapping(value="/getCode")
	@ResponseBody
	public Object getCode(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 WxUser code = tbCityService.getCode(pd);
			 map.put("success", code.isFlag());
			 map.put("message", code.getMessage());
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 3.手机号码校验
	 */
	@RequestMapping(value="/compCode")
	@ResponseBody
	public Object compCode(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 WxUser code = tbCityService.compCode(pd);
			 map.put("success", code.isFlag());
			 map.put("message", code.getMessage());
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 4.注册用户信息   手机号和密码  cookie  密码加密
	 */
	@RequestMapping(value="/register")
	@ResponseBody
	public Object register(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 WxUser code = tbCityService.regist(pd);
			 map.put("success", code.isFlag());
			 map.put("message", code.getMessage());
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 在线状态判断
	 */
	@RequestMapping(value="/statCode")
	@ResponseBody
	public Object statCode(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
		     String string = pd.getString("uid");
			 WxUser code = tbCityService.compLogin(string);
			 map.put("success", code.isFlag());
			 map.put("message", code.getMessage());
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	//查询个人信息
	/**
	 * 5.忘记密码 查询个人信息
	 */
	@RequestMapping(value="/getInfo")
	@ResponseBody
	public Object getInfo(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			  List<PageData> compReg = tbCityService.compReg(pd);
			  if(compReg.size()>0){
				  PageData pageData = compReg.get(0);
				  map.put("success", true);
				  map.put("message", "查询成功");
				  map.put("data", pageData);
			  }else{
				 map.put("success", false);
				 map.put("message", "无此用户注册信息");
			  }
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 6.忘记密码 修改密码信息同时登陆
	 */
	@RequestMapping(value="/remPass")
	@ResponseBody
	public Object remPass(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			  WxUser updateRemPass = tbCityService.updateRemPass(pd);
			  if(updateRemPass!=null){
				  map.put("success", updateRemPass.isFlag());
				  map.put("message", updateRemPass.getMessage());
				  map.put("data", updateRemPass);
			  }else{
				 map.put("success", false);
				 map.put("message", "无此用户注册信息");
			  }
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 7.根据cookie获取用户的信息
	 */
	@RequestMapping(value="/getCookieInfo")
	@ResponseBody
	public Object getCookieInfo(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			   PageData cookieId = tbCityService.getCookieId(pd);
			  if(cookieId!=null){
				  PageData d=  (PageData) cookieId.get("data");
				  boolean flag=  (boolean) cookieId.get("success");
				  map.put("success",flag );
				  map.put("message",  cookieId.getString("message"));
				  map.put("data", d);
				  List<PageData> mp1=  (List<PageData>) cookieId.get("mp1");
				  List<PageData> mp2=  (List<PageData>) cookieId.get("mp2");
				  //正面图片信息
				  map.put("mp1data", mp1);
				  //反面图片信息
				  map.put("mp2datat", mp2);
			  }else{
				 map.put("success", false);
				 map.put("message", "无此用户注册信息");
			  }
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 8.登录时判断系统中有无此用户信息
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/compLog")
	@ResponseBody
	public Object compLog(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			List<PageData> compReg = tbCityService.compReg(pd);
			if(compReg.size()>0){
				 map.put("success", true);
				 map.put("message","系统中有此手机号注册信息");
			}else{
				map.put("success",false);
				 map.put("message","系统中无此手机号注册信息");
			}
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 9.账号密码登录信息
	 */
	@RequestMapping(value="/logAccount")
	@ResponseBody
	public Object logAccount(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 WxUser logAccount = tbCityService.logAccount(pd);
			if(logAccount!=null){
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
			}else{
				map.put("success",false);
				 map.put("message","系统中无此手机号注册信息");
			}
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 10.手机号验证码登录
	 */
	@RequestMapping(value="/logPhone")
	@ResponseBody
	public Object logPhone(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 WxUser logAccount = tbCityService.logPhone(pd);
			if(logAccount!=null){
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
			}else{
				map.put("success",false);
				 map.put("message","系统中无此手机号注册信息");
			}
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 11.判断用户有无登陆状态   通过COOKIE
	 */
	@RequestMapping(value="/landState")
	@ResponseBody
	public Object landState(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 WxUser logAccount = tbCityService.landState(pd);
			if(logAccount!=null){
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
			}else{
				map.put("success",false);
				 map.put("message","系统中无此手机号注册信息");
			}
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
			
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 12.退出登录
	 */
	@RequestMapping(value="/loginOut")
	@ResponseBody
	public Object loginOut(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 WxUser logAccount = tbCityService.loginOut(pd);
			
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
		
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 13.判断房源是否被此用户收藏
	 */
	@RequestMapping(value="/judgeCollection")
	@ResponseBody
	public Object judgeCollection(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 WxUser logAccount = tbCityService.judgeCollection(pd);
			if(logAccount!=null){
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
			}else{
				map.put("success",false);
				 map.put("message","无此用户登录信息");
			}
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 14.收藏房源
	 */
	@RequestMapping(value="/collectHouse")
	@ResponseBody
	public Object collectHouse(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
			 WxUser logAccount = tbCityService.collectHouse(pd);
			if(logAccount!=null){
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
			}else{
				map.put("success",false);
				map.put("message","收藏房源失败");
			}
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 15.收藏房源删除
	 */
	@RequestMapping(value="/delCollect")
	@ResponseBody
	public Object delCollect(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
				WxUser logAccount = tbCityService.delCollect(pd);
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
			
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 16.手机号修改   判断手机号是否已被占用    判断此手机号是否是原来的手机号
	 */
	@RequestMapping(value="/judgePhone")
	@ResponseBody
	public Object judgePhone(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
				WxUser logAccount = tbCityService.judgePhone(pd);
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
			
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 17.手机号修改	
	 */
	@RequestMapping(value="/updatePhone")
	@ResponseBody
	public Object updatePhone(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
				WxUser logAccount = tbCityService.updatePh(pd);
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
			
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
	/**
	 * 19.修改密码
	 */
	@RequestMapping(value="/updatePass")
	@ResponseBody
	public Object updatePass(@RequestBody PageData pd)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if (this.getRequest().getMethod().toUpperCase().equals("POST")) {
				WxUser logAccount = tbCityService.updateLoginPass(pd);
				map.put("success", logAccount.isFlag());
				map.put("message",logAccount.getMessage());
			}else{
				 map.put("success", false);
				 map.put("message", "请求方式错误");
			}
		}catch (Exception e){
			logger.error(e.toString(), e);
		}finally{
			logAfter(logger);
		}
		return AppUtil.returnObject(new PageData(), map);
	}
}
	
 