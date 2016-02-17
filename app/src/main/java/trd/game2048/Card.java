package trd.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by 77982 on 2016/2/16.
 */

public class Card extends FrameLayout {

    private int num = 0;

    private TextView tvnum;

    public Card(Context context) {
        super(context);

        tvnum = new TextView(getContext());
        tvnum.setTextSize(32);
        tvnum.setGravity(Gravity.CENTER);
        tvnum.setBackgroundColor(0x33ffffff);

        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(tvnum, lp);
        setNum(0);
    }


    public int getNum() {

        return num;

    }

    public void setNum(int num) {

        this.num = num;

        if (num <= 0) {
            tvnum.setText("");
        } else {
            tvnum.setText(num + "");
        }

        switch (num) {
            case 0:
                tvnum.setBackgroundColor(0x33ffffff);
                break;
            case 2:
                tvnum.setBackgroundColor(0xffeee4da);
                break;
            case 4:
                tvnum.setBackgroundColor(0xffede0c8);
                break;
            case 8:
                tvnum.setBackgroundColor(0xfff2b179);
                break;
            case 16:
                tvnum.setBackgroundColor(0xfff59563);
                break;
            case 32:
                tvnum.setBackgroundColor(0xfff67c5f);
                break;
            case 64:
                tvnum.setBackgroundColor(0xfff65e3b);
                break;
            case 128:
                tvnum.setBackgroundColor(0xffedcf72);
                break;
            case 256:
                tvnum.setBackgroundColor(0xffedcc61);
                break;
            case 512:
                tvnum.setBackgroundColor(0xffedc850);
                break;
            case 1024:
                tvnum.setBackgroundColor(0xffedc53f);
                break;
            case 2048:
                tvnum.setBackgroundColor(0xffedc22e);
                break;
        }
    }
}
