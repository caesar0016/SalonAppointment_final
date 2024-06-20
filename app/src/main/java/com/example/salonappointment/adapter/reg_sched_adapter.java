package com.example.salonappointment.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salonappointment.Model.staffSched_model;
import com.example.salonappointment.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class reg_sched_adapter extends RecyclerView.Adapter<reg_sched_adapter.ViewHolder> {
    Context context;
    ArrayList<staffSched_model> listSched = new ArrayList<>();

    public reg_sched_adapter(Context context, ArrayList<staffSched_model> listSched) {
        this.context = context;
        this.listSched = listSched;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sched_view_rows, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.time.setText(listSched.get(position).getTime());


        holder.cvSchedRows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog(); // Call method to show dialog
            }
        });
    }
    void deleteSched(String userID){
        DatabaseReference dbRefSched = FirebaseDatabase.getInstance().getReference("Staff_Schedule");
        Query query = dbRefSched.orderByChild("staff_uid").equalTo(userID);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Using a map to collect updates for batch processing
                Map<String, Object> updates = new HashMap<>();

                for(DataSnapshot item : snapshot.getChildren()){
                    String key = item.getKey();
                    updates.put(key + "/time", null); // Set time field to null to delete it
                }

                // Perform batch update
                dbRefSched.updateChildren(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Handle success (optional)
                                Log.d("Firebase", "Time field deleted successfully");
                                // Optionally, perform any other actions here
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle failure
                                Log.e("Firebase", "Error deleting time field", e);
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
                Log.e("Firebase", "Error deleting schedule", error.toException());
            }
        });
    }


    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Removal");  // Set dialog title
        builder.setMessage("Do you want to remove the schedule?"); // Set dialog message

        // Set up the buttons
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteSched(userID);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle 'No' button click
                dialog.dismiss(); // Dismiss the dialog
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public int getItemCount() {
        return listSched.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView regSched_Num, time;
        CardView cvSchedRows;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.schedTime);
            cvSchedRows = itemView.findViewById(R.id.cvSchedVr);
        }

    }
}
