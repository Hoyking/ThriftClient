package service.soap;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import service.ClientStub;

/** Класс-стаб для реализации интерфеса взаимодействия с документно-ориентированным web-сервисом
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class ClientSoapStub implements ClientStub {
	
	private AxisDirectoryStub stub;
	
	/** Конструктор класса */
	public ClientSoapStub(AxisDirectoryStub stub) throws RemoteException {
		this.stub = stub;
		checkConnection();
	}
	
	/** Метод для проверки соединения с сервером */
	private void checkConnection() throws RemoteException {
		AxisDirectoryStub.GetTitles method = new AxisDirectoryStub.GetTitles();
		AxisDirectoryStub.GetTitlesResponse methodResponse = stub.getTitles(method);
		Arrays.asList(methodResponse.get_return());
	}
	
	/** Метод для получения полного списка заголовков статей
	 * 
	 * @return полный список заголовков статей
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
	
	/** Метод для получения содержимого статьи
	 * 
	 * @param name имя искомой статьи
	 * @return содержимое искомой статьи
	 * @throws AxisDirectoryArticleNotFoundFaultException исключение, вызываемое в случае попытки допуститься к несуществующей статье
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

	/** Метод для редактирования статьи
     * 
     * @param oldName текущее имя редактируемой статьи
     * @param newName новое имя редактируемой статьи
     * @param value новое содержимое редактируемой
     * @throws AxisDirectoryArticleNotFoundFaultException исключение, вызываемое в случае попытки допуститься к несуществующей статье
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

	/** Метод для удаления статьи
     * 
     * @param name имя удаляемой
     * @throws AxisDirectoryArticleNotFoundFaultException исключение, вызываемое в случае попытки допуститься к несуществующей статье
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

	/** Метод для удаления всех статей */
	public void deleteArticles() {
		try {
			AxisDirectoryStub.DeleteArticles method = new AxisDirectoryStub.DeleteArticles();
			stub.deleteArticles(method);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/** Метод для создания новой статьи 
     * 
     * @param name имя создаваемой статьи
     * @param value содержимое создаваемой статьи
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
