package com.fh.util;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author jiangxl
 * @QQ:309900655 Jul 15, 2010
 * @FILE:SysConts.java
 * @DESC:系统级静态变量，和系统配置文件绑定的统一在此注册后再使用
 */
public class DelRedis {
	public  static boolean getRedis(String cookie){
	    Jedis  jedis = null;
        jedis = new Jedis("47.92.145.21", 6379);
        jedis.auth("yskj88888");
        boolean flag=false;
        if(cookie!=null&&!"".equals(cookie)){
            jedis.del(cookie);
            flag=true;
            
        }
      return flag;
	}
	public static void main(String[] args) {
	    getRedis("aa");
        
    }
}
