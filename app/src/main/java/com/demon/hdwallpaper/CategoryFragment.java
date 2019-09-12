package com.demon.hdwallpaper;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demon.hdwallpaper.adapter.CategoryAdapter;
import com.demon.hdwallpaper.model.category.Category;
import com.demon.hdwallpaper.model.post.Post;
import com.demon.hdwallpaper.rest.EndlessRecyclerViewScrollListener;
import com.demon.hdwallpaper.rest.Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {
    private SwipeRefreshLayout swipe;
    private RecyclerView lvList;
    //    private ProgressDialog dialog;
    private List<Category> categories;
    private CategoryAdapter categoryAdapter;
    private LinearLayoutManager linearLayoutManager;
    public int page = 1;
    public int per_page = 20;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_category, container, false);
        swipe = view.findViewById(R.id.swipe);
        lvList = view.findViewById(R.id.lvList);
        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        linearLayoutManager = new LinearLayoutManager(getContext());
        lvList.setHasFixedSize(true);
        lvList.setLayoutManager(linearLayoutManager);
        lvList.setAdapter(categoryAdapter);


//        dialog = new ProgressDialog(getContext());
////        dialog.setMessage("Loading...");
////        dialog.show();
        swipe.setRefreshing(true);
        getData(page, per_page);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categories.clear();
                getData(page, per_page);

            }
        });


//        lvList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                getData(page + 1, per_page);
//            }
//        });


        return view;


    }


    public void getData(int page, final int per_page) {
        Retrofit.getInstance().getCategory(page, per_page).enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                swipe.setRefreshing(false);
//                dialog.cancel();
                categories.addAll(response.body());
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                swipe.setRefreshing(false);
            }
        });


    }


}
