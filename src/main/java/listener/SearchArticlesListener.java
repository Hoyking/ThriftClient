package listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.GeneralView;

/** Класс служит для реализации слушателя клавиши Enter при поиске статей
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class SearchArticlesListener extends KeyAdapter {

	/** Ссылка на объект класса GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** Конструктор класса инициализирует поле */
	public SearchArticlesListener(GeneralView view) {
		this.view = view;		
	}
	
	/** Метод выполняет действия при нажатии на клавишу Enter при поиске статей */
	@Override
	public void keyTyped(KeyEvent event) {
		if((int)event.getKeyChar() == KeyEvent.VK_ENTER) {
			view.searchArticles();
			return;
		}
		if((int)event.getKeyChar() == KeyEvent.VK_ESCAPE) {
			view.searchModOff();
			return;
		}
	}

}
