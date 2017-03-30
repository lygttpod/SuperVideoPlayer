package com.allen.supervideoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.allen.playerview.VideoFileUtils;
import com.allen.playerview.VideoPlayerView;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoPlayerView playerView;

    private String videoUrl = "https://ztest3-pic.jikeyue.com/mapi/i/video_exp/micky.mp4?gv=4_1";

    private boolean isPlayLocalVideo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);


        isPlayLocalVideo = getIntent().getBooleanExtra("playLocalVideo", false);

        playerView = (VideoPlayerView) findViewById(R.id.player_view);
        //开启全屏按钮监听事件
        playerView.setOnFullScreenClickListener(this);

        if (isPlayLocalVideo) {
            String localVideoPath = VideoFileUtils.getCopyRawResToSdcardPath(this, R.raw.local_video, ".mp4", "yourVideoPath");
            playerView.setPlayerPath(localVideoPath).start();
        } else {
            playerView.setPlayerPath(videoUrl).start();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        playerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        playerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        playerView.onDestroy();
    }
}
