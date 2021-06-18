package com.app.goaheadapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.app.goaheadapp.onboarding.onBoarding1;
import com.app.goaheadapp.onboarding.onBoarding2;
import com.app.goaheadapp.onboarding.onBoarding3;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    public MyPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return onBoarding1.newInstance();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return onBoarding2.newInstance();
            case 2: // Fragment # 1 - This will show SecondFragment
                return onBoarding3.newInstance();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
