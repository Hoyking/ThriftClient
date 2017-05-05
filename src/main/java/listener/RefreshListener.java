package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ConnectionController;
import view.GeneralView;

/** ����� ������ ��� ���������� ��������� ������ ���������� ���������� 
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class RefreshListener implements ActionListener{

	/** ������ �� ������ ������ GeneralView
	 * @see view.GeneralView
	 */
	private GeneralView view;
	
	/** ����������� ������ �������������� ���� */
	public RefreshListener(GeneralView view) {
		this.view = view;
	}
	
	/** ����� ��������� �������� ��� ������� �� ������ ���������� ���������� */
	@Override
	public void actionPerformed(ActionEvent e) {
		view.loadTitles(ConnectionController.getClientStub().getTitles());
		view.repaint();
	}

}
