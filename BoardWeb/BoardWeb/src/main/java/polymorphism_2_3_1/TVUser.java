package polymorphism_2_3_1;

public class TVUser {

	public static void main(String[] args) {
		/*
		SamSungTV tv = new SamSungTV();  // 강결합 상태 (개발자가 직접 프로그램 코드내에서 객체 생성)
		
		tv.powerOn();
		tv.powerOff();
		tv.volumUp();
		tv.volumDown();
		*/
		
		// 사용자의 요구로 인해서 SamSungTv에서 LGTV로 변경
			// 객체 생성, 메서드 이름 모두 변경
		LgTV tv = new LgTV();
		tv.turnOn();
		tv.turnOff();
		tv.soundUp();
		tv.soundDown();
	}

}
