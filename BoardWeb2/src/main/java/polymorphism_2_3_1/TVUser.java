package polymorphism_2_3_1;

public class TVUser {

	public static void main(String[] args) {
	
		/*
		SamsungTV tv = new SamsungTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		*/
		
		/* 객체의 수정이 일어날때 결합도가 매우 높다. 
		   유지 보수가 힘들다. */ 
		LgTV tv = new LgTV();
		tv.turnOn();
		tv.soundUp();
		tv.soundDown();
		tv.turnOff();
		
	}

}
