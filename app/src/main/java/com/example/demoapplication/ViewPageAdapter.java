package com.example.demoapplication;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPageAdapter extends FragmentStateAdapter {
    public ViewPageAdapter(@NonNull ExploreFragment fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PersonalFragment();
            case 1:
                return new ServiceFragment();
            case 2:
                return new BusinessFragment();
            default:
                return new PersonalFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
