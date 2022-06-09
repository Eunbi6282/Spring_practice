package polymorphism_4_2;

public class SamSungTV implements TV {
	
	private SonySpeaker speaker;

	public SamSungTV() {  // �⺻������ : ��ü ������ ȣ���
		
		System.out.println("==> SamSungTV��ü ����");
	} 
	
	// SamSungTv �����ڿ� �Ű������� SonySpeaker ��ü�� Spring FrameWork ���� DI (������ ����)
	public SamSungTV(SonySpeaker speaker) {
		System.out.println("==> SamSungTV��ü ����2 : ������ ��ü ����");
		this.speaker = speaker;
	}
	
	public void initMethod() {		// ��ü ���� ���Ŀ� ���� ���� ȣ��Ǵ� �޼��� 
		System.out.println("initMethod ȣ�� : ��ü �ʱ�ȭ �Ϸ�");
	}
	
	public void destroyMethod() {	// ��ü ������ ȣ��Ǵ� �޼���
		System.out.println("destroyMethod ȣ�� : ��ü �ʱ�ȭ �Ϸ�");
	}
	
	@Override
	public void powerOn() {
		System.out.println("SamSungTV -- ������ �Ҵ�.");

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
