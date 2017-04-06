package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.ViewController;

public class CreateMenuItemListener implements ActionListener {

	private ViewController vc;
	
	public CreateMenuItemListener(ViewController vc) {
		this.vc = vc;
	}
	
	public void actionPerformed(ActionEvent e) {
		vc.setSelectedView(ViewController.SEARCH);
	}
	
}
