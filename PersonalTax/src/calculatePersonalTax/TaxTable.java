package calculatePersonalTax;

/**
 * 根据个人所得税计算办法维护一个税率表
 */
public class TaxTable {
	/** 
	 * 个人所得税起征点 
	 */
	private int taxThreshold;
	/**
	 * 个人所得税级别数
	 */
    private int rankNum;
    /**
     * 个人所得税各级临界金额
     */
    private int[] separation;
    /**
     * 个人所得税各级税率
     */
    private double[] taxRate;

    /**
     * 构造函数
     * 默认个人所得税计算办法如下：
     * 	默认起征点1600元
     * 	默认超过起征点时:
     * 		0-500的部分税率5%，
     * 		500-2000的税率10%，
     * 		2000-5000税率15%，
     * 		5000-20000税率20%，
     * 		超过20000的税率25%
     */
    public TaxTable() {
        taxThreshold = 1600;
        rankNum = 5;
        separation = new int[]{0, 500, 2000, 5000, 20000};
        taxRate = new double[]{0.05, 0.1, 0.15, 0.2, 0.25};
    }

	/**
     * 获取应纳税所得额级别的总数
     * @return 级别总数
     */
    public int getRank_Num() {
        return rankNum;
    }

    /**
     * 获取个人所得税起征点金额
     * @return 起征点金额
     */
    public int getTax_Threshold() {
        return taxThreshold;
    }

    /**
     * 获取指定级别的临界金额
     * @param i 想访问的应纳税所得额级别
     * @return 级别i对应的临界金额
     */
    public int getSeparation(int i) {
        return separation[i];
    }

    /**
     * 获取应纳税所得额各个级别的临界金额
     * @return 各个临界金额组成的数组
     */
    public int[] getSeparations() {
        return separation;
    }

    /**
     * 获取指定级别的税率
     * @param i 想访问的应纳税所得额级别
     * @return 级别i对应的税率
     */
    public double getTax_Rate(int i) {
        return taxRate[i];
    }
    
    /**
     * 获取各级别的税率
     * @return 各级税率组成的数组
     */
    public double[] getTax_Rates() {
        return taxRate;
    }


    /**
     * 修改个人所得额的总级别数
     * @param newRankNum 新的个人所得额的总级别数
     */
    public void setRankNum(int newRankNum) {
        rankNum = newRankNum;
    }

    /**
     * 修改个人所得额的起征点金额，并验证新的起征点金额的合法性（必须大于等于0）
     * @param newThreshold 新的起征点金额
     */
    public void setTax_Threshold(int newThreshold) {
        // 验证合法性
    	if (newThreshold >= 0) {
			// 合法则设置新的起征点金额
			taxThreshold = newThreshold;
		}else{
			//异常处理
            System.out.println("-------------------------------");
            System.out.println("Error：起征点金额必须大于等于0");
            System.out.println("-------------------------------");
            return;
        }
    	
    }

    /**
     * 设置新的临界金额，并保证金额数均不小于0
     * @param newSeparation 新的临界金额组成的数组
     */
    public void setSeparation(int[] newSeparation) {
        for (int s : newSeparation) {
            if (s < 0) {
                System.out.println("-------------------------------");
                System.out.println("Error：临界金额必须大于等于0");
                System.out.println("-------------------------------");
                return;
            }
        }
        separation = newSeparation;
    }

    /**
     * 设置新的税率，并检验输入税率合法性（在区间[0,1]中）
     * @param newRate 新税率组成的数组
     */
    public void setTaxRate(double[] newRate) {
        for (double r : newRate) {
			// 验证合法性
            if (r < 0 || r > 1) {
                System.out.println("-------------------------------");
                System.out.println("Error：税率必须在在区间[0,1]中");
                System.out.println("-------------------------------");
                return;
            }
        }
        taxRate = newRate;
    }
    
    /**
     * 显示当前个人所得税计算办法.
     */
    public void printTable() {       
        // 打印税率表
        System.out.printf("  个人所得税起征点为%d元\n", taxThreshold);
        System.out.println("  超额累进税率：");
        for (int i = 0; i < rankNum - 1; i++) {
            System.out.printf("    超过%5d元至%5d元区间税率为%3d%%\n", 
            		separation[i], separation[i + 1], (int) (taxRate[i] * 100));
        }
        System.out.printf("    超过%5d元的税率为%d%%\n", separation[rankNum - 1], 
        		(int) (taxRate[rankNum - 1] * 100));
    }
    
}
