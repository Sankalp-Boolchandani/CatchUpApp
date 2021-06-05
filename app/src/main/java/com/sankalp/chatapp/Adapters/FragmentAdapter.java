package com.sankalp.chatapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sankalp.chatapp.Fragments.CallsFragment;
import com.sankalp.chatapp.Fragments.ChatsFragments;
import com.sankalp.chatapp.Fragments.StatusFragments;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ChatsFragments();
            case 1:
                return new StatusFragments();
            case 2:
                return new CallsFragment();
            default:
                return new ChatsFragments();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if (position==0){
            title="Chats";
        }
        if (position==1){
            title="Status";
        }
        if (position==2){
            title="Calls";
        }
        return title;
    }
}