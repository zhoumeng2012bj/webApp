package com.fh.service.system.yskj.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.service.system.yskj.InterfaceDataManager;
import com.fh.util.PageData;

/**app 幼师APP接口实现
 */
@Service("interfacedataService")
public class InterfaceDataService  implements InterfaceDataManager{

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/**
	 * 获取幼师头条信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getTopNews(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.listTopNews", pd);
	}

	
	/**
	 * 业主委托 /商城服务/售后服务
	 * @throws Exception 
	 */
	@Override
	public void saveTransaction(PageData pd) throws Exception {
		String bussinessType=pd.getString("bussinessType");
		//1 业主委托 2商城服务 3售后服务
		if("1".equals(bussinessType)){
			dao.save("InterfaceDataMapper.saveOwnerEntrust", pd);
		}else if("2".equals(bussinessType)){
			 dao.save("InterfaceDataMapper.saveMallService", pd);
		}else if("3".equals(bussinessType)){
			 dao.save("InterfaceDataMapper.saveCustomer", pd);
		}	
	}
	
	/**
	 * 获取会员企业
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getMemberEnterprise(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.listMemberEnterprise", pd);
	}

	/**
	 * 获取企业需求列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getEnterpriseDemand(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.listEnterpriseDemand", pd);
	}

	/**
	 * 获取app 版本信息
	 */
	@Override
	public PageData getAppVersion(PageData pd) throws Exception {
		return (PageData)dao.findForObject("InterfaceDataMapper.appVersionInfo", pd);
	}
	

}
