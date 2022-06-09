package polymorphism_4_2_1;

public class LGTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("LgTV -- 전원을 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV -- 전원을 끈다.");

	}

	@Override
	public void volumUp() {
		System.out.println("LgTV -- 소리를 올린다.");

	}

	@Override
	public void volumDown() {
		System.out.println("LgTV -- 소리를 줄인다.");

	}

}
