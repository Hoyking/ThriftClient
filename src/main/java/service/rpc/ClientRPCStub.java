package service.rpc;

import java.util.List;

import org.apache.thrift.TException;

import service.ClientStub;

/** �����-���� ��� ���������� ��������� �������������� � rpc web-��������
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class ClientRPCStub implements ClientStub {

	ArticleService.Client client;
	
	/** ����������� ������ */
	public ClientRPCStub(ArticleService.Client client) throws TException {
		this.client = client;
		checkConnection();
	}
	
	/** ����� ��� �������� ���������� � �������� */
	private void checkConnection() throws TException {
		try {
			client.getContent("");
		} catch(ArticleNotFoundException e) {
			return;
		}
	}
	
	/** ����� ��� ��������� ������� ������ ���������� ������
	 * 
	 * @return ������ ������ ���������� ������
	 */
	public List<String> getTitles() {
		try {
			return client.getTitles();
		} catch (TException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** ����� ��� ��������� ����������� ������
	 * 
	 * @param name ��� ������� ������
	 * @return ���������� ������� ������
	 * @throws ArticleNotFoundException ����������, ���������� � ������ ������� ����������� � �������������� ������
	 */
	public String getContent(String name) throws ArticleNotFoundException {
		try {
			return client.getContent(name);
		} catch (TException e) {
			throw new ArticleNotFoundException();
		}
	}

	/** ����� ��� �������������� ������
     * 
     * @param oldName ������� ��� ������������� ������
     * @param newName ����� ��� ������������� ������
     * @param value ����� ���������� �������������
     * @throws ArticleNotFoundException ����������, ���������� � ������ ������� ����������� � �������������� ������
     */
	public void editArticle(String oldName, String newName, String value) throws ArticleNotFoundException {
		try {
			client.editArticle(oldName, newName, value);
		} catch (TException e) {
			throw new ArticleNotFoundException();
		}
	}

	/** ����� ��� �������� ������
     * 
     * @param name ��� ���������
     * @throws ArticleNotFoundException ����������, ���������� � ������ ������� ����������� � �������������� ������
     */
	public void deleteArticle(String name) throws ArticleNotFoundException {
		try {
			client.deleteArticle(name);
		} catch (TException e) {
			throw new ArticleNotFoundException();
		}
	}

	/** ����� ��� �������� ���� ������ */
	public void deleteArticles() {
		try {
			client.deleteArticles();
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	/** ����� ��� �������� ����� ������ 
     * 
     * @param name ��� ����������� ������
     * @param value ���������� ����������� ������
     */
	public void createArticle(String name, String value) {
		try {
			client.createArticle(name, value);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

}

