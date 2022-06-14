package polymorphism_4_3_2;

public class SonySpeaker implements Speaker {
	
	public SonySpeaker() {
		System.out.println("SonySpeaker 객체 생성....");
	}
	public void volumeUp() {
		System.out.println("SonySpeaker - 소리를 올린다. ");
	}
	public void volumeDown() {
		System.out.println("SonySpeaker - 소리를 내린다. ");
	}

}
