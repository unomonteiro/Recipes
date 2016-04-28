package io.monteirodev.recipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class RecyclerAdapter extends RecyclerView.Adapter{



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getLayoutId(), parent, false);
        return new ListViewHolder(view);
    }

    // any class that used this recycler must implement an layoutId
    protected abstract int getLayoutId();


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);

    }

    @Override
    public int getItemCount() {
        return Recipes.names.length;
    }

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
            onRecipeSelected(mIndex); // clm.17 create index using bindView
        }
    }

    protected abstract void onRecipeSelected(int index);
}
