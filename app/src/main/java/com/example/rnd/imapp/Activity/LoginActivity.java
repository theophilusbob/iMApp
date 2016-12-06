package com.example.rnd.imapp.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rnd.imapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "" ;
    private String strEmail;
    protected EditText emailEditText;
    protected EditText passwordEditText;
    protected Button logInButton;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getEmail());
                    Intent intent = new Intent(LoginActivity.this, ViewPagerActivity.class);
                    startActivity(intent);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        // Find view
        emailEditText = (EditText) findViewById(R.id.usernameField);
        passwordEditText = (EditText) findViewById(R.id.passwordField);
        logInButton = (Button) findViewById(R.id.loginButton);

        // Get intent
        Intent intent = getIntent();
        String getLastEmail = intent.getStringExtra("last_email_logged_in");

        // Shared preferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString("domain_email", getLastEmail);
        //editor.apply();

        //Retrieve data
        String autoCompleteMail = sharedPreferences.getString("domain_email", "");

        emailEditText.setText(autoCompleteMail);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Shared preferences
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("domain_email", email);
                editor.apply();

                email = email.trim();
                password = password.trim();

                if (email.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    pDialog = new ProgressDialog(LoginActivity.this);
                    // Showing progress dialog before making http request
                    pDialog.setMessage("Logging in...");
                    pDialog.show();

                    mFirebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        hidePDialog();
                                        Intent intent = new Intent(LoginActivity.this, ViewPagerActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        hidePDialog();
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                        builder.setMessage(task.getException().getMessage())
                                                .setTitle(R.string.login_error_title)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
