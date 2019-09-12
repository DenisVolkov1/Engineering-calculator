package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class WindowInputOutput extends JPanel {
	private Border border = BorderFactory.createLineBorder(Color.ORANGE, 4);
	private JLabel input;
	private JLabel postInput;

	private JPanel textPanel = new JPanel() {
		@Override
		public void paint(Graphics g) {
			g.setColor(Color.RED);
			super.paint(g);
			g.drawLine(396, 31, 346, 31);
		}
	};
	public WindowInputOutput() {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		setPreferredSize(new Dimension(402, 80));
		setBackground(Color.WHITE);
			input = new JLabel("0", SwingConstants.RIGHT);
			input.setFont(new Font("Arial", Font.PLAIN, 33));
			input.setFocusable(true);
			postInput = new JLabel("post", SwingConstants.RIGHT);
			postInput.setFont(new Font("Arial", Font.PLAIN, 16));
			postInput.setVerticalAlignment(SwingConstants.CENTER);
				textPanel.setLayout(new GridLayout(2,1,0,0));
				textPanel.setBackground(Color.WHITE);
				textPanel.setPreferredSize(new Dimension(385, 70));
				textPanel.add(postInput);
				textPanel.add(input);
		layout.putConstraint(SpringLayout.EAST, textPanel, -6, SpringLayout.EAST, this);	
		layout.putConstraint(SpringLayout.SOUTH, textPanel, -1, SpringLayout.SOUTH, this);
		add(textPanel);	
		setBorder(border);
	}
	public JLabel getInput() {
		return input;
	}
	public JLabel getPostInput() {
		return postInput;
	}
}
