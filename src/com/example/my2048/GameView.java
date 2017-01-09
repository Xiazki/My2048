package com.example.my2048;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
@SuppressLint("NewApi")
public class GameView extends GridLayout {

	private Card[][] cards = new Card[4][4];
	private List<Point> emptyPoint = new ArrayList<Point>();

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initGameView();
	}

	public void initGameView() {

		// setVisibility(View.INVISIBLE);

		setColumnCount(4);

		setBackgroundColor(0xffbbada0);

		setOnTouchListener(new OnTouchListener() {

			private float startx, starty, offsetx, offsety;

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startx = event.getX();
					starty = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetx = event.getX() - startx;
					offsety = event.getY() - starty;

					if (Math.abs(offsetx) > Math.abs(offsety)) {

						if (offsetx < -5) {
							swipeLeft();
						} else if (offsetx > 5) {
							swipeRight();
						}
					} else {
						if (offsety < -5) {
							swipeUp();
						} else if (offsety > 5) {
							swipeDown();
						}
					}

					break;

				}
				return true;
			}
		});
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);

		int cardWith = (Math.min(w, h) - 10) / 4;
		Config.WIDTH = cardWith;
		addCards(cardWith, cardWith);

		startGame();

	}

	public void startGame() {

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				cards[x][y].setNum(0);
			}
		}
		MainActivity.getActivity().initScore();
		addRandomNum();
		addRandomNum();

	}

	private void addRandomNum() {

		emptyPoint.clear();

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cards[x][y].getNum() == 0) {
					emptyPoint.add(new Point(x, y));
				}
			}
		}

		Point p = emptyPoint.remove((int) (Math.random() * emptyPoint.size()));
		cards[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
		MainActivity.getActivity().getAnimlayer().createScaleTo1(cards[p.x][p.y]);

	}

	private void addCards(int cardHeight, int cardWight) {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				Card c = new Card(getContext());
				c.setNum(0);

				addView(c, cardWight, cardHeight);
				cards[x][y] = c;
			}
		}
	}

	protected void swipeDown() {
		// TODO Auto-generated method stub
		boolean merge = false;
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >= 0; y--) {

				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (cards[x][y1].getNum() > 0) {
						if (cards[x][y].getNum() <= 0) {

							MainActivity
									.getActivity()
									.getAnimlayer()
									.createMoveAnim(cards[x][y1], cards[x][y],
											x, x, y1, y);

							cards[x][y].setNum(cards[x][y1].getNum());
							cards[x][y1].setNum(0);

							y++;// 合并前一个移动的；

							merge = true;
						} else if (cards[x][y1].equals(cards[x][y])) {

							MainActivity
									.getActivity()
									.getAnimlayer()
									.createMoveAnim(cards[x][y1], cards[x][y],
											x, x, y1, y);

							cards[x][y].setNum(cards[x][y].getNum() * 2);
							cards[x][y1].setNum(0);

							MainActivity.getActivity().setScore(
									cards[x][y].getNum());
							merge = true;
						}
						break;
					}

				}
			}
		}
		if (merge) {
			addRandomNum();
			chickCompleted();
		}

	}

	protected void swipeUp() {
		// TODO Auto-generated method stub
		boolean merge = false;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {

				for (int y1 = y + 1; y1 < 4; y1++) {
					if (cards[x][y1].getNum() > 0) {
						if (cards[x][y].getNum() <= 0) {
							
							MainActivity
							.getActivity()
							.getAnimlayer()
							.createMoveAnim(cards[x][y1], cards[x][y],
									x, x, y1, y);
							
							cards[x][y].setNum(cards[x][y1].getNum());
							cards[x][y1].setNum(0);

							y--;// 合并前一个移动的；

							merge = true;
						} else if (cards[x][y1].equals(cards[x][y])) {
							
							MainActivity
							.getActivity()
							.getAnimlayer()
							.createMoveAnim(cards[x][y1], cards[x][y],
									x, x, y1, y);
							
							cards[x][y].setNum(cards[x][y].getNum() * 2);
							cards[x][y1].setNum(0);

							MainActivity.getActivity().setScore(
									cards[x][y].getNum());
							merge = true;
						}
						break;
					}

				}
			}
		}
		if (merge) {
			addRandomNum();
			chickCompleted();
		}

	}

	protected void swipeRight() {
		// TODO Auto-generated method stub
		boolean merge = false;
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >= 0; x--) {

				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cards[x1][y].getNum() > 0) {
						if (cards[x][y].getNum() <= 0) {
							
							MainActivity
							.getActivity()
							.getAnimlayer()
							.createMoveAnim(cards[x1][y], cards[x][y],
									x1, x, y, y);

							cards[x][y].setNum(cards[x1][y].getNum());
							cards[x1][y].setNum(0);

							x++;// 合并前一个移动的；

							merge = true;
						} else if (cards[x1][y].equals(cards[x][y])) {
							
							MainActivity
							.getActivity()
							.getAnimlayer()
							.createMoveAnim(cards[x1][y], cards[x][y],
									x1, x, y, y);
							
							cards[x][y].setNum(cards[x][y].getNum() * 2);
							cards[x1][y].setNum(0);

							MainActivity.getActivity().setScore(
									cards[x][y].getNum());
							merge = true;
						}
						break;
					}

				}
			}
		}
		if (merge) {
			addRandomNum();
			chickCompleted();
		}

	}

	protected void swipeLeft() {
		// TODO Auto-generated method stub

		boolean merge = false;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {

				for (int x1 = x + 1; x1 < 4; x1++) {

					if (cards[x1][y].getNum() > 0) {

						if (cards[x][y].getNum() <= 0) {
							
							MainActivity
							.getActivity()
							.getAnimlayer()
							.createMoveAnim(cards[x1][y], cards[x][y],
									x1, x, y, y);

							cards[x][y].setNum(cards[x1][y].getNum());
							cards[x1][y].setNum(0);

							x--;// 合并前一个移动的；

							merge = true;
						} else if (cards[x1][y].equals(cards[x][y])) {
							
							MainActivity
							.getActivity()
							.getAnimlayer()
							.createMoveAnim(cards[x1][y], cards[x][y],
									x1, x, y, y);
							
							cards[x][y].setNum(cards[x][y].getNum() * 2);
							cards[x1][y].setNum(0);

							MainActivity.getActivity().setScore(
									cards[x][y].getNum());
							merge = true;
						}
						break;
					}

				}
			}
		}
		if (merge) {
			addRandomNum();
			chickCompleted();
		}
	}

	private void chickCompleted() {
		// TODO Auto-generated method stub
		boolean complete = true;
		ALL: for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {

				if (cards[x][y].getNum() == 0
						|| (x > 0 && cards[x][y].equals(cards[x - 1][y]))
						|| (y > 0 && cards[x][y].equals(cards[x][y - 1]))
						|| (x < 3 && cards[x][y].equals(cards[x + 1][y]))
						|| (y < 3 && cards[x][y].equals(cards[x][y + 1]))) {

					complete = false;
					break ALL;
				}
			}
		}

		if (complete) {

			new AlertDialog.Builder(getContext())
					.setTitle("Game Over!")
					.setMessage("游戏结束！")
					.setPositiveButton("重新开始",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									startGame();
								}
							}).show();
		}
	}
}
