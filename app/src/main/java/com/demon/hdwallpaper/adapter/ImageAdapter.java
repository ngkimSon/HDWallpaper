package com.demon.hdwallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demon.hdwallpaper.ImageDetail;
import com.demon.hdwallpaper.ItemClickListener;
import com.demon.hdwallpaper.R;
import com.demon.hdwallpaper.adapter.LatestHolder;
import com.demon.hdwallpaper.model.image.Image;
import com.demon.hdwallpaper.model.post.Post;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageHolder> {
    private Context context;
    private List<Image> imagelist;

    public boolean isOnLoadMore() {
        return onLoadMore;
    }

    public void setOnLoadMore(boolean onLoadMore) {
        this.onLoadMore = onLoadMore;
    }

private boolean onLoadMore = true;


    public ImageAdapter(Context context, List<Image> imagelist) {
        this.context = context;
        this.imagelist = imagelist;

    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemimage, parent, false);

        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageHolder holder, int position) {
        final Image images = imagelist.get(position);

//        holder.tvName.setText(images.getTitle().getRendered());

        Glide.with(context).
                load(images.getMediaDetails().getSizes().getFull().getSourceUrl()).into(holder.imgThumb);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, ImageDetail.class);
//                intent.putExtra("id", images.getId() + "");
                intent.putExtra("detail", images.getGuid().getRendered());
                context.startActivity(intent);

            }

        });
    }

    int ITEM = 1;
    int LOAD_MORE = 2;

    @Override
    public int getItemViewType(int position) {

        if (onLoadMore) {
            if (position == imagelist.size()) {
                return LOAD_MORE;
            } else {
                return ITEM;
            }
        } else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return imagelist.size();
    }


}
