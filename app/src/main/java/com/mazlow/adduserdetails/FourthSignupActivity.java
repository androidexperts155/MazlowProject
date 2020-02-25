package com.mazlow.adduserdetails;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.Mazlow.R;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.Onfido;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.OnfidoFactory;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.upload.Captures;

public class FourthSignupActivity extends AppCompatActivity implements View.OnClickListener {
    Button continue_btn;
    private Onfido client;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_signup);

        initview();
        setListner();
    }

    private void setListner() {
        continue_btn.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    private void initview() {
        continue_btn = findViewById(R.id.continue_btn);
        img_back = findViewById(R.id.img_back);
        client = OnfidoFactory.create(this).getClient();
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

           String ONFIDO_API_TOKEN = "api_sandbox.Rkky2Ssh1vA.pXPEV2s9nnLiPli7Pynbspsbp33PkNGt";

//    ONFIDO_MOBILE_TOKEN = sdk_sandbox.kIJ-pC6qgbC.hGUCBlp2G8BFzatSR2l5mx9VzskHuvSi

        String sdkToken = "YOUR_SDK_TOKEN";

        OnfidoConfig onfidoConfig =
                OnfidoConfig.builder(this)
                        .withSDKToken(ONFIDO_API_TOKEN)
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

                showToast("User cancelled.");


            }

            @Override
            public void userCompleted(Captures captures) {
                startCheck(captures);

            }

            @Override
            public void onError(OnfidoException e) {
                e.printStackTrace();
                showToast("Unknown error");
            }


        });
    }

    private void startCheck(Captures captures) {
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
        }
    }
}
