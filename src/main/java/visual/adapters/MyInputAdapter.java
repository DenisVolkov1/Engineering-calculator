package visual.adapters;

import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;
import visual.ShortVersion;

public class MyInputAdapter extends MouseInputAdapter {

	private int x;
	private int y;
	private ShortVersion shorty;
	private JFrame normal;
	
	public MyInputAdapter(ShortVersion shorty, JFrame normal) {
		this.shorty = shorty;
		this.normal = normal;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			shorty.setVisible(false);
			normal.setVisible(true);
			normal.setContentPane(shorty.mainPanel);
			normal.invalidate();
			normal.validate();
			normal.repaint();
			boolean post = shorty.mainPanel.getWindowInputOutput().getPostInput().getText().equals("Mouse2 - for normal state.");
			if (post) shorty.mainPanel.getWindowInputOutput().getPostInput().setText("post");
		}
		x = e.getX();
		y = e.getY();
	}
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
}
