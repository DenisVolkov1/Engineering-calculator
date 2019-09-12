package visual.adapters;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

public class MyMotionAdapter extends MouseMotionAdapter {
	
	private MyInputAdapter myIA;
	private JFrame frame;
	
	public MyMotionAdapter(JFrame jFrame, MyInputAdapter myInputAdapter) {
		this.frame = jFrame;
		this.myIA = myInputAdapter;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		frame.setLocation(e.getXOnScreen() - myIA.getX(), e.getYOnScreen() - myIA.getY());
	}
}
