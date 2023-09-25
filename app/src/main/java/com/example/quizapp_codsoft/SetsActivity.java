package com.example.quizapp_codsoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.quizapp_codsoft.Adaptor.SetAdaptor;
import com.example.quizapp_codsoft.Module.SetModal;

import java.util.ArrayList;

public class SetsActivity extends AppCompatActivity {

    RecyclerView recycle_one;

//    int[] imgs = {R.drawable.algorithm,R.drawable.algorithm,R.drawable.algorithm,R.drawable.algorithm,R.drawable.algorithm};
//    String[] names = {"1","2","3","4","5","6","7","8","9","10","11"};

    ArrayList<SetModal>list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);

        recycle_one = (RecyclerView) findViewById(R.id.recycle_one);

        list.add(new SetModal(R.drawable.algorithm,"Set-1"));
        list.add(new SetModal(R.drawable.algorithm,"Set-2"));
        list.add(new SetModal(R.drawable.algorithm,"Set-3"));
        list.add(new SetModal(R.drawable.algorithm,"Set-4"));
        list.add(new SetModal(R.drawable.algorithm,"Set-5"));
        list.add(new SetModal(R.drawable.algorithm,"Set-6"));
        list.add(new SetModal(R.drawable.algorithm,"Set-7"));
        list.add(new SetModal(R.drawable.algorithm,"Set-8"));
        list.add(new SetModal(R.drawable.algorithm,"Set-9"));
        list.add(new SetModal(R.drawable.algorithm,"Set-10"));
        list.add(new SetModal(R.drawable.algorithm,"Set-11"));
        list.add(new SetModal(R.drawable.algorithm,"Set-12"));
        list.add(new SetModal(R.drawable.algorithm,"Set-13"));
        list.add(new SetModal(R.drawable.algorithm,"Set-14"));
        list.add(new SetModal(R.drawable.algorithm,"Set-15"));
        list.add(new SetModal(R.drawable.algorithm,"Set-16"));
        list.add(new SetModal(R.drawable.algorithm,"Set-17"));
        list.add(new SetModal(R.drawable.algorithm,"Set-18"));
        list.add(new SetModal(R.drawable.algorithm,"Set-19"));
        list.add(new SetModal(R.drawable.algorithm,"Set-20"));


       SetAdaptor Adaptor = new  SetAdaptor(list,this);
       recycle_one.setLayoutManager(new LinearLayoutManager(this));
       recycle_one.setAdapter(Adaptor);
    }
}