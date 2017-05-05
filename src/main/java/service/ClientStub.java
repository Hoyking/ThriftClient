package service;

import java.util.List;

import service.rpc.ArticleNotFoundException;
import service.soap.AxisDirectoryArticleNotFoundFaultException;

/** Интерфейс определяет методы для взаимодействия с web-сервисом
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public interface ClientStub {
	
	/** Метод для получения содержимого статьи
	 * 
	 * @param name имя искомой статьи
	 * @return содержимое искомой статьи
	 * @throws ArticleNotFoundException исключение, вызываемое в случае попытки допуститься к несуществующей статье (при rpc методе организации web-сервиса)
	 * @throws AxisDirectoryArticleNotFoundFaultException исключение, вызываемое в случае попытки допуститься к несуществующей статье (при документно-ориентированном методе организации web-сервиса)
	 */
	public String getContent(String name) throws ArticleNotFoundException, AxisDirectoryArticleNotFoundFaultException;

	/** Метод для получения полного списка заголовков статей
	 * 
	 * @return полный список заголовков статей
	 */
    public List<String> getTitles();

    /** Метод для редактирования статьи
     * 
     * @param oldName текущее имя редактируемой статьи
     * @param newName новое имя редактируемой статьи
     * @param value новое содержимое редактируемой
     * @throws ArticleNotFoundException исключение, вызываемое в случае попытки допуститься к несуществующей статье (при rpc методе организации web-сервиса)
     * @throws AxisDirectoryArticleNotFoundFaultException исключение, вызываемое в случае попытки допуститься к несуществующей статье (при документно-ориентированном методе организации web-сервиса)
     */
    public void editArticle(String oldName, String newName, String value) throws ArticleNotFoundException, AxisDirectoryArticleNotFoundFaultException;

    /** Метод для удаления статьи
     * 
     * @param name имя удаляемой
     * @throws ArticleNotFoundException исключение, вызываемое в случае попытки допуститься к несуществующей статье (при rpc методе организации web-сервиса)
     * @throws AxisDirectoryArticleNotFoundFaultException исключение, вызываемое в случае попытки допуститься к несуществующей статье (при документно-ориентированном методе организации web-сервиса)
     */
    public void deleteArticle(String name) throws ArticleNotFoundException, AxisDirectoryArticleNotFoundFaultException;

    /** Метод для удаления всех статей */
    public void deleteArticles();

    /** Метод для создания новой статьи 
     * 
     * @param name имя создаваемой статьи
     * @param value содержимое создаваемой статьи
     */
    public void createArticle(String name, String value);
	
}
