package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ConnectionController;
import controller.ViewController;

/** Класс служит для реализации слушателя кнопки создания статьи
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class CreateArticleListener implements ActionListener {

	/** Ссылка на объект ViewController
	 * @see controller.ViewController
	 */
	private ViewController vc;
	/** Текстовое поле с названием статьи */
	private JTextField nameField;
	/** Текстовая область с содержимым статьи */
	private JTextArea valueArea;
	
	/** Конструктор класса инициализирует поля
	 * 
	 * @param vc передаваемы объект класса ViewController
	 * @param nameField передаваемое текстовое поле с названием статьи
	 * @param valueArea передаваемая текстовая область с содержимым статьи
	 */
	public CreateArticleListener(ViewController vc, JTextField nameField, JTextArea valueArea) {
		this.vc = vc;
		this.nameField = nameField;
		this.valueArea = valueArea;
	}
	
	/** Метод выполняет действия при нажатии на кнопку создания статьи */
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
