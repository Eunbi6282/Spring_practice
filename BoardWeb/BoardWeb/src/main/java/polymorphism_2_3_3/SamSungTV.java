package polymorphism_2_3_3;

public class SamSungTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("SamSungTV -- ������ �Ҵ�.");

	}

	@Override
	public void powerOff() {
		System.out.println("SamSungTV -- ������ ����.");

	}

	@Override
	public void volumUp() {
		System.out.println("SamSungTV -- �Ҹ��� �ø���.");

	}

	@Override
	public void volumDown() {
		System.out.println("SamSungTV -- �Ҹ��� ���δ�.");

	}

}
