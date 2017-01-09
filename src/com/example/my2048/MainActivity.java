package com.example.my2048;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView stv;
	private int score = 0;
	private static MainActivity ma;
	private GameView gv;
	private Button restart;
	private Animlayer al;

	public MainActivity() {
		// TODO Auto-generated constructor stub
		ma = this;
	}

	public void showScore() {
		stv.setText(score + "" + "       ×î¸ß¼ÍÂ¼£º" + getMaxScore());
	}

	public void setScore(int score) {
		this.score += score;
		int maxScore = Math.max(this.score, getMaxScore());
		saveMaxScore(maxScore);
		showScore();
	}

	public void initScore() {
		score = 0;
		showScore();
	}

	public void saveMaxScore(int maxScore) {

		Editor ed = getPreferences(MODE_PRIVATE).edit();
		ed.putInt("bestscore", maxScore);
		ed.commit();

	}

	public int getMaxScore() {

		return getPreferences(MODE_PRIVATE).getInt("bestscore", 0);
	}

	public static MainActivity getActivity() {

		return ma;
	}

	public Animlayer getAnimlayer() {
		return al;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		gv = (GameView) findViewById(R.id.gameview);
		al = (Animlayer) findViewById(R.id.animview);
		restart = (Button) findViewById(R.id.restart);
		restart.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				gv.startGame();
			}
		});
		stv = (TextView) findViewById(R.id.score);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
