package com.example.lesson2android3.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson2android3.R;
import com.example.lesson2android3.data.model.Film;
import com.example.lesson2android3.data.remote.FilmStorage;

import java.util.ArrayList;
import java.util.List;

public class BaseFragment extends Fragment {

    private FilmAdapter adapter;
    private RecyclerView recyclerView;
    private List<Film> listFilm = new ArrayList<>();
    private NavController navController;

    public BaseFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_base, container, false);
        navController = Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment);
        loadFilm(view);
        return view;
    }

    private void loadFilm(View view) {
        FilmStorage.getListFilm(new FilmStorage.Result() {
            @Override
            public void onSuccess(List<Film> film) {
                listFilm.clear();
                listFilm.addAll(film);
                init(view);
            }

            @Override
            public void onFailure(String error) {}
        });
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new FilmAdapter();
        adapter.setListener(film -> {

            String filmId = film.getId();

            Bundle bundle = new Bundle();
            bundle.putString("film", filmId);

            NavController navController = Navigation.findNavController(getActivity(),
                    R.id.nav_host_fragment);
            navController.navigate(R.id.filmFragment, bundle);});

        adapter.addList(listFilm);
        recyclerView.setAdapter(adapter);
    }
}