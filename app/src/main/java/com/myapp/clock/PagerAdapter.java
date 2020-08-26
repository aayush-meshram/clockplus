package com.myapp.clock;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class PagerAdapter extends FragmentPagerAdapter {

    private int noOfTabs;

    public PagerAdapter(FragmentManager fm, int noOfTabs)   {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)   {
            case 0:
                return new AlarmFragment();
            case 1:
                return new timerfragment1();
            case 2:
                return new StopwatchFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}