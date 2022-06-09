package polymorphism_4_1_2;

public class SamSungTV implements TV {
	
	private SonySpeaker speaker;

	public SamSungTV() {  // 기본생성자 : 객체 생성시 호출됨
		
		System.out.println("==> SamSungTV객체 생성");
	} 
	
	public void initMethod() {		// 객체 생성 직후에 제일 먼저 호출되는 메서드 
		System.out.println("initMethod 호출 : 객체 초기화 완료");
	}
	
	public void destroyMethod() {	// 객체 종료전 호출되는 메서드
		System.out.println("destroyMethod 호출 : 객체 초기화 완료");
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamSungTV -- 전원을 켠다.");

	}

	@Override
	public void powerOff() {
		System.out.println("SamSungTV -- 전원을 끈다.");

	}

	// volumUp, volumDown => 강결합 상태, 프로그램내에서 객체를 생성할 경우, 새로운 기능의 스피커로 교체할 때 코드를 수정해야 한다. 
		
	@Override
	public void volumUp() {
		speaker = new SonySpeaker();  // SonySpeaker 객체 생성
		speaker.volumUp();		// SonySpeaker 메서드 호출

	}

	@Override
	public void volumDown() {
		speaker = new SonySpeaker();  // SonySpeaker 객체 생성
		speaker.volumDown();

	}

}
