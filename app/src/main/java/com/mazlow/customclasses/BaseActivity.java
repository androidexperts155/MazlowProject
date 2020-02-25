package com.mazlow.customclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.Mazlow.R;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mazlow.interfaces.PermissionListner;

import java.util.List;

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

    public  void checkSinglePermision(AppCompatActivity context, String Permisions, final PermissionListner permisionListener) {
        if (Build.VERSION.SDK_INT >= 21) {
            Dexter.withActivity(context)
                    .withPermission(Permisions)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {

                            permisionListener.OnPermissionGranted();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            permisionListener.OnPermsionDenied();
                            if (response.isPermanentlyDenied()) {
                                permisionListener.OnPermsionDenied();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).check();
        } else {

        }


    }

    public  void checkMultiplePermisions(AppCompatActivity appCompatActivity, String permsions, String permsion, final PermissionListner permisionListener) {
        Dexter.withActivity(appCompatActivity).withPermissions(permsions, permsion).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    permisionListener.OnPermissionGranted();
                } else if (report.isAnyPermissionPermanentlyDenied()) {
                    permisionListener.OnPermsionDenied();
                } else {
                    permisionListener.OnPermsionDenied();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }


    public  void showSettingsDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Need Permissions");
        builder.setCancelable(false);
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.With out Permission Affinite may not function proprely");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        showsettingdialog(context);

                    }
                }
        );

        builder.show();

    }

    private  void showsettingdialog(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

}
