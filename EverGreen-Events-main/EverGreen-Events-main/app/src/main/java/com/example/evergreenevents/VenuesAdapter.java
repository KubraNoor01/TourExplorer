package com.example.evergreenevents;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class VenuesAdapter extends RecyclerView.Adapter<VenuesAdapter.ViewHolder> implements Filterable {

    private ArrayList<VenueClass> venue;
    private ArrayList<VenueClass> venueListFull;
    private Context context;

    public VenuesAdapter(Context context, ArrayList<VenueClass> venue) {
        this.context = context;
        this.venue = venue;
        this.venueListFull = new ArrayList<>(venue);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_venue_item_design, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VenueClass singleVenue = venue.get(position);
        holder.tvVenueName.setText(singleVenue.getVenueName());
        Glide.with(context).load(singleVenue.getImage()).into(holder.ivVenueImage);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, VenueDetails.class);
            intent.putExtra("venue_name", singleVenue.getVenueName());
            intent.putExtra("venue_image", singleVenue.getImage());
            intent.putExtra("venue_description", singleVenue.getVenueDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return venue.size();
    }

    @Override
    public Filter getFilter() {
        return venueFilter;
    }

    private Filter venueFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<VenueClass> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(venueListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (VenueClass item : venueListFull) {
                    if (item.getVenueName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            venue.clear();
            venue.addAll((ArrayList<VenueClass>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivVenueImage;
        TextView tvVenueName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivVenueImage = itemView.findViewById(R.id.ivVenueImage);
            tvVenueName = itemView.findViewById(R.id.tvVenueName);
        }
    }
}
