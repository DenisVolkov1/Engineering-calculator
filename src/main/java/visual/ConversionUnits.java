package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import logic.Calculations;
import logic.enum_format.Capacity;
import logic.enum_format.Sqare;
import logic.enum_format.Temperature;
import logic.enum_format.Time;
import logic.enum_format.Weight;

public class ConversionUnits extends JFrame {
	private static final Border border = BorderFactory.createLineBorder(Color.ORANGE, 1);
	private static final String[] typeConvert = {"---------------------------------------------","Sqare","Weight","Capacity","Time","Temperature"};
	
	private JPanel panel = new JPanel();
	private JComboBox<String> selectConvertible = new JComboBox<>(typeConvert);
	private JComboBox selectOfConvert = new JComboBox<>(Sqare.values());
	private JComboBox selectInConvert = new JComboBox<>(Sqare.values());
	private JTextField ofField = new JTextField();
	private JTextField result = new JTextField();
	private JLabel title = new JLabel("Select the type of convertible");
	private JLabel of = new JLabel("Of");
	private JLabel in = new JLabel("In");
	private JButton buttonResult = new JButton("Result");
	
	public ConversionUnits() {
		buttonResult.setPreferredSize(new Dimension(80, 25));
		buttonResult.setBorder(border);
		buttonResult.setBackground(new Color(160,200,190));
		buttonResult.addMouseListener(new MouseAdapter() {
			// set hover effect
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonResult.setBackground(new Color(210,210,200));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonResult.setBackground(new Color(160,200,190));
			}
			// set background on pressed
			@Override
			public void mousePressed(MouseEvent e) {
				buttonResult.setBackground(new Color(255,255,255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				buttonResult.setBackground(new Color(160,200,190));
			}
		});
		//
		ofField.setPreferredSize(new Dimension(216, 30));
		result.setPreferredSize(new Dimension(216, 30));
		result.setDisabledTextColor(Color.BLACK);
		result.setEditable(false);
	
		panel.setLayout(new FlowLayout());
		selectConvertible.addActionListener(n-> {
			String item = (String) selectConvertible.getSelectedItem();
			if (item.equals("Sqare")) {
				selectOfConvert = new JComboBox<Sqare>(Sqare.values());
				selectInConvert = new JComboBox<Sqare>(Sqare.values());
				isEnableAll(true);
				refreshPanel();
			} else if (item.equals("Weight")) {
				selectOfConvert = new JComboBox<Weight>(Weight.values());
				selectInConvert = new JComboBox<Weight>(Weight.values());
				isEnableAll(true);
				refreshPanel();
			} else if (item.equals("Capacity")) {
				selectOfConvert = new JComboBox<Capacity>(Capacity.values());
				selectInConvert = new JComboBox<Capacity>(Capacity.values());
				isEnableAll(true);
				refreshPanel();
			} else if (item.equals("Time")) {
				selectOfConvert = new JComboBox<Time>(Time.values());
				selectInConvert = new JComboBox<Time>(Time.values());
				isEnableAll(true);
				refreshPanel();
			} else if (item.equals("Temperature")) {
				selectOfConvert = new JComboBox<Temperature>(Temperature.values());
				selectInConvert = new JComboBox<Temperature>(Temperature.values());
				isEnableAll(true);
				refreshPanel();
			} else if (item.equals("---------------------------------------------")) {
				isEnableAll(false);
			}
		});
		selectConvertible.setSelectedIndex(0);
		buttonResult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String inputString = ofField.getText();
				if (inputString.length() == 0) {
					JOptionPane.showMessageDialog(null,"Field input is empty!","Error!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String value = ofField.getText();
				
				String resValue = null;
				try {
					resValue = Calculations.calcConversion(value, selectOfConvert.getSelectedItem(), selectInConvert.getSelectedItem());
				} catch (ScriptException e1) {
					result.setText("Error input!!!");
					return;
				}
				result.setText(resValue);
			}
		});
		ofField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char inputKey = e.getKeyChar();
				String inputSymbol = Character.toString(inputKey);
				boolean backspaceIsPressed = e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
				System.out.println(inputSymbol);
				
				boolean isDouble = inputSymbol.matches("[0-9]|[.]");
				if (!isDouble && !backspaceIsPressed) {
					JOptionPane.showMessageDialog(null,"Input only numbers and point(decimal)!!!","Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		refreshPanel();
		setLayout(new FlowLayout());
		setResizable(false);
		setSize(235, 270);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	
	setContentPane(panel);
	setVisible(true);		
	}
	private void refreshPanel() {
		panel.removeAll();
		panel.add(title);
		panel.add(selectConvertible);
		panel.add(of);
		panel.add(selectOfConvert);
		panel.add(ofField);
		panel.add(in);
		panel.add(selectInConvert);
		panel.add(result);
		panel.add(buttonResult);
		panel.invalidate();
		panel.validate();
		panel.repaint();
	}
	private void isEnableAll(boolean b) {
		selectOfConvert.setEnabled(b);
		selectInConvert.setEnabled(b);
		ofField.setEnabled(b);
		buttonResult.setVisible(b);
	}
}
