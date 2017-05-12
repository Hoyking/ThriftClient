package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import component.ArticleLink;
import controller.ConnectionController;
import listener.DeleteArticleListener;
import listener.EditArticleListener;
import listener.SearchArticlesListener;
import service.rpc.ArticleNotFoundException;
import service.soap.AxisDirectoryArticleNotFoundFaultException;

/** Класс-представление для обзора статей
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class GeneralView extends AbstractClientView{
	
	/** Список всех статей */
	private List <ArticleLink> articles;
	/** Список статей для удаления */
	private List <ArticleLink> unnecArticles;
	/** Выбранная статья */
	private ArticleLink selectedArticle;
	/** Текстовая область с содержимым выбранной статьи */
	private JTextArea textArea;
	/** Текстовое поле для ввода названия статьи в режиме поиска */
	private JTextField searchField;
	/** Панель со списком всех статей */
	private JPanel textPane;
	/** Текстовое поле с названием выбранной статьи */
	private JTextField textField;
	private JPanel delButtonPanel;
	private JPanel editButtonPanel;
	/** Константа определяет белый цвет */
	private static final Color WHITE = Color.WHITE;
	/** Константа определяет серый цвет */
	private static final Color GRAY = new Color(245, 245, 245);
	
	/** Конструктор класса инициализирует списки статей */
	public GeneralView() {
		unnecArticles = new ArrayList <ArticleLink> ();
		articles = new ArrayList <ArticleLink> ();
		decorateView();
	}
	
	/** Метод для формирования представления */
	@Override
	protected final void decorateView() {
		Font font = new Font("Arial", 1, 16);
		textField = new JTextField();
		textField.setColumns(textField.getText().length());
		textField.setBackground(GRAY);
		textField.setEditable(false);
		textField.setFont(font);
		
		JScrollPane textFieldPane = new JScrollPane(textField);
	    textFieldPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    textFieldPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
	    textFieldPane.setPreferredSize(new Dimension(590, 40));
		
		textArea = new JTextArea();
		textArea.setBackground(GRAY);
		textArea.setEditable(false);
		
		JButton editButton = new JButton("Confirm");
		editButton.setBounds(196, 5, 200, 20);
		editButton.addActionListener(new EditArticleListener(this));
		
		editButtonPanel = new JPanel();
		editButtonPanel.setLayout(null);
		editButtonPanel.add(editButton);
		editButtonPanel.setVisible(false);
		
		textPane = new JPanel();
		textPane.setBackground(GRAY);
		textPane.setLayout(new BoxLayout(textPane, BoxLayout.PAGE_AXIS));
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(0, 5, 200, 20);
		deleteButton.addActionListener(new DeleteArticleListener(this));
		
		delButtonPanel = new JPanel();
		delButtonPanel.setLayout(null);
		delButtonPanel.add(deleteButton);
		delButtonPanel.setVisible(false);
		
		searchField = new JTextField(18);
		searchField.addKeyListener(new SearchArticlesListener(this));
		searchField.setVisible(false);
	    
	    JScrollPane areaPane = new JScrollPane(textArea);
	    areaPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    areaPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    areaPane.setPreferredSize(new Dimension(597, 280));
	    
	    JPanel areaPanePanel = new JPanel();
	    areaPanePanel.setLayout(new BoxLayout(areaPanePanel, BoxLayout.PAGE_AXIS));
	    areaPanePanel.add(areaPane);
	    areaPanePanel.add(editButtonPanel);
	    
	    JScrollPane panePane = new JScrollPane(textPane);
	    panePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    panePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    panePane.setPreferredSize(new Dimension(203, 280));
	    
	    JPanel scrollPanePanel = new JPanel();
	    scrollPanePanel.setLayout(new BoxLayout(scrollPanePanel, BoxLayout.PAGE_AXIS));
	    scrollPanePanel.add(panePane);
	    scrollPanePanel.add(delButtonPanel);
	    
	    JPanel panePanel = new JPanel();
	    panePanel.setLayout(new BoxLayout(panePanel, BoxLayout.LINE_AXIS));
	    panePanel.setSize(800, 280);
	    panePanel.add(areaPanePanel);
	    panePanel.add(scrollPanePanel);
	    
	    JPanel upPanel = new JPanel();
	    upPanel.setLayout(new BorderLayout());
	    upPanel.add(textFieldPane, BorderLayout.WEST);
	    upPanel.add(searchField, BorderLayout.EAST);
	    
	    panel.add(upPanel, BorderLayout.NORTH);
	    panel.add(panePanel, BorderLayout.CENTER);
	    font = new Font("Arial", Font.BOLD, 12);
	    textArea.setFont(font);
	}
	
	/** Метод переключает представления в режим удаления статей */
	public void deleteModOn() {
		for(ArticleLink article: articles) {
			article.setMod(true);
			delButtonPanel.setVisible(true);
		}
	}
	
	/** Метод отключает режим удаления статей */
	public void deleteModOff() {
		unnecArticles.clear();
		for(ArticleLink article: articles) {
			article.setMod(false);
			delButtonPanel.setVisible(false);
		}
	}
	
	/** Метод переключает представления в режим поиска статей */
	public void searchModOn() {
		searchField.setVisible(true);
		textField.setColumns(42);
		panel.getParent().repaint();
	}
	
	/** Метод отключает режим поиска статей */
	public void searchModOff() {
		for(ArticleLink  articleLink: articles) {
			articleLink.getComponent().setVisible(true);
		}
		searchField.setVisible(false);
		textField.setColumns(57);
		panel.getParent().repaint();
	}
	
	/** Метод переключает представления в режим редактирования статьи */
	public void editModOn() {
		if(selectedArticle == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Please select article", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		textArea.setEditable(true);
		textField.setEditable(true);
		
		textArea.setBackground(WHITE);
		textField.setBackground(WHITE);
		editButtonPanel.setVisible(true);
	}
	
	/** Метод выполняет редактирования статьи */
	public void completeEditing () {
		try {
			String newName = textField.getText();
			ConnectionController.getClientStub().editArticle(selectedArticle.getName(), newName, textArea.getText());
			selectedArticle.setName(newName);
			repaint();
		} catch (ArticleNotFoundException | AxisDirectoryArticleNotFoundFaultException e) {
			JOptionPane.showMessageDialog(new JFrame(), 
					"Seems like someone changes information. You should refresh directory", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/** Метод отключает режим редактирования статьи */
	public void editModOff() {
		textArea.setEditable(false);
		textArea.setBackground(GRAY);
		textField.setEditable(false);
		textField.setBackground(GRAY);
		editButtonPanel.setVisible(false);
	}
	
	/** Метод отрисовывает на представлении ссылку на статью
	 * 
	 * @param articleLink объект типа ArticleLink
	 * @see component.ArticleLink
	 */
	public void paintArticle(ArticleLink articleLink) {
		textPane.add(articleLink.getComponent());
	}
	
	/** Метод загружает статью
	 * 
	 * @param name имя загружаемой статьи
	 * @param value содержимое загружаемой статьи
	 */
	public void loadArticle(String name, String value) {
		textArea.setText(value);
		textField.setText(name);
		textField.setColumns(name.length());
	}
	
	/** Метод загружает список заголовков статей
	 *  
	 * @param titles список заголовков статей
	 */
	public void loadTitles(List<String> titles) {
		articles.clear();
		for(String title: titles) {
			loadTitle(title);
		}
		reloadArticles();
	}
	
	/** Метод загружает заголовок статьи
	 * 
	 * @param title заголовок статьи
	 */
	public void loadTitle(String title) {
		articles.add(new ArticleLink(title, this));
	}
	
	/** Метод изымает статью из списка
	 * 
	 * @param title название статьи
	 */
	public void removeTitle(String title) {
		for(ArticleLink articleLink: articles) {
			if(articleLink.getName().equals(title)) {
				articles.remove(articleLink);
				reloadArticles();
				return;
			}
		}
	}
	
	/** Метод удаляет выбранные статьи */
	public void deleteArticles() {
		for(ArticleLink article: unnecArticles) {
			articles.remove(article);
			try {
				ConnectionController.getClientStub().deleteArticle(article.getName());
			} catch (ArticleNotFoundException e1) {
				JOptionPane.showMessageDialog(new JFrame(), 
						"Seems like someone changes information. You should refresh directory", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (AxisDirectoryArticleNotFoundFaultException e2) {
				JOptionPane.showMessageDialog(new JFrame(), 
						"Seems like someone changes information. You should refresh directory", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		unnecArticles.clear();
		reloadArticles();
	}
	
	/** Метод выполняет поиск и последующее оторажение найденных статей */
	public void searchArticles() {
		String str = searchField.getText();
		if(str.length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Searching field is empty", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for(ArticleLink articleLink: articles) {
			if(!articleLink.getName().contains(str)) {
				articleLink.getComponent().setVisible(false);
			}
			else {
				articleLink.getComponent().setVisible(true);
			}
		}
		panel.getParent().repaint();
	}
	
	/** Метод добавляет статью к списку удаляемых
	 * 
	 * @param articleLink статья для удаления
	 */
	public void addArticleToBeDel(ArticleLink articleLink) {
		unnecArticles.add(articleLink);
	}
	
	/** Метод изымает статью из списка удаляемых
	 * 
	 * @param articleLink сохраняемая статья
	 */
	public void safeArticle(ArticleLink articleLink) {
		unnecArticles.remove(articleLink);
	}
	
	/** Метод для указания выбранной статьи
	 * 
	 * @param articleLink выбранная статья
	 */
	public void setSelectedArticle(ArticleLink articleLink) {
		selectedArticle = articleLink;
	}
	
	/** Метод перезагружает список статей на представлении */
	public void reloadArticles() {
		textPane.removeAll();
		for(ArticleLink article: articles) {
			paintArticle(article);
		}
		clear();
		panel.repaint();
	}
	
	/** Метод для перерисовывания представления */
	public void repaint() {
		panel.getParent().repaint();
	}
	
	/** Метод для очистки полей с именем и содержимым статьи */
	public void clear() {
		textArea.setText("");
		textField.setText(" ");
		selectedArticle = null;
	}
}
