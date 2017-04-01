package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.thrift.TException;

import controller.ConnectionController;
import service.ArticleNotFoundException;

public class EditArticleListener implements ActionListener {

	private JTextField nameField;
	private JTextField valueField;
	
	public EditArticleListener(JTextField nameField, JTextField valueField) {
		this.nameField = nameField;
		this.valueField = valueField;
	}
	
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String value = valueField.getText();
		if (name.length() == 0 || value.length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Some field is empty", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			ConnectionController.getClient().editArticle(name, value);
		} catch (ArticleNotFoundException exception) {
			JOptionPane.showMessageDialog(new JFrame(), "There is now article with such name", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (TException exception) {
			exception.printStackTrace();
		}
	}
	
}
