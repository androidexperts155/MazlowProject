package com.mazlow.ui.users.dashboard.notification;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.Mazlow.R;
import com.mazlow.ui.users.dashboard.notification.model.NotificationInfo;
import java.util.List;
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyviewHolder> {

    Context context;
    List<NotificationInfo> NotificationList;


    public NotificationAdapter(NotificationActivity notificationActivity, List<NotificationInfo> notificationInfoList) {

        context =notificationActivity;
        NotificationList =notificationInfoList;

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.notification_adapter_view,parent,false);
        MyviewHolder myviewHolder = new MyviewHolder(view);
        return  myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        NotificationInfo responseGoals=NotificationList.get(position);

        holder.title.setText(responseGoals.getTitle());
        holder.description.setText(responseGoals.getMessage());

    }

    @Override
    public int getItemCount() {
        return NotificationList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextView title,description;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            description =itemView.findViewById(R.id.tv_description);
        }
    }
}
