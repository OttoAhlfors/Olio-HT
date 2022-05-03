package com.example.ht.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ht.MainActivity;
import com.example.ht.databinding.FragmentDashboardBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RatingBar rateStars = binding.rateStars;
        final Button rate = binding.rate;
        final EditText movieName = binding.movieName;

        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> starList = new ArrayList<String>();

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameList.add(movieName.getText().toString());
                starList.add(String.valueOf(rateStars.getRating()));
                sendToActivity(nameList, starList);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void sendToActivity(ArrayList names, ArrayList stars) {
        Intent intent = new Intent(getActivity().getBaseContext(), MainActivity.class);
        intent.putExtra("names", names);
        intent.putExtra("stars", stars);
        startActivity(intent);
    }
}