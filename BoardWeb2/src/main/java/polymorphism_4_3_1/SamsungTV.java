package polymorphism_4_3_1;

public class SamsungTV implements TV {
	
	private Speaker speaker;	// Speaker 객체 변수 생성 
	private int price;				// 가격 
	
	public SamsungTV() {
		System.out.println("==> SamsungTV 객체 생성 ");
	}
	
	public void setSpeaker(Speaker speaker) {
		System.out.println("===> setSpeaker() 호출 ");
		this.speaker = speaker;
	}
	public void setPrice(int price) {
		System.out.println("===> setPrice() 호출");
		this.price = price ; 
	}
	
	
	public void initMethod() {
		System.out.println("객체 초기화 처리 완료 ......");
	}
	public void destroyMethod() {
		System.out.println("객체가 제거되기전에 호출되는 메소드.... ");
	}
	
	public void powerOn() {
		System.out.println("SamsungTV -- 전원을 켠다. (가격 : " + price + " )");
	}	
	public void powerOff() {
		System.out.println("SamsungTV -- 전원을 끈다.");
	}	
	public void volumeUp() {		
		speaker.volumeUp();	
	}
	public void volumeDown() {
		
		speaker.volumeDown();
	}

}
