package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/** Абстракный класс-родитель для классов-представлений
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public abstract class AbstractClientView {
	
	/** Ссылка на объект класса JPanel (панель окна класса-представления) */
	protected JPanel panel;
	
	/** Конструктор клсса. Создает объект класса JPanel, задает размеры и макет */
	public AbstractClientView() {
		panel = new JPanel();
		panel.setSize(800, 365);
		panel.setLayout(new BorderLayout());
	}
	
	/** Метод для формирования представления */
	protected void decorateView() {}

	public JPanel getPanel() {
		return panel;
	}
	
}


