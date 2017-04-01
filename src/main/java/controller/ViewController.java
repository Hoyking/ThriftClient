package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import org.apache.thrift.TException;

import listener.CreateArticleListener;
import listener.DeleteMenuItemListener;
import listener.SearchMenuItemListener;
import view.CreateView;
import view.GeneralView;

public class ViewController {

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
		JMenuItem create = new JMenuItem("Create");
		JMenuItem search = new JMenuItem("Search");
		search.addActionListener(new SearchMenuItemListener(generalView));
		JMenuItem delete = new JMenuItem("Delete");
		delete.addActionListener(new DeleteMenuItemListener(generalView));
		menu.add(create);
		menu.add(search);
		menu.add(delete);
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
		
		createView = new CreateView(new String("Confirm"));
		createView.setListener(new CreateArticleListener(this, createView.getTextField(), createView.getTextArea()));
		createView.decorateView();
		
	}
	
	/*private void repaint() {
		frame.setContentPane(tabbedPane);
	}*/
	
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
	
	public static void main(String[] args) {
		new ConnectionController();
		new ViewController();
	}
	
}
