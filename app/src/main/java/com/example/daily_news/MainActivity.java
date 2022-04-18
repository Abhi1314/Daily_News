package com.example.daily_news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.daily_news.Models.NewsApiResponse;
import com.example.daily_news.Models.NewsHeadlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener , View.OnClickListener{
RecyclerView recyclerView;
CustomAdapter customAdapter;
ProgressDialog dialog;
Button b1,b2,b3,b4,b5;
SearchView searchview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching news articles.....");
        dialog.show();
        searchview=findViewById(R.id.searchView);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles....."+query);
                dialog.show();
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listener,"general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        b1=findViewById(R.id.btn1);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.btn2);
        b2.setOnClickListener(this);
        b3=findViewById(R.id.btn3);
        b3.setOnClickListener(this);
        b4=findViewById(R.id.btn4);
        b4.setOnClickListener(this);
        b5=findViewById(R.id.btn5);
        b5.setOnClickListener(this);
        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);
    }
    private final OnFetchDataListener<NewsApiResponse> listener=new OnFetchDataListener<NewsApiResponse>(){
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this,"no data found!!",Toast.LENGTH_SHORT).show();

            }else{
                Shownews(list);
                dialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this,"An Error is occur!!",Toast.LENGTH_SHORT).show();
        }
    };

    private void Shownews(List<NewsHeadlines> list) {
        recyclerView=findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        customAdapter=new CustomAdapter(this,list,this);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
         Intent intent=new Intent(MainActivity.this,DetailActivity.class);
         intent.putExtra("Data",headlines);
         startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Button button=(Button) view;
         String catergory=button.getText().toString();
        dialog.setTitle("Fetching news articles....."+catergory);
        dialog.show();
        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,catergory,null);
    }
}