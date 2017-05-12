package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

/** Класс служит для реализации слушателя кнопки перехода к окну удаления статей
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class DeleteMenuItemListener implements ActionListener {

	/** Ссылка на объект класса GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** Конструктор класса инициализирует поле */
	public DeleteMenuItemListener(GeneralView view) {
		this.view = view;
	}
	
	/** Метод выполняет действия при нажатии на кнопку перехода к окну удаления статей */
	public void actionPerformed(ActionEvent e) {
		view.deleteModOn();
		view.editModOff();
	}

}
