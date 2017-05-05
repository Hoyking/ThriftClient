package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

/** Класс служит для реализации слушателя кнопки перехода к режиму поиска статей
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class SearchMenuItemListener implements ActionListener {

	/** Ссылка на объект класса GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** Конструктор класса инициализирует поле */
	public SearchMenuItemListener(GeneralView view) {
		this.view = view;
	}
	
	/** Метод выполняет действия при нажатии на кнопку перехода к режиму поиска статей */
	public void actionPerformed(ActionEvent e) {
		view.searchModOn();
	}

}
