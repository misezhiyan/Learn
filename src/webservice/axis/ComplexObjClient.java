package webservice.axis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.SerializerFactory;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializer;
import org.apache.axis.encoding.ser.BeanSerializerFactory;

import webservice.axis.complex.Address;

//���ݸ��Ӷ���ͻ���
public class ComplexObjClient {

	public static void main(String[] args) {
		// getList();
		getMap();
	}

	private static void getMap() {
		String url = "http://localhost:9001/axis/services/ComplexObjService";
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			// ע����
			QName qn = new QName("urn:ComplexObjService", "Address");
			call.registerTypeMapping(Address.class, qn, (SerializerFactory) new BeanSerializerFactory(Address.class, qn), new BeanDeserializerFactory(Address.class, qn));
			// ע���ڲ���
			QName qnInner = new QName("urn:ComplexObjInnerService", "innerClass");
			call.registerTypeMapping(Address.innerClass.class, qnInner, (SerializerFactory) new BeanSerializerFactory(Address.innerClass.class, qnInner), new BeanDeserializerFactory(Address.innerClass.class, qnInner));

			
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("ComplexObjService", "getAddressMap"));

			// ���÷��ؽ������
			call.setReturnClass(HashMap.class);

			// ���÷��ؽ��
			Object[] params = null;
			HashMap<Integer, Address> map = (HashMap<Integer, Address>) call.invoke(params);

			for (Iterator<Integer> iterator = map.keySet().iterator(); iterator.hasNext();) {
				Integer key = iterator.next();
				Address value = map.get(key);
				System.out.println(key);
				System.out.println(value.getAddress());
				System.out.println(value.getCity());
				System.out.println(value.getCountry());
				System.out.println(value.getList());
				System.out.println(value.getArray()[1]);
				System.out.println(value.getInnerclass().getInnername());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getList() {
		String url = "http://localhost:9001/axis/services/ComplexObjService";
		Service service = new Service();
		try {
			Call call = (Call) service.createCall();
			QName qn = new QName("urn:ComplexObjService", "Address");
			QName qnInner = new QName("urn:ComplexObjInnerService", "innerClass");
			// ע����
			call.registerTypeMapping(Address.class, qn, (SerializerFactory) new BeanSerializerFactory(Address.class, qn), new BeanDeserializerFactory(Address.class, qn));
			// ע���ڲ���
			call.registerTypeMapping(Address.innerClass.class, qnInner, (SerializerFactory) new BeanSerializerFactory(Address.innerClass.class, qnInner), new BeanDeserializerFactory(Address.innerClass.class, qnInner));

			// ���õ���·��
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("ComplexObjService", "getAddressList"));

			// ���÷��ؽ������
			call.setReturnClass(ArrayList.class);

			// ���÷��ؽ��
			Object[] params = null;
			ArrayList<Address> list = (ArrayList<Address>) call.invoke(params);

			for (Address address : list) {
				System.out.println(address.getAddress());
				System.out.println(address.getCity());
				System.out.println(address.getCountry());
				System.out.println(address.getList());
				System.out.println(address.getArray()[1]);
				System.out.println(address.getInnerclass().getInnername());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
