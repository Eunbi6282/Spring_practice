package polymorphism_4_3_1;

public class SonySpeaker implements Speaker {
	
	// �⺻ ������ 
	public SonySpeaker() {
		System.out.println("--> �ҴϽ���Ŀ ��ü ����");
	}
	
	@Override
	public void volumUp() {
		System.out.println("�ҴϽ���Ŀ -- �Ҹ��� �ø���.");
	}

	@Override
	public void volumDown() {
		System.out.println("�ҴϽ���Ŀ -- �Ҹ��� ���δ�.");
	}
}
