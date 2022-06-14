package polymorphism_4_1_2;

public interface TV {
	public void powerOn();
	public void powerOff();
	public void volumeUp();
	public void volumeDown();
	/* 인터페이스를 사용하면 메소드 이름을 동일하게 사용해서 유지보수를 쉽게
	 * 할수 있다. ( 결합도를 낮춘다. )
	 * 결합도는 낮추는 여러 방법중의 하나. 
	 *   - 인터페이스의 메소드 오버라이딩을 통해 결합도를 낮춘다. 
	 */

}
