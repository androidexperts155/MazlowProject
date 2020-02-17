package com.mazlow.customclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class MediumEdittext extends EditText {
    public MediumEdittext(Context context) {
        super(context);
        init(context);
    }

    public MediumEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MediumEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "futura medium bt.ttf");
        setTypeface(font);
    }

}
