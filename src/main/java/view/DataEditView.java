package view;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JTextField;

import component.Field;

public class DataEditView extends AbstractClientView{

	private JButton button;
	private Map<String, Field> fields;
	
	public DataEditView(String buttonName) {
		fields = new HashMap<String, Field>();
		button = new JButton(buttonName);
	}
	
	public void setListener(ActionListener listener) {
		button.addActionListener(listener);
	}
	
	public void addField(String key, Field value) {
		fields.put(key, value);
	}
	
	public JTextField getTextField(String key) {
		return fields.get(key).getTextField();
	}
	
	@Override
	public void decorateView() {
		panel.setLayout(null);
		
		button.setBounds(350, 150, 100, 20);
		
		for(Field field: fields.values()) {
			panel.add(field.getContainer());
		}
		panel.add(button);
	}
	
}
