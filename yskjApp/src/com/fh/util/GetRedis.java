package com.fh.util;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * 
 * @author jiangxl
 * @QQ:309900655 Jul 15, 2010
 * @FILE:SysConts.java
 * @DESC:系统级静态变量，和系统配置文件绑定的统一在此注册后再使用
 */
public class GetRedis {
	public  static Integer getRedis(String cookie){
	    Jedis  jedis = null;
        jedis = new Jedis("47.92.145.21", 6379);
        jedis.auth("yskj88888");
        String string = jedis.get(cookie);
        Integer id=0;
        if(string!=null&&!"".equals(string)){
            id=Integer.valueOf(string);
            
        }
        System.out.println("dtring:   "+string);
        return id;
	}
	public  static  Set<String> getValues(String cookie){
        Jedis  jedis = null;
        jedis = new Jedis("47.92.145.21", 6379);
        jedis.auth("yskj88888");
        Set<String> smembers = jedis.smembers(cookie);
        return smembers;
    }
	public static boolean comCode(String code){
		
		 Jedis  jedis = null;
	     jedis = new Jedis("47.92.145.21", 6379);
	     jedis.auth("yskj88888");
	     Set<String> keys = jedis.keys("appid*");
	     boolean contains = keys.contains(code);
	     System.out.println(contains);
	     return contains;
		
	}
	
	public static void main(String[] args) {
	  /* // getRedis("aa");
	    Jedis  jedis = null;
        jedis = new Jedis("47.92.145.21", 6379);
        jedis.auth("yskj88888");
        jedis.set("qduser111","aa");
        //有效时间3天有效时间是三天
        jedis.expire("qduser111", 2596000);*/
		comCode("appid1");
		 
	        
	        
        
    }
}
