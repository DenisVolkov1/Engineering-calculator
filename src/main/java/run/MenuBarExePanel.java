package run;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.script.ScriptException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import logic.Calculations;
import visual.AboutTheProgram;
import visual.ConversionUnits;
import visual.MainPanel;
import visual.ShortVersion;

public class MenuBarExePanel extends JFrame {
	private JMenuBar jMenuBar;
	private static MainPanel mainPanel = new MainPanel();

	public MenuBarExePanel() {
		// первоночальная инициализация.
		// для этого вызываем метод calc();
		try {
			Calculations.calc("1+1");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		setLayout(new FlowLayout());
		setSize(440, 590);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		jMenuBar = new JMenuBar();
			jMenuBar.setFont(new Font("Arial", Font.PLAIN, 25));
		JMenu about = new JMenu("About");
		JMenu additionally = new JMenu("Additionally");
		//
		JMenuItem aboutTheProg = new JMenuItem("About the program");
		JMenuItem conversion = new JMenuItem("Conversion units");
		JMenuItem shortVersion = new JMenuItem("Short version");
			about.setFont(new Font("Arial", Font.PLAIN, 17));
			aboutTheProg.setFont(new Font("Arial", Font.PLAIN, 15));
			additionally.setFont(new Font("Arial", Font.PLAIN, 17));
			conversion.setFont(new Font("Arial", Font.PLAIN, 15));
			shortVersion.setFont(new Font("Arial", Font.PLAIN, 15));
		
		additionally.add(shortVersion);
		additionally.add(conversion);
		about.add(aboutTheProg);
		jMenuBar.add(additionally);
		jMenuBar.add(about);
		ActionListener actionListener = n -> {
			if (n.getActionCommand().equals("Conversion units")) {
				SwingUtilities.invokeLater(()-> new ConversionUnits());
			}
			if (n.getActionCommand().equals("About the program")) {
				SwingUtilities.invokeLater(()-> AboutTheProgram.getInstance());
			}
			if (n.getActionCommand().equals("Short version")) {
				new ShortVersion(this);
			}
		};
		shortVersion.addActionListener(actionListener);
		conversion.addActionListener(actionListener);
		aboutTheProg.addActionListener(actionListener);
	setJMenuBar(jMenuBar);
	setContentPane(mainPanel);
	setVisible(true);		
	}
	public static MainPanel getMainPanel() {
		return mainPanel;
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(MenuBarExePanel::new);
	}
}
