package polymorphism_2_3_1;

public class TVUser {

	public static void main(String[] args) {
		/*
		SamSungTV tv = new SamSungTV();  // ������ ���� (�����ڰ� ���� ���α׷� �ڵ峻���� ��ü ����)
		
		tv.powerOn();
		tv.powerOff();
		tv.volumUp();
		tv.volumDown();
		*/
		
		// ������� �䱸�� ���ؼ� SamSungTv���� LGTV�� ����
			// ��ü ����, �޼��� �̸� ��� ����
		LgTV tv = new LgTV();
		tv.turnOn();
		tv.turnOff();
		tv.soundUp();
		tv.soundDown();
	}

}
