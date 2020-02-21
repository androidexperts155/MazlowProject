package com.mazlow.ui.users.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import com.Mazlow.R;
import com.mazlow.customclasses.Bean;
import com.mazlow.signup.SignupActivity;
import com.mazlow.ui.users.activities.MainActivity;


public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    int images[];

    LayoutInflater layoutInflater;

    String[] Stringtitle;
    String[] Stringmessage;



    public ViewPagerAdapter(Context context, int[] images, String[] title_array, String[] message_array) {
        this.context = context;
        this.images = images;
        this.Stringtitle = title_array;
        this.Stringmessage = message_array;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Stringtitle.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.image_item, container, false);
        TextView tv_title = itemView.findViewById(R.id.tv_title);
        TextView tv_message = itemView.findViewById(R.id.tv_message);
        Button btn_join = itemView.findViewById(R.id.btn_join);
//        tv_title.setText(Stringtitle[position]);
        tv_message.setText(Stringmessage[position]);

        if (position==0)
        {

            String str = "With great<br><b>wellbeing,<br></b> comes great <br><b>finance</b>";
//            String str = "<a>With great \n<b>wellbeing,\n </b>comes great\n<b>finance</b></a>";


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tv_title.setText(Html.fromHtml(str, Html.FROM_HTML_MODE_COMPACT));
            }
            else
            {
                tv_title.setText(Html.fromHtml(str));
            }


        }
        else
        {
            tv_title.setText(Stringtitle[position]);
        }


        if (position==3)
        {
            btn_join.setVisibility(View.VISIBLE);
        }
        else
        {
            btn_join.setVisibility(View.GONE);
        }

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, SignupActivity.class);
                intent.putExtra(Bean.LOGINTYPE,"2");
                context.startActivity(intent);
            }
        });

        container.addView(itemView);
        return itemView;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
