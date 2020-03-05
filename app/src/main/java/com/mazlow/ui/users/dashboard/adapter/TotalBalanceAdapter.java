package com.mazlow.ui.users.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.Mazlow.R;
import com.mazlow.ui.users.dashboard.fragments.models.TotalBalanceModel;

import java.util.ArrayList;

public class TotalBalanceAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<TotalBalanceModel> dataList;

    public TotalBalanceAdapter(Context context, ArrayList<TotalBalanceModel> balanceModelArrayList) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dataList=balanceModelArrayList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == (LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TotalBalanceModel item = dataList.get(position);
        View itemView = layoutInflater.inflate(R.layout.item_total_balance, container, false);

       TextView textView= itemView.findViewById(R.id.txt_title);
       String price= String.valueOf((item.balance/100))+"."+String.valueOf((item.balance%100));
       textView.setText(item.currencySymbol+" "+price);

        if(item.active){
            textView.setAlpha(1);


        }else {
            textView.setAlpha(0.5f);

        }


        container.addView(itemView);
        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
