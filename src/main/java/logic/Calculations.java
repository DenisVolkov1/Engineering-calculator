package logic;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import logic.enum_format.Capacity;
import logic.enum_format.Sqare;
import logic.enum_format.Temperature;
import logic.enum_format.Time;
import logic.enum_format.Weight;

public class Calculations {
	private static final ScriptEngineManager manager = new ScriptEngineManager();
	private static final ScriptEngine engine = manager.getEngineByName("js");

	public static String calc(String input) throws ScriptException {
		String percentageHandle = percentageHandleInput(input);
		String squareHandleInput = squareHandleInput(percentageHandle);
		return calcExpression(squareHandleInput);
	}
	private static String calcExpression(String str) throws ScriptException {
		Object result = null;
		result = engine.eval("("+str+").toFixed(8)");
		//trim zero
		String resWithZero = result.toString();
		int endIndex = resWithZero.length()-1;
		boolean isComma = resWithZero.contains(".");
		while (endIndex != 0 && isComma) {
			char ch = resWithZero.charAt(endIndex);
			if (ch == '.') {
				endIndex--; 
				break;
			}
			if (ch == '0') {
				--endIndex;
			} else break;
		}
		return resWithZero.substring(0, endIndex+1);
	}
	private static String squareHandleInput(String inputExpression) {
		int indexRoot = inputExpression.indexOf((int)'\u221A');
		while (indexRoot != -1) {
			StringBuilder replacement = null;
			String nextCharForRoot = Character.toString(inputExpression.charAt(indexRoot+1));
			boolean isOpenBraces = nextCharForRoot .equals("(");
			if (isOpenBraces) {
				replacement = new StringBuilder();
				replacement.append("Math.sqrt(");
				int nextChar = indexRoot+2;
				while (nextChar < inputExpression.length()) {
					boolean b1 = Character.toString(inputExpression.charAt(nextChar)).matches("\\)");
					if (b1) break; 
					replacement.append(Character.toString(inputExpression.charAt(nextChar)));
					++nextChar;
				}
				replacement.append(")");
				inputExpression = inputExpression.replace(inputExpression.subSequence(indexRoot, nextChar+1), replacement);
				indexRoot = inputExpression.indexOf((int)'\u221A');
				
			} else {
				replacement = new StringBuilder();
				replacement.append("Math.sqrt(");
				int nextChar = indexRoot+1;
			
				while (nextChar < inputExpression.length()) {
					boolean b1 = Character.toString(inputExpression.charAt(nextChar)).matches("\\.|[0-9]|\u221A");
					if (!b1) break; 
					replacement.append(Character.toString(inputExpression.charAt(nextChar)));
					++nextChar;
				}
				replacement.append(")");
				inputExpression = inputExpression.replace(inputExpression.subSequence(indexRoot, nextChar), replacement);
				indexRoot = inputExpression.indexOf((int)'\u221A');
			}
		}
		return inputExpression;		
	}
	private static String percentageHandleInput(String inputExpression) {
		int indexPercentage = inputExpression.indexOf('%');
		if (indexPercentage == -1) return inputExpression;
		String prefCharForPercentage = Character.toString(inputExpression.charAt(indexPercentage-1));
		StringBuilder leftPart = null;
		Character operator = null;
		StringBuilder rightPart = null;
		boolean isClosesBraces = prefCharForPercentage .equals(")");
	
		int prefCharIndex = indexPercentage-1;
		
		if (isClosesBraces) {
			while (prefCharIndex > -1) {
				boolean b1 = Character.toString(inputExpression.charAt(prefCharIndex)).matches("\\(");
				if (b1) {
					operator = inputExpression.charAt(prefCharIndex-1);
					break;
				}
				--prefCharIndex;
			}
			rightPart = new StringBuilder(inputExpression.subSequence(prefCharIndex, indexPercentage));
			leftPart = new StringBuilder(inputExpression.subSequence(0, prefCharIndex-1));
			return resultBilder(leftPart, rightPart, operator);
			
		} else {
			while (prefCharIndex > -1) {
				boolean b1 = Character.toString(inputExpression.charAt(prefCharIndex)).matches("\\.|[0-9]|\u221A");
				if (!b1) {
					operator = inputExpression.charAt(prefCharIndex);
					break;
				}
				--prefCharIndex;
			}
			rightPart = new StringBuilder(inputExpression.subSequence(prefCharIndex+1, indexPercentage));
			leftPart = new StringBuilder(inputExpression.subSequence(0, prefCharIndex));
			return resultBilder(leftPart, rightPart, operator);
		}
	}
	private static String resultBilder(StringBuilder leftPart, StringBuilder rightPart, Character operator) {
		if (operator == '*') {
			return "((" +
					leftPart +
					")/100)" +
					operator +
					rightPart;
		} else if (operator == '-') {
			String result = String.valueOf(leftPart) +
					"-(((" +
					leftPart +
					")/100)*" +
					rightPart +
					")";
			return result;
			
		} else if (operator == '+') {
			String result = String.valueOf(leftPart) +
					"+(((" +
					leftPart +
					")/100)*" +
					rightPart +
					")";
			return result;
		} 
		return null;
	}
	public static String calcConversion(String input, Object selectOfConvert, Object selectInConvert ) throws ScriptException {
		if (selectOfConvert.getClass() == Weight.class) {
			Double f1 = ((Weight)selectOfConvert).getFactor();
			Double f2 = ((Weight)selectInConvert).getFactor();
			return calcExpression("" + input + "*" + "(" + f2 +"/"+ f1 +")");  //input * (f2 / f1);
		} else if (selectOfConvert.getClass() == Sqare.class) {
			Double f1 = ((Sqare)selectOfConvert).getFactor();
			Double f2 = ((Sqare)selectInConvert).getFactor();
			return calcExpression("" + input + "*" + "(" + f2 +"/"+ f1 +")");
		} else if (selectOfConvert.getClass() == Capacity.class) {
			Double f1 = ((Capacity)selectOfConvert).getFactor();
			Double f2 = ((Capacity)selectInConvert).getFactor();
			return calcExpression("" + input + "*" + "(" + f2 +"/"+ f1 +")");
		}  else if (selectOfConvert.getClass() == Time.class) {
			Double f1 = ((Time)selectOfConvert).getFactor();
			Double f2 = ((Time)selectInConvert).getFactor();
			return calcExpression("" + input + "*" + "(" + f2 +"/"+ f1 +")");
		} else if (selectOfConvert.getClass() == Temperature.class) {
			Double f1 = ((Temperature)selectOfConvert).getFactor();
			Double f2 = ((Temperature)selectInConvert).getFactor();
			if (selectOfConvert.toString().equals("Degrees kelvin")) return calcExpression("" + input +"+"+ f2);
			if (selectOfConvert.toString().equals("Degrees celsius")) return calcExpression("" + input +"+"+ "-1*" + "f1");
		}
		return "0";
	}
}
