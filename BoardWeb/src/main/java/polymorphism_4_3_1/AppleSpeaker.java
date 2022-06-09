package polymorphism_4_3_1;

public class AppleSpeaker implements Speaker {

	public AppleSpeaker() {
		System.out.println("==> AppleSpeaker °´Ã¼ »ý¼º");
	}
	
	@Override
	public void volumUp() {
		System.out.println("AppleSpeaker = volumUp");
	}

	@Override
	public void volumDown() {
		System.out.println("AppleSpeaker = volumDown");

	}

}
