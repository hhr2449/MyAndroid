package com.java.huhaoran;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

import com.bumptech.glide.Glide;
import com.java.huhaoran.R;

public class NewsDetailActivity extends AppCompatActivity {

    private ExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_news_detail);

        //获取控件
        TextView title = findViewById(R.id.title);
        TextView publishTime = findViewById(R.id.publish_time);
        TextView publisher = findViewById(R.id.publisher);
        TextView content = findViewById(R.id.content);
        ImageView btn_back = findViewById(R.id.btn_back);
        CardView imageCard = findViewById(R.id.image_card);
        LinearLayout imageContainer = findViewById(R.id.image_container);
        PlayerView playerview = findViewById(R.id.player_view);
        //从主界面获取新闻信息
        String titleText = getIntent().getStringExtra("title");
        String publishTimeText = getIntent().getStringExtra("publishTime");
        String publisherText = getIntent().getStringExtra("publisher");
        String contentText = getIntent().getStringExtra("content");
        String image = getIntent().getStringExtra("image");
        String video = getIntent().getStringExtra("video");
        String[] imageLinks = null;
        //设置内容显示
        title.setText(titleText);
        publishTime.setText(publishTimeText);
        publisher.setText(publisherText);
        content.setText(contentText);
        //设置返回键
        btn_back.setOnClickListener(v -> {
            finish();
        });

        //获取图片链接
        if(image != null) {
            imageLinks = FetchNews.getLinks(image);
        }
        //如果有图片
        if(imageLinks != null && imageLinks.length > 0) {
            //将cardview展示出来
            imageCard.setVisibility(View.VISIBLE);
            //遍历图片链接，将图片加载到imageview中，然后放到LinearLayout中
            for (String url : imageLinks) {
                //创建ImageView
                ImageView imageView = new ImageView(this);
                //LayoutParams是用于设定子视图布局的类，这里设置了imageView的宽高
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        300, // 宽度，可根据需要改为 ViewGroup.LayoutParams.WRAP_CONTENT
                        ViewGroup.LayoutParams.MATCH_PARENT);
                params.setMargins(8, 0, 8, 0);
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //设置点击事件，点击后带着图片链接跳转到图片展示界面
                imageView.setOnClickListener(v -> {
                    Intent intent = new Intent(NewsDetailActivity.this, PictureDisplayActivity.class);
                    intent.putExtra("image", url);
                    startActivity(intent);
                });
                //将图片加载出来
                Glide.with(this).load(url).into(imageView);
                //放入展示栏进行展示
                imageContainer.addView(imageView);
            }
        }
        else {
            //注意如果没有图片要显示取消，防止显示空视图
            imageCard.setVisibility(View.GONE);
        }

        //如果有视频
        if (video != null && !video.trim().isEmpty()) {
            playerview.setVisibility(View.VISIBLE);

            player = new ExoPlayer.Builder(this).build();
            playerview.setPlayer(player);

            MediaItem mediaItem = MediaItem.fromUri(video);
            player.setMediaItem(mediaItem);
            player.prepare();


        }
        else {
            playerview.setVisibility(View.GONE);
        }


    }
    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.release();
            player = null;
        }
    }

}