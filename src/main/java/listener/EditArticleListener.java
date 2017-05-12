package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

/** Класс служит для реализации слушателя кнопки редактирования статьи
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class EditArticleListener implements ActionListener {

	/** Ссылка на объект класса GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** Конструктор класса инициализирует поле */
	public EditArticleListener(GeneralView view) {
		this.view = view;
	}
	
	/** Метод выполняет действия при нажатии на кнопку редактирования статьи */
	public void actionPerformed(ActionEvent e) {
		view.completeEditing();
		view.editModOff();
	}
	
}
