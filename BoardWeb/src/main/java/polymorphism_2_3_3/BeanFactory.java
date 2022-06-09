package polymorphism_2_3_3;


public class BeanFactory {
	// ��ü�� �����ϴ� Ŭ���� 
	// Bean => ��ü�� ����
	public Object getBean(String beanName) {
		if(beanName.equals("samsung")) {
			return new SamSungTV();
		}
		if (beanName.equals("lg")) {
			return new LGTV();
		}
		if(beanName.equals("sk")) {
			return new SkTV();
		}
		return null;
	}
}
