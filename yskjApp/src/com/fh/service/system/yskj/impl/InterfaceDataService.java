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
	 * 获取幼师头条详情信息
	 */
	@Override
	public PageData getTopNewsInfo(PageData pd) throws Exception {
		return (PageData) dao.findForObject("InterfaceDataMapper.topNewsInfo", pd);
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
	 * 3、管家服务 （设备报修 BX、物业对接 WY、房屋凭证 PZ、房屋费用 FY、房屋变更 BG）
	 * @throws Exception 
	 */
	@Override
	public void savebutlerInfo(PageData pd) throws Exception {
	    dao.save("InterfaceDataMapper.saveButlerInfo", pd);
	}
	
	/**
	 *  房屋变更（续租、换房、退房）
	 */
	@Override
	public void saveHousingChange(PageData pd) throws Exception {
		dao.save("InterfaceDataMapper.saveHousingChange", pd); 
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
	 * 获取更多全部会员企业
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getListMemberEnterprise(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.listMemberEnterpriseInfo", pd);
	}
    /**
     * 获取会员企业详情信息
     */
	@Override
	public PageData getMemberEnterpriseInfo(PageData pd) throws Exception {
		return (PageData) dao.findForObject("InterfaceDataMapper.getEnterMemberEnterprise", pd);
	}
	
	/**
	 * 加入会员
	 * @throws Exception 
	 */
	@Override
	public void saveMembership(PageData pd) throws Exception {
	    dao.save("InterfaceDataMapper.saveMembership", pd);
	}
	
	/**
	 * 保存会员企业logo图片信息
	 * @throws Exception 
	 */
	@Override
	public void saveEnterprisePic(PageData pd) throws Exception {
		dao.save("InterfaceDataMapper.saveEnterprisePic", pd);
	}
	
	/**
	 * 发布服务、发布需求
	 * @throws Exception 
	 */
	@Override
	public void saveReleaseDemand(PageData pd) throws Exception {
	    dao.save("InterfaceDataMapper.saveReleaseDemand", pd);
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
	 * （2.0版本）企业服务、需求列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> ReleaseDemandList(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.listReleaseDemand", pd);
	}

	/**
	 * 获取app 版本信息
	 */
	@Override
	public PageData getAppVersion(PageData pd) throws Exception {
		return (PageData)dao.findForObject("InterfaceDataMapper.appVersionInfo", pd);
	}
	
	/**
	 * 获取最新数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getLatestData(int type) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getLatestData", type);
	}
	/**
	 * 跟根据合同总面积计算公司规模统计
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getEnterpriseScaleData() throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getEnterpriseScaleData",0 );
	}
	/**
	 * 获取当前登陆用户的房源及公司信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getHouseCompanyById(String id) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getHouseCompanyById", id);
	}
	/**
	 * 获取当前登陆用户的通知列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getMessageByUserId(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getMessageByUserId", pd);
	}
	
	/**
	 * 获取id获取业主委托、售后服务及服务商城及房屋 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getTransactionByUserId(String id) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getTransactionByUserId", id);
	}
	/**
	 * 获取id获取企业会员
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getEnterpriseByUserId(String id) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getEnterpriseByUserId", id);
	}
	/**
	 * 获取id获取企业需求服务
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getEnterpriseDemandByUserId(String id) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getEnterpriseDemandByUserId", id);
	}

	/**
	 * 判断用户是否已加入会员
	 */
	@Override
	public PageData getEnterMembership(PageData pd) throws Exception {
		return (PageData)dao.findForObject("InterfaceDataMapper.getEnterMembership", pd);
	}

	/**
	 * 判断企业是否已加入会员
	 */
	@Override
	public PageData getEnterEnterprise(PageData pd) throws Exception {
		return (PageData)dao.findForObject("InterfaceDataMapper.getEnterEnterprise", pd);
	}

	/**
	 * 获取2.0版最新数据
	 */
	@Override
	public PageData getQuotationData(PageData pd) throws Exception {
		return (PageData)dao.findForObject("InterfaceDataMapper.getEnterNewData", pd);
	}

	/**
	 * 获取登录用户手机号码
	 */
	@Override
	public String getUserPhone(String id) throws Exception {
		return (String)dao.findForObject("InterfaceDataMapper.getEnterUserPhone", id);
	}
	
	/**
	 * 获取当前登陆用户的合同列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getContractListById(String id) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getContractListById", id);
	}
	/**
	 * 获取租金信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getContractZjxxById(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getContractZjxxById", pd);
	}
	/**
	 * 获取付款信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getContractFkfsById(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getContractFkfsById", pd);
	}
	/**
	 * 获取应付信息 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getContractFkById(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getContractFkById", pd);
	}
	/**
	 * 获取应收信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getContractSkById(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getContractSkById", pd);
	}
	/**
	 * 获取付款凭证 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getContractFkpzById(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getContractFkpzById", pd);
	}
	/**
	 * 获取收款凭证
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PageData> getContractSkpzById(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("InterfaceDataMapper.getContractSkpzById", pd);
	}
}
