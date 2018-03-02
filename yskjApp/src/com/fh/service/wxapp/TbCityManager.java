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
	public WxUser  getCode(PageData pd)throws Exception;
	public WxUser compCode(PageData pd) throws Exception;
	public WxUser compLogin(String pd) throws Exception;
	public List<PageData> compReg(PageData pd)  throws Exception;
	public void save(PageData pd) throws Exception;
	public WxUser regist(PageData pd) throws Exception;
	public void updatePass(PageData pd) throws Exception;
	
}
