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
	 *7. 在线状态判断
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
	
	
}
	
 