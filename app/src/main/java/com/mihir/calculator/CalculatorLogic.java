package com.mihir.calculator;

import android.util.Log;

public class CalculatorLogic {
	double num1;
	String operator;
	boolean shouldClearText;
	double result;

	String currentDisplayText;
	String currentDisplayMiniText;

	String finalDisplayText;
	String finalDisplayMiniText;

	public String getFinalDisplayText() {
		return finalDisplayText;
	}

	public String getFinalDisplayMiniText() {
		return finalDisplayMiniText;
	}

	public CalculatorLogic() {
		num1 = 0.0;
		operator = "";
		shouldClearText = false;
//		Log.d("Constructor", Boolean.valueOf(shouldClearText).toString());
		result = 0.0;

		currentDisplayText = Double.valueOf(num1).toString();
		currentDisplayMiniText = Double.valueOf(num1).toString();

		finalDisplayText = Double.valueOf(num1).toString();
		finalDisplayMiniText = Double.valueOf(num1).toString();
	}

	public void calculate(String buttonText) {
		currentDisplayMiniText = finalDisplayMiniText;
		currentDisplayText = finalDisplayText;

		switch (buttonText) {
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
			numberEntered(buttonText);
			break;
		case "x":
		case "/":
		case "-":
		case "+":
			operatorEntered(buttonText);
			break;
		case "=":
			calculateResult();
			break;
		case "B":
			backspaceEntered();
			break;
		case "C":
			clearScreen(buttonText);
			break;
		case "+/-":
			toggleSign();
			break;
		default:
			miscOperatorEntered(buttonText);
			break;
		}
		standardizeDisplay();
	}

	private void standardizeDisplay() {
		switch(finalDisplayText) {
		case "0.0":
		case "-0.0":
			finalDisplayText="0";
			break;
		default:
			double displayNumber = Double.parseDouble(finalDisplayText);
			if ((displayNumber == Math.floor(displayNumber)) && !Double.isInfinite(displayNumber)) {
			    finalDisplayText = String.valueOf((int) Math.floor(displayNumber));
			}
		}
	}

		private void numberEntered(String number) {
		System.out.println(".c text in num enter = " + currentDisplayText);
		String existingDisplay = currentDisplayText;
		Log.d("numberEntered", Boolean.valueOf(shouldClearText).toString());

		if (shouldClearText) {
			existingDisplay = "0.0";
			Log.d("numberEntered", "existingDisplay is " + existingDisplay + "in shouldClearText == true");
			shouldClearText = false;
		}

		if (existingDisplay.equals("0") || existingDisplay.equals("0.0")) {
			existingDisplay = "";
			Log.d("numberEntered", "existingDisplay is " + existingDisplay + "in existingDisplay.equals==0");
		}


		Log.d("numberEntered", "existingDisplay is " + existingDisplay + "right before finalDisplayText is calculated");
		finalDisplayText = existingDisplay + number;
		System.out.println("1 text = " + finalDisplayText);
	}

	// to toggle sign
	private void toggleSign() {
		double number = Double.parseDouble(currentDisplayText);

		number = -number;
		System.out.println("Number is " + number);

		finalDisplayText = Double.valueOf(number).toString();
	}

	private void clearScreen(String clearButtonText) {
		switch (clearButtonText) {
		case "C":
			finalDisplayText = "0";
			num1 = 0;
			operator = "";
			break;
		}
	}

	private void backspaceEntered() {
		Log.d("backspaceEntered", Boolean.valueOf(shouldClearText).toString());
		if (shouldClearText == true) {
			finalDisplayText = "0.0";
			return;
		}

		int length = currentDisplayText.length();

		if (length > 0) {
			String newText = currentDisplayText.substring(0, length - 1);

			if (newText.equals("-")) {
				newText = "0";
			}

			if (newText.equals("")) {
				newText = "0";
			}

			finalDisplayText = newText;
		}

	}

	private void operatorEntered(String operation) {
		Log.d("operatorEntered", "invoked");
		System.out.println("current_display_text: " + currentDisplayText);
		num1 = Double.parseDouble(currentDisplayText);

		if (!operator.equals("")) {
			performOperation(result, num1, "" + operator);
			finalDisplayMiniText = currentDisplayMiniText + " " + currentDisplayText + " " + operation;
		} else {
			result = num1;
			finalDisplayMiniText = currentDisplayText + " " +  operation;
		}

		operator = operation;

		Log.d("operatorEntered", Boolean.valueOf(shouldClearText).toString());
		shouldClearText = true;
	}

	private void miscOperatorEntered(String operation) {
		Log.d("miscOperatorEntered", operation);
	}

	private void calculateResult() {
		double num2 = Double.parseDouble(currentDisplayText);

		performOperation(result, num2, operator);

		finalDisplayText = String.valueOf(result);


		num1 = 0.0;
		operator = "";
		result = 0.0;
		finalDisplayMiniText = String.valueOf(num1);
		shouldClearText = true;
	}
	

	private void performOperation(double num1, double num2, String op) {
		switch (op) {
		case "x":
			result = num1 * num2;
			break;
		case "/":
			result = num1 / num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "+":
			result = num1 + num2;
			break;
		default:

		}

	}



}
