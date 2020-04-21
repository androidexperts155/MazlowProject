package com.mazlow.payments_subscription.adapter;

import android.app.Activity;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.Mazlow.R;

public class CardsAdapter extends PagerAdapter {
    Activity activity;
    TypedArray icons;
    public CardsAdapter(Activity activity){
        this.activity=activity;
        icons = activity.getResources().obtainTypedArray(R.array.cards_list);
    };

    @Override
    public int getCount() {
        return icons.length();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup layout = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.adapter_cars, container, false);
        ImageView imageView =layout.findViewById(R.id.imageViewCards);
        imageView.setImageResource(icons.getResourceId(position, -1));
        container.addView(layout);
        return  layout;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
