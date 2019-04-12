package webservice.axis;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.Handler;
import org.apache.axis.attachments.AttachmentPart;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.encoding.ser.JAFDataHandlerDeserializerFactory;
import org.apache.axis.encoding.ser.JAFDataHandlerSerializerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileClient {

	// static Log log = LogFactory.getLog(FileClient.class);

	static String url = "http://localhost:9001/axis/services/FileService";
	static QName qname = new QName("FileService", "DataHandler");
	static Call call = null;

	public static void main(String[] args) {

		FileClient client = new FileClient();
		client.uploadFile();
		// downloadFile();

	}

	private void init() {
		try {

			Service service = new Service();
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			// ��wsdl��ע��DataHandler������
			call.registerTypeMapping(Handler.class, qname, JAFDataHandlerSerializerFactory.class, JAFDataHandlerDeserializerFactory.class);

		} catch (Exception e) {
			// log.fatal(e);
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void uploadFile() {

		// log.info("��ʼ�ϴ��ļ�");

		init();
		
		try {
			// ָ���ϴ��ļ���
			// String filename = "�ϴ��ļ������ļ�.txt";
			String filename = "test.txt";
			File file = new File(filename);
			System.out.println(file.exists());
			
			
			DataHandler handler = new DataHandler(new FileDataSource(filename));
			// ָ�������������ռ�
			call.setOperationName(new QName("FileService", "uploadFile"));
			// �趨 uploadFile �Ĳ���������
			call.addParameter("s1", qname, ParameterMode.IN);
			call.addParameter("s2", XMLType.XSD_STRING, ParameterMode.IN);

			// �趨����ֵ����
			call.setReturnType(XMLType.XSD_STRING);

			String result = (String) call.invoke(new Object[] { handler, filename });
			System.out.println(result);
			// log.info(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void downloadFile() {

		try {
			call.setOperationName(new QName(url, "downLoadFiles"));
			call.addParameter("name", XMLType.XSD_STRING, ParameterMode.IN);
			// ���÷���ֵ,�����ʹ��
			call.setReturnType(XMLType.SOAP_ARRAY);
			// �����ļ�
			Object[] result = (Object[]) call.invoke(new Object[] { "" });
			for (int i = 0; i < result.length; i++) {
				// log.info("downloadFile:" + result);
				AttachmentPart part = (AttachmentPart) result[i];
				File file = new File(part.getDataHandler().getName());
				// �����ļ��������, �����ļ�
				// log.info("�����ļ�:" + file);
			}

		} catch (Exception e) {
			// log.error(e);
		}
	}
}
