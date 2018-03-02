package com.fh.service.wxapp.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.WxUser;
import com.fh.entity.system.User;
import com.fh.entity.wxapp.TbCity;
import com.fh.service.system.user.UserManager;
import com.fh.service.wxapp.TbCityManager;
import com.fh.util.GetRedis;
import com.fh.util.PageData;
import com.fh.util.SendMessage;


/** 系统用户
 * @author 
 */
@Service("tbCityService")
public class TbCityService implements TbCityManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	public TbCity getTbCity(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return (TbCity) dao.findForObject("TbCityManager.getTbCity", id);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> getRoot(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("TbCityManager.getRoot", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> getBeijing(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("TbCityManager.getBeijing", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> listAll(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("TbCityManager.listAll", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> listFdparentCode(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return  (List<PageData>) dao.findForList("TbCityManager.listFdparentCode", pd);
	}
	//发送手机号
	@SuppressWarnings("unchecked")
	public WxUser getCode(PageData pd) throws Exception {
		WxUser x=new WxUser();
		Integer radomInt = new Random().nextInt(999999);
		String string = pd.getString("cookie").toString();
		String string2 = pd.getString("phone").toString();
		if(!"".equals(string)&&!"".equals(string2)){
			 x = SendMessage.sendMessage2(radomInt.toString(), string2);
			 //存储验证码到redis
			 if(x.isFlag()){
			        Jedis  jedis = null;
			        jedis = new Jedis("47.92.145.21", 6379);
			        jedis.auth("yskj88888");
			        jedis.set("appcode"+string,radomInt.toString());
			        //有效时间5分钟
			        jedis.expire("appcode"+string, 300);
			        }
		}else{
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		
		return x;
		
		
	}
	//手机号校验
	@SuppressWarnings("unchecked")
	public WxUser compCode(PageData pd)  throws Exception{
		WxUser x=new WxUser();
		String string = pd.getString("cookie").toString();
		String string2 = pd.getString("code").toString();
		if(!"".equals(string)&&!"".equals(string2)){
			string="appcode"+string;
			 //校验手机号
			Integer redis = GetRedis.getRedis(string);
			if(redis!=0){
				x.setFlag(true);
				x.setMessage("校验成功");
			}else{
				x.setFlag(false);
				x.setMessage("校验失败");
			}
			
		}else{
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}
	/**
	 * 用户登录判断有无登录在线状态
	 */
	@SuppressWarnings("unchecked")
	public WxUser compLogin(String pd) throws Exception{
		WxUser x=new WxUser();
		String string = pd;
		if(!"".equals(string)){
			 //查询有无此用户登录
			 boolean comCode = GetRedis.comCode(pd);
			 x.setFlag(comCode);
			 x.setMessage("判断成功");
		}else{
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}

	//用户注册时判断手机号有没有注册过     通过手机号查询个人信息
	@SuppressWarnings("unchecked")
	public List<PageData> compReg(PageData pd)  throws Exception{
		return (List<PageData>) dao.findForList("TbCityManager.compReg", pd);
	}
	//添加用户信息  
	@SuppressWarnings("unchecked")
	public void save(PageData pd) throws Exception {
		dao.save("TbCityManager.save", pd);
		
	}
	
	//注册用户信息  同时处于登录状态
	@SuppressWarnings("unchecked")
	public WxUser regist(PageData pd) throws Exception{
		WxUser x=new WxUser();
		//保存
		save(pd);
		//查询 手机号对应的人员信息
		List<PageData> compReg = compReg(pd);
		if(compReg.size()>0){
			String string = compReg.get(0).getString("id");
			//保存用户ID信息
			String string2 = pd.getString("cookie");
			Jedis  jedis = null;
            jedis = new Jedis("47.92.145.21", 6379);
            jedis.auth("yskj88888");
            jedis.set("appuser"+string2,string);
            //保存用户的登陆状态  判断账号唯一登陆
            jedis.set("appid"+string,string2);
	             //设置永久生效
			x.setFlag(true);
			x.setMessage("保存成功");
		}else{
			x.setFlag(false);
			x.setMessage("保存错误");
		}
		return x;
	}
	@SuppressWarnings("unchecked")
	public void updatePass(PageData pd)throws Exception{
		dao.update("TbCityManager.updatePass", pd);
	}
	//忘记密码修改密码信息
	@SuppressWarnings("unchecked")
	public WxUser updateRemPass(PageData pd) throws Exception{
		WxUser x=new WxUser();
		String cookie = pd.getString("cookie");
		String uid = pd.getString("uid");
		String pass = pd.getString("pass");
		if(!"".equals(cookie)&&!"".equals(pass)&&!"".equals(uid)){
			//修改密码同时登陆用户
			updatePass(pd);
			//保存用户ID信息
			Jedis  jedis = null;
            jedis = new Jedis("47.92.145.21", 6379);
            jedis.auth("yskj88888");
            jedis.set("appuser"+cookie,uid);
            //保存用户的登陆状态  判断账号唯一登陆
            jedis.set("appid"+uid,cookie);
	        //设置永久生效
			x.setFlag(true);
			x.setMessage("保存成功");
		}else{
			x.setFlag(false);
			x.setMessage("保存错误");
		}
		return x;
	}
	
	
}
