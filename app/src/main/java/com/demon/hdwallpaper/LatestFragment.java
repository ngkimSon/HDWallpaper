package com.demon.hdwallpaper;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demon.hdwallpaper.adapter.CategoryAdapter;
import com.demon.hdwallpaper.adapter.LatestAdapter;
import com.demon.hdwallpaper.model.category.Category;
import com.demon.hdwallpaper.model.post.Post;
import com.demon.hdwallpaper.rest.EndlessRecyclerViewScrollListener;
import com.demon.hdwallpaper.rest.Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestFragment extends Fragment {
    private SwipeRefreshLayout swipe;
    private RecyclerView lvList;
    //    private ProgressDialog dialog;
    private List<Post> latestList;
    private LatestAdapter latestAdapter;
    private StaggeredGridLayoutManager gridLayoutManager;
    public int page = 1;
    public int per_page = 8;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_latest, container, false);
        swipe = view.findViewById(R.id.swipe);
        lvList = view.findViewById(R.id.lvList);
        latestList = new ArrayList<>();
        latestAdapter = new LatestAdapter(getContext(), latestList);
        gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        lvList.setHasFixedSize(true);
        lvList.setLayoutManager(gridLayoutManager);
        lvList.setAdapter(latestAdapter);


//        dialog = new ProgressDialog(getContext());
//        dialog.setMessage("Loading...");
//        dialog.show();
//        loadData(page, per_page);
        swipe.setRefreshing(true);
        getData();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                latestList.clear();
                getData();

            }
        });

//        lvList.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
////            @Override
////            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
////                getData();
////            }
////        });


        return view;


    }


    public void getData() {
        Retrofit.getInstance().getPost().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                dialog.cancel();
                swipe.setRefreshing(false);

                latestList.addAll(response.body());
                latestAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                swipe.setRefreshing(false);
//                dialog.cancel();
            }
        });


    }


}
