package polymorphism_2_3_2;

public interface TV {
	/*
	 * 인터페이스를 사용하면 메서드 이름을 동일하게 사용해서 유지보수를 쉽게할 수 있다. (결합도를 낮춘다.)
	 * 결합도를 낮추는 여러방법중의 하나
	 * 	-- 인터페이스의 메서드를 오버라이딩해서 결합도를 낮춘다.
	 */
	
	// 추상메서드 : 선언만 되고 구현부가 없는 메서드 
	public void powerOn();
	public void powerOff();
	public void volumUp();
	public void volumDown();
}
