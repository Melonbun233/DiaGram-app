package com.example.ewd.diagram.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ewd.diagram.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.sign_up)
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        setUpSignUpButton();


    }


    /**
     * Method to setup listener for Login Button
     */
    void setUpSignUpButton() {

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Going to Navigation Activity
                //TODO -- Need to add argument that contain user credentials like user_id
                Intent navigationIntent = new Intent(RegistrationActivity.this, NavigationActivity.class);
                startActivity(navigationIntent);
            }
        });
    }
}
