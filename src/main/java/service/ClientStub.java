package service;

import java.util.List;

import service.rpc.ArticleNotFoundException;
import service.soap.AxisDirectoryArticleNotFoundFaultException;

/** ��������� ���������� ������ ��� �������������� � web-��������
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public interface ClientStub {
	
	/** ����� ��� ��������� ����������� ������
	 * 
	 * @param name ��� ������� ������
	 * @return ���������� ������� ������
	 * @throws ArticleNotFoundException ����������, ���������� � ������ ������� ����������� � �������������� ������ (��� rpc ������ ����������� web-�������)
	 * @throws AxisDirectoryArticleNotFoundFaultException ����������, ���������� � ������ ������� ����������� � �������������� ������ (��� ����������-��������������� ������ ����������� web-�������)
	 */
	public String getContent(String name) throws ArticleNotFoundException, AxisDirectoryArticleNotFoundFaultException;

	/** ����� ��� ��������� ������� ������ ���������� ������
	 * 
	 * @return ������ ������ ���������� ������
	 */
    public List<String> getTitles();

    /** ����� ��� �������������� ������
     * 
     * @param oldName ������� ��� ������������� ������
     * @param newName ����� ��� ������������� ������
     * @param value ����� ���������� �������������
     * @throws ArticleNotFoundException ����������, ���������� � ������ ������� ����������� � �������������� ������ (��� rpc ������ ����������� web-�������)
     * @throws AxisDirectoryArticleNotFoundFaultException ����������, ���������� � ������ ������� ����������� � �������������� ������ (��� ����������-��������������� ������ ����������� web-�������)
     */
    public void editArticle(String oldName, String newName, String value) throws ArticleNotFoundException, AxisDirectoryArticleNotFoundFaultException;

    /** ����� ��� �������� ������
     * 
     * @param name ��� ���������
     * @throws ArticleNotFoundException ����������, ���������� � ������ ������� ����������� � �������������� ������ (��� rpc ������ ����������� web-�������)
     * @throws AxisDirectoryArticleNotFoundFaultException ����������, ���������� � ������ ������� ����������� � �������������� ������ (��� ����������-��������������� ������ ����������� web-�������)
     */
    public void deleteArticle(String name) throws ArticleNotFoundException, AxisDirectoryArticleNotFoundFaultException;

    /** ����� ��� �������� ���� ������ */
    public void deleteArticles();

    /** ����� ��� �������� ����� ������ 
     * 
     * @param name ��� ����������� ������
     * @param value ���������� ����������� ������
     */
    public void createArticle(String name, String value);
	
}
