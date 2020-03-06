package com.mazlow.adduserdetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.Mazlow.R;
import com.mazlow.signup.postalcode.model.Thoroughfare;

import java.util.List;

public class PostalCodeAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
      List<Thoroughfare> items;
    private final int mResource;

    public PostalCodeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {


        super(context, resource, 0, objects);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView offTypeTv = view.findViewById(R.id.tv_tilte);

//
        Thoroughfare offerData = items.get(position);

//        offTypeTv.setText(offerData.getDeliveryPoints().get(position).getBuildingNumber());


//        offTypeTv.setText(offerData.getDeliveryPoints().get(position).getBuildingNumber()+" "+offerData.getThoroughfareName()+" "+offerData.getThoroughfareDescriptor());


        return view;
    }
}

