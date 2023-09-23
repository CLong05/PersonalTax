package calculatePersonalTax;

/**
 * 用于计算个人所得税
 */
public class TaxCalculator {
    /**
     * 计算个人所得税金额
     *
     * @param income   收入总额
     * @param taxtable 当前个人所得税率表
     * @return 个人所得税金额
     */
    public double taxCalculate(int income, TaxTable taxtable) {
        // 检验输入合法性（工资金额必须大于等于0）
    	if (income < 0) {
			System.out.println("-------------------------------");
            System.out.println("Error：工资金额必须大于等于0");
			System.out.println("-------------------------------");
            return -1;
        }
    	
    	// 若为合法输入，则计算所得税
        double tax = 0;
        int rank = 1;
        // 若无需缴税，直接返回0
        if (income < taxtable.getTax_Threshold()) {
            return tax;
        } else{
        // 若需要缴税，则进行计算
        int _income = income - taxtable.getTax_Threshold();			/* 计算应纳税所得额 */
        for (; rank < taxtable.getRank_Num() 
        		&& _income > taxtable.getSeparation(rank); rank++) {
            tax += (taxtable.getSeparation(rank) - taxtable.getSeparation(rank - 1)) 
            		* taxtable.getTax_Rate(rank - 1);
        }
        tax += (_income - taxtable.getSeparation(rank - 1)) 
        		* taxtable.getTax_Rate(rank - 1);
        return tax;
		}
    }
}
