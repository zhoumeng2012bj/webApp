package com.fh.service.wxapp.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.crypto.interfaces.PBEKey;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.WxUser;
import com.fh.entity.system.User;
import com.fh.entity.wxapp.TbCity;
import com.fh.service.system.user.UserManager;
import com.fh.service.wxapp.TbCityManager;
import com.fh.util.DateUtil;
import com.fh.util.DelRedis;
import com.fh.util.GetRedis;
import com.fh.util.PageData;
import com.fh.util.SendMessage;
import com.fh.util.SmsDemo;

/**
 * 系统用户
 * 
 * @author
 */
@Service("tbCityService")
public class TbCityService implements TbCityManager {

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
		return (List<PageData>) dao.findForList(
				"TbCityManager.listFdparentCode", pd);
	}

	@SuppressWarnings("unchecked")
	public void updateCollecHouse(String[] ids) throws Exception {

		dao.update("TbCityManager.updateCollecHouse", ids);
	}

	// 发送手机号
	@SuppressWarnings("unchecked")
	public WxUser getCode(PageData pd) throws Exception {
		WxUser x = new WxUser();
		Integer radomInt = new Random().nextInt(999999);
		String string = pd.getString("cookie").toString();
		String string2 = pd.getString("phone").toString();
		if (!"".equals(string) && !"".equals(string2)) {
			x = SendMessage.sendMessage2(radomInt.toString(), string2);
			// 存储验证码到redis
			if (x.isFlag()) {
				Jedis jedis = null;
				jedis = new Jedis("47.92.145.21", 6379);
				jedis.auth("yskj88888");
				jedis.set("appcode" + string, radomInt.toString());
				// 有效时间5分钟
				jedis.expire("appcode" + string, 300);
			}
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}

		return x;

	}

	// 发送手机号
	@SuppressWarnings("unchecked")
	public WxUser getServiceCode(PageData pd) throws Exception {
		WxUser x = new WxUser();
		Integer radomInt = new Random().nextInt(999999);
		String string = pd.getString("cookie").toString();
		String string2 = pd.getString("phone").toString();
		if (!"".equals(string) && !"".equals(string2)) {
			x = SendMessage.sendMessage5(radomInt.toString(), string2);
			// 存储验证码到redis
			if (x.isFlag()) {
				Jedis jedis = null;
				jedis = new Jedis("47.92.145.21", 6379);
				jedis.auth("yskj88888");
				jedis.set("appservice" + string, radomInt.toString());
				// 有效时间5分钟
				jedis.expire("appservice" + string, 300);
			}
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}

		return x;

	}

	// 手机号校验
	@SuppressWarnings("unchecked")
	public WxUser compCode(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("cookie").toString();
		String string2 = pd.getString("code").toString();
		if (!"".equals(string) && !"".equals(string2)) {
			string = "appcode" + string;
			// 校验手机号
			Integer redis = GetRedis.getRedis(string);
			if (redis != 0) {
				String string3 = redis.toString();
				if (!string2.equals(string3)) {
					x.setFlag(false);
					x.setMessage("验证码错误");
				} else {
					x.setFlag(true);
					x.setMessage("校验成功");
				}
			} else {
				x.setFlag(false);
				x.setMessage("验证码错误");
			}

		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}

	/**
	 * 用户登录判断有无登录在线状态
	 */
	@SuppressWarnings("unchecked")
	public WxUser compLogin(String pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd;
		if (!"".equals(string)) {
			// 查询有无此用户登录
			boolean comCode = GetRedis.comCode("appid" + pd);
			x.setFlag(comCode);
			x.setMessage("判断成功");
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}

	// 用户注册时判断手机号有没有注册过 通过手机号查询个人信息
	@SuppressWarnings("unchecked")
	public List<PageData> compReg(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("TbCityManager.compReg", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> getId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("TbCityManager.getId", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> getUserPic(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("TbCityManager.getUserPic", pd);
	}

	@SuppressWarnings("unchecked")
	public List<PageData> getPass(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("TbCityManager.getPass", pd);
	}

	// 添加用户信息
	@SuppressWarnings("unchecked")
	public int save(PageData pd) throws Exception {
		pd.put("createTime", new Date());
		return (int) dao.save("TbCityManager.save", pd);

	}

	@SuppressWarnings("unchecked")
	public int saveCollect(PageData pd) throws Exception {
		pd.put("createTime", new Date());
		return (int) dao.save("TbCityManager.saveCollect", pd);

	}

	// 注册用户信息 同时处于登录状态
	@SuppressWarnings("unchecked")
	public WxUser regist(PageData pd) throws Exception {
		WxUser x = new WxUser();
		// 保存
		Integer save = save(pd);
		// 查询 手机号对应的人员信息
		List<PageData> compReg = compReg(pd);
		if (compReg.size() > 0) {
			Object object = compReg.get(0).get("id");
			String string = object.toString();
			// 保存用户ID信息
			String string2 = pd.getString("cookie");
			Jedis jedis = null;
			jedis = new Jedis("47.92.145.21", 6379);
			jedis.auth("yskj88888");
			jedis.set("appuser" + string2, string);
			// 保存用户的登陆状态 判断账号唯一登陆
			jedis.set("appid" + string, string2);
			// 设置永久生效
			x.setFlag(true);
			x.setMessage("保存成功");
		} else {
			x.setFlag(false);
			x.setMessage("保存错误");
		}
		return x;
	}

	@SuppressWarnings("unchecked")
	public void updatePass(PageData pd) throws Exception {
		pd.put("createTime", new Date());
		dao.update("TbCityManager.updatePass", pd);
	}

	@SuppressWarnings("unchecked")
	public void updatePhone(PageData pd) throws Exception {
		pd.put("createTime", new Date());
		dao.update("TbCityManager.updatePhone", pd);
	}

	// 忘记密码修改密码信息
	@SuppressWarnings("unchecked")
	public WxUser updateRemPass(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String cookie = pd.getString("cookie");
		String uid = pd.getString("id");
		String pass = pd.getString("pass");
		if (!"".equals(cookie) && !"".equals(pass) && !"".equals(uid)) {
			// 修改密码同时登陆用户
			updatePass(pd);
			// 判断系统中有无此用户的登陆状态
			boolean comCode = GetRedis.comCode("appid" + uid);
			if (comCode == true) {
				// 删除原有的登陆的在线状态 保证唯一登陆状态信息
				String a1 = "appid" + uid;
				// 获取cookie信息
				String redis1 = GetRedis.getRedis1(a1);
				if (!"".equals(redis1)) {
					// 删除原有登陆
					DelRedis.getRedis("appuser" + redis1);
					DelRedis.getRedis(a1);
				}
			}
			// 保存用户ID信息
			boolean saveCode = GetRedis.saveCode(cookie, uid);
			if (saveCode == true) {
				x.setFlag(true);
				x.setMessage("保存用户登陆状态成功");
			} else {
				x.setFlag(false);
				x.setMessage("保存用户登陆状态失败");
			}

		} else {
			x.setFlag(false);
			x.setMessage("保存错误");
		}
		return x;
	}

	// 根据cookie获取用户的信息
	@SuppressWarnings("unchecked")
	public PageData getCookieId(PageData pd) throws Exception {
		PageData p = new PageData();
		// 通过cookie查看redis中保存的用户的ID
		String string = pd.getString("cookie");
		if (!"".equals(string) && string != null) {
			String cookie = "appuser" + string;
			Integer redis = GetRedis.getRedis(cookie);
			if (redis != 0) {
				PageData d = new PageData();
				d.put("id", redis);
				List<PageData> compReg = getId(d);
				if (compReg.size() > 0) {
					PageData pageData = compReg.get(0);
					// 获取用户的图片信息
					PageData s1 = new PageData();
					s1.put("TYPE", 11);
					s1.put("RECORDID", redis);
					PageData s2 = new PageData();
					s2.put("TYPE", 12);
					s2.put("RECORDID", redis);
					List<PageData> userPic = getUserPic(s1);
					List<PageData> userPic2 = getUserPic(s2);
					// 名片正面图
					p.put("mp1", userPic);
					// 名片反面图
					p.put("mp2", userPic2);
					p.put("data", pageData);
					p.put("message", "查询用户成功");
					p.put("success", true);
				} else {
					p.put("message", "查询用户失败");
					p.put("success", false);
				}
			} else {
				p.put("message", "无此用户登录信息");
				p.put("success", false);
			}

		} else {
			p.put("message", "参数错误");
			p.put("success", false);
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	public WxUser logAccount(PageData pd) throws Exception {
		WxUser x = new WxUser();
		// 根据账号 密码查询系统用户
		List<PageData> pass = getPass(pd);
		if (pass.size() > 0) {
			String string = pass.get(0).getString("isdelete");
			if (!"0".equals(string)) {
				// 判断唯一登陆状态信息
				String uid = pass.get(0).get("id").toString();
				boolean comCode = GetRedis.comCode("appid" + uid);
				if (comCode == true) {
					// 删除原有的登陆的在线状态 保证唯一登陆状态信息
					String a1 = "appid" + uid;
					// 获取cookie信息
					String redis1 = GetRedis.getRedis1(a1);
					if (!"".equals(redis1)) {
						// 删除原有登陆
						DelRedis.getRedis("appuser" + redis1);
						DelRedis.getRedis(a1);
					}
				}
				// 保存用户ID信息
				String cookie = pd.getString("cookie");
				boolean saveCode = GetRedis.saveCode(cookie, uid);
				if (saveCode == true) {
					x.setFlag(true);
					x.setMessage("保存用户登陆状态成功");
				} else {
					x.setFlag(false);
					x.setMessage("保存用户登陆状态失败,请重新登录");
				}

			} else {
				x.setFlag(false);
				x.setMessage("此用户已被删除，请联系管理员");
			}

		} else {
			x.setFlag(false);
			x.setMessage("登陆失败，请检查您的用户名或密码是否填写正确");

		}

		return x;

	}

	@SuppressWarnings("unchecked")
	public WxUser logPhone(PageData pd) throws Exception {
		WxUser x = new WxUser();
		// 根据手机号登录
		List<PageData> compReg = compReg(pd);
		if (compReg.size() > 0) {
			String uid = compReg.get(0).get("id").toString();
			boolean comCode = GetRedis.comCode("appid" + uid);
			if (comCode == true) {
				// 删除原有的登陆的在线状态 保证唯一登陆状态信息
				String a1 = "appid" + uid;
				// 获取cookie信息
				String redis1 = GetRedis.getRedis1(a1);
				if (!"".equals(redis1)) {
					// 删除原有登陆
					DelRedis.getRedis("appuser" + redis1);
					DelRedis.getRedis(a1);
				}
			}
			// 保存用户ID信息
			String cookie = pd.getString("cookie");
			boolean saveCode = GetRedis.saveCode(cookie, uid);
			if (saveCode == true) {
				x.setFlag(true);
				x.setMessage("保存用户登陆状态成功");
			} else {
				x.setFlag(false);
				x.setMessage("保存用户登陆状态失败,请重新登录");
			}
		} else {
			x.setFlag(false);
			x.setMessage("系統中无此手机号注册信息");
		}
		return x;
	}

	@SuppressWarnings("unchecked")
	public WxUser landState(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("cookie");
		String a = "appuser" + string;
		Integer redis = GetRedis.getRedis(a);
		if (!"".equals(string)) {
			if (redis != 0) {
				x.setFlag(true);
				x.setMessage("此用户已是登陆状态");
			} else {
				x.setFlag(false);
				x.setMessage("无此用户登陆信息");
			}
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;

	}

	@SuppressWarnings("unchecked")
	public WxUser loginOut(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("cookie");
		String a = "appuser" + string;
		String redis = GetRedis.getRedis1(a);
		if (!"".equals(string)) {
			if (redis != "0") {
				// redis删除用户的登陆状态
				String a1 = "appid" + redis;
				DelRedis.getRedis(a1);
				DelRedis.getRedis(a);
				x.setFlag(true);
				x.setMessage("退出成功");
			} else {
				x.setFlag(false);
				x.setMessage("无此用户登陆信息");
			}
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}

	@SuppressWarnings("unchecked")
	public List<PageData> getUserFyid(PageData pd) throws Exception {
		return (List<PageData>) dao
				.findForList("TbCityManager.getUserFyid", pd);
	}

	@SuppressWarnings("unchecked")
	public WxUser judgeCollection(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("cookie");
		String a = "appuser" + string;
		Integer redis = GetRedis.getRedis(a);
		if (!"".equals(string)) {
			if (redis != 0) {
				pd.put("redis", redis);
				List<PageData> userFyid = getUserFyid(pd);
				if (userFyid.size() > 0) {
					Object object = userFyid.get(0).get("id");
					String string2 = object.toString();
					Integer collectId = Integer.valueOf(string2);
					x.setFlag(false);
					x.setMessage("此房源已被此用户收藏");
					x.setFyzt(collectId);
				} else {
					x.setFlag(true);
					x.setMessage("房源未被收藏");
				}
			} else {
				x.setFlag(false);
				x.setMessage("无此用户登陆信息");
			}
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;

	}

	@SuppressWarnings("unchecked")
	public WxUser collectHouse(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("cookie");
		String a = "appuser" + string;
		Integer redis = GetRedis.getRedis(a);
		if (!"".equals(string)) {
			if (redis != 0) {
				pd.put("redis", redis);
				saveCollect(pd);
				x.setFlag(true);
				x.setMessage("房源收藏成功");
			} else {
				x.setFlag(false);
				x.setMessage("无此用户登陆信息");
			}
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;

	}

	@SuppressWarnings("unchecked")
	public WxUser delCollect(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("ids");
		if (!"".equals(string)) {
			String[] split = string.split(",");
			updateCollecHouse(split);
			x.setFlag(true);
			x.setMessage("房源收藏删除成功");

		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}

		return x;
	}

	@SuppressWarnings("unchecked")
	public WxUser cancleCollect(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("collectId");
		if (!"".equals(string)) {
			String[] split = string.split(",");
			updateCollecHouse(split);
			x.setFlag(true);
			x.setMessage("房源收藏取消成功");

		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}

		return x;
	}

	@SuppressWarnings("unchecked")
	public WxUser judgePhone(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("cookie");
		String phone = pd.getString("phone");
		if (!"".equals(string) && !"".equals(phone)) {
			String a = "appuser" + string;
			Integer redis = GetRedis.getRedis(a);
			if (redis != 0) {
				// 判断手机号是否与原手机号重复
				pd.put("id", redis);
				List<PageData> id = getId(pd);
				if (id.size() > 0) {
					String string2 = id.get(0).getString("phone");
					if (!phone.equals(string2)) {
						// 查看手机号是否被其他人占用
						List<PageData> compReg = compReg(pd);
						if (compReg.size() > 0) {
							x.setFlag(false);
							x.setMessage("此手机号已被其他用户占用");
						} else {
							x.setFlag(true);
							x.setMessage("此手机号可以进行修改");
						}
					} else {
						x.setFlag(false);
						x.setMessage("此手机号与原手机号重复");
					}
				} else {
					x.setFlag(false);
					x.setMessage("系统中无此用户信息");
				}

			} else {
				x.setFlag(false);
				x.setMessage("系统中无此用户登录信息");
			}
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}

	// 手机号修改
	@SuppressWarnings("unchecked")
	public WxUser updatePh(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String cookie = pd.getString("cookie");
		String phone = pd.getString("phone");
		if (!"".equals(cookie) && !"".equals(phone)) {
			String a = "appuser" + cookie;
			Integer redis = GetRedis.getRedis(a);
			if (redis != 0) {
				// 修改密码同时登陆用户
				pd.put("id", redis);
				updatePhone(pd);
				x.setFlag(true);
				x.setMessage("修改手机号成功");
			} else {
				x.setFlag(false);
				x.setMessage("无此用户登录信息");
			}

		} else {
			x.setFlag(false);
			x.setMessage("保存错误");
		}
		return x;
	}

	@SuppressWarnings("unchecked")
	public WxUser updateLoginPass(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String cookie = pd.getString("cookie");
		String pass = pd.getString("oldpass");
		String newpass = pd.getString("newpass");
		if (!"".equals(cookie) && !"".equals(pass) && !"".equals(newpass)) {
			String a = "appuser" + cookie;
			Integer redis = GetRedis.getRedis(a);
			if (redis != 0) {
				// 查看用户信息
				PageData pd1 = new PageData();
				pd1.put("id", redis);
				List<PageData> user = getId(pd1);
				if (user.size() > 0) {
					String string = user.get(0).getString("pass");
					if (!string.equals(pass)) {
						x.setFlag(false);
						x.setMessage("原密码填写错误");
					} else {
						pd.put("id", redis);
						pd.put("pass", newpass);
						// 修改密码同时登陆用户
						updatePass(pd);
						x.setFlag(true);
						x.setMessage("修改密码成功");
					}
				} else {
					x.setFlag(false);
					x.setMessage("无此用户信息");
				}

			} else {
				x.setFlag(false);
				x.setMessage("无此用户登录信息");
			}

		} else {
			x.setFlag(false);
			x.setMessage("保存错误");
		}
		return x;
	}

	// 手机号校验
	@SuppressWarnings("unchecked")
	public WxUser compServiceCode(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("cookie").toString();
		String string2 = pd.getString("code").toString();
		if (!"".equals(string) && !"".equals(string2)) {
			string = "appservice" + string;
			// 校验手机号
			Integer redis = GetRedis.getRedis(string);
			if (redis != 0) {
				String string3 = redis.toString();
				if (!string2.equals(string3)) {
					x.setFlag(false);
					x.setMessage("验证码错误");
				} else {
					x.setFlag(true);
					x.setMessage("校验成功");
				}
			} else {
				x.setFlag(false);
				x.setMessage("验证码错误");
			}

		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}

	// 获取 用户对应的房源的楼盘ID
	@SuppressWarnings("unchecked")
	public List<PageData> getButler(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("TbCityManager.getButler", pd);
	}

	// 根据楼盘ID 获取 管家信息 管家的类别 业主还是管家
	@SuppressWarnings("unchecked")
	public List<PageData> getButler2(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("TbCityManager.getButler2", pd);
	}

	// 获取 楼盘的业主管家信息或客户管家信息aaaa
	@SuppressWarnings("unchecked")
	public PageData getButlerInfo(PageData pd) throws Exception {
		PageData p = null;

		List<PageData> butler = getButler(pd);
		if (butler.size() > 0) {
			PageData d = butler.get(0);
			Object obj = d.get("lpid");
			Integer lpid = new Integer(obj.toString());
			pd.put("lpid", lpid);
			List<PageData> butler2 = getButler2(pd);
			if (butler2.size() > 0) {
				p = butler2.get(0);
			}
		}
		return p;

	}
	public void logOpenid(String uid,String cookie)  throws Exception{
		boolean comCode = GetRedis.comCode("appid" + uid);
		if (comCode == true) {
			// 删除原有的登陆的在线状态 保证唯一登陆状态信息
			String a1 = "appid" + uid;
			// 获取cookie信息
			String redis1 = GetRedis.getRedis1(a1);
			if (!"".equals(redis1)) {
				// 删除原有登陆
				DelRedis.getRedis("appuser" + redis1);
				DelRedis.getRedis(a1);
			}
		}
		// 保存用户ID信息
		GetRedis.saveCode(cookie, uid);
	}
	//查询微信授权登录用户
	public PageData getOpenid(PageData pd)  throws Exception{
		return (PageData) dao.findForObject("TbCityManager.getOpenid", pd);
	}
	//更新授权绑定微信号
	public void updateOpenid(PageData pd)throws Exception{
		dao.update("TbCityManager.updateOpenid", pd);
		//查找手机号对应用户
		List<PageData> list=(List<PageData>) dao.findForList("TbCityManager.compReg", pd);
		if(list.size()>0){
			Object object = list.get(0).get("id");
			String uid = object.toString();
			boolean comCode = GetRedis.comCode("appid" + uid);
			if (comCode == true) {
				// 删除原有的登陆的在线状态 保证唯一登陆状态信息
				String a1 = "appid" + uid;
				// 获取cookie信息
				String redis1 = GetRedis.getRedis1(a1);
				if (!"".equals(redis1)) {
					// 删除原有登陆
					DelRedis.getRedis("appuser" + redis1);
					DelRedis.getRedis(a1);
				}
			}
			// 保存用户ID信息
			String cookie = pd.getString("cookie");
			GetRedis.saveCode(cookie, uid);
		}
			
	}
	//保存授权绑定微信号
	public void saveOpenid(PageData pd)throws Exception{
		Object update = dao.update("TbCityManager.saveOpenid", pd);
		String uid = update.toString();
		String cookie = pd.getString("cookie");
		GetRedis.saveCode(cookie, uid);
	}
	
	public WxUser getIdCookie(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("id");
		String ID="appid"+string;
		String redis1 = GetRedis.getRedis1(ID);
		if(!"".equals(redis1)){
			x.setMessage(redis1);
		}else{
			x.setMessage("");
		}
		return x;
	}
	//app和扫码报修发送短信验证码   客户管家发送短信验证码
	public WxUser getSendMsg(PageData pd) throws Exception {
		WxUser x = new WxUser();
		//报修的房源的ID
		String fyid = pd.getString("fyid");
		if(fyid!=null&&!"".equals(fyid)){
			//查询房源对应的楼盘id
			List<PageData> buildId = getBuildId(pd);
			if(buildId.size()>0){
				PageData pageData = buildId.get(0);
				List<PageData> buildOwner = getBuildOwner(pageData);
				if(buildOwner.size()>0){
					PageData pageData2 = buildOwner.get(0);
					String phone = pageData2.getString("phone");
					//给房源的客户管家发送短信验证码
					//报修类型 业主委托、 商城服务、  管家服务（在线报修、物业对接、房屋费用、续租、换房、退租）
					//具体信息
					String appPhone = pd.getString("appPhone");
					String memo = pd.getString("type");
					WxUser sendMessage6 = SmsDemo.sendSms(appPhone,memo, phone);
					x.setFlag(sendMessage6.isFlag());
					x.setMessage(sendMessage6.getMessage());
				}else{
					x.setFlag(false);
					x.setMessage("此房源无客户管家信息！！");
				}
			}else{
				x.setFlag(false);
				x.setMessage("无此房源信息！！");
			}
		}else{
			x.setFlag(false);
			x.setMessage("参数错误！！");
		}
		return x;
	}
	
	//扫码发短信（客户发短信）
	public WxUser getSendMsgService(PageData pd) throws Exception {
		WxUser x=new WxUser();
		//查找所有的400人员的档案信息
		List<PageData> service = getService(pd);
		/*if(service.size()>0){
			String memo = pd.getString("memo");
			for (int i = 0; i <service.size(); i++) {
				String phone = service.get(i).getString("phone");
				WxUser sendMessage6 = SmsDemo.sendSms(memo,"", phone);
				//发送成功
				if(sendMessage6.isFlag()){
					break;
				}
				//继续发送
			}
			x.setFlag(true);
			x.setMessage("发送成功！！");
		}else{
			x.setFlag(false);
			x.setMessage("无400客服人员信息！！");
		}*/
		//当前APP用户的手机号
		String appPhone = pd.getString("appPhone");
		//提交的表单的类型    如业主委托 、服务商城
		String memo = pd.getString("type");
		WxUser sendMessage6 = SmsDemo.scanSendSms("",memo, appPhone); //扫码发短信（客户发短信）
		x.setFlag(sendMessage6.isFlag());
		x.setMessage(sendMessage6.getMessage());
		return x;
	}
	//企业互联   会员企业   企业服务  企业需求   业主委托  商城服务 给400发送短信验证码
	public WxUser getSendMsgEnterPrise(PageData pd) throws Exception {
		WxUser x=new WxUser();
		//查找所有的400人员的档案信息
		/*List<PageData> service = getService(pd);
		if(service.size()>0){
			String memo = pd.getString("memo");
			for (int i = 0; i <service.size(); i++) {
				String phone = service.get(i).getString("phone");
				WxUser sendMessage6 = SmsDemo.sendSms(memo,"", phone);
				//发送成功
				if(sendMessage6.isFlag()){
					break;
				}
				//继续发送
			}
			x.setFlag(true);
			x.setMessage("发送成功！！");
			
		}else{
			x.setFlag(false);
			x.setMessage("无400客服人员信息！！");
		}*/
		//当前APP用户的手机号
		    //客服人员手机号
		   
		List<PageData> listPhone=getListPhone();
		int index=0;
		String appPhone = pd.getString("appPhone");
		String memo = pd.getString("type");
		for (int i = 0; i <listPhone.size(); i++) {
			String phone = listPhone.get(i).getString("phone");
			WxUser sendMessage6 = SmsDemo.sendSms(appPhone,memo, phone);
			//发送成功
			if(!sendMessage6.isFlag()){
				index++;
			}else{
				x.setFlag(true);
				x.setMessage("发送成功！！");
				break;
			}
			if(index >= listPhone.size()){
				x.setFlag(false);
				x.setMessage("发送失败！！");
				break;
			}
			//继续发送
		}
		/*String appPhone = pd.getString("appPhone");
		//提交的表单的类型    如业主委托 、服务商城
		String memo = pd.getString("type");
		WxUser sendMessage6 = SmsDemo.sendSms(appPhone,memo, "15811132039"); //18510884004
		x.setFlag(sendMessage6.isFlag());
		x.setMessage(sendMessage6.getMessage());*/
		return x;
	}

	@Override
	public List<PageData> getBuildId(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("TbCityManager.getBuildId", pd);
	}

	@Override
	public List<PageData> getBuildOwner(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("TbCityManager.getBuildOwner", pd);
	}

	@Override
	public List<PageData> getService(PageData pd) throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("TbCityManager.getService", pd);
	}
	
	
	public List<PageData> getListPhone() throws Exception {
		// TODO Auto-generated method stub
		return (List<PageData>) dao.findForList("TbCityManager.getListPhone", "");
	}

	@Override
	public WxUser browseRecords(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("cookie");
		String a = "appuser" + string;
		Integer redis = GetRedis.getRedis(a);
		if (!"".equals(string)) {
			if (redis != 0) {
				pd.put("redis", redis);
				pd.put("createTime", new Date());
				dao.save("TbCityManager.saveBrowseRecords", pd);
				x.setFlag(true);
				x.setMessage("浏览记录保存成功");
			} else {
				x.setFlag(false);
				x.setMessage("无此用户登陆信息");
			}
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}

	/**
	 * 删除app浏览记录
	 */
	@Override
	public WxUser delBrowseRecords(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.getString("ids");
		if (!"".equals(string)) {
			String[] split = string.split(",");
			dao.update("TbCityManager.deleteBrowseRecords", split);
			x.setFlag(true);
			x.setMessage("浏览记录删除成功");
		} else {
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}

	/**
	 * 查询房源浏览记录
	 */
	@Override
	public PageData getBrowseRecords(PageData pd) throws Exception {
		String string = pd.getString("cookie");
		String a = "appuser" + string;
		Integer redis = GetRedis.getRedis(a);
		pd.put("redis", redis);
		pd.put("createTime", DateUtil.getDay());
		return (PageData)dao.findForObject("TbCityManager.getEnterBrowseRecords", pd);
	}

	/**
	 * 更新app房源浏览时间
	 */
	@Override
	public WxUser updateBrowseRecords(PageData pd) throws Exception {
		WxUser x = new WxUser();
		String string = pd.get("uid")+"";
		if(!"".equals(string)){
			dao.update("TbCityManager.updateBrowseRecords", pd);
			x.setFlag(true);
			x.setMessage("浏览记录时间更新成功");
		}else{
			x.setFlag(false);
			x.setMessage("参数错误");
		}
		return x;
	}

}
