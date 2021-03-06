package com.fh.service.wxapp;

import java.util.List;

import com.fh.entity.WxUser;
import com.fh.entity.wxapp.TbCity;
import com.fh.util.PageData;

/**
 * 用户接口类
 */
public interface TbCityManager {

	/**
	 * 通过行政区域或商圈的ID获取信息
	 * 
	 * @param TbCity_ID
	 * @return
	 * @throws Exception
	 */
	public TbCity getTbCity(Integer id) throws Exception;

	/**
	 * 获取中国下的信息
	 */

	public List<PageData> getRoot(PageData pd) throws Exception;

	/**
	 * 获取固定的父类code的某条信息 北京市的信息
	 */
	public List<PageData> getBeijing(PageData pd) throws Exception;

	/**
	 * 获取北京市下所有的行政区域 父类code是北京的code
	 */
	public List<PageData> listAll(PageData pd) throws Exception;

	/**
	 * 根据某一条行政区域的code获取 行政区域下的商圈
	 */
	public List<PageData> listFdparentCode(PageData pd) throws Exception;

	// 获取手机号验证码信息
	public WxUser getCode(PageData pd) throws Exception;

	// 手机号校验
	public WxUser compCode(PageData pd) throws Exception;

	// 用户登录判断有无登录在线状态
	public WxUser compLogin(String pd) throws Exception;

	// 通过手机号查询个人信息
	public List<PageData> compReg(PageData pd) throws Exception;

	// 保存用户信息
	public int save(PageData pd) throws Exception;

	// 注册用户信息
	public WxUser regist(PageData pd) throws Exception;

	// 通过ID修改密码信息
	public void updatePass(PageData pd) throws Exception;

	// 通过ID 查询个人信息
	public List<PageData> getId(PageData pd) throws Exception;

	// 查询用户的 名片信息
	public List<PageData> getUserPic(PageData pd) throws Exception;

	// 通过账号密码 查询个人信息
	public List<PageData> getPass(PageData pd) throws Exception;

	// 忘记密码 修改密码信息
	public WxUser updateRemPass(PageData pd) throws Exception;

	// 账号密码登录
	public WxUser logAccount(PageData pd) throws Exception;

	// 手机号验证码登录
	public WxUser logPhone(PageData pd) throws Exception;

	// 通过cookie获取用户信息
	public PageData getCookieId(PageData pd) throws Exception;

	// 通过COOKIE 判断用户的登陆状态
	public WxUser landState(PageData pd) throws Exception;

	// 退出登录
	public WxUser loginOut(PageData pd) throws Exception;

	// 判断房源是否已被此用户收藏
	public WxUser judgeCollection(PageData pd) throws Exception;

	// 通过用户和房源查询是否已收藏
	public List<PageData> getUserFyid(PageData pd) throws Exception;

	// 保存房源收藏信息
	public int saveCollect(PageData pd) throws Exception;

	// 收藏房源
	public WxUser collectHouse(PageData pd) throws Exception;

	// 删除收藏房源
	public WxUser delCollect(PageData pd) throws Exception;

	// 删除收藏房源
	public void updateCollecHouse(String[] ids) throws Exception;

	// 登录之后的 手机号的修改 判断手机号
	public WxUser judgePhone(PageData pd) throws Exception;

	// 通过ID修改手机号
	public void updatePhone(PageData pd) throws Exception;

	// 修改手机号
	public WxUser updatePh(PageData pd) throws Exception;

	// 登录之后修改密码
	public WxUser updateLoginPass(PageData pd) throws Exception;

	// 取消收藏房源
	public WxUser cancleCollect(PageData pd) throws Exception;

	// 业主委托 商城服务 获取验证码
	public WxUser getServiceCode(PageData pd) throws Exception;

	// 业主委托 商城服务 手机号验证
	public WxUser compServiceCode(PageData pd) throws Exception;

	// 通过用户Id获取楼盘的管家信息
	public List<PageData> getButler(PageData pd) throws Exception;

	// 根据楼盘ID和管家的类别获取房源的 管家信息
	public List<PageData> getButler2(PageData pd) throws Exception;

	// 获取楼盘具体的 业主管家信息或客户管家信息
	public PageData getButlerInfo(PageData pd) throws Exception;
	
	//查询微信号授权登录
	public PageData getOpenid(PageData pd)  throws Exception;
	//授权绑定微信号 
    public void updateOpenid(PageData pd) throws Exception;
    //保存授权绑定微信号
    public void saveOpenid(PageData pd) throws Exception;
    //微信授权直接登录
    public void logOpenid(String uid,String cookie)  throws Exception;
    //通过ID获取用户的cookie
    public WxUser getIdCookie(PageData pd) throws Exception;
    //通过房源ID获取楼盘的ID
    public List<PageData> getBuildId(PageData pd)  throws Exception;
    //根据楼盘ID获取楼盘的客户管家信息
    public List<PageData> getBuildOwner(PageData pd)  throws Exception;
    //查找所有的400客服人员信息
    public List<PageData> getService(PageData pd)  throws Exception;
    //app和扫码报修发送短信验证码   客户管家发送短信验证码
  	public WxUser getSendMsg(PageData pd) throws Exception ;
  	//报修  400档案人员发送短信验证码信息   报修的信息能够查询到当前的房源信息
  	public WxUser getSendMsgService(PageData pd) throws Exception;
  	//企业互联   会员企业   企业服务  企业需求   业主委托  商城服务 给400发送短信验证码
  	public WxUser getSendMsgEnterPrise(PageData pd) throws Exception;
  	
  	//app房源浏览记录
 	public PageData getBrowseRecords(PageData pd) throws Exception;
 	//更新app房源浏览记录时间
 	public WxUser updateBrowseRecords(PageData pd) throws Exception;
    // app房源浏览记录
 	public WxUser browseRecords(PageData pd) throws Exception;
    // 删除app房源浏览记录
 	public WxUser delBrowseRecords(PageData pd) throws Exception;
}
