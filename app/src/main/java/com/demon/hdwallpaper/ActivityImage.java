package com.demon.hdwallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.demon.hdwallpaper.adapter.CategoryAdapter;
import com.demon.hdwallpaper.adapter.ImageAdapter;
import com.demon.hdwallpaper.model.category.Category;
import com.demon.hdwallpaper.model.image.Image;
import com.demon.hdwallpaper.model.post.Post;
import com.demon.hdwallpaper.rest.EndlessRecyclerViewScrollListener;
import com.demon.hdwallpaper.rest.Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@TargetApi(Build.VERSION_CODES.O)
public class ActivityImage extends AppCompatActivity {
    private SwipeRefreshLayout swipe;
    private RecyclerView lvList;
    private Toolbar toolbar;
    //    private ProgressDialog dialog;
    private List<Image> listImage;
    private ImageAdapter imageAdapter;
    private GridLayoutManager gridLayoutManager;
    public int page = 1;
    public int per_page = 50;
    public int patent;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Photos");
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        swipe = findViewById(R.id.swipe);
        lvList = findViewById(R.id.lvList);
        listImage = new ArrayList<>();

        imageAdapter = new ImageAdapter(this, listImage);
        gridLayoutManager = new GridLayoutManager(this, 2);
        lvList.setHasFixedSize(true);
        lvList.setLayoutManager(gridLayoutManager);
        lvList.setAdapter(imageAdapter);


        swipe.setRefreshing(true);
        getData(page, per_page, Integer.parseInt(getIntent().getStringExtra("id")));

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                listImage.clear();

                getData(page, per_page, Integer.parseInt(getIntent().getStringExtra("id")));
                imageAdapter.setOnLoadMore(true);
//                imageAdapter.setOnLoadMore(true);


            }
        });

//        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
//
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                getData(page + 1, per_page, Integer.parseInt(getIntent().getStringExtra("id")));
//                Log.e("ABC", page + "");
//                Log.e("BAD", totalItemsCount + "");
//                Log.e("CDE", view + "");
//            }
//
//        };
//
//        lvList.addOnScrollListener(scrollListener);
    }

    public void getData(final int page, int per_page, int parent) {
        Retrofit.getInstance().getImage(page, per_page, parent).enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {


//                dialog.cancel();
//                if (response.body() == null) {
//                    lvList.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
//                        @Override
//                        public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//
//                        }
//
//                    });
//
//                    imageAdapter.setOnLoadMore(false);
//
//                }
                listImage.addAll(response.body());
                swipe.setRefreshing(false);
                imageAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {
                swipe.setRefreshing(false);
            }
        });


    }
}
