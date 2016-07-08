package com.example.android.miwok;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by juil on 16-07-06.
 */
public class TabFragmentAdapter extends FragmentPagerAdapter {

    private Fragment[] pages = new Fragment[]{new NumbersFragment(), new ColorsFragment(),
                                                new FamilyFragment(), new PhrasesFragment()};
    private Integer[] titles = new Integer[]{R.string.category_numbers, R.string.category_colors,
                                            R.string.category_family, R.string.category_phrases};
    private Context context;

    public TabFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
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
        return context.getString(titles[position]);
    }
}
