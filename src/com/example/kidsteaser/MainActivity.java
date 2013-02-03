package com.example.kidsteaser;

import android.os.Bundle;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity {
	
	ImageAdapter imageAdapter;
	int [] options = {R.id.btn_option1, R.id.btn_option2,
			R.id.btn_option3, R.id.btn_option4
			};
	
	Button[] buttons = new Button[options.length];
	private Character mAnswerChar;
	TextToSpeech tts = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		class OnInitListener implements TextToSpeech.OnInitListener {

			@Override
			public void onInit(int status) {
				// TODO Auto-generated method stub
				tts.speak("Welcome to Pick a pic", TextToSpeech.QUEUE_FLUSH, null);
				newQuiz();
			}
			
		}
//		GridView grid = (GridView) findViewById(R.id.gridview);
		
		imageAdapter = new ImageAdapter(this);
//		grid.setAdapter(imageAdapter);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = (Button) findViewById(options[i]);
		}
		
		tts = new TextToSpeech(this, new OnInitListener());
		
		
	}
	
	@Override
	protected void onResume() {
		
		super.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void onNextClick(View view) {
		newQuiz();
	}

	private void newQuiz() {
		imageAdapter.newQuiz();
		populateQuizOptions();
		speakAnswer();
	}

	private void populateQuizOptions() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText(imageAdapter.getTextForPosition(i));
		}
	}

	public void onPlayClick(View view) {
		speakAnswer();
	}

	private void speakAnswer() {
		tts.speak("Find the letter - " + imageAdapter.getAnswer().toString(), TextToSpeech.QUEUE_ADD, null);
	}
	
	public void onAnswer(View view) {
		
		if (view instanceof Button) {
			Button btn  = (Button)view;
			if (imageAdapter.getAnswer().toString().equals(btn.getText().toString())) {
				tts.speak("Good job!", TextToSpeech.QUEUE_FLUSH, null);
				newQuiz();
			}
			else {
				tts.speak("Try again.", TextToSpeech.QUEUE_FLUSH, null);
			}
		}
		
	}
}
