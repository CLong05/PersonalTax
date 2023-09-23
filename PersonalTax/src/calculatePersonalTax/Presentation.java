package calculatePersonalTax;

import java.util.Scanner;

/**
 * 操作界面，用于用户交互与I/O操作
 * @author 陈泷 19335019
 */
public class Presentation {
    /**
     * main函数
     * 显示操作指引信息同时打印税率表
     * @param args 由命令行输入获得的参数
     */
    public static void main(String[] args) {
    	System.out.println("-------------------------------");
        System.out.println("< 欢迎使用个人所得税计算程序！>");
        System.out.println("-------------------------------");
        System.out.println("当前个人所得税计算办法：");
        TaxTable taxtable = new TaxTable();             /* 创建计税规则表的对象实例 */
        taxtable.printTable();                          /* 打印默认计税规则 */
        System.out.println("-------------------------------");
        executeComand(getComand(), taxtable);               /* 获取输入，选择对应功能 */
    }

    /**
     * 显示操作指引，并读取用户指令
     * @return 用户输入的指令编号
     */
    static int getComand() {
    	Scanner input = new Scanner(System.in);
        System.out.println("操作指引\n  请输入指令编号以实现对应功能：");
        System.out.println("  指令编号1--计算个人所得税\n  "
        		+ "指令编号2--修改个人所得税起征点\n  "
        		+ "指令编号3--修改个人所得税各临界金额与税率\n  "
        		+ "指令编号4--退出程序");
        System.out.print("请输入指令编号："); 
        if(input.hasNextInt()){
        	int comand = input.nextInt();
            return comand;
        }else {
           /*
            * 异常处理：
         	*	若为正确格式的指令编号（即1-4的数字），则读取指令
         	*	若为非法输入，将会报错并显示错误信息
         	*/
        	System.out.println("-------------------------------");
            System.out.println("Error：请输入正确格式的指令编号");
            System.out.println("-------------------------------");
            return getComand();
        }
    }

    /**
     * 执行指令1：计算个人所得税
     * @param taxtable   当前个人所得税率表
     */
    static void executeComand1(TaxTable taxtable) {
    	Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.print("正在使用个人所得税计算程序...\n"
        		+ "请输入您的收入总额：");
        TaxCalculator taxCalculator = new TaxCalculator();                     /* 创建计算器 */
        double tax = taxCalculator.taxCalculate(input.nextInt(), taxtable);    /* 计算所得税 */
        if(tax!=-1) {
           /*
            * 异常处理：
        	*	若为合法的数值（即输入金额大于等于0），则输出算出的个人所得税
        	*	若为非法输入，TaxCalculator将会报错并显示错误信息
        	*/
        	System.out.println("您的个人所得税为 " + String.format("%.2f", tax) + "元");
        	}
        System.out.println("-------------------------------");
    }
    
    /**
     * 执行指令2:修改个人所得税起征点
     * @param taxtable   当前个人所得税率表
     */
    static void executeComand2(TaxTable taxtable) {
    	Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("正在修改个人所得税起征点...");
        System.out.print("目前的起征点为：" + taxtable.getTax_Threshold() + "元，请输入新的起征点：");
        int _newThreshold=input.nextInt();
        /*
         * 异常处理：
         * 判断是否为合法输入
         * 	若为合法的数值（即输入金额大于等于0），则修改起征金额，并打印新的个人所得税计算办法
         * 	若为非法输入，taxtable.setTax_Threshold将会报错并显示错误信息
         */
        taxtable.setTax_Threshold(_newThreshold);
        if (_newThreshold > 0) {
        	System.out.println("修改成功，新的个人所得税计算办法如下，");
        	taxtable.printTable();
        	System.out.println("-------------------------------");
        	}
    	
    }
    
    /**
     * 执行指令3：修改个人所得税各级临界金额与税率
     * 修改成功返回True,否则返回False
     * @param taxtable   当前个人所得税率表
     */
    static boolean executeComand3(TaxTable taxtable) {
    	Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("请输入各级个人所得税的临界金额（第一个临界金额为0，各临界金额间以空格分开，按回车结束）：");                    
        String[] inputArray = input.nextLine().split(" "); 		/* 读取临界金额 */
        /*
         * 异常处理：
         * 	判断临界金额是否符合规范,若不符合则报错并要求重新输入
         */
        while (!inputArray[0].equals("0")) {
			System.out.println("---------------------------------------------");
            System.out.println("Error：第一个临界金额必须为0，请重新输入");
			System.out.println("---------------------------------------------");
            inputArray = input.nextLine().split(" ");
        }
        boolean Separation_valid=true; 			/* 记录临界金额是否合法 */
        boolean Rate_valid=true;	  			/* 记录各级税率是否合法 */
        int[] newSeparation = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            newSeparation[i] = Integer.parseInt(inputArray[i]);
            /*
             * 异常处理：
             * 	判断临界金额是否严格单调递增
             * 	若不符合则报错并返回初始菜单
             */
            if(i>0 && newSeparation[i]<=newSeparation[i-1]) {
            	Separation_valid=false;
				System.out.println("---------------------------------------------");
            	System.out.println("Error：输入的各个临界金额必须严格单调递增");
            	System.out.println("---------------------------------------------");
            	break;
            }
        } 
        if(Separation_valid==false) return false;				/* 若输入不合法则退出 */
        
        System.out.println("请输入各级的税率（请以小数形式输入，各税率间以空格分开，按回车结束）");
        inputArray = input.nextLine().split(" ");		/* 读取各级税率 */
        double[] newTaxRate = new double[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            newTaxRate[i] = Double.parseDouble(inputArray[i]);
            
            /*
             * 异常处理：
             * 判断税率是否在在区间[0,1]中
             * 若不符合则报错并返回初始菜单
             */
            if(newTaxRate[i]<0 || newTaxRate[i]>1) {
            	Rate_valid=false;
				System.out.println("---------------------------------------------");
            	System.out.println("Error：存在非法税率（税率必须在在区间[0,1]中）");
            	System.out.println("---------------------------------------------");
            	break;
            }
        }                   
        if(Rate_valid==false) return false;				/* 若输入不合法则退出 */
        
        /*
         * 异常处理：
         * 	检验临界金额数与税率级别数是否相等
         * 	若不相等则退出
         */
        if(newSeparation.length!=newTaxRate.length) {
			System.out.println("---------------------------------------------");
        	System.out.println("Error：临界金额总数与税率级别总数不相等");
        	System.out.println("---------------------------------------------");
        	return false;
        }                    

        //若输入数值均合法，则修改TaxTable
        taxtable.setRankNum(inputArray.length);
        taxtable.setSeparation(newSeparation);
        taxtable.setTaxRate(newTaxRate);
        System.out.print("修改成功，新的个人所得税计算办法如下，");
        taxtable.printTable();
        System.out.println("-------------------------------");
        return true;
    }
    
    /**
     * 根据指令编号执行对应的功能
     * @param comandNumber 用户输入的指令编号
     * @param taxtable   当前个人所得税率表
     */
    static void executeComand(int comandNumber, TaxTable taxtable) {
        Scanner input = new Scanner(System.in);
        while (comandNumber != 4) {
            switch (comandNumber) {
                case 1:
                    executeComand1(taxtable);
                    break;
                    
                case 2:
                    executeComand2(taxtable);
                    break;
                    
                case 3:
                    executeComand3(taxtable);
                    break;
                
                default:
                	/*
                	 * 异常处理：
                     * 判断输入的指令编号是否合法
                     * 若不存在该指令编号则报错并返回初始菜单
                     */
                    System.out.println("------------------------------------");
                    System.out.println("Error：该指令编号不存在，请重新输入");
                    System.out.println("------------------------------------");
            }
            comandNumber = getComand();
        }
        System.out.print("程序已关闭，欢迎您再次使用");
        input.close();
    }
}
