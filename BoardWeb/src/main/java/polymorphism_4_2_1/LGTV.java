package polymorphism_4_2_1;

public class LGTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("LgTV -- ������ �Ҵ�.");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV -- ������ ����.");

	}

	@Override
	public void volumUp() {
		System.out.println("LgTV -- �Ҹ��� �ø���.");

	}

	@Override
	public void volumDown() {
		System.out.println("LgTV -- �Ҹ��� ���δ�.");

	}

}
