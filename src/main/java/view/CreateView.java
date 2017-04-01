package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class CreateView extends AbstractClientView {
	
	private JButton button;
	private JTextField field;
	private JTextArea area;
	private JLabel fieldLabel;
	private JLabel areaLabel;
	
	public CreateView(String buttonName) {
		field = new JTextField();
		button = new JButton(buttonName);
		area = new JTextArea();
		fieldLabel = new JLabel("Name of article :");
		areaLabel = new JLabel("Content :");
	}
	
	public void setListener(ActionListener listener) {
		button.addActionListener(listener);
	}
	
	public JTextField getTextField() {
		return field;
	}
	
	public JTextArea getTextArea() {
		return area;
	}
	
	@Override
	public void decorateView() {
		panel.setLayout(null);
		
		button.setBounds(350, 325, 100, 20);
		fieldLabel.setBounds(5, 5, 100, 20);
		field.setBounds(5, 30, 780, 20);
		areaLabel.setBounds(5, 55, 100, 20);
		
		JScrollPane areaPane = new JScrollPane(area);
	    areaPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    areaPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    areaPane.setPreferredSize(new Dimension(600, 280));
		areaPane.setBounds(5, 80, 780, 230);
		
		panel.add(button);
		panel.add(fieldLabel);
		panel.add(field);
		panel.add(areaLabel);
		panel.add(areaPane);
	}

}
