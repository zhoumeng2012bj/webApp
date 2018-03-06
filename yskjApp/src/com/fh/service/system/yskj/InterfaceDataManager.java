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
	 *  1：业主委托、2：商城服务、3：售后服务 
	 * @param pd
	 * @return
	 */
	public void saveTransaction(PageData pd)throws Exception;
	
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

}
