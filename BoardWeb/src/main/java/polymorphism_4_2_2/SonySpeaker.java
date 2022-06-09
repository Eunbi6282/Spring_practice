package polymorphism_4_2_2;

public class SonySpeaker implements Speaker {
	
	// 기본 생성자 
	public SonySpeaker() {
		System.out.println("--> 소니스피커 객체 생성");
	}
	
	@Override
	public void volumUp() {
		System.out.println("소니스피커 -- 소리를 올린다.");
	}

	@Override
	public void volumDown() {
		System.out.println("소니스피커 -- 소리를 줄인다.");
	}
}
