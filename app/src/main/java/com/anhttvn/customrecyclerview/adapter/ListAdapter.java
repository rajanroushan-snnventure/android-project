package com.anhttvn.customrecyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anhttvn.customrecyclerview.R;
import com.anhttvn.customrecyclerview.model.ItemAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemAdapter.Data> mList;
    private Context mContext;
    public ListAdapter(List<ItemAdapter.Data> list, Context context){
        super();
        mList = list;
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_custom, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder viewHolder, int position) {
        ItemAdapter.Data itemAdapter = mList.get(position);

        if(itemAdapter.getTitle() != null) {
            ((ViewHolder) viewHolder).mTv_name.setText(itemAdapter.getTitle());
        }else{
            ((ViewHolder) viewHolder).mTv_name.setText("No Title");
        }
        if(itemAdapter.getDescription() != null){
            ((ViewHolder) viewHolder).mTv_desc.setText(itemAdapter.getDescription());
        }else{
            ((ViewHolder) viewHolder).mTv_desc.setText("No Description");
        }
        //((ViewHolder) viewHolder).mImg.setImageResource(itemAdapter.data.get(position).imageHref);
        if(itemAdapter.getImageHref() != null) {
            Picasso.get().load(itemAdapter.getImageHref()).placeholder(R.drawable.dau).resize(50, 50)
                    .centerCrop().into(((ViewHolder) viewHolder).mImg);
        }else{
            ((ViewHolder) viewHolder).mImg.setImageResource(R.drawable.cachua);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTv_name;
        public TextView mTv_desc;
        public ImageView mImg;
        public ViewHolder(View itemView) {

            super(itemView);
            mTv_name = (TextView) itemView.findViewById(R.id.tv_name);
            mTv_desc = (TextView) itemView.findViewById(R.id.tv_desc);
            mImg = (ImageView) itemView.findViewById(R.id.img_item);

        }
    }
}
