package com.example.daily_news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daily_news.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
NewsHeadlines headlines;
TextView txt_title,txt_author,txt_time,txt_detail,txt_content;
ImageView newsimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txt_title=findViewById(R.id.text_detail_title);
        txt_author=findViewById(R.id.text_detail_author);
        txt_detail=findViewById(R.id.text_detail_detail);
        txt_time=findViewById(R.id.text_detail_time);
        txt_content=findViewById(R.id.text_detail_content);
        newsimage=findViewById(R.id.img_detail_news);
        headlines= (NewsHeadlines) getIntent().getSerializableExtra("Data");
        txt_title.setText(headlines.getTitle());
        txt_author.setText(headlines.getAuthor());
        txt_time.setText(headlines.getPublishedAt());
        txt_content.setText(headlines.getContent());
        txt_detail.setText(headlines.getDescription());

        Picasso.get().load(headlines.getUrlToImage()).into(newsimage);

    }
}