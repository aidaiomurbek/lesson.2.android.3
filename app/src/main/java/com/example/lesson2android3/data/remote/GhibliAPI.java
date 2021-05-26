package com.example.lesson2android3.data.remote;


import com.example.lesson2android3.data.model.Film;
import com.example.lesson2android3.data.model.People;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GhibliAPI {

    @GET(EndPoints.END_POINTS)
    Call<List<Film>> getFilms();

    @GET(EndPoints.END_POINTS_ID)
    Call<Film> getSpecFilm(@Path("id") String id
    );

    @GET(EndPoints.END_POINTS_PEOPLE)
    Call<List<People>> getPeople();

    @GET(EndPoints.END_POINTS_PEOPLE_BY_ID)
    Call<People> getPeopleById(@Path("id") String id);
}