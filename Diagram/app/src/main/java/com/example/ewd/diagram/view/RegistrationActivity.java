package com.example.ewd.diagram.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ewd.diagram.R;
import com.example.ewd.diagram.model.local.AuthResponse;
import com.example.ewd.diagram.model.local.SignUpCredentials;
import com.example.ewd.diagram.model.remote.retrofit.ApiService;
import com.example.ewd.diagram.model.remote.retrofit.RetrofitClientInstance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText usernameEditText;

    @BindView(R.id.password)
    EditText passwordEditText;

    @BindView(R.id.first_name)
    EditText firstNameEditText;

    @BindView(R.id.last_name)
    EditText lastNameEditText;

    @BindView(R.id.access_code)
    EditText accessCodeEditText;

    @BindView(R.id.sign_up)
    Button signUpButton;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String accessCode;


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

                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                accessCode = accessCodeEditText.getText().toString();


                /*
                 *  COMMENT : Henry Zeng
                 *      I think it would would be better if you can also check the input doesn't contain
                 *      any sql command (or other command used to modify database). This check can prevent SQL injection.
                 */
                
                //Checking if fields are empty
                if (TextUtils.isEmpty(firstName)) {

                    firstNameEditText.setError("Can't leave field empty.");
                    return;
                }


                if (TextUtils.isEmpty(lastName)) {

                    lastNameEditText.setError("Can't leave field empty.");
                    return;
                }

                if (TextUtils.isEmpty(username)) {

                    usernameEditText.setError("Can't leave field empty.");
                    return;
                }


                if (TextUtils.isEmpty(password)) {

                    passwordEditText.setError("Can't leave field empty.");
                    return;
                }


                if (TextUtils.isEmpty(accessCode)) {

                    accessCodeEditText.setError("Can't leave field empty.");
                    return;
                }



                SignUpCredentials signUpCredentials = new SignUpCredentials();
                signUpCredentials.setFirstName(firstName);
                signUpCredentials.setLastName(lastName);
                signUpCredentials.setUsername(username);
                signUpCredentials.setPassword(password);
                signUpCredentials.setAccessCode(accessCode);

                /*Create handle for the RetrofitInstance interface*/
                final ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
                Call<AuthResponse> call = service.getSignUpResponse(signUpCredentials);
                call.enqueue(new Callback<AuthResponse>() {
                    @Override
                    public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                        if (response.isSuccessful()) {

                            AuthResponse authResponse = response.body();

                            // Going to Navigation Activity
                            Intent navigationIntent = new Intent(RegistrationActivity.this, NavigationActivity.class);
                            navigationIntent.putExtra("token", authResponse.getJwt());
                            navigationIntent.putExtra("userId", authResponse.getUser().getId());

                            startActivity(navigationIntent);

                        } else {

                            /*
                            JSONObject jsonObject;
                            JSONArray jsonArray;
                            try {
                                jsonObject = new JSONObject(response.errorBody().toString());
                                jsonArray = jsonObject.getJSONArray("errors");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            */

                            Toast.makeText(RegistrationActivity.this, "Sign up failed.", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<AuthResponse> call, Throwable t) {

                        Toast.makeText(RegistrationActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
