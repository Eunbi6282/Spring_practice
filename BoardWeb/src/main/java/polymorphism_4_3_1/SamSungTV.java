package polymorphism_4_3_1;

public class SamSungTV implements TV {
	
	private Speaker speaker; // speaker�� �������̽� : SonySpeaker, AppleSpeaker
	private int price; // ���� ���� ����

	public SamSungTV() {  // �⺻������ : ��ü ������ ȣ���
		
		System.out.println("==> SamSungTV��ü ����");
	} 
	
	// SamSungTv �����ڿ� �Ű������� SonySpeaker ��ü�� Spring FrameWork ���� DI (������ ����)
	
	public void setSpeaker(Speaker speaker) {
		System.out.println("===> setSpeaker()ȣ�� : Setterȣ��");
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		System.out.println("===> setPrice()ȣ�� : Setterȣ��");
		this.price = price;
	}

	
	public void initMethod() {		// ��ü ���� ���Ŀ� ���� ���� ȣ��Ǵ� �޼��� 
		System.out.println("initMethod ȣ�� : ��ü �ʱ�ȭ �Ϸ�");
	}
	
	public void destroyMethod() {	// ��ü ������ ȣ��Ǵ� �޼���
		System.out.println("destroyMethod ȣ�� : ��ü �ʱ�ȭ �Ϸ�");
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamSungTV -- ������ �Ҵ�.(����: " + price + " )");

	}

	@Override
	public void powerOff() {
		System.out.println("SamSungTV -- ������ ����.");

	}

	// volumUp, volumDown => ������ ����, ���α׷������� ��ü�� ������ ���, ���ο� ����� ����Ŀ�� ��ü�� �� �ڵ带 �����ؾ� �Ѵ�. 
		
	@Override
	public void volumUp() {
		speaker.volumUp();		// SonySpeaker �޼��� ȣ��

	}

	@Override
	public void volumDown() {
		speaker.volumDown();

	}

}
