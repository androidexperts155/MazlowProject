package com.mazlow.customclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class MediumButton extends Button {
    public MediumButton(Context context) {
        super(context);
        init(context);
    }

    public MediumButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MediumButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "futura medium bt.ttf");
        setTypeface(font);
    }

}
