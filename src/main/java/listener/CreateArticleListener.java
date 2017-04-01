package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.thrift.TException;

import controller.ConnectionController;
import controller.ViewController;

public class CreateArticleListener implements ActionListener {

	private ViewController vc;
	private JTextField nameField;
	private JTextArea valueArea;
	
	public CreateArticleListener(ViewController vc, JTextField nameField, JTextArea valueArea) {
		this.vc = vc;
		this.nameField = nameField;
		this.valueArea = valueArea;
	}
	
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String value = valueArea.getText();
		if (name.length() == 0 || value.length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Some field is empty", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			ConnectionController.getClient().createArticle(name, value);
			vc.loadTitleToView(name);
		} catch (TException exception) {
			exception.printStackTrace();
		}
	}
	
}
