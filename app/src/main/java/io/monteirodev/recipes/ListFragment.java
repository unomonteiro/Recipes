package io.monteirodev.recipes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// extends LoggingFragment to debug lifecycle
public class ListFragment extends Fragment {

    // clm .8 implementing click inside fragment
    public interface OnRecipeSelectedInterface {
        void onListRecipeSelected(int index);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Log.d(LoggingFragment.TAG, "onCreateView");
        // clm.10
        OnRecipeSelectedInterface listener = (OnRecipeSelectedInterface) getActivity();

        // return super.onCreateView(inflater, container, savedInstanceState);
        // this method will create and add to viewGroup
        // if the third parameter is true we would add two times the same fragment to root
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        // clm.7
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        ListAdapter listAdapter = new ListAdapter(listener); // clm.12 add listener to ListAdapter
        recyclerView.setAdapter(listAdapter);
        //fragments always have access to activity: getActivity()
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
