package webservice.axis;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChainClient {

	public static void main(String[] args) throws ServiceException, RemoteException {

		Log log = LogFactory.getLog(ChainClient.class);

		String url = "http://localhost:9001/axis/services/Chain";
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(url);
		call.setOperationName(new QName(url, "hello"));
		String result = (String) call.invoke(new Object[] { "kimmy" });
		System.out.println(result);
	}
}
