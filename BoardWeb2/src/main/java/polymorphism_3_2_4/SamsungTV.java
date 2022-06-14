package polymorphism_3_2_4;

public class SamsungTV implements TV {
	
	public SamsungTV() {
		System.out.println("==> SamsungTV 객체 생성 ");
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
		System.out.println("SamsungTV -- 소리를 올린다.");
	}
	public void volumeDown() {
		System.out.println("SamsungTV -- 소리를 내린다.");
	}

}
