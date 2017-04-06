package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import org.apache.thrift.TException;

import listener.CreateArticleListener;
import listener.CreateMenuItemListener;
import listener.DeleteMenuItemListener;
import listener.EditMenuItemListener;
import listener.SearchMenuItemListener;
import view.CreateView;
import view.GeneralView;

public class ViewController {

	public static final int GENERAL = 0;
	public static final int SEARCH = 1;
	
	private JFrame frame;
	private GeneralView generalView;
	private CreateView createView;
	private JTabbedPane tabbedPane;
	
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
	
	private void createTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(800, 385));
        tabbedPane.addTab("Articles", null, generalView.getPanel(), "Java articles");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("Create", null, createView.getPanel(), "Creating a new record");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("      Edit      ");
		JMenuItem create = new JMenuItem("Create article");
		create.addActionListener(new CreateMenuItemListener(this));
		JMenuItem search = new JMenuItem("Find articles");
		search.addActionListener(new SearchMenuItemListener(generalView));
		JMenuItem delete = new JMenuItem("Delete articles");
		delete.addActionListener(new DeleteMenuItemListener(generalView));
		JMenuItem edit = new JMenuItem("Edit article");
		edit.addActionListener(new EditMenuItemListener(generalView));
		menu.add(create);
		menu.add(search);
		menu.add(delete);
		menu.add(edit);
		menuBar.add(menu);
		menuBar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
		
		frame.setLayout(null);
		tabbedPane.setBounds(0, 0, 800, 385);
		menuBar.setBounds(726, 0, 70, 21);
		frame.add(menuBar);
		frame.add(tabbedPane);
	}
	
	private void buildPanels() {
		generalView = new GeneralView();
		try {
			generalView.loadTitles(ConnectionController.getClient().getTitles());
		} catch (TException e) {
			e.printStackTrace();
		}
		
		createView = new CreateView();
		createView.setListener(new CreateArticleListener(this, createView.getTextField(), createView.getTextArea()));
		createView.decorateView();
	}
	
	public void removeTitleFromView(String title) {
		generalView.removeTitle(title);
	}
	
	public void loadTitleToView(String title) {
		generalView.loadTitle(title);
		generalView.repaint();
	}
	
	public void loadArticleToView(String name, String value) {
		generalView.loadArticle(name, value);
	}
	
	public void setSelectedView(int index) {
		tabbedPane.setSelectedIndex(index);
	}
	
	public static void main(String[] args) {
		new ConnectionController();
		new ViewController();
	}
	
}
