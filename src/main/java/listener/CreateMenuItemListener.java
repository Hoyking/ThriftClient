package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ViewController;

/** Класс служит для реализации слушателя кнопки перехода к окну создания статьи 
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class CreateMenuItemListener implements ActionListener {

	/** Ссылка на объект класса ViewController
	 * @see controller.ViewController
	 */
	private ViewController vc;
	
	/** Конструктор класса инициализирует поле */
	public CreateMenuItemListener(ViewController vc) {
		this.vc = vc;
	}
	
	/** Метод выполняет действия при нажатии на кнопку перехода к окну создания статьи */
	public void actionPerformed(ActionEvent e) {
		vc.setSelectedView(ViewController.SEARCH);
	}
	
}
