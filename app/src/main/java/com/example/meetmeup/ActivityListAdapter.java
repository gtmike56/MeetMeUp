package com.example.meetmeup;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ActivityListAdapter extends RecyclerView.Adapter<ActivityListAdapter.ActivityViewHolder> {

    class ActivityViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView activityTitle, activityDate, activityTime;
        final ActivityListAdapter mAdapter;
        private final Context context;

        public ActivityViewHolder(View itemView, ActivityListAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            activityTitle = itemView.findViewById(R.id.activity_title);
            activityDate = itemView.findViewById(R.id.activity_date);
            activityTime = itemView.findViewById(R.id.activity_time);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("kek", "Item clicked");
            int mPosition = getLayoutPosition();
            Activity mActivity = mActivityList.get(mPosition);
            Intent intent = new Intent(context, ActivityFrame.class);
            intent.putExtra("activity_id",mActivity.getId());
            intent.putExtra("group_id",mActivity.getGroupID());
            ((android.app.Activity) context).startActivity(intent);
        }
    }

    private LinkedList<Activity> mActivityList;
    private LayoutInflater mInflater;

    public ActivityListAdapter(Context context, LinkedList<Activity> activityList){
        mInflater = LayoutInflater.from(context);
        this.mActivityList = activityList;
    }
    @NonNull
    @Override
    public ActivityListAdapter.ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.activitylist_item,parent,false);
        return new ActivityViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityListAdapter.ActivityViewHolder holder, int position) {
        Activity mCurrent = mActivityList.get(position);
        holder.activityTitle.setText(mCurrent.getTitle());
        holder.activityDate.setText(mCurrent.getDate());
        holder.activityTime.setText(mCurrent.getTime());
    }

    @Override
    public int getItemCount() {
        return mActivityList.size();
    }
}
