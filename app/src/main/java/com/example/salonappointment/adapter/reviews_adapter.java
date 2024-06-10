package com.example.salonappointment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.writeReviewsModel;
import com.example.salonappointment.R;

import java.util.ArrayList;

public class reviews_adapter extends RecyclerView.Adapter<reviews_adapter.ViewHolder> {
    ArrayList<writeReviewsModel> listReviews = new ArrayList<>();
    private Context context;

    public reviews_adapter(ArrayList<writeReviewsModel> listReviews, Context context) {
        this.listReviews = listReviews;
        this.context = context;
    }

    public reviews_adapter(ArrayList<writeReviewsModel> listReviews) {
        this.listReviews = listReviews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(listReviews.get(position).getCustomerID());
        holder.tvDesc.setText(listReviews.get(position).getDescription());
        holder.tvDate.setText(listReviews.get(position).getDate());
        holder.tvRate.setText(String.valueOf(listReviews.get(position).getScoreRating()));
        holder.tvTitle.setText(listReviews.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return listReviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDesc, tvDate, tvRate, tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.customerName);
            tvDesc = itemView.findViewById(R.id.reviewDesc);
            tvDate = itemView.findViewById(R.id.datePosted);
            tvRate = itemView.findViewById(R.id.scoreRate);
            tvTitle = itemView.findViewById(R.id.reviewTitle);

        }
    }
}
