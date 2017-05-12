package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ConnectionController;
import controller.ViewController;

/** Класс служит для реализации слушателя кнопки удаления статей
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class CreateArticleListener implements ActionListener {

	/** Ссылка на объект класса ViewController
	 * @see controller.ViewController
	 */
	private ViewController vc;
	/** Ссылка на текстовое поле с именем статьи */
	private JTextField nameField;
	/** Ссылка на текстовую область с содержимым статьи */
	private JTextArea valueArea;
	
	/** Конструктор класса инициализирует поля
	 * 
	 * @param name передаваемый объект ViewController
	 * @param nameField передаваемый объект текстового поля с именем
	 * @param valueArea передаваемый объект текстовой области с содержимым
	 */
	public CreateArticleListener(ViewController vc, JTextField nameField, JTextArea valueArea) {
		this.vc = vc;
		this.nameField = nameField;
		this.valueArea = valueArea;
	}
	
	/** Метод выполняет действия при нажатии на кнопку создания статей */
	public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String value = valueArea.getText();
		if (name.length() == 0 || value.length() == 0) {
			JOptionPane.showMessageDialog(new JFrame(), "Some field is empty", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ConnectionController.getClientStub().createArticle(name, value);
		vc.loadTitleToView(name);
	}
	
}