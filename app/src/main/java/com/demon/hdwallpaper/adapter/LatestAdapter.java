package com.demon.hdwallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.demon.hdwallpaper.ActivityImage;
import com.demon.hdwallpaper.ImageDetail;
import com.demon.hdwallpaper.ItemClickListener;
import com.demon.hdwallpaper.R;

import com.demon.hdwallpaper.model.post.Post;

import java.util.List;


public class LatestAdapter extends RecyclerView.Adapter<LatestHolder> {
    private Context context;
    private List<Post> postlist;


    public LatestAdapter(Context context, List<Post> postlist) {
        this.context = context;
        this.postlist = postlist;

    }

    @NonNull
    @Override
    public LatestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemmain, parent, false);

        return new LatestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestHolder holder, int position) {
        final Post latest = postlist.get(position + 1);


        holder.tvName.setText(latest.getTitle().getRendered());


        Glide.with(context).
                load(latest.
                        getEmbedded().
                        getWpFeaturedmedia().
                        get(0).getMediaDetails().getSizes().getThumbnail().getSourceUrl()).into(holder.imgThumb);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, ActivityImage.class);
                intent.putExtra("id", latest.getId() + "");
                context.startActivity(intent);

            }

        });
    }

    @Override
    public int getItemCount() {

        return postlist.size();
    }


}
