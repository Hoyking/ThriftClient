package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

public class EditMenuItemListener implements ActionListener {

	private GeneralView view;
	
	public EditMenuItemListener(GeneralView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		view.deleteModOff();
		view.editModOn();
	}
	
}
