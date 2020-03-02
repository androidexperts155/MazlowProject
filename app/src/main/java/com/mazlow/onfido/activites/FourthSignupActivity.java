package com.mazlow.onfido.activites;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.Mazlow.R;
import com.mazlow.onfido.OnfidoPresenterImplementation;
import com.mazlow.onfido.OnfidoView;
import com.mazlow.onfido.model.OnFidoResponseModel;
import com.mazlow.customclasses.Bean;
import com.mazlow.customclasses.Prefs;
import com.mazlow.onfido.verification_failed.OndidoVerificationFailed;
import com.mazlow.onfido.verification_identity.ConductingVerificationActivity;
import com.mazlow.skipandexplore.SkipExploreActivity;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.Onfido;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.OnfidoFactory;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.token.TokenExpirationHandler;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.upload.Captures;

public class FourthSignupActivity extends AppCompatActivity implements View.OnClickListener, OnfidoView {

    Button continue_btn;
    private Onfido client;
    ImageView img_back;

//    String ONFIDO_API_TOKEN = "sdk_sandbox.mCLnca4AxSE.ybJExRKZdNLnkr9-lVUP2fUJ6edTQ_es";
    String ONFIDO_API_TOKEN = "sdk_sandbox.kIJ-pC6qgbC.hGUCBlp2G8BFzatSR2l5mx9VzskHuvSi";
    OnfidoPresenterImplementation onfidoPresenterImplementation;
    Prefs prefs;
    String firstname,lastname,email,dob,address,country;
    String application_id;
    TextView tv_skipexplore;
    String token="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_signup);

        initview();
        setListner();

        firstname =prefs.getString(Bean.FIRST_NAME,"");
        lastname=prefs.getString(Bean.LAST_NAME,"");
        dob=prefs.getString(Bean.DATEOF_BIRTH,"");
        email=prefs.getString(Bean.EMAIL_ADDRESS,"");
        address=prefs.getString(Bean.ADDRESS,"");
        country=prefs.getString(Bean.POSTALCODE,"");
        getApplication_id();

    }

    private void getApplication_id() {
        onfidoPresenterImplementation=new OnfidoPresenterImplementation(this,this);
        onfidoPresenterImplementation.onFidoApplication("Token token="+ONFIDO_API_TOKEN,firstname,lastname,email,"15 may 2020","","ind");
    }

    private void setListner() {
        continue_btn.setOnClickListener(this);
        img_back.setOnClickListener(this);
        tv_skipexplore.setOnClickListener(this);
    }

    private void initview() {


        prefs =new Prefs(this);
        client = OnfidoFactory.create(getApplicationContext()).getClient();
        continue_btn = findViewById(R.id.continue_btn);
        img_back = findViewById(R.id.img_back);
        tv_skipexplore = findViewById(R.id.tv_skipexplore);

    }

    private void startFlow() {


        final FlowStep[] flowStepsWithOptions = new FlowStep[]{
                FlowStep.WELCOME,
                FlowStep.CAPTURE_DOCUMENT,
                FlowStep.CAPTURE_FACE,
                FlowStep.FINAL
        };
        startFlow(flowStepsWithOptions);
    }

    private void startFlow(final FlowStep[] flowSteps) {
        OnfidoConfig onfidoConfig =
                OnfidoConfig.builder(this)
                        .withToken(ONFIDO_API_TOKEN)
                        .withApplicant(application_id)
                        .withCustomFlow(flowSteps)
                        .build();
        client.startActivityForResult(this, 1, onfidoConfig);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        client.handleActivityResult(resultCode, data, new Onfido.OnfidoResultListener() {
            @Override
            public void userExited(ExitCode exitCode) {

            }

            @Override
            public void userCompleted(Captures captures) {
                startCheck(captures);
            }

            @Override
            public void onError(OnfidoException e) {
                e.printStackTrace();


            }
        });
    }

    private void startCheck(Captures captures) {
        Intent intent = new Intent(FourthSignupActivity.this, ConductingVerificationActivity.class);
        startActivity(intent);
    }

    private void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.img_back:
                finish();
                break;
            case R.id.continue_btn:
                startFlow();
                break;
            case R.id.tv_skipexplore:
                skipexploreActivity();
                break;
        }
    }

    private void skipexploreActivity() {
        Intent intent = new Intent(FourthSignupActivity.this, SkipExploreActivity.class);
        startActivity(intent);

//        Intent intent = new Intent(FourthSignupActivity.this, ConductingVerificationActivity.class);
//        startActivity(intent);

    }

    @Override
    public void Sucess(OnFidoResponseModel onFidoResponseModel) {
        application_id = onFidoResponseModel.getId();
        Log.e("applicationid", application_id);
        //onFidoResponseModel
    }

    @Override
    public void error(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noInternet(String tag) {

    }


}
