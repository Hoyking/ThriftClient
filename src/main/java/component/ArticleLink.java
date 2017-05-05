package component;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import controller.ConnectionController;
import service.rpc.ArticleNotFoundException;
import service.soap.AxisDirectoryArticleNotFoundFaultException;
import view.GeneralView;

/** ����� ������������� ������ ��� ������������� � ������������� ����������-������ �� ������
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class ArticleLink {

	/** ������ �� ������ GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	/** ������ �� ��������� ���� � ��������� ������ */
	private JTextPane textPane;
	/** �������� ������ */
	private String name;
	/** Check box �������� / ���������� ������ */
	private JCheckBox delBox;
	/** ������� ���� ��������� ���������� */
	private Color color;
	/** ���������, ���������� ��� ������������ ���������� */
	private Container c;
	/** ��������� ���������� ���� ����������� ���������� (�����-�����) */
	private static final Color SELECTED = new Color(225, 225, 225);
	/** ��������� ���������� ���� ������������� ���������� (������-�����) */
	private static final Color UNSELECTED = new Color(245, 245, 245);
	
	/** ���������� � �����������
	 * 
	 * @param name ��� ������
	 * @param view ������ �� ������ GeneralView
	 */
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
	
	/** ����� ��������� ������� ��������� */
	private void buildComponent() {
		c.setLayout(new BoxLayout(c, BoxLayout.LINE_AXIS));
		
		delBox.addActionListener(new BoxListener());
		delBox.setBackground(color);
		delBox.setVisible(false);
		
		textPane.setContentType("text/html");
	    textPane.setText("<a href>" + visibleName(name) + "</a>");
	    textPane.setMaximumSize(new Dimension(5000, 21));
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
					value = ConnectionController.getClientStub().getContent(name);
					view.loadArticle(name, value);
					view.setSelectedArticle(getInstance());
				} catch (ArticleNotFoundException e1) {
					JOptionPane.showMessageDialog(new JFrame(), 
							"Seems like someone changes information. You should refresh directory", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (AxisDirectoryArticleNotFoundFaultException e2) {
					JOptionPane.showMessageDialog(new JFrame(), 
							"Seems like someone changes information. You should refresh directory", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
	}
	
	/** ����� �������� ������, ���� ��� ��������� 500 ��������
	 * 
	 * @param name ������ ��� ���������
	 * @return ���������� ������
	 */
	private String visibleName(String name) {
		if(name.length() > 500) {
			return new String(name.substring(0, 500) + "...");
		}
		return name;
	}
	
	/** ����� ������������� ����������, ������������ �� ������� ��������� ��� ���
	 * 
	 * @param mod �������� ���������������� ����������
	 */
	public void setMod(boolean mod) {
		delBox.setVisible(mod);
		delBox.setSelected(false);
		if(mod == false) {
			textPane.setBackground(UNSELECTED);
		}
	}
	
	/** ����� ���������� ���������� 
	 * 
	 * @return ����������
	 */
	public boolean getMod() {
		return delBox.isVisible();
	}
	
	/** ����� ��������� ��������� ����������
	 * 
	 * @param listener ��������������� ��������� ����������
	 */
	public void addListener(HyperlinkListener listener) {
		textPane.addHyperlinkListener(listener);
	}
	
	public Container getComponent() {
		return c;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		textPane.setText("<a href>" + visibleName(name) + "</a>");
	}
	
	/** ��������� ����� ���������� ������ ������
	 * 
	 * @return ������ ������
	 */
	private ArticleLink getInstance() {
		return this;
	}
	
	/** ����� ������ ��� �������� ���������� ��������� check box
	 * 
	 * @author Parfenenko Artem
	 * @version 1.0
	 *
	 */
	private class BoxListener implements ActionListener {
		
		/** ����� ����������� ��� �������������� � check box */
		public void actionPerformed(ActionEvent e) {
			trigger();
		}
		
		/** ����� ��������� �������� ��� ��������� / ������������ check box */
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
