package com.example.quoteapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quoteapp.APIs.ApiClient;
import com.example.quoteapp.APIs.ApiInterface;
import com.example.quoteapp.Model.PoetryModel;
import com.example.quoteapp.R;
import com.example.quoteapp.Response.DeleteResponse;
import com.example.quoteapp.UpdateActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PoetryAdapter extends RecyclerView.Adapter<PoetryAdapter.ViewHolder> {

    Context context;
    List<PoetryModel> poetryModels;
    ApiInterface apiInterface;

    public PoetryAdapter(Context context, List<PoetryModel> poetryModels) {
        this.context = context;
        this.poetryModels = poetryModels;
        Retrofit retrofit = ApiClient.getClient();
        apiInterface = retrofit.create(ApiInterface.class);

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

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletepoetry(poetryModels.get(position).getId()+"",position);
            }
        });

        holder.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",poetryModels.get(position).getId());
                intent.putExtra("poetry",poetryModels.get(position).getPoetryText());
                context.startActivity(intent);
            }
        });


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

    private void deletepoetry(String id, int position){
        apiInterface.deletpoetry(id).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {

                try{

                    if (response != null) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        if (response.body().getStatus().equals("1")) {
                            poetryModels.remove(position);
                            notifyDataSetChanged();

                        }
                    }

                }catch (Exception e){
                    Toast.makeText(context, "error occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Toast.makeText(context, "unable to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
