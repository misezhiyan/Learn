package webservice.bean;

//�Զ�����
public class BeanService {

	public Bean getBean(Bean bean) {

		Bean newBean = new Bean();

		if ("1".equals(bean.getId())) {
			newBean.setName("��1");
		} else {
			newBean.setName("����1");
		}

		return new Bean();
	}

}
