package polymorphism_4_3_2;

public class SamSungTV implements TV {
	
	private Speaker speaker; // speaker는 인터페이스 : SonySpeaker, AppleSpeaker
	private int price; // 정수 변수 선언

	public SamSungTV() {  // 기본생성자 : 객체 생성시 호출됨
		
		System.out.println("==> SamSungTV객체 생성");
	} 
	
	// SamSungTv 생성자에 매개변수로 SonySpeaker 객체를 Spring FrameWork 에서 DI (의존성 주입)
	
	// 생성자 : 매개변수가 1개인 생성자
	public SamSungTV(Speaker speaker) {
		System.out.println("==> SamSungTV객체 생성2 : 생성자 객체 주입");
		this.speaker = speaker;
	}
	
	// 생성자 : 매개변수가 2개인 생성자
	public SamSungTV(Speaker speaker , int price) {
		System.out.println("==> SamSungTv객체 생성3 : 매개변수 2개인 생성자 호출");
		this.speaker = speaker;
		this.price = price;
	}
	
	public void initMethod() {		// 객체 생성 직후에 제일 먼저 호출되는 메서드 
		System.out.println("initMethod 호출 : 객체 초기화 완료");
	}
	
	public void destroyMethod() {	// 객체 종료전 호출되는 메서드
		System.out.println("destroyMethod 호출 : 객체 초기화 완료");
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamSungTV -- 전원을 켠다.(가격: " + price + " )");

	}

	@Override
	public void powerOff() {
		System.out.println("SamSungTV -- 전원을 끈다.");

	}

	// volumUp, volumDown => 강결합 상태, 프로그램내에서 객체를 생성할 경우, 새로운 기능의 스피커로 교체할 때 코드를 수정해야 한다. 
		
	@Override
	public void volumUp() {
		speaker.volumUp();		// SonySpeaker 메서드 호출

	}

	@Override
	public void volumDown() {
		speaker.volumDown();

	}

}
