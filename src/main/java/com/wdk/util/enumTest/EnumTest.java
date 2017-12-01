package com.wdk.util.enumTest;

public class EnumTest {
	
	public static void main(String[] args) {  
        forEnum();  
        useEnumInJava();  
    }  
  
    /** 
     * 循环枚举,输出ordinal属性；若枚举有内部属性，则也输出。(说的就是我定义的TYPE类型的枚举的typeName属性) 
     */  
    private static void forEnum() {  
        for (SimpleEnum simpleEnum : SimpleEnum.values()) {  
        	System.out.println(simpleEnum + "  name  " + simpleEnum.name());
            System.out.println(simpleEnum + "  ordinal  " + simpleEnum.ordinal());  
        }  
        System.out.println("------------------");  
        for (TYPE type : TYPE.values()) {  
            System.out.println("type = " + type + "    type.name = " + type.name() + "   typeName = " + type.getTypeName() + "   ordinal = " + type.ordinal()+ "   typeName = " + type.typeName );  
        }
        System.out.println("--------------------");
        
        for(DATE date : DATE.values()){
        	System.out.println("date = "+date+"   date.name =" + date.name()+"  date = "+date.date + " ordinal = "+date.ordinal());
        }
        
    }  
  
    /** 
     * 在Java代码使用枚举 
     */  
    private static void useEnumInJava() {  
        String typeName = "test";  
        TYPE type = TYPE.fromTypeName(typeName);  
        if (TYPE.TEST.equals(type)) {  
            System.out.println("根据字符串获得的枚举类型实例跟枚举常量一致");  
        } else {  
            System.out.println("大师兄代码错误");  
        }  
    }  
  
    /** 
     * 季节枚举(不带参数的枚举常量)这个是最简单的枚举使用实例 
     * Ordinal 属性，对应的就是排列顺序，从0开始。 
     */  
    private enum SimpleEnum {  
        SPRING,  
        SUMMER,  
        AUTUMN,  
        WINTER  
    }  
  
  
    /** 
     * 常用类型(带参数的枚举常量，这个只是在书上不常见，实际使用还是很多的，看懂这个，使用就不是问题啦。) 
     */  
    private enum TYPE {  
        FIREWALL("firewall"),  
        SECRET("secretMac"),  
        BALANCE("f5"),
        TEST("test");
  
        private final String typeName;  
  
        TYPE(String typeName) {  
            this.typeName = typeName;  
        }  
  
        /** 
         * 根据类型的名称，返回类型的枚举实例。 
         * 
         * @param typeName 类型名称 
         */  
        public static TYPE fromTypeName(String typeName) {  
            for (TYPE type : TYPE.values()) {  
                if (type.getTypeName().equals(typeName)) {  
                    return type;  
                }  
            }  
            return null;  
        }  
  
        public String getTypeName() {  
            return this.typeName;  
        }  
    }  
    
    public enum DATE {
    	MONDAY(1),
    	TUESDAY(2),
    	WEDNESDAY(3),
    	THURSDAY(4),
    	FRIDAY(5),
    	SATURDAY(6),
    	SUNDAY(7);
    	
    	private final int date;
    	
    	DATE(int date){
    		this.date = date;
    	}
    }
}
