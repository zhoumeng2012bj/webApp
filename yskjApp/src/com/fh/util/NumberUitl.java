package com.fh.util;
import java.util.HashMap;
import java.util.Map;

/** 
 * 说明：常用工具
 * @version
 */
public class NumberUitl {
	
	private static Map<String,Map<String,Integer>> NUMMAP=new HashMap<String,Map<String,Integer>>();
	
	public static String getNumber(String type){
		String key=type+DateUtil.getYMDTime();
		String number="";
		if(NUMMAP.get(type) !=null){
			Map<String,Integer> valMap=NUMMAP.get(type);
			if(valMap.get(key) !=null){
				Integer val=valMap.get(key);
				if(val !=null && !"".equals(val)){
					int index=val+1;
					number=getNumStr(index);
					valMap.put(key,index);
					NUMMAP.put(type, valMap);
				}
			}else{
				valMap.clear();
				valMap.put(key,1);
				NUMMAP.put(type, valMap);
				number="0001";
			}
		}else{
			Map<String,Integer> tempMp=new HashMap<String,Integer>();
			tempMp.put(key, 1);
			number="0001";
			NUMMAP.put(type, tempMp);
		}
		return key+number;
	}
	
	private static String getNumStr(Integer number){
		String valStr="";
		if(number < 9999){
	    	if (number < 10){
	    		valStr = "000"+number;
	    	}else if (number < 100 && number >= 10){
	    		valStr = "00"+number;
		    }else if (number < 1000 && number >= 100){
		    	valStr ="0"+number;
		    }else if (number < 10000 && number >= 1000){
		    	valStr =""+number;
		     }
		}else{
			valStr ="0000";
		}
		return valStr;
	}
	
	private static int longTem;
	
	private static String strNum;
	
	public NumberUitl(){
		longTem = 0;
		strNum = "";
	}
	    
    public static String getNextNum() {
    	if(longTem > 9999)
    	longTem = 0;
    	if (longTem < 10)
    	strNum = "000"+longTem;
    	else if (longTem < 100 && longTem >= 10)
    	strNum = "00"+longTem;
    	else if (longTem < 1000 && longTem >= 100)
        	strNum ="0"+longTem;
    	else if (longTem < 10000 && longTem >= 1000)
        	strNum =""+longTem;
    	else
    	strNum = String.valueOf(longTem);

    	longTem++;
    	return strNum;
    }

	public int getLongTem() {
		return longTem;
	}

	public void setLongTem(int longTem) {
		this.longTem = longTem;
	}

	public String getStrNum() {
		return strNum;
	}

	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}
	
	public static void main(String[] args) {
		//System.out.println (getNextNum());
		//System.out.println(NumberUitl.getNumber("SH"));
		//System.out.println(NumberUitl.getNumber("WT"));
		//System.out.println(NumberUitl.getNumber("SW"));
		//System.out.println(NumberUitl.getNumber("ZX"));
		//System.out.println(NumberUitl.getNumber("JR"));
		//System.out.println(NumberUitl.getNumber("ZZ"));
	}
	
}
