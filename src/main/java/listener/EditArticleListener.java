package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

public class EditArticleListener implements ActionListener {

private GeneralView view;
	
	public EditArticleListener(GeneralView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		view.completeEditing();
		view.editModOff();
	}
	
}
