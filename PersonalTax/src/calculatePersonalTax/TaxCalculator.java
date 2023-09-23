package calculatePersonalTax;

/**
 * ���ڼ����������˰
 */
public class TaxCalculator {
    /**
     * �����������˰���
     *
     * @param income   �����ܶ�
     * @param taxtable ��ǰ��������˰�ʱ�
     * @return ��������˰���
     */
    public double taxCalculate(int income, TaxTable taxtable) {
        // ��������Ϸ��ԣ����ʽ�������ڵ���0��
    	if (income < 0) {
			System.out.println("-------------------------------");
            System.out.println("Error�����ʽ�������ڵ���0");
			System.out.println("-------------------------------");
            return -1;
        }
    	
    	// ��Ϊ�Ϸ����룬���������˰
        double tax = 0;
        int rank = 1;
        // �������˰��ֱ�ӷ���0
        if (income < taxtable.getTax_Threshold()) {
            return tax;
        } else{
        // ����Ҫ��˰������м���
        int _income = income - taxtable.getTax_Threshold();			/* ����Ӧ��˰���ö� */
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
