package com.mazlow.ui.users.fragments;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.Mazlow.R;
import com.mazlow.adduserdetails.FirstFragmentSignup;
import com.mazlow.customclasses.BaseFragment;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;

public class SecondFragmentSignup extends BaseFragment implements View.OnClickListener {

    Button continue_btn;
    FragmentManager fragmentManager;
    EditText et_firstname,et_lastname,et_datebirth,email_address,retypeemailaddress;
    LinearLayout rootlayout;
    String refreshedToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_second_fragment_signup, container, false);
        findIds(view);

//        FirebaseApp.initializeApp(getActivity());
//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( getActivity(),  new OnSuccessListener<InstanceIdResult>() {
//            @Override
//            public void onSuccess(InstanceIdResult instanceIdResult) {
//                refreshedToken = instanceIdResult.getToken();
//                Log.e("newToken",refreshedToken);
//
//            }
//        });
        initView();
        setListner();
        return  view;
    }

    private void initView() {
        fragmentManager = getActivity().getSupportFragmentManager();
    }
    private void setListner() {
        continue_btn.setOnClickListener(this);
        et_datebirth.setOnClickListener(this);
    }
    private void findIds(View view) {
        continue_btn = view.findViewById(R.id.continue_btn);
        et_firstname = view.findViewById(R.id.et_firstname);
        et_lastname = view.findViewById(R.id.et_lastname);
        et_datebirth = view.findViewById(R.id.et_datebirth);
        email_address = view.findViewById(R.id.email_address);
        retypeemailaddress = view.findViewById(R.id.retypeemailaddress);
        rootlayout = view.findViewById(R.id.rootlayout);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.continue_btn:
                validateData();
                callFragment();
                break;
            case R.id.et_datebirth:
                M.datePiker(getActivity(),et_datebirth);
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
        Bundle bundle=new Bundle();
        bundle.putString(Bean.FIRST_NAME, et_firstname.getText().toString().trim());
        bundle.putString(Bean.LAST_NAME, et_lastname.getText().toString().trim());
        bundle.putString(Bean.DATEOF_BIRTH, et_datebirth.getText().toString().trim());
        bundle.putString(Bean.EMAIL_ADDRESS, email_address.getText().toString().trim());
        FirstFragmentSignup firstFragmentSignup=new FirstFragmentSignup();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, firstFragmentSignup);
        transaction.attach(firstFragmentSignup);
        transaction.commit();
        firstFragmentSignup.setArguments(bundle);
    }
}
