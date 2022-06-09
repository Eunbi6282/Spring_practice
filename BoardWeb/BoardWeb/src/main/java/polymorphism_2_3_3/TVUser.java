package polymorphism_2_3_3;

public class TVUser {

	public static void main(String[] args) {
		// TVUSerŬ�������� ��ü�� �������� �ʰ� �ܺ� Ŭ�������� ��ü�� ���� �� ������ ��ü�� ������ ��
			// ���յ��� ���ߴ� ���, ���� ������ ���� ���� ���ش�.
		
		// ���յ��� ���ߴ� 2���� ��� 
			// 1. �������̽��� ��� (������ �޼���)
			// 2. ������ ���� Factory Pattern
				// ��ü�� �����ϴ� ������ Ŭ������ �д�.
		
		BeanFactory factory = new BeanFactory();
		
		TV tv = (TV)factory.getBean(args[0]);  // ������ ��ü�� TVŸ������ ��ĳ���� 
		
		tv.powerOn(); // tv�� �޼��� ȣ�� ==> ������ ��ü�� �޼��带 ȣ��
		tv.powerOff();
		tv.volumUp();
		tv.volumDown();
	}

}
