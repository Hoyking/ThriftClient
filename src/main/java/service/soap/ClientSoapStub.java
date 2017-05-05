package service.soap;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import service.ClientStub;

/** �����-���� ��� ���������� ��������� �������������� � ����������-��������������� web-��������
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class ClientSoapStub implements ClientStub {
	
	private AxisDirectoryStub stub;
	
	/** ����������� ������ */
	public ClientSoapStub(AxisDirectoryStub stub) throws RemoteException {
		this.stub = stub;
		checkConnection();
	}
	
	/** ����� ��� �������� ���������� � �������� */
	private void checkConnection() throws RemoteException {
		AxisDirectoryStub.GetTitles method = new AxisDirectoryStub.GetTitles();
		AxisDirectoryStub.GetTitlesResponse methodResponse = stub.getTitles(method);
		Arrays.asList(methodResponse.get_return());
	}
	
	/** ����� ��� ��������� ������� ������ ���������� ������
	 * 
	 * @return ������ ������ ���������� ������
	 */
	public List<String> getTitles() {
		try {
			AxisDirectoryStub.GetTitles method = new AxisDirectoryStub.GetTitles();
			AxisDirectoryStub.GetTitlesResponse methodResponse = stub.getTitles(method);
			return Arrays.asList(methodResponse.get_return());
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** ����� ��� ��������� ����������� ������
	 * 
	 * @param name ��� ������� ������
	 * @return ���������� ������� ������
	 * @throws AxisDirectoryArticleNotFoundFaultException ����������, ���������� � ������ ������� ����������� � �������������� ������
	 */
	public String getContent(String name) throws AxisDirectoryArticleNotFoundFaultException {
		try {
			AxisDirectoryStub.GetContent method = new AxisDirectoryStub.GetContent();
			method.setName(name);
			AxisDirectoryStub.GetContentResponse methodResponse = stub.getContent(method);
			return methodResponse.get_return();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** ����� ��� �������������� ������
     * 
     * @param oldName ������� ��� ������������� ������
     * @param newName ����� ��� ������������� ������
     * @param value ����� ���������� �������������
     * @throws AxisDirectoryArticleNotFoundFaultException ����������, ���������� � ������ ������� ����������� � �������������� ������
     */
	public void editArticle(String oldName, String newName, String value) throws AxisDirectoryArticleNotFoundFaultException {
		try {
			AxisDirectoryStub.EditArticle method = new AxisDirectoryStub.EditArticle();
			method.setOldName(oldName);
			method.setNewName(newName);
			method.setValue(value);
			stub.editArticle(method);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/** ����� ��� �������� ������
     * 
     * @param name ��� ���������
     * @throws AxisDirectoryArticleNotFoundFaultException ����������, ���������� � ������ ������� ����������� � �������������� ������
     */
	public void deleteArticle(String name) throws AxisDirectoryArticleNotFoundFaultException {
		try {
			AxisDirectoryStub.DeleteArticle method = new AxisDirectoryStub.DeleteArticle();
			method.setName(name);
			stub.deleteArticle(method);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/** ����� ��� �������� ���� ������ */
	public void deleteArticles() {
		try {
			AxisDirectoryStub.DeleteArticles method = new AxisDirectoryStub.DeleteArticles();
			stub.deleteArticles(method);
		} catch (RemoteException e) {
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
			AxisDirectoryStub.CreateArticle method = new AxisDirectoryStub.CreateArticle();
			method.setName(name);
			method.setValue(value);
			stub.createArticle(method);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
