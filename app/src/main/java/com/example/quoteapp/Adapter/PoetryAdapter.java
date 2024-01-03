package com.example.quoteapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quoteapp.Model.PoetryModel;
import com.example.quoteapp.R;

import java.util.List;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.ViewHolder> {

    Context context;
    List<PoetryModel> poetryModels;

    public PoetryAdapter(Context context, List<PoetryModel> poetryModels) {
        this.context = context;
        this.poetryModels = poetryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.poetry_list_view,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.poet_name.setText(poetryModels.get(position).getPoetName());
        holder.poetry.setText(poetryModels.get(position).getPoetryText());
        holder.timestamp.setText(poetryModels.get(position).getTimeStamp());


    }

    @Override
    public int getItemCount() {
        return poetryModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView poetry,poet_name,timestamp;
        Button updatebtn,deletebtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poetry = itemView.findViewById(R.id.poetry_text);
            poet_name = itemView.findViewById(R.id.poet_name);
            timestamp = itemView.findViewById(R.id.timestamp);
            updatebtn = itemView.findViewById(R.id.updatebtn);
            deletebtn = itemView.findViewById(R.id.deletebtn);
        }
    }
}
