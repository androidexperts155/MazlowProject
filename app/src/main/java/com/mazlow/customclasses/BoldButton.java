package com.mazlow.customclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class BoldButton extends Button {
    public BoldButton(Context context) {
        super(context);
        init(context);
    }

    public BoldButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BoldButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "futura-bold_[allfont.net].ttf");
        setTypeface(font);
    }

}
