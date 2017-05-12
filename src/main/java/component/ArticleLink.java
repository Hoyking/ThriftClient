package component;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import controller.ConnectionController;
import service.rpc.ArticleNotFoundException;
import service.soap.AxisDirectoryArticleNotFoundFaultException;
import view.GeneralView;

/** Класс предоставляет методы для инициализации и использования компонента-ссылки на статью
* 
* @author Parfenenko Artem
* @version 1.0
*
*/
public class ArticleLink {
	
	/** Ссылка на объект GeneralView
	* @see view.GeneralView
	*/
	private GeneralView view;
	/** Ссылка на текстовое поле с названием статьи */
	private JTextPane textPane;
	/** Название статьи */
	private String name;
	/** Check box удаления / неудаления статьи */
	private JCheckBox delBox;
	/** Текущий цвет выделения компонента */
	private Color color;
	/** Контейнер, содержащий все составляющие компонента */
	private Container c;
	/** Константа определяет цвет выделенного компонента (темно-серый) */
	private static final Color SELECTED = new Color(225, 225, 225);
	/** Константа определяет цвет невыделенного компонента (светло-серый) */
	private static final Color UNSELECTED = new Color(245, 245, 245);
	
	/** Конструтор с параметрами
	* 
	* @param name имя статьи
	* @param view ссылка на объект GeneralView
	*/
	public ArticleLink(String name, GeneralView view) {
		this.view = view;
		textPane = new JTextPane();
		textPane.setEditable(false);
		delBox = new JCheckBox();
		color = UNSELECTED;
		c = new Container();
		this.name = name;
		buildComponent();
	}
	
	/** Метод формирует готовый компонент */
	private void buildComponent() {
		c.setLayout(new BoxLayout(c, BoxLayout.LINE_AXIS));
		
		delBox.addActionListener(new BoxListener());
		delBox.setBackground(color);
		delBox.setVisible(false);
		
		textPane.setContentType("text/html");
		textPane.setText("<a href>" + visibleName(name) + "</a>");
		textPane.setMaximumSize(new Dimension(5000, 21));
		textPane.setBackground(color);
		
		c.add(delBox);
		c.add(textPane);
		
		addListener(new HyperlinkListener() {
		
			public void hyperlinkUpdate(HyperlinkEvent e) {
				if(e.getInputEvent().getModifiers() != InputEvent.BUTTON1_MASK) {
					return;
				}
				String value;
				try {
					value = ConnectionController.getClientStub().getContent(name);
					view.loadArticle(name, value);
					view.setSelectedArticle(getInstance());
				} catch (ArticleNotFoundException e1) {
				JOptionPane.showMessageDialog(new JFrame(), 
						"Seems like someone changes information. You should refresh directory", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (AxisDirectoryArticleNotFoundFaultException e2) {
					JOptionPane.showMessageDialog(new JFrame(), 
					"Seems like someone changes information. You should refresh directory", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		
		});
	}
	
	/** Метод обрезает строку, если оно превышает 500 символов
	* 
	* @param name строка для обрезания
	* @return обрезанная строка
	*/
	private String visibleName(String name) {
		if(name.length() > 500) {
			return new String(name.substring(0, 500) + "...");
		}
		return name;
	}
	
	/** Метод устанавливает показатель, показывающий то выделен компонент или нет
	* 
	* @param mod значение устанавливаемого показателя
	*/
	public void setMod(boolean mod) {
		delBox.setVisible(mod);
		delBox.setSelected(false);
		if(mod == false) {
			textPane.setBackground(UNSELECTED);
		}
	}
	
	/** Метод возвращает показатель 
	* 
	* @return показатель
	*/
	public boolean getMod() {
		return delBox.isVisible();
	}
	
	/** Метод добавляет слушатель компонента
	* 
	* @param listener устанавливаемый слушатель компонента
	*/
	public void addListener(HyperlinkListener listener) {
		textPane.addHyperlinkListener(listener);
	}
	
	public Container getComponent() {
		return c;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		textPane.setText("<a href>" + visibleName(name) + "</a>");
	}
	
	/** Приватный метод возвращает объект класса
	* 
	* @return объект класса
	*/
	private ArticleLink getInstance() {
		return this;
	}
	
	/** Класс служит для создания экземпляра слушателя check box
	* 
	* @author Parfenenko Artem
	* @version 1.0
	*
	*/
	private class BoxListener implements ActionListener {
		
		/** Метод выполняется при взаимодействии с check box */
		public void actionPerformed(ActionEvent e) {
			trigger();
		}
		
		/** Метод выполняет действия при активации / дезактивации check box */
		private void trigger() {
			if(color == UNSELECTED) {
				color = SELECTED;
				textPane.setBackground(color);
				delBox.setBackground(color);
				view.addArticleToBeDel(getInstance());
			}
			else {
				color = UNSELECTED;
				textPane.setBackground(color);
				delBox.setBackground(color);
				view.safeArticle(getInstance());
			}
		}
	}
}