package com.example.ht.ui.dashboard;

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

import com.example.ht.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

class Movie {
    public static List<Movie> MovieList = new ArrayList<Movie>();

    public String name;
    public int stars;

    public Movie(String name, int stars) {
        this.name = name;
        this.stars = stars;
    }

    public static void addMovie(Movie movie) {
        MovieList.add(movie);
        System.out.println(MovieList.size());
    }
}

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

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new Movie(movieName.getText().toString(), rateStars.getNumStars());
                movie.addMovie(movie);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}