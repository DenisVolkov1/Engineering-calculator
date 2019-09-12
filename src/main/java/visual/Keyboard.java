package visual;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Keyboard extends JPanel {
	
	private static final Border border = BorderFactory.createLineBorder(Color.ORANGE, 1);
	private JPanel keyboardGrid = new JPanel();
	private JButton[] jButtons = new JButton[24];
	private JButton exactlyButton = new JButton("=");

	public Keyboard() {
		keyboardGrid.setPreferredSize(new Dimension(380, 480));
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		setLayout(gridBagLayout);
		
		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.gridy = GridBagConstraints.RELATIVE; 
		constraints.insets = new Insets(7, 7, 7, 7);
		for (int i = 0; i < 24;i++) {
			jButtons[i] = new JButton();
			jButtons[i].setFont(new Font("Arial", Font.PLAIN, 25));
		}
		for (JButton jButton : jButtons) {
			jButton.setPreferredSize(new Dimension(65, 65));
			jButton.setBorder(border);
		}
		for (JButton jButton : jButtons) {
			jButton.addMouseListener(new MouseAdapter() {
				// set hover effect
				@Override
				public void mouseEntered(MouseEvent e) {
					jButton.setBackground(new Color(210,210,200));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					jButton.setBackground(new Color(190,200,190));
					setColorBackground();
				}
				// set background on pressed
				@Override
				public void mousePressed(MouseEvent e) {
					jButton.setBackground(new Color(255,255,255));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					jButton.setBackground(new Color(190,200,190));
					setColorBackground();
				}
			});
			exactlyButton.addMouseListener(new MouseAdapter() {
				// set hover effect
				@Override
				public void mouseEntered(MouseEvent e) {
					exactlyButton.setBackground(new Color(210,210,200));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					exactlyButton.setBackground(new Color(160,200,190));
				}
				// set background on pressed
				@Override
				public void mousePressed(MouseEvent e) {
					exactlyButton.setBackground(new Color(255,255,255));
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					exactlyButton.setBackground(new Color(160,200,190));
				}
			});
			jButton.setBackground(new Color(190,200,190));
			exactlyButton.setBackground(new Color(160,200,190));
			gridBagLayout.setConstraints(jButton, constraints);
			keyboardGrid.add(jButton);
		}
		setColorBackground();
		jButtons[0].setText("UP");
		jButtons[1].setText("DOWN");
		jButtons[2].setText("C");
		
		jButtons[4].setText("<-");
		jButtons[4].setActionCommand("backspace");
		//
		jButtons[5].setText("1");
		jButtons[6].setText("2");
		jButtons[7].setText("3");
		//
		jButtons[10].setText("4");
		jButtons[11].setText("5");
		jButtons[12].setText("6");
		//
		jButtons[15].setText("7");
		jButtons[16].setText("8");
		jButtons[17].setText("9");
		jButtons[18].setText("0");
		//
		jButtons[19].setText(",");
		jButtons[20].setText("+");
		jButtons[21].setText("-");
		jButtons[22].setText("/");
		jButtons[23].setText("*");
		//
		jButtons[13].setText("\u221A");
		jButtons[14].setText("%");
		//
		jButtons[9].setText(")");
		jButtons[8].setText("(");
		//
		jButtons[0].setFont(new Font("Arial", Font.PLAIN, 15));
		jButtons[1].setFont(new Font("Arial", Font.PLAIN, 15));
		//setExactlyB
		exactlyButton.setBorder(border);
		exactlyButton.setPreferredSize(new Dimension(137, 65));
		exactlyButton.setFont(new Font("Arial", Font.PLAIN, 30));
		
		gridBagLayout.setConstraints(exactlyButton, constraints);
		keyboardGrid.add(exactlyButton);
	add(keyboardGrid);	
	setVisible(true);	
	}
	private void setColorBackground() {
		Color digitColorButton = new Color(170,180,170);
		for (int i = 5; i < 8; i++) {
			jButtons[i].setBackground(digitColorButton);
		}
		for (int i = 10; i < 13; i++) {
			jButtons[i].setBackground(digitColorButton);
		}
		for (int i = 15; i < 18; i++) {
			jButtons[i].setBackground(digitColorButton);
		}
	}
	public JButton[] getjButtons() {
		return jButtons;
	}
	public JButton getExactlyButton() {
		return exactlyButton;
	}
}
