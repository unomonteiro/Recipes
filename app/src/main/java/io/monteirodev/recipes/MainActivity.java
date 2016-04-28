package io.monteirodev.recipes;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
// extends LoggingActivity to debug lifecycle
public class MainActivity extends AppCompatActivity
        // clm.9
        implements ListFragment.OnRecipeSelectedInterface,
        GridFragment.OnRecipeSelectedInterface {
    // clm.25 TAG
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // clm.33 isTablet
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        //Toast.makeText(MainActivity.this, isTablet +"", Toast.LENGTH_SHORT).show();


        if (!isTablet){
            // clm.17 avoid fragment onCreate copy
            // search, if not find by id create new one
            // clm.21 change getFragmentManager to getSupportFragmentManager()
            // (ListFragment) getFragmentManager().findFragmentById(R.id.placeHolder);
            // .25 changed findFragmentById(R.id.placeHolder) to findByTag
            ListFragment savedFragment = (ListFragment)
                    getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
            if(savedFragment == null) {
                // create a fragment
                ListFragment fragment = new ListFragment();
                // add to viewGroup use android.app!
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT); // .25 TAG
                fragmentTransaction.commit();
            }
        } else {
            GridFragment savedFragment = (GridFragment)
                    getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
            if(savedFragment == null) {
                GridFragment fragment = new GridFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT); // .25 TAG
                fragmentTransaction.commit();
            }
        }


    }

    @Override
    public void onListRecipeSelected(int index) {
        // clm.10
        //Toast.makeText(MainActivity.this, Recipes.names[index], Toast.LENGTH_SHORT).show();

        // clm.19 replacing one fragment with another is similar
        ViewPagerFragment fragment = new ViewPagerFragment();
        // clm.23 i
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);
        // 23 f
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT); // .25 TAG
        // clm.20 since were only get back once we don't need to provide name
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onGridRecipeSelected(int index) {
        DualPaneFragment fragment = new DualPaneFragment();
        // clm.23 i
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);
        // 23 f
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT); // .25 TAG
        // clm.20 since were only get back once we don't need to provide name
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
