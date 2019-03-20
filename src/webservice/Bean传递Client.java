package webservice;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import webservice.bean.Bean;

public class Bean����Client {

	public static void main(String[] args) {
		try {

			// ����ws�ķ���, ����ֵ��javaObject����, Ҫ���ڿͻ��˱��봴��java����
			Bean bean = new Bean();
			bean.setId("1");

			// ����ws����
			String url = "http://localhost:9001/axis/services/BeanService";
			Service service = new Service();
			Call call = (Call) service.createCall();

			// ע��javaBean ��server-config.wsdd����һ��
			QName qn = new QName("urn:BeanService", "Bean");
			call.registerTypeMapping(Bean.class, qn, new BeanSerializerFactory(Bean.class, qn),
					new BeanDeserializerFactory(Bean.class, qn));

			// ���뷵������
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("Bean", "getBean"));
			call.addParameter("ss", qn, ParameterMode.IN);
			call.setReturnType(qn, Bean.class);
			// ���ս��
			Object result = call.invoke(new Object[] { bean });
			if (null != result) {
				Bean newBean = (Bean) result;
				System.out.println(newBean.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
