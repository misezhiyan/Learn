package webservice;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class 定制发布访问Client {

	public static void main(String[] args) {
		try {
			// 定义服务地址
			String url = "http://localhost:9001/axis/services/WSDD";
			// 创建服务
			Service service = new Service();
			// 创建调用
			Call call = (Call) service.createCall();
			// 指定服务
			call.setTargetEndpointAddress(url);
			// 测试方法
			// 指定方法
			call.setOperationName(new QName(url, "test"));
			// 执行
			String result = (String) call.invoke(new Object[] { "Student" });
			System.out.println(result);

			// 加法方法
			// 指定方法
			call.setOperationName(new QName(url, "add"));
			// 执行
			Float addResult = (Float) call.invoke(new Object[] { new Float(3.2), new Float(2.9) });
			System.out.println(addResult);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
