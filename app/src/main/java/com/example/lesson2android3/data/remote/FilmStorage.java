package com.example.lesson2android3.data.remote;


import android.util.Log;

import com.example.lesson2android3.data.model.Film;
import com.example.lesson2android3.data.model.People;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmStorage {
    public static void getListFilm(Result result) {
        RetrofitFactory
                .getInstance()
                .getFilms()
                .enqueue(new Callback<List<Film>>() {
                    @Override
                    public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            result.onSuccess(response.body());
                        } else {
                            result.onFailure("Something went wrong");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Film>> call, Throwable t) {
                        result.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public static void getFilmById(String id, FilmClick filmClick) {
        RetrofitFactory
                .getInstance()
                .getSpecFilm(id)
                .enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {

                        if (response.isSuccessful() && response.body() != null) {
                            filmClick.onSuccess(response.body());
                        } else {
                            filmClick.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {
                    }
                });
    }


    public static void getPeople(PeopleClick peopleClick) {
        RetrofitFactory
                .getInstance()
                .getPeople()
                .enqueue(new Callback<List<People>>() {
                    @Override
                    public void onResponse(Call<List<People>> call, Response<List<People>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            peopleClick.onSuccess(response.body());
                        } else {
                            peopleClick.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<People>> call, Throwable t) {
                        Log.e("TAG", "PeopleClick was wrooooong");
                    }
                });
    }

    public static void getPeopleById(String peopleId, PeopleIdById peopleById) {
        RetrofitFactory
                .getInstance()
                .getPeopleById(peopleId)
                .enqueue(new Callback<People>() {
                    @Override
                    public void onResponse(Call<People> call, Response<People> response) {

                        if (response.isSuccessful() && response.body() != null) {

                            peopleById.onSuccess(response.body());

                        } else {

                            peopleById.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<People> call, Throwable t) {
                        Log.e("TAG", "PeopleById is failed dude: ");
                    }
                });
    }


    public interface Result {
        void onSuccess(List<Film> film);

        void onFailure(String error);
    }

    public interface FilmClick {
        void onSuccess(Film film);

        void onFailure(String error);
    }

    public interface PeopleClick {
        void onSuccess(List<People> listPeople);

        void onFailure(String error);
    }

    public interface PeopleIdById {
        void onSuccess(People peopleById);

        void onFailure(String error);
    }
}

