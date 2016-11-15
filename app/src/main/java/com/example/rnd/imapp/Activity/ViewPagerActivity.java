package com.example.rnd.imapp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.adapter.ViewPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ViewPagerActivity extends AppCompatActivity{
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ViewPagerAdapter vpAdapter;
    private ViewPager vpPager;
    private ImageView homeIcon, soIcon, ackIcon, historyIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(ViewPagerActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

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
    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mFirebaseAuth.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
