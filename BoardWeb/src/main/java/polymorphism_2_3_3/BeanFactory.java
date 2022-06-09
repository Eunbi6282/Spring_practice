package polymorphism_2_3_3;


public class BeanFactory {
	// 객체를 생성하는 클래스 
	// Bean => 객체를 뜻함
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
