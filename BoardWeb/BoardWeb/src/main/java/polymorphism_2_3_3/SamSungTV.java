package polymorphism_2_3_3;

public class SamSungTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("SamSungTV -- 전원을 켠다.");

	}

	@Override
	public void powerOff() {
		System.out.println("SamSungTV -- 전원을 끈다.");

	}

	@Override
	public void volumUp() {
		System.out.println("SamSungTV -- 소리를 올린다.");

	}

	@Override
	public void volumDown() {
		System.out.println("SamSungTV -- 소리를 줄인다.");

	}

}
