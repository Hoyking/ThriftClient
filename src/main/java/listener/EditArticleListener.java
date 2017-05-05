package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

/** ����� ������ ��� ���������� ��������� ������ �������������� ������
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class EditArticleListener implements ActionListener {

	/** ������ �� ������ ������ GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** ����������� ������ �������������� ���� */
	public EditArticleListener(GeneralView view) {
		this.view = view;
	}
	
	/** ����� ��������� �������� ��� ������� �� ������ �������������� ������ */
	public void actionPerformed(ActionEvent e) {
		view.completeEditing();
		view.editModOff();
	}
	
}
