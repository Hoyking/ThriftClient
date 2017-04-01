package component;

import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.JTextField;

public class Field {

	private JLabel label;
	private JTextField textField;
	private Container c;
	private Rectangle r;
	
	public Field(String text, Rectangle r) {
		label = new JLabel(text);
		textField = new JTextField();//50);
		this.r = r;
		orientComponents();
	}
	
	private void orientComponents() {
		c = new Container();
		c.setLayout(new BoxLayout(c, BoxLayout.LINE_AXIS));
		c.add(label);
		c.add(textField);
		c.setBounds(r);
	}
	
	public Container getContainer() {
		return c;
	}
	
	public JTextField getTextField() {
		return textField;
	}
	
	public JLabel getLabel() {
		return label;
	}
}
