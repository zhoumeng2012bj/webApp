package com.fh.service.wxapp;

import java.util.List;

import com.fh.entity.WxUser;
import com.fh.entity.wxapp.TbCity;
import com.fh.util.PageData;


/** 用户接口类
 */
public interface TbCityManager {
	
	
	

	
	/**通过行政区域或商圈的ID获取信息
	 * @param TbCity_ID
	 * @return
	 * @throws Exception
	 */
	public TbCity getTbCity(Integer id) throws Exception;
	/**
	 * 获取中国下的信息
	 */
	
	public List<PageData>  getRoot(PageData pd)throws Exception;
	/**
	 * 获取固定的父类code的某条信息   北京市的信息
	 */
	public List<PageData>  getBeijing(PageData pd)throws Exception;
	/**
	 * 获取北京市下所有的行政区域  父类code是北京的code
	 */
	public List<PageData>  listAll(PageData pd)throws Exception;
	/**
	 * 根据某一条行政区域的code获取  行政区域下的商圈
	 */
	public List<PageData>  listFdparentCode(PageData pd)throws Exception;
	//获取手机号验证码信息
	public WxUser  getCode(PageData pd)throws Exception;
	//手机号校验
	public WxUser compCode(PageData pd) throws Exception;
	// 用户登录判断有无登录在线状态
	public WxUser compLogin(String pd) throws Exception;
	//通过手机号查询个人信息
	public List<PageData> compReg(PageData pd)  throws Exception;
	//保存用户信息
	public int save(PageData pd) throws Exception;
	//注册用户信息
	public WxUser regist(PageData pd) throws Exception;
	//通过ID修改密码信息
	public void updatePass(PageData pd) throws Exception;
	//通过ID 查询个人信息
	public List<PageData> getId(PageData pd)  throws Exception;
	//查询用户的   名片信息
	public List<PageData> getUserPic(PageData pd)  throws Exception;
	//通过账号密码 查询个人信息
	public List<PageData> getPass(PageData pd)  throws Exception;
	//忘记密码   修改密码信息
	public WxUser updateRemPass(PageData pd) throws Exception;
	//账号密码登录
	public WxUser logAccount(PageData pd) throws Exception;
	//手机号验证码登录
	public WxUser logPhone(PageData pd) throws Exception;
	//通过cookie获取用户信息
	public PageData getCookieId(PageData pd)throws Exception;
	//通过COOKIE 判断用户的登陆状态
	public WxUser landState(PageData pd) throws Exception;
	//退出登录
	public WxUser loginOut(PageData pd) throws Exception;
	//判断房源是否已被此用户收藏
	public WxUser judgeCollection(PageData pd) throws Exception;
	//通过用户和房源查询是否已收藏
	public List<PageData> getUserFyid(PageData pd)  throws Exception;
	//保存房源收藏信息
	public int saveCollect(PageData pd) throws Exception;
	//收藏房源
	public WxUser collectHouse(PageData pd) throws Exception;
	//删除收藏房源
	public WxUser delCollect(PageData pd) throws Exception;
	//删除收藏房源
	public void updateCollecHouse(String[] ids)throws Exception;
	//登录之后的  手机号的修改  判断手机号
	public WxUser judgePhone(PageData pd) throws Exception;
	//通过ID修改手机号
    public void updatePhone(PageData pd) throws Exception;
    //修改手机号
    public WxUser updatePh(PageData pd) throws Exception;
    //登录之后修改密码
    public WxUser updateLoginPass(PageData pd) throws Exception;
	
	
}
