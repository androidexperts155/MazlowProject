package com.mazlow.customclasses;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.Mazlow.R;
import com.mazlow.search_address.SearchAddressView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class M {
    public static String TOPIC = "9";
    private static ProgressDialog dialog;
    private static Dialog dialog1;

    private static ProgressDialog progressDialog;
    Context context;

    public static void showToast(Context mainActivity, String string) {
        Toast.makeText(mainActivity, string, Toast.LENGTH_SHORT).show();
    }

    public static Dialog showloader(Context context, String message, boolean isCancel, boolean isDismiss) {
        return CustomProgress.show(context, message, isCancel);
    }

    public static void hideloader(Activity loginActivity) {
        progressDialog.dismiss();
    }

    public static void launchActivity(Context context, Class<?> launchingActivity) {
        (context).startActivity(new Intent(context, launchingActivity));
    }


    public static boolean isNetworkAvailable(Context context) {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);

            if (netInfo != null
                    && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                status = true;
            } else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo != null
                        && netInfo.getState() == NetworkInfo.State.CONNECTED)
                    status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }

    public static void networkDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.internetlayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button rl_back = dialog.findViewById(R.id.btnOk);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static String changeDateOnly(String inputString) {
        String[] date1 = new String[1];
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String outputPattern = "EEE,MMM dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(inputString);
            str = outputFormat.format(date);
            date1 = str.split("&");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1[0];
    }

    public static String chngeTime(String inputString) {
        String[] date1 = new String[1];
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String outputPattern = "hh:mm aa";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;
        try {
            date = inputFormat.parse(inputString);
            str = outputFormat.format(date);
            date1 = str.split("&");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1[0];
    }

    public static String chngeTime12to24(String inputString) {

        //Format of the date defined in the input String
        DateFormat df = new SimpleDateFormat("hh:mm aa");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("HH:mm");
        Date date = null;
        String output = null;
        try {
            //Converting the input String to Date
            date = df.parse(inputString);
            //Changing the format of date and storing it in String
            output = outputformat.format(date);
            //Displaying the date
            System.out.println(output);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return output;
    }

    public static String chngeTime24to12(String inputString) {

        //Format of the date defined in the input String
        DateFormat df = new SimpleDateFormat("HH:mm");
        //Desired format: 24 hour format: Change the pattern as per the need
        DateFormat outputformat = new SimpleDateFormat("hh:mm aa");
        Date date = null;
        String output = null;
        try {
            //Converting the input String to Date
            date = df.parse(inputString);
            //Changing the format of date and storing it in String
            output = outputformat.format(date);
            //Displaying the date
            System.out.println(output);
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        return output;
    }

    public static void datePiker(FragmentActivity activity, EditText et_datebirth) {
         int mYear, mMonth, mDay, mHour, mMinute;

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.set(mYear-18, mMonth, mDay, 0, 0, 0);


        DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        et_datebirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());

        //arguments are   year , month , date (use for setting custom max date)
//        datePickerDialog.getDatePicker().setMaxDate(2017,8,7);
        datePickerDialog.show();
    }


}


