package com.mazlow.ui.users.addmoney.adapter;

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
import com.mazlow.ui.users.addmoney.models.CardDetail;
import com.mazlow.ui.users.addmoney.models.MyCardModel;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<CardDetail> items;
    private final int mResource;

    public CustomArrayAdapter(@NonNull Context context, @LayoutRes int resource,
                              @NonNull List objects) {
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

        TextView title = (TextView) view.findViewById(R.id.txt_title);
        if(items.get(position).getPfsToken().equals("")){

        }else {
            title.setText(items.get(position).getCardNumber());
        }
        return view;
    }
}