package polymorphism_5_1_1;

import org.springframework.stereotype.Component;

//@Component("tv")
public class SkTV implements TV{
	@Override
	public void powerOn() {
		System.out.println("SkTV -- ������ �Ҵ�.");
	}

	@Override
	public void powerOff() {
		System.out.println("SkTV -- ������ ����.");

	}

	@Override
	public void volumUp() {
		System.out.println("SkTV -- �Ҹ��� �ø���.");

	}

	@Override
	public void volumDown() {
		System.out.println("SkTV -- �Ҹ��� ���δ�.");

	}

}
