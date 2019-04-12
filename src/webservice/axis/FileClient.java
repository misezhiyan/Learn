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
			// 在wsdl中注册DataHandler的类型
			call.registerTypeMapping(Handler.class, qname, JAFDataHandlerSerializerFactory.class, JAFDataHandlerDeserializerFactory.class);

		} catch (Exception e) {
			// log.fatal(e);
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void uploadFile() {

		// log.info("开始上传文件");

		init();
		
		try {
			// 指定上传文件名
			// String filename = "上传文件所用文件.txt";
			String filename = "test.txt";
			File file = new File(filename);
			System.out.println(file.exists());
			
			
			DataHandler handler = new DataHandler(new FileDataSource(filename));
			// 指定方法的命名空间
			call.setOperationName(new QName("FileService", "uploadFile"));
			// 设定 uploadFile 的参数的类型
			call.addParameter("s1", qname, ParameterMode.IN);
			call.addParameter("s2", XMLType.XSD_STRING, ParameterMode.IN);

			// 设定返回值类型
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
			// 设置返回值,数组的使用
			call.setReturnType(XMLType.SOAP_ARRAY);
			// 保存文件
			Object[] result = (Object[]) call.invoke(new Object[] { "" });
			for (int i = 0; i < result.length; i++) {
				// log.info("downloadFile:" + result);
				AttachmentPart part = (AttachmentPart) result[i];
				File file = new File(part.getDataHandler().getName());
				// 设置文件的输出流, 保存文件
				// log.info("接收文件:" + file);
			}

		} catch (Exception e) {
			// log.error(e);
		}
	}
}
