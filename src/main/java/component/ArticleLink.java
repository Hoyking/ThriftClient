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
	private JCheckBox box;
	private Color color;
	private Container c;
	private static final Color GRAY = new Color(225, 225, 225);
	private static final Color WHITE = Color.WHITE;
	
	public ArticleLink(String name, GeneralView view) {
		this.view = view;
		textPane = new JTextPane();
		textPane.setEditable(false);
		box = new JCheckBox();
		color = WHITE;
		c = new Container();
		this.name = name;
		buildComponent();
	}
	
	private void buildComponent() {
		c.setLayout(new BoxLayout(c, BoxLayout.LINE_AXIS));
		
		box.addActionListener(new BoxListener(this));
		box.setBackground(WHITE);
		box.setVisible(false);
		
		textPane.setContentType("text/html");
	    textPane.setText("<a href>" + name + "</a>");
	    textPane.setMaximumSize(new Dimension(2000, 20));
	    
	    c.add(box);
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
				} catch(ArticleNotFoundException exception) {
					System.out.println("FUCK YOU !");
				} catch(TException exception) {
					exception.printStackTrace();
				}
			}
			
		});
	}
	
	public void switchMod(boolean mod) {
		box.setVisible(mod);
		box.setSelected(false);
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
	
	public boolean isSelected() {
		return box.isSelected();
	}
	
	private class BoxListener implements ActionListener {

		private ArticleLink articleLink;
		
		public BoxListener(ArticleLink articleLink) {
			this.articleLink = articleLink;
		}
		
		public void actionPerformed(ActionEvent e) {
			trigger();
		}
		
		private void trigger() {
			if(color == WHITE) {
				color = GRAY;
				textPane.setBackground(color);
				box.setBackground(color);
				view.addArticleToBeDel(articleLink);
			}
			else {
				color = WHITE;
				textPane.setBackground(color);
				box.setBackground(color);
				view.safeArticle(articleLink);
			}
		}
	}
}
