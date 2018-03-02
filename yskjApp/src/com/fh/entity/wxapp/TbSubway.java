package com.fh.entity.wxapp;


import java.util.Date;


/**
 * 地铁线路表
 * 
 * @author MyEclipse Persistence Tools
 */
public class TbSubway implements java.io.Serializable {
    /**
     * ID
     */
    private Integer id;
    /**
     * 线路名称
     */
    private String fdname;
    /**
     * 地铁站名称
     */
    /**
     * 
     */
  
    private Integer fdparentId;
    /**
     * 地铁站是否可用
     */
    private Integer fdstatus;
    /**
     * 排序
     */
    private Integer fdsort;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFdname() {
        return fdname;
    }
    public void setFdname(String fdname) {
        this.fdname = fdname;
    }
   
    public Integer getFdparentId() {
        return fdparentId;
    }
    public void setFdparentId(Integer fdparentId) {
        this.fdparentId = fdparentId;
    }
    public Integer getFdstatus() {
        return fdstatus;
    }
    public void setFdstatus(Integer fdstatus) {
        this.fdstatus = fdstatus;
    }
    public Integer getFdsort() {
        return fdsort;
    }
    public void setFdsort(Integer fdsort) {
        this.fdsort = fdsort;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    
    
}
