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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

public class GeneralView extends AbstractClientView{
	
	private List <ArticleLink> articles;
	private List <ArticleLink> unnecArticles;
	private ArticleLink selectedArticle;
	private JTextArea textArea;
	private JTextField searchField;
	private JPanel textPane;
	private JLabel label;
	private JMenuBar menuBar;
	private JPanel delButtonPanel;
	private JPanel editButtonPanel;
	private static final Color WHITE = Color.WHITE;
	private static final Color GRAY = new Color(245, 245, 245);
	
	public GeneralView() {
		unnecArticles = new ArrayList <ArticleLink> ();
		articles = new ArrayList <ArticleLink> ();
		decorateView();
	}
	
	@Override
	protected final void decorateView() {
		Font font = new Font("Arial", 1, 16);
		label = new JLabel();
		label.setFont(font);
		
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
		
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("      Edit      ");
		JMenuItem search = new JMenuItem("Search");
		JMenuItem delete = new JMenuItem("Delete");
		menu.add(search);
		menu.add(delete);
		menuBar.add(menu);
	    
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
	    upPanel.add(label, BorderLayout.WEST);
	    upPanel.add(searchField, BorderLayout.EAST);
	    
	    panel.add(upPanel, BorderLayout.NORTH);
	    panel.add(panePanel, BorderLayout.CENTER);
	    font = new Font("Arial", Font.BOLD, 12);
	    textArea.setFont(font);
	}
	
	public void deleteModOn() {
		for(ArticleLink article: articles) {
			article.setMod(true);
			delButtonPanel.setVisible(true);
		}
	}
	
	public void deleteModOff() {
		unnecArticles.clear();
		for(ArticleLink article: articles) {
			article.setMod(false);
			delButtonPanel.setVisible(false);
		}
	}
	
	public void searchModOn() {
		searchField.setVisible(true);
		panel.getParent().repaint();
	}
	
	public void searchModOff() {
		for(ArticleLink  articleLink: articles) {
			articleLink.getComponent().setVisible(true);
		}
		searchField.setVisible(false);
		panel.getParent().repaint();
	}
	
	public void editModOn() {
		if(selectedArticle == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Please select article", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		textArea.setEditable(true);
		textArea.setBackground(WHITE);
		editButtonPanel.setVisible(true);
	}
	
	public void completeEditing () {
		try {
			ConnectionController.getClientStub().editArticle(selectedArticle.getName(), textArea.getText());
		} catch (ArticleNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void editModOff() {
		textArea.setEditable(false);
		textArea.setBackground(GRAY);
		editButtonPanel.setVisible(false);
	}
	
	public void paintArticle(ArticleLink articleLink) {
		textPane.add(articleLink.getComponent());
	}
	
	public void loadArticle(String name, String value) {
		textArea.setText(value);
		label.setText(name);
	}
	
	public void loadTitles(List<String> titles) {
		articles.clear();
		for(String title: titles) {
			loadTitle(title);
		}
		repaint();
	}
	
	public void loadTitle(String title) {
		articles.add(new ArticleLink(title, this));
	}
	
	public void removeTitle(String title) {
		for(ArticleLink articleLink: articles) {
			if(articleLink.getName().equals(title)) {
				articles.remove(articleLink);
				repaint();
				return;
			}
		}
	}
	
	public void deleteArticles() {
		for(ArticleLink article: unnecArticles) {
			articles.remove(article);
			try {
				ConnectionController.getClientStub().deleteArticle(article.getName());
			} catch (ArticleNotFoundException e) {
				e.printStackTrace();
			}
		}
		unnecArticles.clear();
		repaint();
	}
	
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
	
	public void addArticleToBeDel(ArticleLink articleLink) {
		unnecArticles.add(articleLink);
	}
	
	public void safeArticle(ArticleLink articleLink) {
		unnecArticles.remove(articleLink);
	}
	
	public void setSelectedArticle(ArticleLink articleLink) {
		selectedArticle = articleLink;
	}
	
	public void repaint() {
		textPane.removeAll();
		for(ArticleLink article: articles) {
			paintArticle(article);
		}
		clear();
		panel.repaint();
	}
	
	public void clear() {
		textArea.setText("");
		label.setText(" ");
		selectedArticle = null;
	}
}
