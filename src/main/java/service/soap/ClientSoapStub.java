package service.soap;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.axis2.AxisFault;
import org.apache.thrift.TException;

import service.ClientStub;
import service.rpc.ArticleNotFoundException;
import service.rpc.ArticleService;

public class ClientSoapStub implements ClientStub {
	
	private AxisDirectoryStub stub;
	
	public ClientSoapStub(AxisDirectoryStub stub) {
		this.stub = stub;
	}
	
	public List<String> getTitles() {
		try {
			AxisDirectoryStub.GetTitles method = new AxisDirectoryStub.GetTitles();
			AxisDirectoryStub.GetTitlesResponse methodResponse = stub.getTitles(method);
			return Arrays.asList(methodResponse.get_return());
		} catch (AxisFault e) {
			e.printStackTrace();
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getContent(String name) throws ArticleNotFoundException {
		try {
			AxisDirectoryStub.GetContent method = new AxisDirectoryStub.GetContent();
			method.setName(name);
			AxisDirectoryStub.GetContentResponse methodResponse = stub.getContent(method);
			return methodResponse.get_return();
		} catch (AxisFault e) {
			e.printStackTrace();
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void editArticle(String name, String value) throws ArticleNotFoundException {
		try {
			AxisDirectoryStub.EditArticle method = new AxisDirectoryStub.EditArticle();
			method.setName(name);
			method.setValue(value);
			stub.editArticle(method);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void deleteArticle(String name) throws ArticleNotFoundException {
		try {
			AxisDirectoryStub.DeleteArticle method = new AxisDirectoryStub.DeleteArticle();
			method.setName(name);
			stub.deleteArticle(method);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void deleteArticles() {
		try {
			AxisDirectoryStub.DeleteArticles method = new AxisDirectoryStub.DeleteArticles();
			stub.deleteArticles(method);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void createArticle(String name, String value) {
		try {
			AxisDirectoryStub.CreateArticle method = new AxisDirectoryStub.CreateArticle();
			method.setName(name);
			method.setValue(value);
			stub.createArticle(method);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
