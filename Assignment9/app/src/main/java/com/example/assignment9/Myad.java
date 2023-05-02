package com.example.assignment9;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

abstract class Myad extends FragmentPagerAdapter {




    int totalTabs1;
    public Myad(Context c1, FragmentManager fm, int totalTabs1) {
        super(fm);
        context1 = c1;
        FragmentManager fm;
        this.totalTabs1 = totalTabs1;


    }
    Context context1;
    String res,rev;

    public Fragment get(int p) {



        Bundle bundle = new Bundle();
        bundle.getString("bbd", res);
        Bundle bundle1 = new Bundle();
        bundle1.getString("rre", rev);
        switch (p) {
            case 0:
                business_details footballFragment = null;
                try {
                    footballFragment = new business_details();
                } finally {

                }
                footballFragment.setAllowEnterTransitionOverlap(bundle);
                return footballFragment;
            case 1:
                map cricketFragment = new map();
                cricketFragment.setAllowEnterTransitionOverlap(bundle;
                return cricketFragment;
            case 2:
                ItemFragment nbaFragment = new ItemFragment();
                nbaFragment.setAllowEnterTransitionOverlap(bundle;
                return nbaFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs1;
    }


}
