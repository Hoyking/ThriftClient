package listener;

import java.awt.event.ActionEvent;

import view.GeneralView;

public class CreateMenuItemListener {

	private GeneralView view;
	
	public CreateMenuItemListener(GeneralView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		view.deleteModOn();
	}
	
}
