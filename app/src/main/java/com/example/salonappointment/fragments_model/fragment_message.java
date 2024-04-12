package com.example.salonappointment.fragments_model;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.salonappointment.R;

public class fragment_message extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_message, container, false);

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