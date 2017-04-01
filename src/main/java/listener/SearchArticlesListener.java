package listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.GeneralView;

public class SearchArticlesListener extends KeyAdapter {

	private GeneralView view;
	
	public SearchArticlesListener(GeneralView view) {
		this.view = view;		
	}
	
	@Override
	public void keyTyped(KeyEvent event) {
		if((int)event.getKeyChar() == KeyEvent.VK_ENTER) {
			view.searchArticles();
			return;
		}
		if((int)event.getKeyChar() == KeyEvent.VK_ESCAPE) {
			view.searchModOff();
			return;
		}
	}

}
