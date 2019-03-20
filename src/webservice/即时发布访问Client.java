package webservice;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class ��ʱ��������Client {

	public static void main(String[] args) {
		try {
			// ��������ַ
			String url = "http://localhost:9001/axis/Immediate.jws";
			// ��������
			Service service = new Service();
			// ��������
			Call call = (Call) service.createCall();
			// ָ������
			call.setTargetEndpointAddress(url);
			// ָ������
			call.setOperationName(new QName(url, "test"));
			// ִ��
			String result = (String) call.invoke(new Object[] { "Student", "Teacher" });
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
