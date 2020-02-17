package com.mazlow.customclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class MediumTextView extends TextView {
    public MediumTextView(Context context) {
        super(context);
        init(context);
    }

    public MediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MediumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "futura medium bt.ttf");
        setTypeface(font);
    }

}
