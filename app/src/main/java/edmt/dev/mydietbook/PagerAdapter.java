package edmt.dev.mydietbook;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Breakfast breakfast = new Breakfast();
                return breakfast;
            case 1:
                Lunch lunch = new Lunch();
                return lunch;
            case 2:
                Dinner dinner = new Dinner();
                return dinner;
            case 3:
                Snacks snacks = new Snacks();
                return snacks;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){
        switch (position) {
            case 0:
                return "Breakfast";
            case 1:
                return "Lunch";
            case 2:
                return "Dinner";
            case 3:
                return "Snacks";
            default:
                return null;
        }
    }
}
