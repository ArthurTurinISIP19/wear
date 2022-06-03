package com.example.myapplication3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication3.fragmentOne;
import com.example.myapplication3.fragmentTwo;

public class MyAdapter extends FragmentStatePagerAdapter
{
    public MyAdapter(@NonNull FragmentManager fm){
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new fragmentOne();
            case 1:
                return new fragmentTwo();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
