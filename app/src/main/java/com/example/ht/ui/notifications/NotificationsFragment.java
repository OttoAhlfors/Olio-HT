package com.example.ht.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ht.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    ArrayList names;
    ArrayList stars;
    ArrayAdapter listAdapter;
    Button showMovies;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        showMovies = binding.showRatings;

        ListView ratingsList = binding.listRatings;
        ratingsList.setAdapter(listAdapter);

        listAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1);
        ratingsList.setAdapter(listAdapter);

        showMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < names.size(); i++) {
                    listAdapter.add(names.get(i) + "\t\t" + stars.get(i) + "/5");
                }
                listAdapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        names = getActivity().getIntent().getParcelableArrayListExtra("names");
        stars = getActivity().getIntent().getParcelableArrayListExtra("stars");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}