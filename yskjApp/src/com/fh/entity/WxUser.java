package com.fh.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 微信登陆
 * 
 * @author MyEclipse Persistence Tools
 */

public class WxUser implements java.io.Serializable {
    private static final long serialVersionUID = -649602436466185624L;
    private boolean flag;
    private String message;
    private String qxzt;
    private Integer ry;
    private Integer fyzt;
    private String uname;
	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the qxzt
	 */
	public String getQxzt() {
		return qxzt;
	}
	/**
	 * @param qxzt the qxzt to set
	 */
	public void setQxzt(String qxzt) {
		this.qxzt = qxzt;
	}
	/**
	 * @return the ry
	 */
	public Integer getRy() {
		return ry;
	}
	/**
	 * @param ry the ry to set
	 */
	public void setRy(Integer ry) {
		this.ry = ry;
	}
	/**
	 * @return the fyzt
	 */
	public Integer getFyzt() {
		return fyzt;
	}
	/**
	 * @param fyzt the fyzt to set
	 */
	public void setFyzt(Integer fyzt) {
		this.fyzt = fyzt;
	}
	/**
	 * @return the uname
	 */
	public String getUname() {
		return uname;
	}
	/**
	 * @param uname the uname to set
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}
    
   
   
}
