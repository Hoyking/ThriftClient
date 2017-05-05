package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/** ���������� �����-�������� ��� �������-�������������
 * 
 * @author Parfenenko Artem
 * @version 1.0
 *
 */
public abstract class AbstractClientView {
	
	/** ������ �� ������ ������ JPanel (������ ���� ������-�������������) */
	protected JPanel panel;
	
	/** ����������� �����. ������� ������ ������ JPanel, ������ ������� � ����� */
	public AbstractClientView() {
		panel = new JPanel();
		panel.setSize(800, 365);
		panel.setLayout(new BorderLayout());
	}
	
	/** ����� ��� ������������ ������������� */
	protected void decorateView() {}

	public JPanel getPanel() {
		return panel;
	}
	
}


