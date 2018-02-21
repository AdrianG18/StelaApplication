package com.example.adrian.stelaapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by adrian on 2/19/18.
 */

public class ConstellationAdapter extends RecyclerView.Adapter<ConstellationAdapter.ViewHolder> {
    private List<Constellation> mConstellations;
    private ConstellationAdapterListener mListener;
    Context context;

    // define an interface required by the ViewHolder
    public interface ConstellationAdapterListener {
        void onItemSelected(View view, int position, boolean b);
    }

    // pass in the Constellation array in the constructor
    public ConstellationAdapter(List<Constellation> constellations,
                                ConstellationAdapterListener listener) {
        mConstellations = constellations;
        mListener = listener;
    }

    // create a ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;
        public TextView tvName;
        public TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            // perform findViewById lookups
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);

            // handle row click event
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        // get the position of row element
                        int position = getAdapterPosition();
                        // fire the listener callback
                        mListener.onItemSelected(view, position, false);
                    }
                }
            });
        }
    }

    // for each row, inflate the layout and cache references into ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View constellationView = inflater.inflate(R.layout.item_constellation,
                parent,false);
        ViewHolder viewHolder = new ViewHolder(constellationView);
        return viewHolder;
    }

    // bind the values based on the position of the element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // get the data according to position
        final Constellation constellation = mConstellations.get(position);
    }

    @Override
    public int getItemCount() {
        return mConstellations.size();
    }

    public void clear() {
        mConstellations.clear();
        notifyDataSetChanged();
    }

}
