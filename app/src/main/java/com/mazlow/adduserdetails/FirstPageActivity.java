package com.mazlow.adduserdetails;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.Mazlow.R;
import com.mazlow.customclasses.BaseActivity;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
public class FirstPageActivity extends BaseActivity implements View.OnClickListener {

    Button continue_btn;
    EditText et_firstname,et_lastname,et_datebirth,email_address,retypeemailaddress;
    LinearLayout rootlayout;
    ImageView img_back;
    Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findIds();
        setListner();
        setData();
    }

    private void setData() {

        if (prefs.getString(Bean.FIRST_NAME," ")!=null)
        {
            if (prefs.getString(Bean.DATEOF_BIRTH,"").equals("01/01/1970"))
            et_datebirth.setText("");
            else
                et_datebirth.setText(prefs.getString(Bean.DATEOF_BIRTH,""));


            et_firstname.setText(prefs.getString(Bean.FIRST_NAME,""));
            et_lastname.setText(prefs.getString(Bean.LAST_NAME,""));

            email_address.setText(prefs.getString(Bean.EMAIL_ADDRESS,""));
            retypeemailaddress.setText(prefs.getString(Bean.EMAIL_ADDRESS,""));

        }
    }

    @Override
    protected int myView() {
        return R.layout.activity_first_page;
    }

    private void setListner() {
        continue_btn.setOnClickListener(this);
        et_datebirth.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }
    private void findIds() {

        prefs =  new Prefs(this);

        continue_btn = findViewById(R.id.continue_btn);
        et_firstname = findViewById(R.id.et_firstname);
        et_lastname = findViewById(R.id.et_lastname);
        et_datebirth = findViewById(R.id.et_datebirth);
        email_address = findViewById(R.id.email_address);
        retypeemailaddress = findViewById(R.id.retypeemailaddress);
        rootlayout = findViewById(R.id.rootlayout);
        img_back = findViewById(R.id.img_back);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.continue_btn:
                validateData();
                break;
            case R.id.et_datebirth:
                M.datePiker(this,et_datebirth);
                break;
            case R.id.img_back:
              finish();
                break;
        }
    }

    private void validateData() {
        if (!isEditTextEmpty(et_firstname))
            showSnackbar(rootlayout,getResources().getString(R.string.anterfirstname),"");
        else if (!isEditTextEmpty(et_lastname))
            showSnackbar(rootlayout,getResources().getString(R.string.enterlastname),"");
        else if (!isEditTextEmpty(et_datebirth))
            showSnackbar(rootlayout,getResources().getString(R.string.choosedate),"");
        else if (!isEditTextEmpty(email_address))
            showSnackbar(rootlayout,getResources().getString(R.string.emptyemail),"");
        else if (!isEMailOk(email_address))
            showSnackbar(rootlayout,getResources().getString(R.string.entervalidemail),"");
        else if (!isEditTextEmpty(retypeemailaddress))
            showSnackbar(rootlayout,getResources().getString(R.string.emptyretypemail),"");
        else if (!isEditTextEmpty(retypeemailaddress))
            showSnackbar(rootlayout,getResources().getString(R.string.entervalidemail),"");
        else if (!compareString(email_address,retypeemailaddress))
            showSnackbar(rootlayout,getResources().getString(R.string.emailnotnatched),"");
        else
            callFragment();

    }

    private void callFragment() {

        Intent intent= new Intent(FirstPageActivity.this,SecondPageActivity.class);
        intent.putExtra(Bean.FIRST_NAME, et_firstname.getText().toString().trim());
        intent.putExtra(Bean.LAST_NAME, et_lastname.getText().toString().trim());
        intent.putExtra(Bean.DATEOF_BIRTH, et_datebirth.getText().toString().trim());
        intent.putExtra(Bean.EMAIL_ADDRESS, email_address.getText().toString().trim());
        startActivity(intent);

    }
}

