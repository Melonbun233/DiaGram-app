package com.example.ewd.diagram.model.remote.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton class that returns a Retrofit instance
 */
public class RetrofitClientInstance {

    private static Retrofit retrofit;
    /*
     * COMMENT : by Henry Zeng
     *  I think it would be better if you store thoes sensitive information such as ip address in 
     *  a configure file, and it should not be pushed to github.
    */
    private static final String BASE_URL = "http://40.83.209.30";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
