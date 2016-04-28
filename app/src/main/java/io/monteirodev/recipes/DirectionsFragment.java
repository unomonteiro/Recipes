package io.monteirodev.recipes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

// .27
public class DirectionsFragment extends Fragment {
    public static final String KEY_CHECK_BOXES = "key_check_boxes;";
    private CheckBox[] mCheckBoxes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragment_directions, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.directionsLayout);
        String[] directions = Recipes.directions[index].split("`");
        mCheckBoxes = new CheckBox[directions.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if (savedInstanceState != null &&
                savedInstanceState.getBooleanArray(KEY_CHECK_BOXES) != null){
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECK_BOXES);
        }

        setUpCheckBoxes(directions, linearLayout, checkedBoxes);

        return view;
    }

    private void setUpCheckBoxes(String[] directions, ViewGroup container, boolean[] checkedBoxes) {
        int i = 0;
        for (String direction : directions) {
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8, 16, 8, 16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(direction);
            container.addView(mCheckBoxes[i]);
            if (checkedBoxes[i]){
                mCheckBoxes[i].toggle();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for (CheckBox checkBox : mCheckBoxes) {
            stateOfCheckBoxes[i] = checkBox.isChecked();
        }
        outState.putBooleanArray(KEY_CHECK_BOXES,stateOfCheckBoxes);
        super.onSaveInstanceState(outState);
    }
}
