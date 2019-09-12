package com.demon.hdwallpaper.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.demon.hdwallpaper.ItemClickListener;
import com.demon.hdwallpaper.LatestFragment;
import com.demon.hdwallpaper.PostInCateActivity;
import com.demon.hdwallpaper.R;
import com.demon.hdwallpaper.model.category.Category;


import java.util.List;
import java.util.Objects;


public class CategoryAdapter extends RecyclerView.Adapter<CateHolder> {
    private Context context;
    private List<Category> catelist;


    public CategoryAdapter(Context context, List<Category> catelist) {
        this.context = context;
        this.catelist = catelist;

    }

    @NonNull
    @Override
    public CateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemcate, parent, false);

        return new CateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CateHolder holder, int position) {
        final Category category = catelist.get(position);

        holder.tvTitle.setText(category.getName());
        holder.tvSize.setText("(" + category.getCount() + ")");

//        Glide.with(context).
//                load(category.
//                        getEmbedded().
//                        getWpFeaturedmedia().
//                        get(0).getMediaDetails().
//                        getSizes().getThumbnail().
//                        getSourceUrl()).into(holder.imgThumb);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, PostInCateActivity.class);
                intent.putExtra("title", category.getName());
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return catelist.size();
    }


}
