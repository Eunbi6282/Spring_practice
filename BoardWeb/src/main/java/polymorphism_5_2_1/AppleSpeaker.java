package polymorphism_5_2_1;

import org.springframework.stereotype.Component;

//@Component("apple")  //  Speaker 타입과 AppleSpeaker타입 모두 가지고 있다.
public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("==> AppleSpeaker 객체 생성");
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
