package polymorphism_4_3_2;

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
		
		
		
		factory.close();  // �޸𸮿��� ��ü ����
		
		
	
			
		
	}

}
