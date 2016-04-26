package io.monteirodev.recipes;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        // this method will create and add to viewGroup
        // if the third parameter is true we would add two times the same fragment to root
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }
}
