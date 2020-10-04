package com.example.Android_3_home.data.network;

import com.example.Android_3_home.data.models.FilmsGhibli;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GhibliService {

    //Interface API
    public interface GhibliAPI {
        @GET("films/{FilmId}")
        Call<FilmsGhibli> filmById
                (@Path("FilmId") String FilmId);

        @GET("films")
        Call<ArrayList<FilmsGhibli>> films();
    }

    //Builder
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://ghibliapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    GhibliAPI service = retrofit.create(GhibliAPI.class);

    public void getFilmById (String id, final FilmByIdCallBack filmByIdCallBack){
        Call<FilmsGhibli> call = service.filmById(id);
        call.enqueue(new Callback<FilmsGhibli>() {
            @Override
            public void onResponse(Call<FilmsGhibli> call, Response<FilmsGhibli> response) {
                filmByIdCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<FilmsGhibli> call, Throwable t) {
                filmByIdCallBack.onFailure(t);
            }
        });
    }

    public void getFilms ( FilmsCallBack filmsCallBack){
        Call<ArrayList<FilmsGhibli>> call = service.films();
        call.enqueue(new Callback<ArrayList<FilmsGhibli>>() {
            @Override
            public void onResponse(Call<ArrayList<FilmsGhibli>> call, Response<ArrayList<FilmsGhibli>> response) {
                filmsCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<FilmsGhibli>> call, Throwable t) {

                filmsCallBack.onFailure(t);
            }
        });
    }

    public interface FilmByIdCallBack{
        void onSuccess(FilmsGhibli filmsGhibli);
        void onFailure(Throwable throwable);
    }

    public interface FilmsCallBack {
        void onSuccess(ArrayList<FilmsGhibli> filmsGhiblis);
        void onFailure(Throwable throwable);
    }

}
