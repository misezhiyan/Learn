package webservice;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HandlerClient {

	public static void main(String[] args) {

		Log log = LogFactory.getLog(HandlerClient.class);

		// 验证账号密码
		authHandler(log);
		// 记录日志
		// logHandler(log);
	}

	private static void authHandler(Log log) {

		try {
			// 创建ws链接
			String url = "http://localhost:9001/axis/services/Handler";
			Service service = new Service();
			Call call = (Call) service.createCall();
			// 传入返回设置
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("Handler", "publicMethod"));

			// 由于当前ws有认证过程, 需要设置访问权限用户信息
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
			// 创建ws链接
			String url = "http://localhost:9001/axis/services/Handler";
			Service service = new Service();
			Call call = (Call) service.createCall();

			// 传入返回设置
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("Handler", "publicMethod"));

			String result = (String) call.invoke(new Object[] { "Mr.li" });
			log.info(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
