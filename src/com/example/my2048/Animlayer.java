package com.example.my2048;

import android.content.Context;
import android.util.AttributeSet;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

public class Animlayer extends FrameLayout {

	public Animlayer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public Animlayer(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public Animlayer(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void createMoveAnim(final Card from, final Card to, int fromX,
			int toX, int fromY, int toY) {

		final Card c = from.clone();

		if (to.getNum() <= 0)
			to.getLable().setVisibility(View.INVISIBLE);

		LayoutParams lp = new LayoutParams(Config.WIDTH, Config.WIDTH);
		lp.leftMargin = fromX * Config.WIDTH;
		lp.topMargin = fromY * Config.WIDTH;
		addView(c, lp);

		TranslateAnimation ta = new TranslateAnimation(0, Config.WIDTH
				* (toX - fromX), 0, Config.WIDTH * (toY - fromY));
		ta.setDuration(100);
		ta.setAnimationListener(new Animation.AnimationListener() {

			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				to.getLable().setVisibility(View.VISIBLE);
				c.setVisibility(View.INVISIBLE);
				removeView(c);
			}
		});

		c.setAnimation(ta);

	}

	public void createScaleTo1(Card c) {
		ScaleAnimation sa = new ScaleAnimation(0.1f, 1, 0.1f, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		sa.setDuration(100);
		c.setAnimation(null);
		c.getLable().setAnimation(sa);
	}
}
