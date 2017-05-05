package listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.GeneralView;

/** ����� ������ ��� ���������� ��������� ������� Enter ��� ������ ������
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class SearchArticlesListener extends KeyAdapter {

	/** ������ �� ������ ������ GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** ����������� ������ �������������� ���� */
	public SearchArticlesListener(GeneralView view) {
		this.view = view;		
	}
	
	/** ����� ��������� �������� ��� ������� �� ������� Enter ��� ������ ������ */
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
