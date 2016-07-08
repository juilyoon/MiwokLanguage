package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by juil on 16-07-06.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {

    private Fragment[] pages = new Fragment[]{new NumbersFragment(), new ColorsFragment(),
                                                new FamilyFragment(), new PhrasesFragment()};
    private String[] titles = new String[]{"Numbers", "Colours", "Family", "Phrases"};

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

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
