package io.monteirodev.recipes;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnRecipeSelectedInterface // clm.9
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create a fragment
        ListFragment fragment = new ListFragment();

        // add to viewGroup use android.app!
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeHolder, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onListRecipeSelected(int index) {
        // clm.10
        Toast.makeText(MainActivity.this, Recipes.names[index], Toast.LENGTH_SHORT).show();
    }
}
