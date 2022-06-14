package polymorphism_3_2_4;

public class BeanFactory {
	public Object getBeanTest (String beanName) {
		if (beanName.equals("samsung")) {
			return new SamsungTV();
		}
		if (beanName.equals("lg")) {
			return new LgTV();
		}
		return null;
	}

}
