package polymorphism_4_1_2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class TVUser {

	public static void main(String[] args) {
		// TVUSerŬ�������� ��ü�� �������� �ʰ� �ܺ� Ŭ�������� ��ü�� ���� �� ������ ��ü�� ������ ��
			// ���յ��� ���ߴ� ���, ���� ������ ���� ���� ���ش�.
		
		// ���յ��� ���ߴ� 2���� ��� 
			// 1. �������̽��� ��� (������ �޼���)
			// 2. ������ ���� Factory Pattern
				// ��ü�� �����ϴ� ������ Ŭ������ �д�.
			// 3. ������ �����ӿ�ũ���� ��ü�� ���� �� �����ϴ� ���
				// IOC(������ ����) : ��ü (������) ==> ��ü(SpringFrameWork)
				// DI(������ ����) : ��ü�� �ڹ��ڵ� ���ο��� �������� �ʰ� Spring FrameWork���� ��ü�� ���� �� ��ü�� �ڹ��ڵ� ���η� �����Ѵ�.
		
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
				
			// GenericXmlApplicationContext : Bean ���� ������ �ε�
		
		// Spring Container���� ��ü�� ��û : �� 4���� ��ü ��ûû
		TV tv = (TV)factory.getBean("tv");  // ������ ��ü�� TVŸ������ ��ĳ���� 
		
		tv.powerOn(); // tv�� �޼��� ȣ�� ==> ������ ��ü�� �޼��带 ȣ��
		tv.powerOff();
		tv.volumUp();
		tv.volumDown();
		
		
		// Spring Container�κ��� ��ü ��û : �ϳ��� ��ü�� ������ ��û�� �� �ִ�.
		TV tv10 = (TV)factory.getBean("tv");  // ������ ��ü�� TVŸ������ ��ĳ���� 
		TV tv11 = (TV)factory.getBean("tv");
		TV tv12 = (TV)factory.getBean("tv");
		TV tv13 = (TV)factory.getBean("tv");
		
		System.out.println(tv10);
		System.out.println(tv11);
		System.out.println(tv12);
		System.out.println(tv13);
		factory.close();  // �޸𸮿��� ��ü ����
		
		
		System.out.println("=====================================");
		// ������ ��ü�� ������ ��� :�޸��� ���� �ʷ���
			TV tv1 = new SamSungTV();
			TV tv2 = new SamSungTV();
			TV tv3 = new SamSungTV();
			TV tv4 = new SamSungTV();
			
			System.out.println(tv1);
			System.out.println(tv2);
			System.out.println(tv3);
			System.out.println(tv4);
		
			System.out.println("================================");
		// �ּҸ� �����ؼ� ����ϸ� �޸��� ���� �ٿ��� �� �ִ�.
			TV tv5 = new SamSungTV();
			TV tv6 = tv5;  // tv6�� tv5�� �ּҸ� ������
			TV tv7 = tv6;
			
			System.out.println(tv5);
			System.out.println(tv6);
			System.out.println(tv7);
			
		
	}

}
