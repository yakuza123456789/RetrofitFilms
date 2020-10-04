package com.example.Android_3_home.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Android_3_home.App;
import com.example.Android_3_home.R;
import com.example.Android_3_home.data.models.FilmsGhibli;
import com.example.Android_3_home.data.network.GhibliService;

public class InfoActivity extends AppCompatActivity {

    TextView tv_desc;
    TextView tv_director;
    TextView tv_producer;
    TextView tv_date;
    String position;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

           idInitialization();

           getPositionIntent();

           showInfoFilm();

    }

    private void showInfoFilm() {
        App.ghibliService.getFilmById(position, new GhibliService.FilmByIdCallBack() {
            @Override
            public void onSuccess(FilmsGhibli filmsGhibli) {
                if (filmsGhibli != null) {
                    tv_desc.setText(filmsGhibli.getDescription());
                    tv_director.setText(filmsGhibli.getDirector());
                    tv_producer.setText(filmsGhibli.getProducer());
                    tv_date.setText(filmsGhibli.getReleaseDate());
                } else {
                    Toast.makeText(InfoActivity.this, "not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(InfoActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getPositionIntent() {
        if (getIntent() != null){
            position = getIntent().getStringExtra("keyFilms");
        }

    }

    

    private void idInitialization() {
        tv_desc = findViewById(R.id.tv_description);
        tv_producer = findViewById(R.id.tv_producer);
        tv_director = findViewById(R.id.tv_director);
        tv_date = findViewById(R.id.tv_dater);
    }
}
