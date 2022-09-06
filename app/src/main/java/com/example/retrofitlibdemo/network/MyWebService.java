package com.example.retrofitlibdemo.network;

import com.example.retrofitlibdemo.Comment;
import com.example.retrofitlibdemo.Post;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface MyWebService {

    String FEED = "posts";

    //Get method

    //https://jsonplaceholder.typicode.com/posts
    @GET(FEED)
    Call<List<Post>> getPosts();


    //https://jsonplaceholder.typicode.com/posts/3/comments
    //dynamic url parameters with path
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int userId);



    //https://jsonplaceholder.typicode.com/comments?postId=4
    //https://jsonplaceholder.typicode.com/comments?null
    @GET("comments")
    Call<List<Comment>> getAllComments(@Query("postId") Integer postId);


    //https://jsonplaceholder.typicode.com/comments?postId=4&_sort=name&_order=asc
    @GET("comments")
    Call<List<Comment>> getAllCommentsQuery(
            @Query("postId") Integer postId,
            @Query("_sort") String sortBY,
            @Query("_order") String orderBy
    );


    //https://jsonplaceholder.typicode.com/comments?postId=4&_sort=name&_order=asc
    @GET("comments")
    Call<List<Comment>> getAllCommentsQueryMap(@QueryMap Map<String,String> params);



    //https://jsonplaceholder.typicode.com/comments?postId=3
    @GET
    Call<List<Comment>> getAllCommentsUrl(@Url String url);



    //https://jsonplaceholder.typicode.com/comments?postId=3&postId=4&postId=5
    @GET("comments")
    Call<List<Comment>> getAllCommentsArray(@Query("postId") Integer[] ids);


    //https://jsonplaceholder.typicode.com/comments?postId=3&postId=4&postId=5
    @GET("comments")
    Call<List<Comment>> getAllCommentsArray(@Query("postId") int... ids);



    //post method
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //userId=1&title=New%20title&body=New%20Body
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );
    @FormUrlEncoded
    @POST("posts/{id}")
    Call<Post> createPostDynamic(
            @Path("id") int userId,
            @Field("title") String title,
            @Field("body") String text
    );
    @FormUrlEncoded
    @POST("posts")

    Call<Post> createPost(@FieldMap Map<String,String> postMap);
    //put method
    @PUT("posts/{id}")
    Call<Post> updatePostUsingPut(@Path("id") int id,@Body Post post);

    @PATCH("posts/{id}")
    Call<Post> updatePostUsingPatch(@Path("id") int id,@Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
