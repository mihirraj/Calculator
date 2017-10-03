package com.mihir.calculator.activities;

import com.mihir.calculator.CalculatorLogic;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CalculatorMainActivity extends Activity {

	CalculatorLogic calc;
	private String TAG = "calc";
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();

		try {
			display.getSize(size);
		} catch (java.lang.NoSuchMethodError ignore) { // Older device
			size.x = display.getWidth();
			size.y = display.getHeight();
		}

		int screen_width = size.x;
		int screen_height = size.y;

		// Prepare screen based on width and height of the display
		prepare_screen_default(screen_width, screen_height);

		calc = new CalculatorLogic();
	}

	@SuppressLint("InflateParams")
	private void prepare_screen_default(int screen_width, int screen_height) {
		LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout v = (RelativeLayout) li.inflate(R.layout.activity_main,
				null);

		// Divide the space equally for the controls
		// width has the be divided into 5 as there are 5 columns.
		// height has to be diveded by 7.5 -> 6 for buttons, 1 for the display,
		// and .5 for the mini display that keeps track of operation sequence
		// equals symbol will have twice the height, zero will have twice the
		// width
		int width = screen_width;
		int height = screen_height - (screen_height % 15);

		System.out.println("width = " + width + " " + "height = " + height);

		TextView textView = (TextView) v.findViewById(R.id.display_result);
		textView.setMinHeight(height / 15 * 2 + (screen_height % 15));

		TextView textViewMini = (TextView) v
				.findViewById(R.id.display_result_mini);

		// this is mini display. actually height should be divided by 15
		// (7.5 * 2). but for whatever reasons the display of the last row is
		// not proper. adjustment is done here by reducing the height.
		
		textViewMini.setMinHeight(height / 30);

		Button zero = (Button) v.findViewById(R.id.zero);
		Button one = (Button) v.findViewById(R.id.one);
		Button two = (Button) v.findViewById(R.id.two);
		Button three = (Button) v.findViewById(R.id.three);
		Button four = (Button) v.findViewById(R.id.four);
		/*four.setMinWidth(width / 5);
		four.setMinHeight(height / 15 * 2);*/
		Button five = (Button) v.findViewById(R.id.five);

		Button six = (Button) v.findViewById(R.id.six);

		Button seven = (Button) v.findViewById(R.id.seven);

		Button eight = (Button) v.findViewById(R.id.eight);

		Button nine = (Button) v.findViewById(R.id.nine);

		Button Multiply = (Button) v.findViewById(R.id.Multiply);

		Button Divide = (Button) v.findViewById(R.id.Divide);

		Button Subtract = (Button) v.findViewById(R.id.Subtract);

		Button Add = (Button) v.findViewById(R.id.Add);

		Button Equals = (Button) v.findViewById(R.id.Equals);

		Button BackSpace = (Button) v.findViewById(R.id.BackSpace);

		Button Clear = (Button) v.findViewById(R.id.C);

		Button PlusMinus = (Button) v.findViewById(R.id.PlusMinus);


		setContentView(v);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void buttonClick(View v) {
		Button button = (Button) findViewById(v.getId());
		String button_text = (String) button.getText();
		calc.calculate(button_text);

		TextView textView = (TextView) findViewById(R.id.display_result);
		TextView textViewMini = (TextView) findViewById(R.id.display_result_mini);

		Log.d(TAG, "buttonClick: view triggered!");
		textView.setText(calc.getFinalDisplayText());
		Log.d(TAG, "buttonClick: get final display on button click is"+calc.getFinalDisplayText());
		textViewMini.setText(calc.getFinalDisplayMiniText());
	}

}