package com.demon.hdwallpaper.rest;

import com.demon.hdwallpaper.model.category.Category;
import com.demon.hdwallpaper.model.image.Image;
import com.demon.hdwallpaper.model.post.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("/wp-json/wp/v2/posts?_embed")
    Call<List<Post>> getPost();

    @GET("/wp-json/wp/v2/categories")
    Call<List<Category>> getCategory(@Query("page") int page,
                                     @Query("per_page") int per_page);
//    http://asian.dotplays.com/wp-json/wp/v2/media?parent=377&page=1&per_page=10
    @GET("/wp-json/wp/v2/media")
    Call<List<Image>> getImage(@Query("page") int page,
                               @Query("per_page") int per_page,
                               @Query("parent") int parent
                               );


}
