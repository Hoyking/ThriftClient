package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

/** Класс служит для реализации слушателя кнопки перехода к режиму редактирования статьи
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class EditMenuItemListener implements ActionListener {

	/** Ссылка на объект класса GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** Конструктор класса инициализирует поле */
	public EditMenuItemListener(GeneralView view) {
		this.view = view;
	}
	
	/** Метод выполняет действия при нажатии на кнопку перехода к режиму редактирования статьи */
	public void actionPerformed(ActionEvent e) {
		view.deleteModOff();
		view.editModOn();
	}
	
}
