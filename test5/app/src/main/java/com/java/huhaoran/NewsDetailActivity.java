package com.java.huhaoran;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.google.gson.Gson;
import com.java.huhaoran.R;
import com.java.huhaoran.note.FavoritesHistoryNote;
import com.java.huhaoran.note.LikeNote;
import com.java.huhaoran.note.SummaryNote;

public class NewsDetailActivity extends AppCompatActivity {

    private ExoPlayer player;
    private boolean isLike = false;
    private boolean isFavor = false;

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
        TextView summary_content = findViewById(R.id.summary_content);
        ImageView reload = findViewById(R.id.btn_reload_summary);
        ImageView btn_like = findViewById(R.id.btn_like);
        ImageView btn_favor = findViewById(R.id.btn_favor);
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

        // 注意网络请求必须放在一个单独的线程里
        new Thread(() -> {
            // 获取数据库实例
            AppDatabase db = AppDatabase.getInstance(this);
            // 根据title查询
            SummaryNote cacheSummary = db.summaryDao().queryByTitle(titleText);

            // 先判空
            String summary_;
            if (cacheSummary != null) {
                summary_ = cacheSummary.getSummary_content();
            } else {
                summary_ = null;
            }

            // 如果有储存，直接显示
            if (summary_ != null && !summary_.isEmpty()) {
                runOnUiThread(() -> {
                    if (summary_content != null) {
                        summary_content.setText(summary_);
                    }
                });
            }
            // 没有总结过，调用
            else {
                String summary = SummarizeByGLM.summarize(titleText, contentText);
                Gson gson = new Gson();
                try {
                    ChatResponse chatResponse = gson.fromJson(summary, ChatResponse.class);
                    if (chatResponse == null || chatResponse.choices == null || chatResponse.choices.length == 0) {
                        runOnUiThread(() -> {
                            if (summary_content != null) {
                                summary_content.setText("AI 总结失败，请稍后再试");
                            }
                        });
                        return;
                    }
                    String summaryText = chatResponse.choices[0].message.content;
                    // 回到主线程更新UI
                    runOnUiThread(() -> {
                        if (summary_content != null) {
                            summary_content.setText(summaryText);
                        }
                    });
                    // 储存新闻总结
                    SummaryNote note = new SummaryNote(titleText, summaryText);
                    db.summaryDao().insert(note);
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> {
                        if (summary_content != null) {
                            summary_content.setText("AI 总结失败，请稍后再试");
                        }
                    });
                }
            }
        }).start();


        // 重新加载总结
        reload.setOnClickListener(v -> {
            if (summary_content != null) {
                summary_content.setText("AI 正在分析本文内容……");
            }
            new Thread(() -> {
                String summary = SummarizeByGLM.summarize(titleText, contentText);
                Gson gson = new Gson();
                try {
                    ChatResponse chatResponse = gson.fromJson(summary, ChatResponse.class);
                    if (chatResponse == null || chatResponse.choices == null || chatResponse.choices.length == 0) {
                        runOnUiThread(() -> {
                            if (summary_content != null) {
                                summary_content.setText("AI 总结失败，请稍后再试");
                            }
                        });
                        return;
                    }
                    String summaryText = chatResponse.choices[0].message.content;
                    // 回到主线程更新UI
                    runOnUiThread(() -> {
                        if (summary_content != null) {
                            summary_content.setText(summaryText);
                        }
                    });
                    // 储存新闻总结
                    AppDatabase db = AppDatabase.getInstance(getApplicationContext()); // 或 MainActivity.this
                    SummaryNote note = new SummaryNote(titleText, summaryText);
                    db.summaryDao().insert(note);
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> {
                        if (summary_content != null) {
                            summary_content.setText("AI 总结失败，请稍后再试");
                        }
                    });
                }
            }).start();
        });

        //如果点过赞或是进行过收藏，要显示出来
        //注意子线程中不能改变ui
        AppDatabase db = AppDatabase.getInstance(this);
        // 初始化点赞/收藏状态并更新 UI
        new Thread(() -> {
            boolean like = db.likeDao().existsByTitle(titleText);
            boolean favor = db.favoritesHistoryDao().existsByTitle(titleText);
            //注意不能把ui操作放到线程外，否则可能来不及更新
            runOnUiThread(() -> {
                isLike = like;
                isFavor = favor;
                btn_like.setImageResource(isLike ? R.drawable.like_light : R.drawable.like_dark);
                btn_favor.setImageResource(isFavor ? R.drawable.favor_light : R.drawable.favor_dark);
            });
        }).start();

        //设置点赞和收藏的点击事件
        btn_like.setOnClickListener(v -> {
            new Thread(() -> {
                if (isLike) {
                    db.likeDao().deleteByTitle(titleText);
                } else {
                    db.likeDao().insert(new LikeNote(titleText));
                }
                isLike = !isLike;
                runOnUiThread(() -> {
                    btn_like.setImageResource(isLike ? R.drawable.like_light : R.drawable.like_dark);
                    btn_like.animate()
                            .scaleX(1.3f)
                            .scaleY(1.3f)
                            .setDuration(100)
                            .withEndAction(() -> {
                                btn_like.animate()
                                        .scaleX(1.0f)
                                        .scaleY(1.0f)
                                        .setDuration(100)
                                        .start();
                            })
                            .start();
                });
            }).start();
        });


        btn_favor.setOnClickListener(v -> {
            new Thread(() -> {
                //如果已经收藏，则取消收藏
                if (isFavor) {
                    db.favoritesHistoryDao().deleteByTitle(titleText);
                }
                else {
                    //否则加入
                    db.favoritesHistoryDao().insert(new FavoritesHistoryNote(titleText, publishTimeText, contentText, publisherText, image, video, System.currentTimeMillis()));
                }
                //反转
                isFavor = !isFavor;

                runOnUiThread(() -> {
                    // 图标切换
                    btn_favor.setImageResource(
                            isFavor ? R.drawable.favor_light : R.drawable.favor_dark
                    );

                    // 缩放动画
                    btn_favor.animate()
                            .scaleX(1.3f)
                            .scaleY(1.3f)
                            .setDuration(100)
                            .withEndAction(() -> {
                                btn_favor.animate()
                                        .scaleX(1.0f)
                                        .scaleY(1.0f)
                                        .setDuration(100)
                                        .start();
                            })
                            .start();
                } );
            }).start();


        });

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

class ChatResponse {
    Choice [] choices;
    class Choice {
        Message message;
    }

    class Message {
        String content;
    }
}