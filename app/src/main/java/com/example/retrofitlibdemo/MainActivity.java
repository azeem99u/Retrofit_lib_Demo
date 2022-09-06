package com.example.retrofitlibdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitlibdemo.network.MyWebService;
import com.example.retrofitlibdemo.network.NetworkHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    MyWebService myWebService;
    Button runButton,clearButton;
    TextView outPutTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        myWebService = NetworkHelper.getRetrofit().create(MyWebService.class);
        runButton.setOnClickListener(this::runCode);
        clearButton.setOnClickListener(this::clearTxt);
    }



    private void runCode(View view) {
        // null values are ignored by retrofit json by default;

        //Get Method
        //getPosts();
        //getComments();
        //getAllComments();
        //getAllCommentsQuery();
        //getAllCommentsQueryMap();
        //getAllCommentsUrl();
        //getAllCommentsArrayIds();

        //Post method
        //createPost();
        //createPostUsingField();
        //createPostUsingFieldMap();

        //Put Method
        //updatePostUsingPut();

        //patch Method
        //updatePostUsingPatch();

        //Delete Method
        //deletePost();

    }

    private void deletePost() {

        Call<Void> postCall = myWebService.deletePost(4);
        postCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Deleted Successfully."+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }


    private void updatePostUsingPatch() {

        //putting new values without affecting other all existing values (selected only affected)
        Post post = new Post(32,"New Test",null);
        Call<Post> postCall = myWebService.updatePostUsingPatch(4, post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    Post post = response.body();
                    showPost(post);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }

    private void updatePostUsingPut() {
        //putting new values and remove all existing values (All affected)
        Post post = new Post(12,"New Test",null);
        Call<Post> postCall = myWebService.updatePostUsingPut(4, post);
        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    Post post = response.body();
                    showPost(post);
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });


    }

    private void createPostUsingFieldMap() {
        Map<String,String> postMap = new HashMap<>();

        postMap.put("userId","12");
        postMap.put("title","Test");
        postMap.put("body","test body");
        Call<Post> call = myWebService.createPost(postMap);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    showPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void createPostUsingField() {
        Call<Post> call = myWebService.createPost(21,"myTest","my Test Txt");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    showPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }


    private void createPost() {

        Post post = new Post(2,"Test","my test desc");
        Call<Post> call = myWebService.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()){
                    showPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });

    }










    public void getAllCommentsArrayIds () {
        Call<List<Comment>> call = myWebService.getAllCommentsArray(new Integer[]{12,3,4,5});
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()){
                    for (Comment comment:response.body()) {
                        showComments(comment);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private void getAllCommentsUrl() {

            Call<List<Comment>> call = myWebService.getAllCommentsUrl("https://jsonplaceholder.typicode.com/comments?postId=3");
            call.enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    if (response.isSuccessful()){
                        for (Comment comment:response.body()) {
                            showComments(comment);
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {

                }
            });
    }

    private void getAllCommentsQuery() {

            Call<List<Comment>> call = myWebService.getAllCommentsQuery(4,"name","desc");
            call.enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                    if (response.isSuccessful()){
                        for (Comment comment:response.body()) {
                            showComments(comment);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {

                }
            });

    }

    private void getAllComments() {
        Call<List<Comment>> call = myWebService.getAllComments(null);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()){
                    for (Comment comment:response.body()) {
                        showComments(comment);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }

    private void getAllCommentsQueryMap() {
        Map<String, String> params = new HashMap<>();
        params.put("postId","3");
        params.put("_sort","name");
        params.put("_order","asc");

        Call<List<Comment>> call = myWebService.getAllCommentsQueryMap(params);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()){
                    for (Comment comment:response.body()) {
                        showComments(comment);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }


    private void getComments() {
        Call<List<Comment>> call = myWebService.getComments(1);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()){
                    for (Comment comment:response.body()) {
                        showComments(comment);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }


    private void getPosts() {
        Call<List<Post>> call = myWebService.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()){
                    for (Post post:response.body()) {
                        showPost(post);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    private void showComments(Comment comment) {

        String text = comment.getPostId()+"\n"+
                comment.getId()+"\n"+
                comment.getName()+"\n"+
                comment.getEmail()+"\n"+
                comment.getBody()+"\n\n";

        outPutTxt.append(text);
    }

    private void showPost(Post post) {

        String text = post.getUserId()+"\n"+
                post.getId()+"\n"+
                post.getTitle()+"\n"+
                post.getText()+"\n\n";

        outPutTxt.append(text);

    }

    private void clearTxt(View view) {
        outPutTxt.setText("");
    }



    private void initViews() {
        runButton = findViewById(R.id.runCodeBtn);
        clearButton = findViewById(R.id.clearBtn);
        outPutTxt = findViewById(R.id.outPutText);
        outPutTxt.setMovementMethod(new ScrollingMovementMethod());
    }


}