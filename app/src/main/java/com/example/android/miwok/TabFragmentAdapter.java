package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by juil on 16-07-06.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {

    private Fragment[] pages = new Fragment[]{new NumbersFragment()};

    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return pages[position];
    }

    @Override
    public int getCount() {
        return pages.length;
    }
}
