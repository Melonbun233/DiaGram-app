package com.example.ewd.diagram.view;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ewd.diagram.R;
import com.example.ewd.diagram.model.local.AuthResponse;
import com.example.ewd.diagram.model.local.LoginCredentials;
import com.example.ewd.diagram.model.remote.retrofit.ApiService;
import com.example.ewd.diagram.model.remote.retrofit.RetrofitClientInstance;
import com.example.ewd.diagram.utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * COMMENT : Henry Zeng
 *      I am not familiar with android/java, so maybe I am incorrect for the following comment. Just ignore this one if 
 *      I'm wrong.
 *      I cannot find the code that you store users' info, so I am not sure if users close the app, and the session hasn't expired, 
 *      will they be able to sign in silently the next time they open it?
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.username)
    EditText usernameEditText;

    @BindView(R.id.password)
    EditText passwordEditText;

    @BindView(R.id.log_in)
    Button loginButton;

    @BindView(R.id.sign_up)
    TextView signUpTextView;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Data Binding using butterknife
        ButterKnife.bind(this);

        //Initialize Views
        initViews();


    }


    /**
     * Method to initialize Views
     */
    void initViews() {

        setUpLoginButton();
        setUpSignUpButton();


    }

    /**
     * Method to setup listener for Login Button
     */
    void setUpLoginButton() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                //Checking if fields are empty
                if (TextUtils.isEmpty(username)) {

                    usernameEditText.setError("Can't leave field empty.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {

                    passwordEditText.setError("Can't leave field empty.");
                    return;
                }

                /*
                 * COMMENT : Henry Zeng
                 *      I do think those functions realative to request/response should be put in a separate 
                 *      file (maybe in utils directory) as they are highly resusable. 
                 /

                //Body for API call
                LoginCredentials loginCredentials = new LoginCredentials(username, password);


                /*Create handle for the RetrofitInstance interface*/
                ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
                Call<AuthResponse> call = service.getLoginResponse(loginCredentials);
                call.enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                        if (response.isSuccessful()) {

                            AuthResponse authResponse = response.body();

                            // Going to Navigation Activity
                            Intent navigationIntent = new Intent(MainActivity.this, NavigationActivity.class);
                            navigationIntent.putExtra("token", authResponse.getJwt());
                            navigationIntent.putExtra("userId", authResponse.getUser().getId());

                            startActivity(navigationIntent);

                        } else {

                            Toast.makeText(MainActivity.this, "Login Failed.", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {

                        Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }


    /**
     * Method to setup listener for sign up TextView
     */
    void setUpSignUpButton() {

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Going to RegistrationActivity

                Intent registrationIntent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(registrationIntent);


            }
        });
    }


}

