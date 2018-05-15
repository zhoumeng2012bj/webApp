package com.fh.service.system.yskj;


import java.util.List;

import com.fh.util.PageData;

/**
 * App接口
 * @author admin
 *
 */
public interface InterfaceDataManager {
	
	/**
	 *  获取幼师头条信息
	 * @param pd
	 * @return
	 */
	public List<PageData> getTopNews(PageData pd) throws Exception;
	
	/**
	 *  获取幼师头条详情信息
	 * @param pd
	 * @return
	 */
	public PageData getTopNewsInfo(PageData pd) throws Exception;
	
	
	/**
	 *  1：业主委托、2：商城服务、3：售后服务 
	 * @param pd
	 * @return
	 */
	public void saveTransaction(PageData pd)throws Exception;
	
	/**
	 * 管家服务 （设备报修 BX、物业对接 WY、房屋凭证 PZ、房屋费用 FY、房屋变更 BG）
	 * @param pd
	 * @return
	 */
	public void savebutlerInfo(PageData pd)throws Exception;
	
	/**
	 * 房屋变更（续租、换房、退房）
	 * @param pd
	 * @return
	 */
	public void saveHousingChange(PageData pd)throws Exception; 
	
	
	/**
	 * 获取会员企业
	 * @param pd
	 */
	public List<PageData> getMemberEnterprise(PageData pd) throws Exception;
	
	/**
	 * 获取企业需求列表
	 * @param pd
	 */
	public List<PageData> getEnterpriseDemand(PageData pd) throws Exception;
	
	/**
	 * 获取App 版本信息
	 * @param pd
	 */
	public PageData getAppVersion(PageData pd) throws Exception;
	
	/**
	 * 获取最新数据列表
	 * @param type
	 */
	public List<PageData> getLatestData(int type) throws Exception;
	

	/**
	 * 获取企业面积规模列表
	 * 
	 */
	public List<PageData> getEnterpriseScaleData() throws Exception;
	
	/**
	 * 获取当前登陆用户的房源及公司信息
	 * @param id
	 */
	public List<PageData> getHouseCompanyById(String id) throws Exception;
	
	/**
	 * 获取当前登陆用户的房源及公司信息
	 * @param id
	 */
	public List<PageData> getMessageByUserId(PageData pd) throws Exception;
	

}
