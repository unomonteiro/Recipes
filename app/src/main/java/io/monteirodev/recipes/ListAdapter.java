package io.monteirodev.recipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends RecyclerView.Adapter{

    private final ListFragment.OnRecipeSelectedInterface mListener;

    public ListAdapter(ListFragment.OnRecipeSelectedInterface listener) {
        // clm.13
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // clm.3
        // return null;
        // inflate the view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // clm.4 update view and view holder to reflect position on screen
        // clm.5 cast holder parameter list view holder
        ((ListViewHolder) holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        // clm 6
        return Recipes.names.length;
    }

    // clm.2
    private class  ListViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {
        private TextView mTextView;
        private ImageView mImageView;
        private int mIndex; // clm.15

        public ListViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.itemText);
            mImageView = (ImageView) itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }

        // clm.5 bind view
        public void bindView(int position){
            mTextView.setText(Recipes.names[position]);
            mImageView.setImageResource(Recipes.resourceIds[position]);
            mIndex = position; //clm.16
        }

        @Override
        public void onClick(View v) {
            // clm.14
            mListener.onListRecipeSelected(mIndex); // clm.17 create index using bindView
        }
    }
}
