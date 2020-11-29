package com.example.punkbeer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.punkbeer.R;
import com.example.punkbeer.models.BeerItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {
    private Context mContext;
    private ArrayList<BeerItem> mBeerList;
    public BeerAdapter(Context context, ArrayList<BeerItem> beerList){
        mContext = context;
        mBeerList = beerList;
    }

    @NonNull
    @Override
    public BeerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent,false);
        return new BeerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BeerViewHolder holder, int position) {
        BeerItem currentItem = mBeerList.get(position);
        String imageUrl = currentItem.getImageUrl();
        String name = currentItem.getName();
        String description = currentItem.getDescription();

        holder.mTextViewCreator.setText(name);
        holder.mTextViewLikes.setText(description);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mBeerList.size();
    }

    public class BeerViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public BeerViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);
        }
    }
}
