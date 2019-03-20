package webservice;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class ���Ʒ�������Client {

	public static void main(String[] args) {
		try {
			// ��������ַ
			String url = "http://localhost:9001/axis/services/WSDD";
			// ��������
			Service service = new Service();
			// ��������
			Call call = (Call) service.createCall();
			// ָ������
			call.setTargetEndpointAddress(url);
			// ���Է���
			// ָ������
			call.setOperationName(new QName(url, "test"));
			// ִ��
			String result = (String) call.invoke(new Object[] { "Student" });
			System.out.println(result);

			// �ӷ�����
			// ָ������
			call.setOperationName(new QName(url, "add"));
			// ִ��
			Float addResult = (Float) call.invoke(new Object[] { new Float(3.2), new Float(2.9) });
			System.out.println(addResult);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
