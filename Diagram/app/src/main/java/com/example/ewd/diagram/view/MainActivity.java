package com.example.ewd.diagram.view;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.ewd.diagram.R;
import com.example.ewd.diagram.utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.log_in)
    Button loginButton;

    @BindView(R.id.sign_up)
    TextView signUpTextView;

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


                // Going to Navigation Activity
                //TODO -- Need to add argument that contain login credientials like user_id
                Intent navigationIntent = new Intent(MainActivity.this, NavigationActivity.class);
                startActivity(navigationIntent);
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

