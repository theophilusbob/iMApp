package com.example.rnd.imapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.adapter.ViewPagerAdapter;
import com.example.rnd.imapp.util.LogoutTimer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ViewPagerActivity extends AppCompatActivity{
    private ViewPagerAdapter vpAdapter;
    private ViewPager vpPager;
    private ImageView homeIcon, soIcon, ackIcon, historyIcon;
    private LogoutTimer logoutTimer;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.signout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_log_out:
                Log.i("Current User", FirebaseAuth.getInstance().getCurrentUser().getEmail());
                startActivity(new Intent(ViewPagerActivity.this, LoginActivity.class).putExtra("last_email_logged_in", FirebaseAuth.getInstance().getCurrentUser().getEmail()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
                FirebaseAuth.getInstance().signOut();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ViewPagerActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("domain_email",  user.getEmail());
                    editor.apply();
                }/* else {
                    // User is signed out
                    startActivity(new Intent(ViewPagerActivity.this, LoginActivity.class).putExtra("last_email_logged_in", user.getEmail()));
                    finish();
                }*/
            }
        };
        // [END auth_state_listener]

        // ViewPager
        vpAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        vpPager.setAdapter(vpAdapter);

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        homeIcon.setImageResource(R.drawable.icon_home_active);
                        soIcon.setImageResource(R.drawable.icon_stockopname);
                        ackIcon.setImageResource(R.drawable.icon_ack);
                        historyIcon.setImageResource(R.drawable.icon_history);
                        getSupportActionBar().setTitle("Home");
                        break;
                    case 1:
                        homeIcon.setImageResource(R.drawable.icon_home);
                        soIcon.setImageResource(R.drawable.icon_stockopname_active);
                        ackIcon.setImageResource(R.drawable.icon_ack);
                        historyIcon.setImageResource(R.drawable.icon_history);
                        getSupportActionBar().setTitle("Stock Opname");
                        break;
                    case 2:
                        homeIcon.setImageResource(R.drawable.icon_home);
                        soIcon.setImageResource(R.drawable.icon_stockopname);
                        ackIcon.setImageResource(R.drawable.icon_ack_active);
                        historyIcon.setImageResource(R.drawable.icon_history);
                        getSupportActionBar().setTitle("ACK");
                        break;
                    case 3:
                        homeIcon.setImageResource(R.drawable.icon_home);
                        soIcon.setImageResource(R.drawable.icon_stockopname);
                        ackIcon.setImageResource(R.drawable.icon_ack);
                        historyIcon.setImageResource(R.drawable.icon_history_active);
                        getSupportActionBar().setTitle("History Order");
                        break;
                    default:
                        homeIcon.setImageResource(R.drawable.icon_home_active);
                        soIcon.setImageResource(R.drawable.icon_stockopname);
                        ackIcon.setImageResource(R.drawable.icon_ack);
                        historyIcon.setImageResource(R.drawable.icon_history);
                        getSupportActionBar().setTitle("Home");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });

        // Tab icon
        homeIcon = (ImageView) findViewById(R.id.homeIcon);
        soIcon = (ImageView) findViewById(R.id.soIcon);
        ackIcon = (ImageView) findViewById(R.id.ackIcon);
        historyIcon = (ImageView) findViewById(R.id.historyIcon);

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Countdown timer
        logoutTimer = new LogoutTimer(10000, 1000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
       // logoutTimer.cancel();
        Log.i("Timer: ", "Cancelled");

        /*if (user == null) {
            loadLogInView();
        }*/
    }

    @Override
    protected void onPause() {
        super.onPause();
       // logoutTimer.start();
        Log.i("Timer: ", "Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //logoutTimer.cancel();
        Log.i("Timer: ", "Cancelled");

        /*if (user == null) {
            Intent intent = new Intent(ViewPagerActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        //logoutTimer.start();
        Log.i("Timer: ", "Started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*try {
            FirebaseAuth.getInstance().signOut();
            Log.i("iMApp: ", "Destroyed");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
