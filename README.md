# SuperVideoPlayer
基于 ijkplayer 和PLDroidPlayer( based on ffplay )二次封装的视频播放器


#playerView使用说明

**1、在XML中使用**

a、引入VideoPlayerView

            <com.allen.playerview.VideoPlayerView
                    android:id="@+id/common_video_player_view"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    vpv:player_isShowFullScreenBtn="true"
                    vpv:player_isShowLoadingView="true"
                    vpv:player_isShowPlayerBottomBar="true"
                    vpv:player_isStart="true"
                    vpv:player_repeatPlay="false" />
                    
                    
                    
                    
b、可配置的所有属性

            <declare-styleable name="VideoPlayerView">
                    <attr name="player_videoPath" format="string" />
                    <attr name="player_isStart" format="boolean" />
                    <attr name="player_repeatPlay" format="boolean" />
                    <attr name="player_isShowPlayerBottomBar" format="boolean" />
                    <attr name="player_isShowLoadingView" format="boolean" />
                    <attr name="player_isShowFullScreenBtn" format="boolean" />
                    <attr name="player_isTouchForPause" format="boolean" />
            
                    <attr name="player_coverViewDrawableId" format="reference" />
                    <attr name="player_aspectRatio" format="enum">
                        <enum name="origin" value="0" />
                        <enum name="fit_parent" value="1" />
                        <enum name="paved_parent" value="2" />
                        <enum name="is_16_9" value="3" />
                        <enum name="is_4_3" value="4" />
                    </attr>
            
                </declare-styleable>
                
                
                
                
c、参数意义

            /**
                 * 视频地址
                 */
                private String videoPath = null;
                /**
                 * 是否自动播放
                 */
                private boolean isStartVideo = false;
                /**
                 * 是否重复播放
                 */
                private boolean repeatPlay = false;
                /**
                 * 是否显示底部播放控制布局
                 */
                private boolean isShowPlayerBottomBar = false;
                /**
                 * 是否显示加载loading
                 */
                private boolean isShowLoadingView = true;
                /**
                 * 是否显示视频全屏按钮
                 */
                private boolean isShowFullScreenBtn = false;
                /**
                 * 是否允许点击屏幕暂停播放
                 */
                private boolean isTouchForPause = true;
            
                /**
                 * 是否显示中间视频播放按钮
                 */
                private boolean isShowCenterPlayerBtn = true;
            
                /**
                 * 封面图片
                 */
                private Drawable coverViewDrawable = null;
                /**
                 * 视频画面预览模式    16：9    4：3  适应屏幕  等等
                 */
                private int aspectRatio;
                
                
**2、代码中使用**

            playerView
                            .setPlayerPath("yourVideoUrl")
                            .isShowPlayerBottomBar(true)
                            .isShowLoadingView(true)
                            .isShowCenterPlayerBtn(true)
                            .isRepeatPlay(true)
                            .isTouchForPause(true)
                            .isShowFullScreenBtn(true)
                            .setSoundOff()
                            .setSound(1)
                            .setScreenRatio(VideoPlayerView.FIT_PARENT)
                            .setSoundOpen()
                            .start();
                            
                            
      
            a、设置视频播放完毕监听回调方法
            playerView.setOnVideoCompletionListener(new VideoPlayerView.OnVideoCompletionListener() {
                        @Override
                        public void doOnVideoCompletionListener() {
            
                        }
                    });
            b、设置播放进度回调方法       
            playerView.setOnCurrentProgressChange(new VideoPlayerView.OnCurrentProgressChange() {
                        @Override
                        public void onCurrentProgressChange(int currentProgress, String currentTime) {
            
                        }
                    });
                    
                    
            c、如需要全屏按钮生效需要加上这句代码传递上下文对象
               配合isShowFullScreenBtn(true)使用       
            playerView.setOnFullScreenClickListener(this);
            
            
            
            
**3、如果需要边播变缓存的话需要在application里边添加如下代码（ 依赖第三方视频缓存库    compile 'com.danikula:videocache:2.6.4'  ）
    具体使用方法可参考 https://github.com/danikula/AndroidVideoCache**
    

                private HttpProxyCacheServer proxy;


                /**
                 * 视频缓存
                 *
                 * @return
                 */
                public static HttpProxyCacheServer getProxy() {
                    return sDatingApp.proxy == null ? (sDatingApp.proxy = sDatingApp.newProxy()) : sDatingApp.proxy;
                }
            
                private HttpProxyCacheServer newProxy() {
                    return new HttpProxyCacheServer(this);
                }
                
                
                在每次传递网络地址的时候使用方法如下
                String urlPath = DatingApp.getProxy().getProxyUrl(videoPath);这个方法获取缓存地址
                playerView.setPlayerPath(urlPath).start();
                
**4、如有疑问可阅读源码注释**