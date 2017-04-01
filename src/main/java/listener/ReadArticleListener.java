package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.thrift.TException;

import controller.ConnectionController;
import controller.ViewController;
import service.Article;
import service.ArticleNotFoundException;

public class ReadArticleListener implements ActionListener {

	private ViewController vc;
	private JTextField textField;
	
	public ReadArticleListener(ViewController vc, JTextField textField) {
		this.vc = vc;
		this.textField = textField;
	}
	
	public void actionPerformed(ActionEvent e) {
		String name = textField.getText();
		if (name.length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "The text field is empty", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			Article article = ConnectionController.getClient().getArticle(name);
			vc.loadArticleToView(article.getName(), article.getValue());
		} catch (ArticleNotFoundException exception) {
			JOptionPane.showMessageDialog(new JFrame(), "There is now article with such name", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (TException exception) {
			exception.printStackTrace();
		}
	}

}
