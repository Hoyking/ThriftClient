package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GeneralView;

public class DeleteArticleListener implements ActionListener {

	private GeneralView view;
	
	public DeleteArticleListener(GeneralView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		view.deleteArticles();
		view.deleteModOff();
	}
	
}
