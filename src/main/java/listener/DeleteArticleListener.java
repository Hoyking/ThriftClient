package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

/** Класс служит для реализации слушателя кнопки удаления статей
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class DeleteArticleListener implements ActionListener {

	/** Ссылка на объект класса GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** Конструктор класса инициализирует поле */
	public DeleteArticleListener(GeneralView view) {
		this.view = view;
	}
	
	/** Метод выполняет действия при нажатии на кнопку удаления статей */
	public void actionPerformed(ActionEvent e) {
		view.deleteArticles();
		view.deleteModOff();
	}
	
}
