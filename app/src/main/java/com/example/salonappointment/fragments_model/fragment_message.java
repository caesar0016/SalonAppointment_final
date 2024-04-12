package com.example.salonappointment.fragments_model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.salonappointment.Model.acc_messages_model;
import com.example.salonappointment.R;
import com.example.salonappointment.adapter.messages_adapter;

import java.util.ArrayList;

public class fragment_message extends Fragment {
    private RecyclerView rvMessage;
    private messages_adapter adapterMessage;
    private ArrayList<acc_messages_model> messageList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

        //Initialization of the Recycleview
        rvMessage = rootView.findViewById(R.id.frMessage_rvMessage);
        LinearLayoutManager lm_message = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvMessage.setLayoutManager(lm_message);
        messageList = new ArrayList<>();
        messageList.add(new acc_messages_model("Gigi Hadid", "Loving your new hairstyle!", "5:15pm"));
        messageList.add(new acc_messages_model("Kendall Jenner", "Can't wait to see your fashion show!", "4:25pm"));
        messageList.add(new acc_messages_model("Cara Delevingne", "Your hair looks amazing!", "3:55pm"));
        messageList.add(new acc_messages_model("Beyoncé", "Your sense of style is inspiring!", "2:45pm"));
        messageList.add(new acc_messages_model("Rihanna", "Your hair color is stunning!", "6:30pm"));
        messageList.add(new acc_messages_model("Zendaya", "Your fashion sense is always on point!", "6:45pm"));
        messageList.add(new acc_messages_model("Harry Styles", "Love the way you accessorize!", "7:00pm"));
        messageList.add(new acc_messages_model("Cindy Crawford", "Your haircut is so chic!", "7:15pm"));
        messageList.add(new acc_messages_model("Lupita Nyong'o", "Your dress at the gala was breathtaking!", "7:30pm"));
        adapterMessage = new messages_adapter( messageList);
        rvMessage.setAdapter(adapterMessage);


        // Find the Toolbar by ID
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);

        // Set the Toolbar as the activity's action bar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        if (activity != null) {
            activity.setSupportActionBar(toolbar);

            // Set the title of the toolbar
            activity.getSupportActionBar().setTitle("    MESSAGES"); //THis is the text appearing on the toolbar
            SpannableString spannableString = new SpannableString(activity.getSupportActionBar().getTitle());
            spannableString.setSpan(new AbsoluteSizeSpan(20, true), 0, spannableString.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            activity.getSupportActionBar().setTitle(spannableString);
        }
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        MenuItem menuItem = menu.findItem(R.id.action_search);
        if (menuItem != null) {
            SearchView searchView = (SearchView) menuItem.getActionView();
            if (searchView != null) {
                searchView.setQueryHint("Type here to search");
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });
            }
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}