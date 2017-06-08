package com.example.exam;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Ольга on 08.06.2017.
 */

public class ShapeListAdapter extends RecyclerView.Adapter<ShapeViewHolder> {

    private ArrayList<Shape> profiles;

    public ShapeListAdapter(ArrayList<Shape> profiles) {
        this.profiles = profiles;
    }

    @Override
    public ShapeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ShapeViewHolder(parent, R.layout.item_shape);

    }

    @Override
    public void onBindViewHolder(ShapeViewHolder holder, int position) {
        holder.bindView(profiles.get(position));
    }

    @Override
    public int getItemCount() {
        return profiles.size();

    }

}
