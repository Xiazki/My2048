package com.example.my2048;

import org.w3c.dom.Text;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

	private TextView lable;
	private int num;
	private View background;
	public Card(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
		background = new View(context);
		background.setBackgroundColor(0x33ffffff);
		LayoutParams lp = new LayoutParams(-1, -1);
		lp.setMargins(10, 10, 0, 0);
		addView(background,lp);

		lable = new TextView(context);
		lable.setTextSize(32);
		
		lable.setGravity(Gravity.CENTER);

		lp = new LayoutParams(-1, -1);
		lp.setMargins(10, 10, 0, 0);

		addView(lable, lp);

		setNum(0);
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {

		this.num = num;
		if (num == 0) {
			lable.setText("");
		} else {
			lable.setText("" + num);
		}
		setBackGround(num);
	}

	public void setBackGround(int num) {

		switch (num) {
		case 0:
			lable.setBackgroundColor(0x00000000);
			break;
		case 2:
			lable.setBackgroundColor(0xffeee4da);
			break;
		case 4:
			lable.setBackgroundColor(0xffede0c8);
			break;
		case 8:
			lable.setBackgroundColor(0xfff2b179);
			break;
		case 16:
			lable.setBackgroundColor(0xfff59563);
			break;
		case 32:
			lable.setBackgroundColor(0xfff67c5f);
			break;
		case 64:
			lable.setBackgroundColor(0xfff65e3b);
			break;
		case 128:
			lable.setBackgroundColor(0xffedcf72);
			break;
		case 256:
			lable.setBackgroundColor(0xffedcc61);
			break;

		case 512:
			lable.setBackgroundColor(0xffedc850);
			break;
		case 1024:
			lable.setBackgroundColor(0xffedc53f);
			break;
		case 2048:
			lable.setBackgroundColor(0xffedc22e);
			break;

		default:
			lable.setBackgroundColor(0xff3c3a32);
			break;
		}
	}

	public boolean equals(Card o) {
		// TODO Auto-generated method stub
		return getNum() == o.getNum();
	}

	public Card clone() {
		Card cardclone = new Card(getContext());
		cardclone.setNum(num);
		return cardclone;

	}

	public TextView getLable() {
		return lable;
	}
}
