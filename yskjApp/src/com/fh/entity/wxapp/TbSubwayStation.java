package com.fh.entity.wxapp;


import java.util.Date;



/**
 * 地铁站点表
 * 
 * @author MyEclipse Persistence Tools
 */
public class TbSubwayStation implements java.io.Serializable {
    private static final long serialVersionUID = -649602436466185624L;
    /**
     * ID
     */
    private Integer id;
    /**
     * 站点名称
     */
    private String fdname;
    /**
     * 站点是否可用
     */
    private Integer fdstatus;
   
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
  
    public Integer getFdstatus() {
        return fdstatus;
    }
    public void setFdstatus(Integer fdstatus) {
        this.fdstatus = fdstatus;
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
