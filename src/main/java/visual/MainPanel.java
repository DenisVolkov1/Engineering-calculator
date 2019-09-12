package visual;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Calculations;

public class MainPanel extends JPanel {
	/**
	 * 
	 */
	private static final int countSaveExpression = 25;
	private String[] inputExpression = new String[countSaveExpression]; 
	private WindowInputOutput windowInputOutput = new WindowInputOutput();
	private Keyboard keyboard = new Keyboard();
	private static StringBuilder enter = new StringBuilder("0");
	private String enteredCharacter = null;
	private String answer;

	private boolean percentageIsPressed;
	private boolean backspaceIsPressed;
	private boolean enterIsPressed;
	private boolean squareRootIsPressed;
	private boolean cIsPressed;
	private boolean openBracesIsPressed;
	private boolean closeBracesIsPressed;
	private boolean isDigital;
	private boolean isOperatorsExceptMinus;
	private boolean isComma;
	private boolean isMinus;
	private boolean isLastDigit;
	private boolean isLastOperator;
	private boolean isLastCloseBraces;
	private boolean isLastOpenBraces;
	private boolean upViewLastExpressionIsPressed;
	private boolean downViewLastExpressionIsPressed;
	
	public MainPanel() {
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(500, 550));
		//Listeners
		KeyListener keyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				eventsHandle(e);
			}
		};
		ActionListener actionListener = this::eventsHandle;
		for (JButton button : keyboard.getjButtons()) {
			button.addActionListener(actionListener);
		}
	keyboard.getExactlyButton().addActionListener(actionListener);
	windowInputOutput.getInput().addKeyListener(keyListener);	

	add(windowInputOutput);	
	add(keyboard);	
	setVisible(true);	
	}
	private void eventsHandle(Object event) {
		determinantOfPressure(event);
		
		 if (isMaxLength()) {
			 correctInputOfBackspace();
			 correctInputOfC();
			 inputEnterHandler();
		 } else {
		 	inputEnterHandler();
			correctInputOfNumbersAndOperators();
			correctInputOfBackspace();
			correctInputOfPercentage();
			correctInputOfSquareRoot();
			correctInputOfC();
			correctInputOfBraces();
			pressUpHandler();
			pressDownHandler();
		 }
	setFontForLength();
	setFalse();
	}
	private void determinantOfPressure(Object objEvent) {
		//buttons
		if (objEvent.getClass() == java.awt.event.ActionEvent.class) {
			ActionEvent clickEvent = (ActionEvent) objEvent;
			enteredCharacter = clickEvent.getActionCommand();
			
			backspaceIsPressed =  clickEvent.getActionCommand().equals("backspace");
			enterIsPressed = clickEvent.getActionCommand().equals("=");
			squareRootIsPressed = clickEvent.getActionCommand().equals("\u221A");
			cIsPressed = clickEvent.getActionCommand().equals("C");
			openBracesIsPressed = clickEvent.getActionCommand().equals("(");
			closeBracesIsPressed = clickEvent.getActionCommand().equals(")");
			upViewLastExpressionIsPressed = clickEvent.getActionCommand().equals("UP");
			downViewLastExpressionIsPressed = clickEvent.getActionCommand().equals("DOWN");
			percentageIsPressed = clickEvent.getActionCommand().equals("%");
		//keyboard
		} else if (objEvent.getClass() == java.awt.event.KeyEvent.class) {
			KeyEvent keyEvent = (KeyEvent) objEvent;
			enteredCharacter = ((Character)keyEvent.getKeyChar()).toString();
		
			backspaceIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE);
			enterIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_ENTER);
			cIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_C);
			openBracesIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_9 && keyEvent.isShiftDown());
			closeBracesIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_0 && keyEvent.isShiftDown());
			upViewLastExpressionIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_UP);
			downViewLastExpressionIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_DOWN);
			percentageIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_5 && keyEvent.isShiftDown());
			squareRootIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_R);
			percentageIsPressed = (keyEvent.getKeyCode() == KeyEvent.VK_P);
		}
		 isDigital = enteredCharacter.matches("[0-9]");
		 isOperatorsExceptMinus = enteredCharacter.matches("/|[*+]");
		 isMinus = enteredCharacter.matches("-");
		 isComma = enteredCharacter.matches(",");
			 Character lastInputCharacter = enter.charAt(enter.length()-1);
			 isLastDigit = lastInputCharacter.toString().matches("[0-9]");
			 isLastOperator = lastInputCharacter.toString().matches("/|[*+]|-");
		isLastCloseBraces = lastInputCharacter.equals(')');
			 isLastOpenBraces = lastInputCharacter.equals('(');
	}
	private void correctInputOfNumbersAndOperators() {
		boolean isOnlyZero = enter.toString().equals("0"); 
		if (isLastCloseBraces && isDigital) return; 
		if (isOnlyZero && (isDigital || isMinus)) {
			enter.replace(0, 1, enteredCharacter);
			windowInputOutput.getInput().setText(enter.toString());
		} else {
			if (isDigital) appendAndViewExpression();
			if (isComma && isLastDigit) {
				if(!numberIsDouble()) appendAndViewExpression();
			}
			if (isLastDigit || isLastCloseBraces) {
				if (isOperatorsExceptMinus || isMinus) {
					appendAndViewExpression();
				}
			}
		}
		windowInputOutput.getInput().requestFocusInWindow();
	}
	private boolean numberIsDouble() {
		int index = enter.length()-1;
		while (index != -1) {
			Character ch = enter.charAt(index);
			if (ch == ',') return true;
			if (!ch.toString().matches("[0-9]")) return false;
			index--;
		}
		return false;
	}
	private void appendAndViewExpression() {
		enter.append(enteredCharacter);
		windowInputOutput.getInput().setText(enter.toString());
	}
	private void correctInputOfBackspace() {
		if (enter.length() == 1 && backspaceIsPressed) {
			enter.replace(0, 1, "0");
			windowInputOutput.getInput().setText(enter.toString());
		} else if (backspaceIsPressed) {
		
			enter.deleteCharAt(enter.length()-1);
			windowInputOutput.getInput().setText(enter.toString());
		}
	}
	private int indexSave;
	private boolean isFullQueue;
	private void saveAndViewExpression() {
		String str = enter.toString();
		inputExpression[indexSave] = str;
		windowInputOutput.getPostInput().setText(str);
		++indexSave;
		if (indexSave == countSaveExpression) {
			isFullQueue = true;
			indexSave = 0;
		}
		indexPop = indexSave;
	}
	private int indexPop;
	private void pressUpHandler() {
		if(upViewLastExpressionIsPressed) {
			if (inputExpression[0] == null) return;
			--indexPop;
			if (isFullQueue) {
				if (indexPop < 0) indexPop = countSaveExpression-1;
			} else {
				if (indexPop < 0) indexPop = indexSave-1;
			}
			String popString = inputExpression[indexPop];
			enter = new StringBuilder(popString);
			windowInputOutput.getInput().setText(enter.toString());
			setFontForLength();
		}
	}
	private void pressDownHandler() {
		if(downViewLastExpressionIsPressed) {
			if (inputExpression[0] == null) return;
			++indexPop;
			
			if (isFullQueue) {
				if (indexPop > countSaveExpression-1) indexPop = 0;
			} else {
				if (indexPop >= indexSave-1) indexPop = 0;
			}
			String popString = inputExpression[indexPop];
			enter = new StringBuilder(popString);
			windowInputOutput.getInput().setText(enter.toString());
			setFontForLength();
		}
	}
	private void inputEnterHandler() {
	
		if (enterIsPressed) {
			saveAndViewExpression();
					
			try {
				String replaceCommaOnPoint = enter.toString().replace(',', '.');
				answer = Calculations.calc(replaceCommaOnPoint);
				switch (answer) {
				case "NaN":
					enter = new StringBuilder("0");
					windowInputOutput.getInput().setText("Not a Number!");
					return;
				case "-Infinity":
					enter = new StringBuilder("0");
					windowInputOutput.getInput().setText("-Infinity!");
					return;	
				case "Infinity":
					enter = new StringBuilder("0");
					windowInputOutput.getInput().setText("Infinity!");
					return;	
				}
				
				String answerRe = answer.replace('.', ',');
				windowInputOutput.getInput().setText(answerRe);
				enter = new StringBuilder(answerRe);
			} catch (Exception e) {
				windowInputOutput.getInput().setText("Error input expression!");
				saveAndViewExpression();
				enter = new StringBuilder("0");
			}
		}
	}
	private void correctInputOfPercentage() {
		 if (percentageIsPressed) {
			 if (isLastDigit || isLastCloseBraces) {
						 enter.append("%");
			windowInputOutput.getInput().setText(enter.toString()); 
			 }
		 }
	}
	private void correctInputOfSquareRoot() {
		
		boolean zeroEnter = enter.toString().equals("0");
		boolean zeroDoubleEnter = enter.toString().equals("0,0");
		if ((zeroEnter || zeroDoubleEnter) && squareRootIsPressed) {
			enter.replace(0, enter.length(), "\u221A");
			windowInputOutput.getInput().setText(enter.toString());
		} else if (squareRootIsPressed) {
			if (isLastOperator || isLastOpenBraces) { 
				enter.append("\u221A");
				windowInputOutput.getInput().setText(enter.toString());
				String replacePointOnComma = enter.toString().replace('.', ',');
				windowInputOutput.getInput().setText(replacePointOnComma);
			}
		}
	}
	private void correctInputOfC() {
		if (cIsPressed) {  
			this.requestFocusInWindow();
			enter = new StringBuilder("0");
			windowInputOutput.getInput().setText(enter.toString());
		}
		windowInputOutput.getInput().requestFocusInWindow();
	}
	private void correctInputOfBraces() {
		boolean zeroEnter = enter.toString().equals("0");
		boolean zeroDoubleEnter = enter.toString().equals("0,0");
		
		if ((zeroEnter || zeroDoubleEnter) && openBracesIsPressed) {
			enter.replace(0, 1, "(");
			windowInputOutput.getInput().setText(enter.toString());
		} else if (openBracesIsPressed && !isLastDigit) {
			enter.append("(");
			windowInputOutput.getInput().setText(enter.toString());
		} else if ((zeroEnter || zeroDoubleEnter) && closeBracesIsPressed) {
			return;
		} else if (closeBracesIsPressed && !isLastOperator) {
			enter.append(")");
			windowInputOutput.getInput().setText(enter.toString());
		}
	}
	private void setFalse() {
		 percentageIsPressed    = false;
		 backspaceIsPressed     = false;
		 enterIsPressed         = false;
		 squareRootIsPressed    = false;
		 cIsPressed             = false;
		 openBracesIsPressed    = false;
		 closeBracesIsPressed   = false;
		 isDigital              = false;
	     isOperatorsExceptMinus = false; 
		 isMinus                = false;
		 isComma                = false;
		 isLastDigit            = false;
		 isLastOperator         = false;
		 isLastCloseBraces      = false;
		 isLastOpenBraces       = false;
		upViewLastExpressionIsPressed   = false;
		 downViewLastExpressionIsPressed = false;
	}
	private boolean isMaxLength() {
		JLabel input = windowInputOutput.getInput();
		int length = input.getText().length();
		return length == 43;
	}
	private void setFontForLength() {
		JLabel input = windowInputOutput.getInput();
			int length = input.getText().length();
			if (length < 21) {
				input.setFont(new Font("Arial", Font.PLAIN, 33));
			} else if (length > 21 && length < 27) {	
				input.setFont(new Font("Arial", Font.PLAIN, 25));
			} else if (length > 27 && length < 35) {
				input.setFont(new Font("Arial", Font.PLAIN, 20));
			} else if (length > 35) {
				input.setFont(new Font("Arial", Font.PLAIN, 17));	
			} 
	}
	public WindowInputOutput getWindowInputOutput() {
		return windowInputOutput;
	}
}

