package com.example.Android_3_home.data.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Android_3_home.R;
import com.example.Android_3_home.data.Interface.OnClickListeners;
import com.example.Android_3_home.data.models.FilmsGhibli;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    OnClickListeners onClickListeners;


    public void setOnClickListeners(OnClickListeners onClickListeners) {
        this.onClickListeners = onClickListeners;
    }

    ArrayList<FilmsGhibli> ListGhibli = new ArrayList<>();

//    public MainAdapter(){
//        notifyDataSetChanged();
//    }


    public void setListGhibli(ArrayList<FilmsGhibli> listGhibli) {
        this.ListGhibli = listGhibli;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_film, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
    holder.tv_title.setText(ListGhibli.get(position).getTitle());
    holder.tv_producer.setText(ListGhibli.get(position).getProducer());
    }

    @Override
    public int getItemCount() {
        return ListGhibli.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_producer;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListeners.onItemClick(getAdapterPosition());
                }
            });

            tv_title = itemView.findViewById(R.id.tv_Title);
            tv_producer = itemView.findViewById(R.id.tv_producer);
        }
    }
}
