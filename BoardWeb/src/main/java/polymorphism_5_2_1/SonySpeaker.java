package polymorphism_5_2_1;

import org.springframework.stereotype.Component;

//@Component("sony")
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
