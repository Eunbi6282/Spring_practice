package polymorphism_5_2_1;

import org.springframework.stereotype.Component;

//@Component("apple")  //  Speaker Ÿ�԰� AppleSpeakerŸ�� ��� ������ �ִ�.
public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("==> AppleSpeaker ��ü ����");
	}
	
	@Override
	public void volumUp() {
		System.out.println("AppleSpeaker = volumUp");
	}

	@Override
	public void volumDown() {
		System.out.println("AppleSpeaker = volumDown");

	}

}
