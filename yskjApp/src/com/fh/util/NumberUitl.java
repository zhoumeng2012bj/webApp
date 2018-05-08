package com.fh.util;
import java.util.HashMap;
import java.util.Map;

/** 
 * 说明：常用工具
 * @version
 */
public class NumberUitl {
	
	private static Map<String,Map<String,Integer>> NUMMAP=new HashMap<String,Map<String,Integer>>();
	
	//单号
	private static Map<String,Integer> ooddNumbersMap=new HashMap<String,Integer>();
	
	//生成单号规则
    static{
    	//设备报修 BX
    	ooddNumbersMap.put("BX", 1);   //设备报修 
    	ooddNumbersMap.put("BXMC", 1); //门窗维修
    	ooddNumbersMap.put("BXDJ", 2); //灯具电路
    	ooddNumbersMap.put("BXKT", 3); //空调制冷
    	ooddNumbersMap.put("BXSL", 4); //水路管件
    	//物业对接 WY
    	ooddNumbersMap.put("WY", 2);   //物业对接
    	ooddNumbersMap.put("WYCM", 1); //开具出门条
    	ooddNumbersMap.put("WYZX", 2); //装修同意函
    	ooddNumbersMap.put("WYWL", 3); //网络问题
    	//房屋凭证 PZ
    	ooddNumbersMap.put("PZ", 3);   //房屋凭证
    	ooddNumbersMap.put("PZCX", 1); //大产权证
    	ooddNumbersMap.put("PZFB", 2); //房本原件
    	ooddNumbersMap.put("PZSF", 3); //业主身份证原件
    	ooddNumbersMap.put("BZYZ", 4); //业主签字
    	//房屋费用 FY
    	ooddNumbersMap.put("FY", 4);   //房屋费用
    	ooddNumbersMap.put("FYWY", 1); //物业费
    	ooddNumbersMap.put("FYQN", 2); //取暖费
    	ooddNumbersMap.put("FYSD", 3); //遗留水电费
    	ooddNumbersMap.put("FYKT", 4); //空调转换费
    	//房屋变更BG
    	ooddNumbersMap.put("BG", 5);   //房屋变更
    	ooddNumbersMap.put("BGXZ", 1); //续租
    	ooddNumbersMap.put("BGHZ", 2); //换房
    	ooddNumbersMap.put("BGTZ", 3); //退房
    }
	    
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
	
	public static Integer getCategory(String category){
    	int type=0;
    	if(ooddNumbersMap.get(category) !=null){
    		type=ooddNumbersMap.get(category);
    	}
    	return type;
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
		
		//System.out.println(NumberUitl.getCategory("BGXZ"));
	}
	
}
