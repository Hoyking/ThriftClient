package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ViewController;

/** ����� ������ ��� ���������� ��������� ������ �������� � ���� �������� ������ 
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public class CreateMenuItemListener implements ActionListener {

	/** ������ �� ������ ������ ViewController
	 * @see controller.ViewController
	 */
	private ViewController vc;
	
	/** ����������� ������ �������������� ���� */
	public CreateMenuItemListener(ViewController vc) {
		this.vc = vc;
	}
	
	/** ����� ��������� �������� ��� ������� �� ������ �������� � ���� �������� ������ */
	public void actionPerformed(ActionEvent e) {
		vc.setSelectedView(ViewController.SEARCH);
	}
	
}
