package com.example.rnd.imapp.util;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;

import com.example.rnd.imapp.Activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by RnD on 08/12/2016.
 */

public class LogoutTimer extends CountDownTimer {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public LogoutTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {
        FirebaseAuth.getInstance().signOut();
        Log.i(user.getEmail(), "Just signed out!");
    }
}
