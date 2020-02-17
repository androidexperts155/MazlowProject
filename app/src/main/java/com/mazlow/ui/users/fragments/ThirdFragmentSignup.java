package com.mazlow.ui.users.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.Mazlow.R;
import com.onfido.android.sdk.capture.ExitCode;
import com.onfido.android.sdk.capture.Onfido;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.OnfidoFactory;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.ui.options.FlowStep;
import com.onfido.android.sdk.capture.upload.Captures;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragmentSignup extends Fragment {
    Button continue_btn;
    private Onfido client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_fragment_signup, container, false);

        initview(view);
        return  view;
    }

    private void initview(View view) {
        continue_btn = view.findViewById(R.id.continue_btn);
        client = OnfidoFactory.create(getActivity()).getClient();
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFlow();
            }
        });
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
        String sdkToken = "YOUR_SDK_TOKEN";

        OnfidoConfig onfidoConfig =
                OnfidoConfig.builder(getActivity())
                        .withSDKToken(sdkToken)
                        .withCustomFlow(flowSteps)
                        .build();

        client.startActivityForResult(getActivity(), 1, onfidoConfig);
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

            }

            @Override
            public void onError(OnfidoException e) {

            }


        });
    }
}
