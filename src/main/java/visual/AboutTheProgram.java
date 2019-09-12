package visual;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AboutTheProgram extends JFrame {
	
	private static final AboutTheProgram ABOUT_THE_PROGRAM = new AboutTheProgram();
	
	private JPanel image = new JPanel() {
		private BufferedImage bufferedImage;
		{
			try {
				bufferedImage = ImageIO.read(AboutTheProgram.class.getClassLoader().getResource("about.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(bufferedImage, 0, 0, this);
		}
	};
	private AboutTheProgram() {
		setLayout(new FlowLayout());
		setSize(350, 400);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	setContentPane(image);
	setVisible(true);			
	}
	static public AboutTheProgram getInstance() {
		if (!ABOUT_THE_PROGRAM.isShowing()) {
			ABOUT_THE_PROGRAM.show();
		}
		return ABOUT_THE_PROGRAM;
	}
}
