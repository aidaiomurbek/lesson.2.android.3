package com.example.lesson2android3.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson2android3.R;
import com.example.lesson2android3.data.model.Film;
import com.example.lesson2android3.data.model.People;
import com.example.lesson2android3.data.remote.FilmStorage;

import java.util.ArrayList;
import java.util.List;

public class AdditionalFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdditionalAdapter adapter;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> listP = new ArrayList<>();
    private Film film;
    private People people;

    public AdditionalFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            film = (Film) getArguments().getSerializable("film");
        }
        listP.addAll(film.getPeople());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_additional, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewAdd);
        adapter = new AdditionalAdapter();
        loadFilm();
        return view;
    }

    private void loadFilm() {
        FilmStorage.getPeople(new FilmStorage.PeopleClick() {
            @Override
            public void onSuccess(List<People> listPeople) {

                if (listP.size() > 2) {
                    String peopleId;
                    String s;

                    for (int j = 0; j < listP.size(); j++) {
                        peopleId = listP.get(j);
                        s = peopleId.substring(39);

                        FilmStorage.getPeopleById(s, new FilmStorage.PeopleIdById() {
                            @Override
                            public void onSuccess(People peoplebyid) {
                                list.add(peoplebyid.getName());
                                init();
                            }

                            @Override
                            public void onFailure(String error) {
                                Log.e("TAG", "onFailure: " + error);
                            }
                        });
                    }

                } else {

                    ArrayList<People> listPeop = new ArrayList<>(listPeople);

                    for (int i = 0; i < listPeop.size(); i++) {
                        people = listPeop.get(i);
                        String name = people.getName();
                        list.add(name);
                        init();
                    }
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }

    private void init() {
        adapter.addList(list);
        recyclerView.setAdapter(adapter);
    }
}