package polymorphism_4_2;

public class SamsungTV implements TV {
	
	private SonySpeaker speaker;	// SonySpeaker 객체 변수 생성 
	
	public SamsungTV() {
		System.out.println("==> SamsungTV 객체 생성 ");
	}
	
	public SamsungTV(SonySpeaker speaker) { 
		System.out.println("===> SamsungTV(2) 객체 생성");
		this.speaker = speaker;
		
	}
	
	public void initMethod() {
		System.out.println("객체 초기화 처리 완료 ......");
	}
	public void destroyMethod() {
		System.out.println("객체가 제거되기전에 호출되는 메소드.... ");
	}
	
	public void powerOn() {
		System.out.println("SamsungTV -- 전원을 켠다.");
	}	
	public void powerOff() {
		System.out.println("SamsungTV -- 전원을 끈다.");
	}	
	public void volumeUp() {
		
		speaker.volumUp();
		
	}
	public void volumeDown() {
		
		speaker.volumDown();
	}

}
