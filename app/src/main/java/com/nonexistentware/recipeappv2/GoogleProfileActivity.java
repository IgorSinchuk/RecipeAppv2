package com.nonexistentware.recipeappv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class GoogleProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    TextView userEmail, user_email_txt, user_name, user_phone, logout;
    ImageView user_image;
    FirebaseUser fUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_profile);

        fAuth = FirebaseAuth.getInstance();
        fUser = fAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        user_image = findViewById(R.id.user_image);
        logout = findViewById(R.id.logout_btn);
        user_name = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email_txt);

        if (getIntent() != null)
            userEmail.setText(getIntent().getStringExtra("email"));

        userConfiguration();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                if (status.isSuccess()) {
                                    startActivity(new Intent(GoogleProfileActivity.this, GoogleSignActivity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Session does ton close", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                );
            }
        });

    }

    public void userConfiguration() {
        options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build();
    }



    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handlerSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handlerSignInResult(result);
                }
            });
        }
    }

    private void handlerSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            user_name.setText(account.getDisplayName());
            userEmail.setText(account.getEmail());
           try {
                Glide.with(this)
                .load(account.getPhotoUrl())
                .into(user_image);

            } catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(),"Image not found",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
