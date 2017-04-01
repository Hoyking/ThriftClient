package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

public class DeleteMenuItemListener implements ActionListener {

	private GeneralView view;
	
	public DeleteMenuItemListener(GeneralView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		view.deleteModOn();
	}

}
