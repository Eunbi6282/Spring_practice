package polymorphism_2_3_2;

public class TVUser {

	public static void main(String[] args) {
		// (1) SamSungTV tv = new SamSungTV();
		// (2) LGTV tv = new LGTV();
		
		// (3) TV tv = new SamSungTV(); // ��ĳ���� (������ ����)
		TV tv = new LGTV();  
		
		tv.powerOn();
		tv.powerOff();
		tv.volumUp();
		tv.volumDown();
	}
	// �������̽��� ����ϸ� ������� ���� ���������� ���� �Ѵ�.
}
