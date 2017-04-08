package controller;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import service.ClientStub;
import service.rpc.ArticleNotFoundException;
import service.rpc.ArticleService;
import service.soap.AxisDirectoryStub;
import service.soap.ClientSoapStub;

public class ConnectionController {

	private static ArticleService.Client client;
	private static TTransport transport;
	private static ClientStub clientStub;
	
	public ConnectionController() {
		//connect();
		soapConnection();
	}
	
	private void connect() {
		/*try {
			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ArticleService.Client client = new ArticleService.Client(protocol);
			
			setClient(client);
		} catch (TTransportException e) {
			e.printStackTrace();
		}*/
	}
	
	private void soapConnection() {
		try {
			ClientStub clientStub = new ClientSoapStub(new AxisDirectoryStub());
			setClientStub(clientStub);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		
	}
	
	private static void setClientStub(ClientStub clientStubArg) {
		clientStub = clientStubArg;
	}
	
	public static ClientStub getClientStub() {
		return clientStub;
	}
	
	public static void closeConnection() {
		transport.close();
	}

}
