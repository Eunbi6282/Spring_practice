package polymorphism_4_3_1;

public class SkTV implements TV{
	@Override
	public void powerOn() {
		System.out.println("SkTV -- 전원을 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("SkTV -- 전원을 끈다.");

	}

	@Override
	public void volumUp() {
		System.out.println("SkTV -- 소리를 올린다.");

	}

	@Override
	public void volumDown() {
		System.out.println("SkTV -- 소리를 줄인다.");

	}

}
