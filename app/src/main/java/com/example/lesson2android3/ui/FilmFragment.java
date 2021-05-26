package com.example.lesson2android3.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lesson2android3.R;
import com.example.lesson2android3.data.model.Film;
import com.example.lesson2android3.data.remote.FilmStorage;

public class FilmFragment extends Fragment {

    private TextView textTitle;
    private TextView textRD;
    private TextView textDesc;
    private TextView textDirector;
    private TextView textProducer;
    private Button btnPeople;
    private String filmId;
    private Film filmT;

    public FilmFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            filmId = getArguments().getString("film");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        init(view);
        getFilm();
        return view;
    }

    private void init(View view) {
        textTitle = view.findViewById(R.id.textTitle);
        textRD = view.findViewById(R.id.textRD);
        textDesc = view.findViewById(R.id.textDesc);
        textDirector = view.findViewById(R.id.textDirector);
        textProducer = view.findViewById(R.id.textProducer);
        btnPeople = view.findViewById(R.id.btnPeople);

        btnPeople.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putSerializable("film", filmT);

            NavController navController = Navigation.findNavController(getActivity(),
                    R.id.nav_host_fragment);
            navController.navigate(R.id.additionalFragment, bundle);
        });
    }

    private void getFilm() {
        FilmStorage.getFilmById(filmId, new FilmStorage.FilmClick() {
            @Override
            public void onSuccess(Film film) {
                textTitle.setText(film.getTitle());
                textRD.setText(film.getReleaseDate());
                textDesc.setText(film.getDescription());
                textDirector.setText(film.getDirector());
                textProducer.setText(film.getProducer());
                filmT = film;
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }
}