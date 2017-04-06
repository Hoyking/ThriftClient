package component;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.thrift.TException;

import controller.ConnectionController;
import service.ArticleNotFoundException;
import view.GeneralView;

public class ArticleLink {

	private GeneralView view;
	private JTextPane textPane;
	private String name;
	private JCheckBox delBox;
	private Color color;
	private Container c;
	private static final Color SELECTED = new Color(225, 225, 225);
	private static final Color UNSELECTED = new Color(245, 245, 245);
	
	public ArticleLink(String name, GeneralView view) {
		this.view = view;
		textPane = new JTextPane();
		textPane.setEditable(false);
		delBox = new JCheckBox();
		color = UNSELECTED;
		c = new Container();
		this.name = name;
		buildComponent();
	}
	
	private void buildComponent() {
		c.setLayout(new BoxLayout(c, BoxLayout.LINE_AXIS));
		
		delBox.addActionListener(new BoxListener());
		delBox.setBackground(color);
		delBox.setVisible(false);
		
		textPane.setContentType("text/html");
	    textPane.setText("<a href>" + name + "</a>");
	    textPane.setMaximumSize(new Dimension(2000, 20));
	    textPane.setBackground(color);
	    
	    c.add(delBox);
	    c.add(textPane);
	    
	    addListener(new HyperlinkListener() {
			
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if(e.getInputEvent().getModifiers() != InputEvent.BUTTON1_MASK) {
					return;
				}
				String value;
				try {
					value = ConnectionController.getClient().getArticle(name).getValue();
					view.loadArticle(name, value);
					view.setSelectedArticle(getInstance());
				} catch(ArticleNotFoundException exception) {
					System.out.println("FUCK YOU !");
				} catch(TException exception) {
					exception.printStackTrace();
				}
			}
			
		});
	}
	
	public void setMod(boolean mod) {
		delBox.setVisible(mod);
		delBox.setSelected(false);
		if(mod == false) {
			textPane.setBackground(UNSELECTED);
		}
	}
	
	public boolean getMod() {
		return delBox.isVisible();
	}
	
	public void addListener(HyperlinkListener listener) {
		textPane.addHyperlinkListener(listener);
	}
	
	public Container getComponent() {
		return c;
	}
	
	public String getName() {
		return name;
	}
	
	private ArticleLink getInstance() {
		return this;
	}
	
	private class BoxListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			trigger();
		}
		
		private void trigger() {
			if(color == UNSELECTED) {
				color = SELECTED;
				textPane.setBackground(color);
				delBox.setBackground(color);
				view.addArticleToBeDel(getInstance());
			}
			else {
				color = UNSELECTED;
				textPane.setBackground(color);
				delBox.setBackground(color);
				view.safeArticle(getInstance());
			}
		}
	}
}
