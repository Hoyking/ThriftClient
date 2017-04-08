package service.rpc;

import java.util.List;

import org.apache.thrift.TException;

import service.ClientStub;

public class ClientRPCStub implements ClientStub {

	ArticleService.Client client;
	
	public ClientRPCStub(ArticleService.Client client) {
		this.client = client;
	}
	
	public List<String> getTitles() {
		try {
			return client.getTitles();
		} catch (TException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getContent(String name) throws ArticleNotFoundException {
		try {
			return client.getContent(name);
		} catch (TException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void editArticle(String name, String value) throws ArticleNotFoundException {
		try {
			client.editArticle(name, value);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	public void deleteArticle(String name) throws ArticleNotFoundException {
		try {
			client.deleteArticle(name);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	public void deleteArticles() {
		try {
			client.deleteArticles();
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	public void createArticle(String name, String value) {
		try {
			client.createArticle(name, value);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

}
