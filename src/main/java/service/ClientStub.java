package service;

import java.util.List;

import service.rpc.ArticleNotFoundException;

public interface ClientStub {
	
	public String getContent(String name) throws ArticleNotFoundException;

    public List<String> getTitles();

    public void editArticle(String name, String value) throws ArticleNotFoundException;

    public void deleteArticle(String name) throws ArticleNotFoundException;

    public void deleteArticles();

    public void createArticle(String name, String value);
	
}
