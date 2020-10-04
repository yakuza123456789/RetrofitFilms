package com.example.Android_3_home.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.Android_3_home.App;
import com.example.Android_3_home.R;
import com.example.Android_3_home.data.Interface.OnClickListeners;
import com.example.Android_3_home.data.adapters.MainAdapter;
import com.example.Android_3_home.data.models.FilmsGhibli;
import com.example.Android_3_home.data.network.GhibliService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter adapter;
    ArrayList<FilmsGhibli> FilmsGhibli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerViewActive();

        ShowListFilmsOnCLick();

    }

    private void ShowListFilmsOnCLick() {

        App.ghibliService.getFilms(new GhibliService.FilmsCallBack() {
            @Override
            public void onSuccess(ArrayList<com.example.Android_3_home.data.models.FilmsGhibli> filmsGhiblis) {
                if (filmsGhiblis != null) {
                    adapter.setListGhibli(filmsGhiblis);
                    adapter.setOnClickListeners(new OnClickListeners() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                            intent.putExtra("keyFilms", filmsGhiblis.get(position).getId());
                            startActivity(intent);

                        }

                    });
                }
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

    }

    private void RecyclerViewActive() {

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}