package trd.game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77982 on 2016/2/16.
 */
public class Gameview extends GridLayout {

    public int lenth;

    private Card[][] cardary = new Card[4][4];

    private List<Point> emptypoint = new ArrayList<Point>();

    public Gameview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initGameview();
    }

    public Gameview(Context context, AttributeSet attrs) {
        super(context, attrs);

        initGameview();
    }

    public Gameview(Context context) {
        super(context);

        initGameview();
    }

    private void initGameview() {

        setColumnCount(4);
        setBackgroundColor(0xffbbada0);
        System.out.println("gameview");

        setOnTouchListener(new View.OnTouchListener() {

            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                left();
                            } else if (offsetX > 5) {
                                right();
                            }
                        } else {
                            if (offsetY < -5) {
                                up();
                            } else if (offsetY > 5) {
                                down();
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
        super.onSizeChanged(w, h, oldw, oldh);

        if (w != oldw) {
            int cardwidth = (Math.min(w, h) - 10) / 4;
            lenth = cardwidth;
            addcard(cardwidth, cardwidth);
            startgame();
        }
    }

    private void addcard(int cardwidth, int cardhight) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardary[x][y] = new Card(getContext());
                cardary[x][y].setNum(0);
                addView(cardary[x][y], cardwidth, cardhight);
            }
        }
    }

    private void startgame() {

        MainActivity.getMainActivity().clearscore();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardary[x][y].setNum(0);
            }
        }
        addnum();
        addnum();
    }

    private void addnum() {
        emptypoint.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardary[x][y].getNum() <= 0) {
                    emptypoint.add(new Point(x, y));
                }
            }
        }
        Point p = emptypoint.remove((int) (Math.random() * emptypoint.size()));
        cardary[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }

    private void left() {

        boolean merge = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int i = x + 1; i < 4; i++) {
                    if (cardary[i][y].getNum() > 0) {
                        if (cardary[x][y].getNum() <= 0) {
                            cardary[x][y].setNum(cardary[i][y].getNum());
                            cardary[i][y].setNum(0);
                            x--;
                            merge = true;
                            break;
                        } else if (cardary[x][y].getNum() == cardary[i][y].getNum()) {
                            cardary[x][y].setNum(cardary[i][y].getNum() * 2);
                            cardary[i][y].setNum(0);
                            MainActivity.getMainActivity().addsocre(cardary[x][y].getNum());
                            merge = true;
                            break;
                        } else if (cardary[x][y].getNum() != cardary[i][y].getNum()) {
                            break;
                        }
                    }
                }
            }
        }
        if (merge == true) {
            addnum();
            checkend();
        }
        removeAllViews();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                addView(cardary[x][y], lenth, lenth);
            }
        }
    }

    private void right() {

        boolean merge = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int i = x - 1; i >= 0; i--) {
                    if (cardary[i][y].getNum() > 0) {
                        if (cardary[x][y].getNum() <= 0) {
                            cardary[x][y].setNum(cardary[i][y].getNum());
                            cardary[i][y].setNum(0);
                            x++;
                            merge = true;
                            break;
                        } else if (cardary[x][y].getNum() == cardary[i][y].getNum()) {
                            cardary[x][y].setNum(cardary[i][y].getNum() * 2);
                            cardary[i][y].setNum(0);
                            MainActivity.getMainActivity().addsocre(cardary[x][y].getNum());
                            merge = true;
                            break;
                        } else if (cardary[x][y].getNum() != cardary[i][y].getNum()) {
                            break;
                        }
                    }
                }
            }
        }
        if (merge == true) {
            addnum();
            checkend();
        }
        removeAllViews();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                addView(cardary[x][y], lenth, lenth);
            }
        }
    }

    private void up() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int i = y + 1; i < 4; i++) {
                    if (cardary[x][i].getNum() > 0) {
                        if (cardary[x][y].getNum() <= 0) {
                            cardary[x][y].setNum(cardary[x][i].getNum());
                            cardary[x][i].setNum(0);
                            y--;
                            merge = true;
                            break;
                        } else if (cardary[x][y].getNum() == cardary[x][i].getNum()) {
                            cardary[x][y].setNum(cardary[x][i].getNum() * 2);
                            cardary[x][i].setNum(0);
                            MainActivity.getMainActivity().addsocre(cardary[x][y].getNum());
                            merge = true;
                            break;
                        } else if (cardary[x][y].getNum() != cardary[x][i].getNum()) {
                            break;
                        }
                    }
                }
            }
        }
        if (merge == true) {
            addnum();
            checkend();
        }
        removeAllViews();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                addView(cardary[x][y], lenth, lenth);
            }
        }
    }

    private void down() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int i = y - 1; i >= 0; i--) {
                    if (cardary[x][i].getNum() > 0) {
                        if (cardary[x][y].getNum() <= 0) {
                            cardary[x][y].setNum(cardary[x][i].getNum());
                            cardary[x][i].setNum(0);
                            y++;
                            merge = true;
                            break;
                        } else if (cardary[x][y].getNum() == cardary[x][i].getNum()) {
                            cardary[x][y].setNum(cardary[x][i].getNum() * 2);
                            cardary[x][i].setNum(0);
                            MainActivity.getMainActivity().addsocre(cardary[x][y].getNum());
                            merge = true;
                            break;
                        } else if (cardary[x][y].getNum() != cardary[x][i].getNum()) {
                            break;
                        }
                    }
                }
            }
        }
        if (merge == true) {
            addnum();
            checkend();
        }
        removeAllViews();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                addView(cardary[x][y], lenth, lenth);
            }
        }
    }

    private void checkend() {

        boolean end = true;

        ALL:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardary[x][y].getNum() <= 0
                        || ((x > 0) && (cardary[x][y].getNum() == cardary[x - 1][y].getNum()))
                        || ((x < 3) && (cardary[x][y].getNum() == cardary[x + 1][y].getNum()))
                        || ((y > 0) && (cardary[x][y].getNum() == cardary[x][y - 1].getNum()))
                        || ((y < 3) && (cardary[x][y].getNum() == cardary[x][y + 1].getNum()))) {
                    end = false;
                    break ALL;
                }
            }
        }
        if (end) {
            new AlertDialog.Builder(getContext()).setTitle("Sorry").setMessage("Game Over").setPositiveButton("try again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startgame();
                }
            }).show();
        }
    }
}

