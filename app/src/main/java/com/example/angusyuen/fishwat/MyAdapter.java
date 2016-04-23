package com.example.angusyuen.fishwat;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
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
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView fishName;
        TextView scientificFishName;
        String image;
        ImageView fishPhoto;
        TextView description;
        String isConsumable;
        int isSeasonable;

        // one particular "card" information holder
        public ViewHolder(View v) {
            super(v);
            fishName = (TextView) v.findViewById(R.id.fishName);
            scientificFishName = (TextView) v.findViewById(R.id.scientificFishName);
            description = (TextView) v.findViewById(R.id.description);
            fishPhoto = (ImageView) v.findViewById(R.id.fishPhoto);
            context = v.getContext();

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), FishDetails.class);
                    //myIntent.putExtra("key", 1); // this line is for if we want to send any information from this activity to the next
                    myIntent.putExtra("name", fishName.getText() + " " + scientificFishName.getText());
                    myIntent.putExtra("description", description.getText());
                    myIntent.putExtra("image", image);
                    myIntent.putExtra("consumptionStatus", isConsumable);
                    myIntent.putExtra("seasonStatus", isSeasonable);
                    v.getContext().startActivity(myIntent);
                }
            });
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
        holder.description.setText(allFish.get(position).getDescription());
        holder.isConsumable = allFish.get(position).getIsConsumable();
        holder.isSeasonable = allFish.get(position).getIsSeasonable();

        String imageString = allFish.get(position).getPrimaryImage();
        imageString = imageString.substring(0, imageString.length() - 4);
        holder.image = imageString;

        System.out.println("Image string: " + imageString);
        int resID = context.getResources().getIdentifier(imageString , "drawable", context.getPackageName());

        System.out.println(resID);
        holder.fishPhoto.setImageResource(resID);
    }

    @Override
    public int getItemCount() {
        return allFish.size();
    }


    public ArrayList<Fish> getAllFish() {
        return allFish;
    }
}