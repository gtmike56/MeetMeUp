package com.example.meetmeup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class GroupListAdapter extends RecyclerView.Adapter<GroupListAdapter.GroupViewHolder> {

    class GroupViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView groupNameView;
        final GroupListAdapter mAdapter;
        private final Context context;

        public GroupViewHolder(View itemView, GroupListAdapter adapter) {
            super(itemView);
            context = itemView.getContext();
            groupNameView = itemView.findViewById(R.id.group_name);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("kek", "Item clicked");
            int mPosition = getLayoutPosition();
            Group mGroup = mGroupList.get(mPosition);
            Intent intent = new Intent(context, GroupDetailsActivity.class);
            intent.putExtra("group_id", mGroup.getDocumentId());
            ((Activity) context).startActivity(intent);
        }
    }

    private LinkedList<Group> mGroupList;
    private LayoutInflater mInflater;

    public GroupListAdapter(Context context, LinkedList<Group> groupList){
        mInflater = LayoutInflater.from(context);
        this.mGroupList = groupList;
    }
    @NonNull
    @Override
    public GroupListAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.grouplist_item,parent,false);
        return new GroupViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupListAdapter.GroupViewHolder holder, int position) {
        Group mCurrent = mGroupList.get(position);
        holder.groupNameView.setText(mCurrent.getName());
    }

    @Override
    public int getItemCount() {
        return mGroupList.size();
    }
}
