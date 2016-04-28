package io.monteirodev.recipes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ViewPagerFragment extends Fragment {
    // clm.22
    // Avoid non-default constructors in fragments: use a default constructor plus
    // Fragment#setArguments(Bundle) instead more... (Ctrl+F1)
    // public ViewPagerFragment(int index){}
    public static final String KEY_RECIPE_INDEX = "recipe_index";

    // clm.18
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // clm.24 show a Toast with recipe name
        int index = getArguments().getInt(KEY_RECIPE_INDEX);
        // clm.26 Change Toast to Change title
        // Toast.makeText(getActivity(), Recipes.names[index], Toast.LENGTH_SHORT).show();
        getActivity().setTitle(Recipes.names[index]);


        // return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);

        // clm.27 adding viewPager with two fragments
        final IngredientsFragment ingredientsFragment = new IngredientsFragment();
        // clm.30 bundling indexes
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index);
       ingredientsFragment.setArguments(bundle); // .30
        final DirectionsFragment directionsFragment = new DirectionsFragment();
        bundle = new Bundle();
        bundle.putInt(KEY_RECIPE_INDEX, index);

        directionsFragment.setArguments(bundle);
        // FragmentPagerAdapter requires a fragmentManager
        // we pass in getChildFragmentManager() when using fragments within fragments
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        // use new FragmentPagerAdapter autocomplete
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? ingredientsFragment : directionsFragment;
            }

            // .28 handle viewPager change and title
            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "Ingredients" : "Directions";
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        // clm.28 Adding tabs to viewPager
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        // .26 return title to app
        getActivity().setTitle(getResources().getString(R.string.app_name));

    }
}
