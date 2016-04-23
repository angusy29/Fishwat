package com.example.angusyuen.fishwat;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by angusyuen on 6/02/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Fish> allFish;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView fishName;
        TextView scientificFishName;
        ImageView image;

        // one particular "card" information holder
        public ViewHolder(View v) {
            super(v);
            fishName = (TextView) v.findViewById(R.id.fishName);
            scientificFishName = (TextView) v.findViewById(R.id.scientificFishName);
            image = (ImageView) v.findViewById(R.id.fishPhoto);

        }
    }

    public MyAdapter(ArrayList<Fish> allFish) {
        this.allFish = allFish;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fish_card, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.fishName.setText(allFish.get(position).getName());
        holder.scientificFishName.setText(allFish.get(position).getScientificNameOfFish());
    }

    @Override
    public int getItemCount() {
        return allFish.size();
    }


    public ArrayList<Fish> getAllFish() {
        return allFish;
    }
}