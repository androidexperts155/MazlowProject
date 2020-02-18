package com.mazlow.customclasses;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

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



}
