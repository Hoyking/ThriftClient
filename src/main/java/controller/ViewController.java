package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;

import org.apache.thrift.TException;

import component.Field;
import listener.CreateArticleListener;
import listener.DeleteArticleListener;
import listener.DeleteMenuItemListener;
import listener.EditArticleListener;
import listener.ReadArticleListener;
import view.CreateView;
import view.DataEditView;
import view.GeneralView;

public class ViewController {

	private JFrame frame;
	private GeneralView generalView;
	private DataEditView readView;
	private CreateView createView;
	private DataEditView editView;
	private JTabbedPane tabbedPane;
	
	public ViewController() {
		frame = new JFrame("Java Tutorial !");
		frame.setSize(804, 412);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		buildPanels();
		createTabbedPane();
		//repaint();
		frame.setVisible(true);
		
	}
	
	private void createTabbedPane() {
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(800, 385));
        tabbedPane.addTab("Articles", null, generalView.getPanel(), "Java articles");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPane.addTab("Read", null, readView.getPanel(), "Reading of an existing record");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        tabbedPane.addTab("Create", null, createView.getPanel(), "Creating a new record");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        tabbedPane.addTab("Edit", null, editView.getPanel(), "Editing an existing record");
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        //tabbedPane.addTab("Delete", null, deleteView.getPanel(), "Deleting a record");
        //tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
        tabbedPane.setVisible(true);
        
        JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("      Edit      ");
		JMenuItem create = new JMenuItem("Create");
		JMenuItem search = new JMenuItem("Search");
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
		
		readView = new DataEditView(new String("Confirm"));
		readView.addField("print", new Field("Print article name here:", new Rectangle(220, 100, 345, 20)));
		readView.setListener(new ReadArticleListener(this, readView.getTextField("print")));
		readView.decorateView();
		
		createView = new CreateView(new String("Confirm"));
		//createView.addField("title", new Field("Title:", new Rectangle(170, 100, 180, 20)));
		//createView.addField("content", new Field("Content:", new Rectangle(420, 100, 200, 20)));
		createView.setListener(new CreateArticleListener(this, createView.getTextField(), createView.getTextArea()));
		createView.decorateView();
		
		editView = new DataEditView(new String("Confirm"));
		editView.addField("title", new Field("Title:", new Rectangle(170, 100, 180, 20)));
		editView.addField("content", new Field("Content:", new Rectangle(420, 100, 200, 20)));
		editView.setListener(new EditArticleListener(editView.getTextField("title"), editView.getTextField("content")));
		editView.decorateView();
		
	}
	
	private void repaint() {
		frame.setContentPane(tabbedPane);
	}
	
	/*public void repaintView() {
		generalView.repaint();
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
