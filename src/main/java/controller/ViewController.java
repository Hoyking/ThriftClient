package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import listener.CreateArticleListener;
import listener.CreateMenuItemListener;
import listener.DeleteMenuItemListener;
import listener.EditMenuItemListener;
import listener.RefreshListener;
import listener.SearchMenuItemListener;
import view.CreateView;
import view.GeneralView;

/** Класс служит для создания окна для работы со справочником 
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class ViewController {

	/** Константа для соответствия с окном обзора статей справочника */
	public static final int GENERAL = 0;
	/** Константа для соответствия с окном создания статьи справочника */
	public static final int SEARCH = 1;
	private JFrame frame;
	/** Ссылка на экземпляр класса GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView generalView;
	/** Ссылка на объект класса CreateView
	 * @see view.CreateView
	 */
	private CreateView createView;
	private JTabbedPane tabbedPane;
	
	/** Конструктор класса */
	public ViewController() {
		frame = new JFrame("Java Tutorial !");
		frame.setSize(804, 412);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		buildPanels();
		createTabbedPane();
		frame.setVisible(true);
	}
	
	/** Метод создает и заполняет вкладки окна */
	private void createTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(800, 385));
        tabbedPane.addTab("Articles", null, generalView.getPanel(), "Java articles");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("Create", null, createView.getPanel(), "Creating a new record");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		JToolBar toolBar = new JToolBar();
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new CreateMenuItemListener(this));
		JButton searchButton = new JButton("Find");
		searchButton.addActionListener(new SearchMenuItemListener(generalView));
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new DeleteMenuItemListener(generalView));
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new EditMenuItemListener(generalView));
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new RefreshListener(generalView));
		toolBar.add(createButton);
		toolBar.add(searchButton);
		toolBar.add(deleteButton);
		toolBar.add(editButton);
		toolBar.add(refreshButton);
		toolBar.setEnabled(false);
		
		
		frame.setLayout(null);
		tabbedPane.setBounds(0, 0, 800, 385);
		toolBar.setBounds(555, 0, 245, 21);
		frame.add(toolBar);
		frame.add(tabbedPane);
		
		generalView.repaint();
	}
	
	/** Метод формирует окна generalView и createView
	 * @see view.GeneralView
	 * @see view.CreateView
	 */
	private void buildPanels() {
		generalView = new GeneralView();
		generalView.loadTitles(ConnectionController.getClientStub().getTitles());
		
		createView = new CreateView();
		createView.setListener(new CreateArticleListener(this, createView.getTextField(), createView.getTextArea()));
		createView.decorateView();
	}
	
	/** Метод для извлечения компонента-ссылки на статью
	 * 
	 * @param title имя извлекаемого компонента
	 */
	public void removeTitleFromView(String title) {
		generalView.removeTitle(title);
	}
	
	/** Метод для добавления компонента-ссылки на статью
	 * 
	 * @param title имя добавляемого компонента
	 */
	public void loadTitleToView(String title) {
		generalView.loadTitle(title);
		generalView.reloadArticles();
		generalView.repaint();
	}
	
	/** Метод для загрузки статьи
	 * 
	 * @param name имя статьи
	 * @param value содержимое статьи
	 */
	public void loadArticleToView(String name, String value) {
		generalView.loadArticle(name, value);
	}
	
	/** Метод для установления выбранного окна
	 * 
	 * @param index номер выбираемого окна
	 */
	public void setSelectedView(int index) {
		tabbedPane.setSelectedIndex(index);
	}
	
}