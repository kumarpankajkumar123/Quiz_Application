package com.example.quizapp_codsoft.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp_codsoft.Module.SetModal;
import com.example.quizapp_codsoft.QuestionActivity;
import com.example.quizapp_codsoft.R;

import java.util.ArrayList;

public class SetAdaptor extends RecyclerView.Adapter<SetAdaptor.MyViewHolder> {


    Context context;
    ArrayList<SetModal> list;

    public SetAdaptor( ArrayList<SetModal> list,Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_show_recy,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

      SetModal modal = list.get(position);
      holder.img.setImageResource(modal.getImg());
      holder.name.setText(modal.getName());

      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(context, QuestionActivity.class);
               intent.putExtra("set",modal.getName());
              context.startActivity(intent);
          }
      });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.name);

        }
    }


}

