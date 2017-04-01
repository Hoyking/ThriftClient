package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

public class SearchMenuItemListener implements ActionListener {

	private GeneralView view;
	
	public SearchMenuItemListener(GeneralView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		view.searchModOn();
	}

}
