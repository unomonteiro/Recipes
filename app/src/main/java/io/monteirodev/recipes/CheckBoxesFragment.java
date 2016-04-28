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
// clm.32 abstract classes cannot be instantiated.
//  And since we never want this class to be instantiated
//  we declare it as abstract
public abstract class CheckBoxesFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes";
    // clm.31 saving checkboxes state on rotation
    private CheckBox[] mCheckBoxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        // .30 add Ingredient check boxes and index
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragment_checkboxes, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.checkBoxesLayout);
        String[] contents = getContents(index);

        // .31
        mCheckBoxes = new CheckBox[contents.length];
        boolean[] checkedBoxes = new boolean[mCheckBoxes.length];
        if (savedInstanceState != null &&
                savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null) {
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);

        }
        setUpCheckBoxes(contents, linearLayout, checkedBoxes);

        return view;
    }

    // .32 we never want a empty view so were using abstract
    public abstract String[] getContents(int index);

    private void setUpCheckBoxes(String[] contents, ViewGroup container, boolean[] checkedBoxes) {
        int i = 0;
        for (String content : contents) {
            // .31
            // CheckBox checkBox = new CheckBox(getActivity());
            mCheckBoxes[i] = new CheckBox(getActivity());
            mCheckBoxes[i].setPadding(8, 16, 8, 16);
            mCheckBoxes[i].setTextSize(20f);
            mCheckBoxes[i].setText(content);
            container.addView(mCheckBoxes[i]);
            // check if it 2should be checked
            if (checkedBoxes[i]) {
                mCheckBoxes[i].toggle();
            }
            i++;
        }
    }

    // .31
    @Override
    public void onSaveInstanceState(Bundle outState) {
        boolean[] stateOfCheckBoxes = new boolean[mCheckBoxes.length];
        int i = 0;
        for (CheckBox checkBox : mCheckBoxes) {
        stateOfCheckBoxes[i] = checkBox.isChecked();
            i++;
        }
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckBoxes);
        super.onSaveInstanceState(outState);

    }
}
