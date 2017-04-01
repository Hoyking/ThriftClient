package controller;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import service.ArticleService;

public class ConnectionController {

	private static ArticleService.Client client;
	private static TTransport transport;
	
	public ConnectionController() {
		connect();
	}
	
	private void connect() {
		try {
			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ArticleService.Client client = new ArticleService.Client(protocol);
			
			setClient(client);
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
	
	private static void setClient(ArticleService.Client client) {
		ConnectionController.client = client;
	}
	
	public static ArticleService.Client getClient() {
		return client;
	}
	
	public static void closeConnection() {
		transport.close();
	}

}
