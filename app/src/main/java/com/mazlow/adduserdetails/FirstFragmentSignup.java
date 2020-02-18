package com.mazlow.adduserdetails;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.Mazlow.R;
import com.mazlow.adduserdetails.model.UpdateUserDetails;
import com.mazlow.customclasses.BaseFragment;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.M;
import com.mazlow.customclasses.Prefs;
import com.mazlow.search_address.SearchAddressView;
import com.mazlow.search_address.SearchPresentermple;
import com.mazlow.search_address.model.AddressZipcodeResponse;
import com.mazlow.ui.users.fragments.ThirdFragmentSignup;

public class FirstFragmentSignup extends BaseFragment implements View.OnClickListener , AddDetailsView {


    Button continue_btn;
    FragmentManager fragmentManager;
    String firstname,lastname,email, datebirth;
    EditText et_country,et_pstalcoe,et_streetand_number,et_address2,et_city;
    LinearLayout rootlayout;
    UpdateDetailPresenterImple updateDetailPresenterImple;
    SearchPresentermple searchPresentermple;
    Prefs prefs;
    String accesstoken;
    String refreshedToken;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_first_fragment_signup, container, false);
        findIds(view);
        initView();
        setListner();
        getDataFromintent();
        return  view;
    }

    private void getDataFromintent() {
        firstname = getArguments().getString(Bean.FIRST_NAME);
        lastname = getArguments().getString(Bean.LAST_NAME);
        email = getArguments().getString(Bean.EMAIL_ADDRESS);
        datebirth = getArguments().getString(Bean.DATEOF_BIRTH);
        accesstoken= prefs.getString(Bean.ACCESS_TOKEN,"");
        Toast.makeText(getActivity(), accesstoken, Toast.LENGTH_SHORT).show();

    }

    private void initView() {

        fragmentManager = getActivity().getSupportFragmentManager();
        prefs= new Prefs(getActivity());
        et_pstalcoe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (et_pstalcoe.getRight() - et_pstalcoe.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        {
                            if (!isEditTextEmpty(et_pstalcoe))
                            {
                                showSnackbar(rootlayout,getResources().getString(R.string.enterpostalcode),"");
                            }
                            else
                            {
                                searchPresentermple=new SearchPresentermple(new SearchAddressView() {
                                    @Override
                                    public void onSucess(AddressZipcodeResponse addressZipcodeResponse) {
                                        et_streetand_number.setText(addressZipcodeResponse.getAddressInfo().get(0));
                                    }
                                    @Override
                                    public void onError(String error1) {
                                        showToast(error1);
                                    }
                                    @Override
                                    public void noInternet(String tag) {
                                        M.networkDialog(getActivity());
                                    }
                                }, getActivity());
                                searchPresentermple.dosearch(accesstoken,et_pstalcoe.getText().toString());


                            }


                        }


                        return true;
                    }
                }
                return false;
            }
        });


    }
    private void setListner() {
        continue_btn.setOnClickListener(this);
    }

    private void findIds(View view) {
        continue_btn = view.findViewById(R.id.continue_btn);
        rootlayout = view.findViewById(R.id.rootlayout);
        et_country = view.findViewById(R.id.et_country);
        et_pstalcoe = view.findViewById(R.id.et_pstalcoe);
        et_streetand_number = view.findViewById(R.id.et_streetand_number);
        et_address2 = view.findViewById(R.id.et_address2);
        et_city = view.findViewById(R.id.et_city);
    }
    private void callFragment() {
        ThirdFragmentSignup firstFragmentSignup=new ThirdFragmentSignup();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, firstFragmentSignup);
        transaction.attach(firstFragmentSignup);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.continue_btn:
                validateData();

                break;
        }
    }

    private void validateData() {

        if (!isEditTextEmpty(et_country))
            showSnackbar(rootlayout,getResources().getString(R.string.choosecountry),"");
        else if (!isEditTextEmpty(et_pstalcoe))
            showSnackbar(rootlayout,getResources().getString(R.string.enterpostalcode),"");
        else if (!isEditTextEmpty(et_streetand_number))
            showSnackbar(rootlayout,getResources().getString(R.string.enteremailandphone),"");
        else if (!isEMailOk(et_city))
            showSnackbar(rootlayout,getResources().getString(R.string.entercity),"");
        else
            apiCall();

    }

    private void apiCall() {
        updateDetailPresenterImple =new UpdateDetailPresenterImple(this,getActivity());
        String countrycode= et_country.getText().toString();
        String postalcode = et_pstalcoe.getText().toString();
        String address1 = et_streetand_number.getText().toString();
        String address2 = et_address2.getText().toString();
        String city = et_city.getText().toString();
        updateDetailPresenterImple.doUpdate(accesstoken,firstname,lastname,datebirth,email,countrycode,postalcode,address1,address2,city,refreshedToken,Bean.DEVICETYPE);
    }

    @Override
    public void onSucess(UpdateUserDetails updateUserDetails) {
        callFragment();
    }



    @Override
    public void onError(String error) {
        showToast(error);
    }

    @Override
    public void noInternet(String tag) {
        M.networkDialog(getActivity());
    }
}
