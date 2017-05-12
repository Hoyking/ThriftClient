package service.rpc;

import java.util.List;

import org.apache.thrift.TException;

import service.ClientStub;

/** Класс-стаб для реализации интерфеса взаимодействия с rpc web-сервисом
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class ClientRPCStub implements ClientStub {

	ArticleService.Client client;
	
	/** Конструктор класса */
	public ClientRPCStub(ArticleService.Client client) throws TException {
		this.client = client;
		checkConnection();
	}
	
	/** Метод для проверки соединения с сервером */
	private void checkConnection() throws TException {
		try {
			client.getContent("");
		} catch(ArticleNotFoundException e) {
			return;
		}
	}
	
	/** Метод для получения полного списка заголовков статей
	 * 
	 * @return полный список заголовков статей
	 */
	public List<String> getTitles() {
		try {
			return client.getTitles();
		} catch (TException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** Метод для получения содержимого статьи
	 * 
	 * @param name имя искомой статьи
	 * @return содержимое искомой статьи
	 * @throws ArticleNotFoundException исключение, вызываемое в случае попытки допуститься к несуществующей статье
	 */
	public String getContent(String name) throws ArticleNotFoundException {
		try {
			return client.getContent(name);
		} catch (TException e) {
			throw new ArticleNotFoundException();
		}
	}

	/** Метод для редактирования статьи
     * 
     * @param oldName текущее имя редактируемой статьи
     * @param newName новое имя редактируемой статьи
     * @param value новое содержимое редактируемой
     * @throws ArticleNotFoundException исключение, вызываемое в случае попытки допуститься к несуществующей статье
     */
	public void editArticle(String oldName, String newName, String value) throws ArticleNotFoundException {
		try {
			client.editArticle(oldName, newName, value);
		} catch (TException e) {
			throw new ArticleNotFoundException();
		}
	}

	/** Метод для удаления статьи
     * 
     * @param name имя удаляемой
     * @throws ArticleNotFoundException исключение, вызываемое в случае попытки допуститься к несуществующей статье
     */
	public void deleteArticle(String name) throws ArticleNotFoundException {
		try {
			client.deleteArticle(name);
		} catch (TException e) {
			throw new ArticleNotFoundException();
		}
	}

	/** Метод для удаления всех статей */
	public void deleteArticles() {
		try {
			client.deleteArticles();
		} catch (TException e) {
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
			client.createArticle(name, value);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

}

