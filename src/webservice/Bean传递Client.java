package webservice;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import webservice.bean.Bean;

public class Bean传递Client {

	public static void main(String[] args) {
		try {

			// 调用ws的方法, 返回值是javaObject对象, 要求在客户端必须创建java类型
			Bean bean = new Bean();
			bean.setId("1");

			// 创建ws链接
			String url = "http://localhost:9001/axis/services/BeanService";
			Service service = new Service();
			Call call = (Call) service.createCall();

			// 注册javaBean 和server-config.wsdd内容一致
			QName qn = new QName("urn:BeanService", "Bean");
			call.registerTypeMapping(Bean.class, qn, new BeanSerializerFactory(Bean.class, qn),
					new BeanDeserializerFactory(Bean.class, qn));

			// 传入返回设置
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("Bean", "getBean"));
			call.addParameter("ss", qn, ParameterMode.IN);
			call.setReturnType(qn, Bean.class);
			// 接收结果
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
