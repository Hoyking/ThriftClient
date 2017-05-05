package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ConnectionController;
import view.GeneralView;

/** Класс служит для реализации слушателя кнопки обновления информации 
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class RefreshListener implements ActionListener{

	/** Ссылка на объект класса GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** Конструктор класса инициализирует поле */
	public RefreshListener(GeneralView view) {
		this.view = view;
	}
	
	/** Метод выполняет действия при нажатии на кнопку обновления информации */
	@Override
	public void actionPerformed(ActionEvent e) {
		view.loadTitles(ConnectionController.getClientStub().getTitles());
		view.repaint();
	}

}
