package com.example.kidsteaser;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private Object mAnswerChar;

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return mChars.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		// ImageView imageView;
		// if (convertView == null) { // if it's not recycled, initialize some
		// attributes
		// imageView = new ImageView(mContext);
		// imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
		// imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		// imageView.setPadding(8, 8, 8, 8);
		//
		//
		// } else {
		// imageView = (ImageView) convertView;
		// }
		//
		// imageView.setImageResource(mThumbIds[position]);
		// return imageView;

		TextView view;

		if (null == convertView) {
			view = new TextView(mContext);
			view.setTextSize(48);
			view.setLayoutParams(new GridView.LayoutParams(150, 150));
			view.setPadding(8, 8, 8, 8);
			view.setBackgroundColor(Color.GRAY);
//			view.setBackgroundResource(R.drawable.ic_launcher);
		} else {
			view = (TextView) convertView;
		}

		if (position == 0) {
			initNewQuiz();
		}
		view.setText(getTextForPosition(position));
		return view;
	}

	private CharSequence getTextForPosition(int position) {

		return mChars[position].toString();

	}

	private void initNewQuiz() {

		for (int i = 0; i < mChars.length; i++) {

			mChars[i] = null;

		}

		for (int i = 0; i < mChars.length; i++) {

			do {
				mChars[i] = randomChar();
			} while (isRepeated(mChars[i], i));

		}

		mAnswerChar = randomAnswerChar();

	}

	private boolean isRepeated(Character character, int current) {

		for (int i = 0; i < mChars.length; i++) {
			
			//skip checking the current one to avoid an infinite loop
			if (i == current) {
				continue;
			}
			//optimization if character at i is null
			if (null == mChars[i]) { 
				break;
			}
			if (mChars[i].equals(character)) {
				return true;
			}
		}
		return false;
	}

	private Character randomAnswerChar() {

		int i = random.nextInt(4);
		return mChars[i];

	}
	
	Random random = new Random();

	private Character randomChar() {
		char ri = (char) (65 + random.nextInt(26));
		return Character.valueOf(ri);
	}

	public void newQuiz() {

		initNewQuiz();

		notifyDataSetChanged();
	}

	// references to our images
	private Character[] mChars = { 'A', 'B', 'C', 'D' };
}
