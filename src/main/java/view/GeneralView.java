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

/** �����-������������� ��� ������ ������
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class GeneralView extends AbstractClientView{
	
	/** ������ ���� ������ */
	private List <ArticleLink> articles;
	/** ������ ������ ��� �������� */
	private List <ArticleLink> unnecArticles;
	/** ��������� ������ */
	private ArticleLink selectedArticle;
	/** ��������� ������� � ���������� ��������� ������ */
	private JTextArea textArea;
	/** ��������� ���� ��� ����� �������� ������ � ������ ������ */
	private JTextField searchField;
	/** ������ �� ������� ���� ������ */
	private JPanel textPane;
	/** ��������� ���� � ��������� ��������� ������ */
	private JTextField textField;
	private JPanel delButtonPanel;
	private JPanel editButtonPanel;
	/** ��������� ���������� ����� ���� */
	private static final Color WHITE = Color.WHITE;
	/** ��������� ���������� ����� ���� */
	private static final Color GRAY = new Color(245, 245, 245);
	
	/** ����������� ������ �������������� ������ ������ */
	public GeneralView() {
		unnecArticles = new ArrayList <ArticleLink> ();
		articles = new ArrayList <ArticleLink> ();
		decorateView();
	}
	
	/** ����� ��� ������������ ������������� */
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
	
	/** ����� ����������� ������������� � ����� �������� ������ */
	public void deleteModOn() {
		for(ArticleLink article: articles) {
			article.setMod(true);
			delButtonPanel.setVisible(true);
		}
	}
	
	/** ����� ��������� ����� �������� ������ */
	public void deleteModOff() {
		unnecArticles.clear();
		for(ArticleLink article: articles) {
			article.setMod(false);
			delButtonPanel.setVisible(false);
		}
	}
	
	/** ����� ����������� ������������� � ����� ������ ������ */
	public void searchModOn() {
		searchField.setVisible(true);
		textField.setColumns(42);
		panel.getParent().repaint();
	}
	
	/** ����� ��������� ����� ������ ������ */
	public void searchModOff() {
		for(ArticleLink  articleLink: articles) {
			articleLink.getComponent().setVisible(true);
		}
		searchField.setVisible(false);
		textField.setColumns(57);
		panel.getParent().repaint();
	}
	
	/** ����� ����������� ������������� � ����� �������������� ������ */
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
	
	/** ����� ��������� �������������� ������ */
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
	
	/** ����� ��������� ����� �������������� ������ */
	public void editModOff() {
		textArea.setEditable(false);
		textArea.setBackground(GRAY);
		textField.setEditable(false);
		textField.setBackground(GRAY);
		editButtonPanel.setVisible(false);
	}
	
	/** ����� ������������ �� ������������� ������ �� ������
	 * 
	 * @param articleLink ������ ���� ArticleLink
	 * @see component.ArticleLink
	 */
	public void paintArticle(ArticleLink articleLink) {
		textPane.add(articleLink.getComponent());
	}
	
	/** ����� ��������� ������
	 * 
	 * @param name ��� ����������� ������
	 * @param value ���������� ����������� ������
	 */
	public void loadArticle(String name, String value) {
		textArea.setText(value);
		textField.setText(name);
		textField.setColumns(name.length());
	}
	
	/** ����� ��������� ������ ���������� ������
	 *  
	 * @param titles ������ ���������� ������
	 */
	public void loadTitles(List<String> titles) {
		articles.clear();
		for(String title: titles) {
			loadTitle(title);
		}
		reloadArticles();
	}
	
	/** ����� ��������� ��������� ������
	 * 
	 * @param title ��������� ������
	 */
	public void loadTitle(String title) {
		articles.add(new ArticleLink(title, this));
	}
	
	/** ����� ������� ������ �� ������
	 * 
	 * @param title �������� ������
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
	
	/** ����� ������� ��������� ������ */
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
	
	/** ����� ��������� ����� � ����������� ���������� ��������� ������ */
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
	
	/** ����� ��������� ������ � ������ ���������
	 * 
	 * @param articleLink ������ ��� ��������
	 */
	public void addArticleToBeDel(ArticleLink articleLink) {
		unnecArticles.add(articleLink);
	}
	
	/** ����� ������� ������ �� ������ ���������
	 * 
	 * @param articleLink ����������� ������
	 */
	public void safeArticle(ArticleLink articleLink) {
		unnecArticles.remove(articleLink);
	}
	
	/** ����� ��� �������� ��������� ������
	 * 
	 * @param articleLink ��������� ������
	 */
	public void setSelectedArticle(ArticleLink articleLink) {
		selectedArticle = articleLink;
	}
	
	/** ����� ������������� ������ ������ �� ������������� */
	public void reloadArticles() {
		textPane.removeAll();
		for(ArticleLink article: articles) {
			paintArticle(article);
		}
		clear();
		panel.repaint();
	}
	
	/** ����� ��� ��������������� ������������� */
	public void repaint() {
		panel.getParent().repaint();
	}
	
	/** ����� ��� ������� ����� � ������ � ���������� ������ */
	public void clear() {
		textArea.setText("");
		textField.setText(" ");
		selectedArticle = null;
	}
}
