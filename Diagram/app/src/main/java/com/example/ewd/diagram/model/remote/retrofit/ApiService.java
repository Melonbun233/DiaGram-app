package com.example.ewd.diagram.model.remote.retrofit;

import com.example.ewd.diagram.model.local.AuthResponse;
import com.example.ewd.diagram.model.local.LoginCredentials;
import com.example.ewd.diagram.model.local.Post;
import com.example.ewd.diagram.model.local.PostsListResponse;
import com.example.ewd.diagram.model.local.SignUpCredentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * All API calls
 */
public interface ApiService {

//Login
@POST("/login")
Call<AuthResponse> getLoginResponse(@Body LoginCredentials loginCredentials);

//Sign up
@POST("/signup")
Call<AuthResponse> getSignUpResponse(@Body SignUpCredentials signUpCredentials);

//Get all posts
@GET("/posts")
Call<List<Post>> getAllPosts(@Header("Authorization") String token);


}
