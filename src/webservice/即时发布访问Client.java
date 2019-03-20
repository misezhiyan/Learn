package webservice;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class 即时发布访问Client {

	public static void main(String[] args) {
		try {
			// 定义服务地址
			String url = "http://localhost:9001/axis/Immediate.jws";
			// 创建服务
			Service service = new Service();
			// 创建调用
			Call call = (Call) service.createCall();
			// 指定服务
			call.setTargetEndpointAddress(url);
			// 指定方法
			call.setOperationName(new QName(url, "test"));
			// 执行
			String result = (String) call.invoke(new Object[] { "Student", "Teacher" });
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
