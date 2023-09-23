package calculatePersonalTax;

/**
 * ���ݸ�������˰����취ά��һ��˰�ʱ�
 */
public class TaxTable {
	/** 
	 * ��������˰������ 
	 */
	private int taxThreshold;
	/**
	 * ��������˰������
	 */
    private int rankNum;
    /**
     * ��������˰�����ٽ���
     */
    private int[] separation;
    /**
     * ��������˰����˰��
     */
    private double[] taxRate;

    /**
     * ���캯��
     * Ĭ�ϸ�������˰����취���£�
     * 	Ĭ��������1600Ԫ
     * 	Ĭ�ϳ���������ʱ:
     * 		0-500�Ĳ���˰��5%��
     * 		500-2000��˰��10%��
     * 		2000-5000˰��15%��
     * 		5000-20000˰��20%��
     * 		����20000��˰��25%
     */
    public TaxTable() {
        taxThreshold = 1600;
        rankNum = 5;
        separation = new int[]{0, 500, 2000, 5000, 20000};
        taxRate = new double[]{0.05, 0.1, 0.15, 0.2, 0.25};
    }

	/**
     * ��ȡӦ��˰���ö�������
     * @return ��������
     */
    public int getRank_Num() {
        return rankNum;
    }

    /**
     * ��ȡ��������˰��������
     * @return ��������
     */
    public int getTax_Threshold() {
        return taxThreshold;
    }

    /**
     * ��ȡָ��������ٽ���
     * @param i ����ʵ�Ӧ��˰���ö��
     * @return ����i��Ӧ���ٽ���
     */
    public int getSeparation(int i) {
        return separation[i];
    }

    /**
     * ��ȡӦ��˰���ö����������ٽ���
     * @return �����ٽ�����ɵ�����
     */
    public int[] getSeparations() {
        return separation;
    }

    /**
     * ��ȡָ�������˰��
     * @param i ����ʵ�Ӧ��˰���ö��
     * @return ����i��Ӧ��˰��
     */
    public double getTax_Rate(int i) {
        return taxRate[i];
    }
    
    /**
     * ��ȡ�������˰��
     * @return ����˰����ɵ�����
     */
    public double[] getTax_Rates() {
        return taxRate;
    }


    /**
     * �޸ĸ������ö���ܼ�����
     * @param newRankNum �µĸ������ö���ܼ�����
     */
    public void setRankNum(int newRankNum) {
        rankNum = newRankNum;
    }

    /**
     * �޸ĸ������ö�������������֤�µ���������ĺϷ��ԣ�������ڵ���0��
     * @param newThreshold �µ���������
     */
    public void setTax_Threshold(int newThreshold) {
        // ��֤�Ϸ���
    	if (newThreshold >= 0) {
			// �Ϸ��������µ���������
			taxThreshold = newThreshold;
		}else{
			//�쳣����
            System.out.println("-------------------------------");
            System.out.println("Error���������������ڵ���0");
            System.out.println("-------------------------------");
            return;
        }
    	
    }

    /**
     * �����µ��ٽ������֤���������С��0
     * @param newSeparation �µ��ٽ�����ɵ�����
     */
    public void setSeparation(int[] newSeparation) {
        for (int s : newSeparation) {
            if (s < 0) {
                System.out.println("-------------------------------");
                System.out.println("Error���ٽ��������ڵ���0");
                System.out.println("-------------------------------");
                return;
            }
        }
        separation = newSeparation;
    }

    /**
     * �����µ�˰�ʣ�����������˰�ʺϷ��ԣ�������[0,1]�У�
     * @param newRate ��˰����ɵ�����
     */
    public void setTaxRate(double[] newRate) {
        for (double r : newRate) {
			// ��֤�Ϸ���
            if (r < 0 || r > 1) {
                System.out.println("-------------------------------");
                System.out.println("Error��˰�ʱ�����������[0,1]��");
                System.out.println("-------------------------------");
                return;
            }
        }
        taxRate = newRate;
    }
    
    /**
     * ��ʾ��ǰ��������˰����취.
     */
    public void printTable() {       
        // ��ӡ˰�ʱ�
        System.out.printf("  ��������˰������Ϊ%dԪ\n", taxThreshold);
        System.out.println("  �����۽�˰�ʣ�");
        for (int i = 0; i < rankNum - 1; i++) {
            System.out.printf("    ����%5dԪ��%5dԪ����˰��Ϊ%3d%%\n", 
            		separation[i], separation[i + 1], (int) (taxRate[i] * 100));
        }
        System.out.printf("    ����%5dԪ��˰��Ϊ%d%%\n", separation[rankNum - 1], 
        		(int) (taxRate[rankNum - 1] * 100));
    }
    
}
