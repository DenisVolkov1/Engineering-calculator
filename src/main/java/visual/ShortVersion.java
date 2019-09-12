package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import run.MenuBarExePanel;
import visual.adapters.MyInputAdapter;
import visual.adapters.MyMotionAdapter;

public class ShortVersion extends JFrame {
	
	public MainPanel mainPanel = MenuBarExePanel.getMainPanel();
	
	public ShortVersion(JFrame frame) {
		mainPanel.getWindowInputOutput().getPostInput().setText("Mouse2 - for normal state.");
		frame.setVisible(false);
		setBackground(Color.BLACK);
		setLayout(new FlowLayout());
		setSize(new Dimension(408, 88));
		setLocation(30, 40);

			MyInputAdapter adapter = new MyInputAdapter(this, frame);	
		this.addMouseListener(adapter);
			MyMotionAdapter adapter2 = new MyMotionAdapter(this, adapter);
		this.addMouseMotionListener(adapter2);
		
	setUndecorated(true);	
	setContentPane(mainPanel);	
	setVisible(true);
	}
}
