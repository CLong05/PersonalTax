package calculatePersonalTax;

import java.util.Scanner;

/**
 * �������棬�����û�������I/O����
 * @author ���� 19335019
 */
public class Presentation {
    /**
     * main����
     * ��ʾ����ָ����Ϣͬʱ��ӡ˰�ʱ�
     * @param args �������������õĲ���
     */
    public static void main(String[] args) {
    	System.out.println("-------------------------------");
        System.out.println("< ��ӭʹ�ø�������˰�������>");
        System.out.println("-------------------------------");
        System.out.println("��ǰ��������˰����취��");
        TaxTable taxtable = new TaxTable();             /* ������˰�����Ķ���ʵ�� */
        taxtable.printTable();                          /* ��ӡĬ�ϼ�˰���� */
        System.out.println("-------------------------------");
        executeComand(getComand(), taxtable);               /* ��ȡ���룬ѡ���Ӧ���� */
    }

    /**
     * ��ʾ����ָ��������ȡ�û�ָ��
     * @return �û������ָ����
     */
    static int getComand() {
    	Scanner input = new Scanner(System.in);
        System.out.println("����ָ��\n  ������ָ������ʵ�ֶ�Ӧ���ܣ�");
        System.out.println("  ָ����1--�����������˰\n  "
        		+ "ָ����2--�޸ĸ�������˰������\n  "
        		+ "ָ����3--�޸ĸ�������˰���ٽ�����˰��\n  "
        		+ "ָ����4--�˳�����");
        System.out.print("������ָ���ţ�"); 
        if(input.hasNextInt()){
        	int comand = input.nextInt();
            return comand;
        }else {
           /*
            * �쳣����
         	*	��Ϊ��ȷ��ʽ��ָ���ţ���1-4�����֣������ȡָ��
         	*	��Ϊ�Ƿ����룬���ᱨ����ʾ������Ϣ
         	*/
        	System.out.println("-------------------------------");
            System.out.println("Error����������ȷ��ʽ��ָ����");
            System.out.println("-------------------------------");
            return getComand();
        }
    }

    /**
     * ִ��ָ��1�������������˰
     * @param taxtable   ��ǰ��������˰�ʱ�
     */
    static void executeComand1(TaxTable taxtable) {
    	Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.print("����ʹ�ø�������˰�������...\n"
        		+ "���������������ܶ");
        TaxCalculator taxCalculator = new TaxCalculator();                     /* ���������� */
        double tax = taxCalculator.taxCalculate(input.nextInt(), taxtable);    /* ��������˰ */
        if(tax!=-1) {
           /*
            * �쳣����
        	*	��Ϊ�Ϸ�����ֵ������������ڵ���0�������������ĸ�������˰
        	*	��Ϊ�Ƿ����룬TaxCalculator���ᱨ����ʾ������Ϣ
        	*/
        	System.out.println("���ĸ�������˰Ϊ " + String.format("%.2f", tax) + "Ԫ");
        	}
        System.out.println("-------------------------------");
    }
    
    /**
     * ִ��ָ��2:�޸ĸ�������˰������
     * @param taxtable   ��ǰ��������˰�ʱ�
     */
    static void executeComand2(TaxTable taxtable) {
    	Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("�����޸ĸ�������˰������...");
        System.out.print("Ŀǰ��������Ϊ��" + taxtable.getTax_Threshold() + "Ԫ���������µ������㣺");
        int _newThreshold=input.nextInt();
        /*
         * �쳣����
         * �ж��Ƿ�Ϊ�Ϸ�����
         * 	��Ϊ�Ϸ�����ֵ������������ڵ���0�������޸�����������ӡ�µĸ�������˰����취
         * 	��Ϊ�Ƿ����룬taxtable.setTax_Threshold���ᱨ����ʾ������Ϣ
         */
        taxtable.setTax_Threshold(_newThreshold);
        if (_newThreshold > 0) {
        	System.out.println("�޸ĳɹ����µĸ�������˰����취���£�");
        	taxtable.printTable();
        	System.out.println("-------------------------------");
        	}
    	
    }
    
    /**
     * ִ��ָ��3���޸ĸ�������˰�����ٽ�����˰��
     * �޸ĳɹ�����True,���򷵻�False
     * @param taxtable   ��ǰ��������˰�ʱ�
     */
    static boolean executeComand3(TaxTable taxtable) {
    	Scanner input = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("�����������������˰���ٽ����һ���ٽ���Ϊ0�����ٽ�����Կո�ֿ������س���������");                    
        String[] inputArray = input.nextLine().split(" "); 		/* ��ȡ�ٽ��� */
        /*
         * �쳣����
         * 	�ж��ٽ����Ƿ���Ϲ淶,���������򱨴�Ҫ����������
         */
        while (!inputArray[0].equals("0")) {
			System.out.println("---------------------------------------------");
            System.out.println("Error����һ���ٽ������Ϊ0������������");
			System.out.println("---------------------------------------------");
            inputArray = input.nextLine().split(" ");
        }
        boolean Separation_valid=true; 			/* ��¼�ٽ����Ƿ�Ϸ� */
        boolean Rate_valid=true;	  			/* ��¼����˰���Ƿ�Ϸ� */
        int[] newSeparation = new int[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            newSeparation[i] = Integer.parseInt(inputArray[i]);
            /*
             * �쳣����
             * 	�ж��ٽ����Ƿ��ϸ񵥵�����
             * 	���������򱨴����س�ʼ�˵�
             */
            if(i>0 && newSeparation[i]<=newSeparation[i-1]) {
            	Separation_valid=false;
				System.out.println("---------------------------------------------");
            	System.out.println("Error������ĸ����ٽ�������ϸ񵥵�����");
            	System.out.println("---------------------------------------------");
            	break;
            }
        } 
        if(Separation_valid==false) return false;				/* �����벻�Ϸ����˳� */
        
        System.out.println("�����������˰�ʣ�����С����ʽ���룬��˰�ʼ��Կո�ֿ������س�������");
        inputArray = input.nextLine().split(" ");		/* ��ȡ����˰�� */
        double[] newTaxRate = new double[inputArray.length];
        for (int i = 0; i < inputArray.length; i++) {
            newTaxRate[i] = Double.parseDouble(inputArray[i]);
            
            /*
             * �쳣����
             * �ж�˰���Ƿ���������[0,1]��
             * ���������򱨴����س�ʼ�˵�
             */
            if(newTaxRate[i]<0 || newTaxRate[i]>1) {
            	Rate_valid=false;
				System.out.println("---------------------------------------------");
            	System.out.println("Error�����ڷǷ�˰�ʣ�˰�ʱ�����������[0,1]�У�");
            	System.out.println("---------------------------------------------");
            	break;
            }
        }                   
        if(Rate_valid==false) return false;				/* �����벻�Ϸ����˳� */
        
        /*
         * �쳣����
         * 	�����ٽ�������˰�ʼ������Ƿ����
         * 	����������˳�
         */
        if(newSeparation.length!=newTaxRate.length) {
			System.out.println("---------------------------------------------");
        	System.out.println("Error���ٽ���������˰�ʼ������������");
        	System.out.println("---------------------------------------------");
        	return false;
        }                    

        //��������ֵ���Ϸ������޸�TaxTable
        taxtable.setRankNum(inputArray.length);
        taxtable.setSeparation(newSeparation);
        taxtable.setTaxRate(newTaxRate);
        System.out.print("�޸ĳɹ����µĸ�������˰����취���£�");
        taxtable.printTable();
        System.out.println("-------------------------------");
        return true;
    }
    
    /**
     * ����ָ����ִ�ж�Ӧ�Ĺ���
     * @param comandNumber �û������ָ����
     * @param taxtable   ��ǰ��������˰�ʱ�
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
                	 * �쳣����
                     * �ж������ָ�����Ƿ�Ϸ�
                     * �������ڸ�ָ�����򱨴����س�ʼ�˵�
                     */
                    System.out.println("------------------------------------");
                    System.out.println("Error����ָ���Ų����ڣ�����������");
                    System.out.println("------------------------------------");
            }
            comandNumber = getComand();
        }
        System.out.print("�����ѹرգ���ӭ���ٴ�ʹ��");
        input.close();
    }
}
