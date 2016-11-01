package com.example.rnd.imapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.rnd.imapp.Fragment.HomeFragment;
import com.example.rnd.imapp.Fragment.SOFragment;

/*
 * Created by RND on 10/28/2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private static final int NUM_ITEMS = 4;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return HomeFragment.newInstance("1", "Home Page");
            case 1:
                return SOFragment.newInstance("2", "Stock Opname Page");
            case 2:
                return SOFragment.newInstance("1", "Home Page");
            case 3:
                return SOFragment.newInstance("1", "Home Page");
            default:
                return HomeFragment.newInstance("1","Home Page");
        }
    }

    @Override
    public CharSequence getPageTitle(int position){
        return "Page" + position;
    }
}
