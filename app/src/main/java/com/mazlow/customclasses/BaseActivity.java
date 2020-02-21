package com.mazlow.customclasses;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.Mazlow.R;
import com.google.android.material.snackbar.Snackbar;

public abstract class BaseActivity extends AppCompatActivity {

    String emailpattren = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(myView());
    }

    protected abstract int myView();

    public void showToast(String msg){
        try {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        }catch (Exception e){

        }
    }
    public boolean isEditTextEmpty(EditText et){
        if (TextUtils.isEmpty(et.getText().toString().trim())){
            return false;
        }
        else{
            return true;
        }

    }
    public boolean isEMailOk(EditText email) {
        if (!email.getText().toString().trim().matches(emailpattren)) {
            email.requestFocus();
            return false;
        }
        return true;
    }

    public void showSnackbar(View view, String text, final String from) {
        final Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView snackbarActionTextView = (TextView) snackbar.getView().findViewById(R.id.snackbar_action);
        snackbarActionTextView.setTextSize(18);
        snackbarActionTextView.setTypeface(snackbarActionTextView.getTypeface(), Typeface.BOLD);
        snackbarActionTextView.setTextColor(getResources().getColor(R.color.colorPrimary));

        TextView textView = (TextView) sbView.findViewById(

                R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(15);
        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        sbView.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        snackbar.setActionTextColor(getResources().getColor(R.color.colorAccent));

        snackbar.show();
    }

    public  boolean compareString(EditText editText1, EditText editText2)
    {
        if (!editText1.getText().toString().trim().equals(editText2.getText().toString().trim())){
            return  false;
        }
        else{
            return  true;
        }

    }

}
