package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public abstract class AbstractClientView {
	
	protected JPanel panel;
	
	public AbstractClientView() {
		panel = new JPanel();
		panel.setSize(800, 365);
		panel.setLayout(new BorderLayout());
	}
	
	protected void decorateView() {}

	public JPanel getPanel() {
		return panel;
	}
	
}
