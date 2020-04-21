package com.mazlow.customclasses;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.Mazlow.R;
import com.google.android.material.snackbar.Snackbar;

public class BaseFragment extends Fragment {

    String emailpattren = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public void showToast(String msg){
        try {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

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

    public  boolean compareString(EditText editText1, EditText editText2)
    {
        if (!editText1.getText().toString().trim().equals(editText2.getText().toString().trim())){
            return  false;
        }
        else{
            return  true;
        }

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
    public boolean isEMailOk(EditText email) {
        if (!email.getText().toString().trim().matches(emailpattren)) {
            email.requestFocus();
            return false;
        }
        return true;
    }

    public void startNewActivity(Activity a, Class<? extends Activity> class1, boolean isfinis, Intent intent) {

        try {
            Bundle bundle = null;
            if (intent != null) {
                bundle = intent.getExtras();

            }
            Intent i = new Intent(a, class1);
            if (bundle != null) {
                i.putExtras(bundle);

            }
            a.startActivity(i);


            if (isfinis) {
                a.finish();


            }


        } catch (ActivityNotFoundException e) {
            showToast("Something Went Wrong Please Restart the application!!!!!!!!");
        } catch (Exception e) {
            showToast("Something Went Wrong!!!!!!!!");
        }


    }
}
