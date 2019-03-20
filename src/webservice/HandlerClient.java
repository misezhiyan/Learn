package webservice;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HandlerClient {

	public static void main(String[] args) {

		Log log = LogFactory.getLog(HandlerClient.class);

		// ��֤�˺�����
		authHandler(log);
		// ��¼��־
		// logHandler(log);
	}

	private static void authHandler(Log log) {

		try {
			// ����ws����
			String url = "http://localhost:9001/axis/services/Handler";
			Service service = new Service();
			Call call = (Call) service.createCall();
			// ���뷵������
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("Handler", "publicMethod"));

			// ���ڵ�ǰws����֤����, ��Ҫ���÷���Ȩ���û���Ϣ
			call.getMessageContext().setUsername("admin");
			call.getMessageContext().setPassword("admin");
			String result = (String) call.invoke(new Object[] { "Authen Message" });
			log.info(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void logHandler(Log log) {

		try {
			// ����ws����
			String url = "http://localhost:9001/axis/services/Handler";
			Service service = new Service();
			Call call = (Call) service.createCall();

			// ���뷵������
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("Handler", "publicMethod"));

			String result = (String) call.invoke(new Object[] { "Mr.li" });
			log.info(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
