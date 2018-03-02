package com.fh.entity.wxapp;

import java.util.Date;
/**
 * 城市行政区域商圈表
 * 
 * @author MyEclipse Persistence Tools
 */

public class TbCity  {
    /**
     * ID
     */
    private Integer id;
    /**
     * 城市区县编码
     */
   private String fdcode;
   /**
    * 城市区域名称
    */
   private String fdname;
   /**
    * 城市区域父ID
    */
   private String fdparentCode;
   /**
    * 城市区域拼音
    */
   private String fdpinyin;
   /**
    * 是否可用  1可用 2 不可用
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
    * 经度
    */
   private String jd;
   /**
    * 维度
    */
   private String wd;
   //修改时间
   private Date updateTime;
public Integer getId() {
    return id;
}
public void setId(Integer id) {
    this.id = id;
}
public String getFdcode() {
    return fdcode;
}
public void setFdcode(String fdcode) {
    this.fdcode = fdcode;
}
public String getFdname() {
    return fdname;
}
public void setFdname(String fdname) {
    this.fdname = fdname;
}
public String getFdparentCode() {
    return fdparentCode;
}
public void setFdparentCode(String fdparentCode) {
    this.fdparentCode = fdparentCode;
}
public String getFdpinyin() {
    return fdpinyin;
}
public void setFdpinyin(String fdpinyin) {
    this.fdpinyin = fdpinyin;
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
/**
 * @return the jd
 */
public String getJd() {
    return jd;
}
/**
 * @param jd the jd to set
 */
public void setJd(String jd) {
    this.jd = jd;
}
/**
 * @return the wd
 */
public String getWd() {
    return wd;
}
/**
 * @param wd the wd to set
 */
public void setWd(String wd) {
    this.wd = wd;
}
   
}