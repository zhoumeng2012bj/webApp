package com.fh.entity.wxapp;


import java.util.Date;


/**
 * 地铁线路站点关系表
 * 
 * @author MyEclipse Persistence Tools
 */
public class TbSubwayRelation implements java.io.Serializable {
    private static final long serialVersionUID = -649602436466185624L;
    /**
     * ID
     */
    private Integer id;
    /**
     * 地铁Id
     */
    private TbSubway fd_subway_id;
    /**
     * 地铁站名称
     */
    private TbSubwayStation fd_station_id;
    /**
     * 关系表是否可用
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

    public TbSubway getFd_subway_id() {
        return fd_subway_id;
    }
    public void setFd_subway_id(TbSubway fd_subway_id) {
        this.fd_subway_id = fd_subway_id;
    }
    public TbSubwayStation getFd_station_id() {
        return fd_station_id;
    }
    public void setFd_station_id(TbSubwayStation fd_station_id) {
        this.fd_station_id = fd_station_id;
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
