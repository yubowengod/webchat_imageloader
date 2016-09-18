package com.example.god.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewTreeObserver;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.imagedemo.ItemEntity;
import com.example.imagedemo.ListItemAdapter;
import com.example.oracle.pic_info;
import com.example.oracle.pic_info_name;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;;


/**
 * 照片墙主活动，使用GridView展示照片墙。
 *
 * @author guolin
 */
public class MainActivity extends Activity {

    private ExecutorService executorService;
    public static String [] test_str={"1","2","3","4"};
    /**
     * 用于展示照片墙的GridView
     */
    private GridView mPhotoWall;

    /**
     * GridView的适配器
     */
    private PhotoWallAdapter mAdapter;

    private int mImageThumbSize;
    private int mImageThumbSpacing;


    /** Item数据实体集合 */
    private ArrayList<ItemEntity> itemEntities;
    private ArrayList<ItemEntity> pic_itemEntities;
    /** ListView对象 */
    private ListView listview;

    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 3333) {
                //只要在主线程就可以处理ui
                ((ImageView) MainActivity.this.findViewById(msg.arg1)).setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    private void test()
    {

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                pic_info_name.getImageromSdk("3");

                final String [] test_name = new String[pic_info_name.getList_result().size()];

                for (int i = 0 ;i < pic_info_name.getList_result().size(); i++)
                {
                    test_name[i] = pic_info_name.getList_result().get(i);
                }

                final String [][] test_name_picinfo = new String[pic_info_name.getList_result().size()][];

                for (int i = 0 ;i < pic_info_name.getList_result().size(); i++)
                {
                    pic_info.getImageromSdk("2",test_name[i]);

                    List<String> picinfo = new ArrayList<String>();

                    test_name_picinfo[i] = new String[pic_info.getList_result().size()];

                    for (int j=0;j<pic_info.getList_result().size();j++)
                    {
                        test_name_picinfo[i][j] = pic_info.getList_result().get(j);
                    }
                }

                int ww=pic_info_name.getList_result().size();

                int www=pic_info_name.getList_result().size();

                try {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listview = (ListView) findViewById(R.id.listview);

                            itemEntities = new ArrayList<ItemEntity>();


                            // 1.无图片
                            ArrayList<String> urls = new ArrayList<String>();
                            urls.add("http://192.168.155.1:8011/webnnn/11.jpg");
                            ItemEntity entity1 = new ItemEntity(//
                                    "http://192.168.155.1:8011/webnnn/1.jpg", test_name[2], null, urls);
                            itemEntities.add(entity1);
                            // 2.1张图片
                            ArrayList<String> urls_1 = new ArrayList<String>();
                            urls_1.add("http://192.168.155.1:8011/webnnn/22.jpg");
                            ItemEntity entity2 = new ItemEntity(//
                                    "http://192.168.155.1:8011/webnnn/2.jpg", test_name[5], null, urls_1);
                            itemEntities.add(entity2);
                            // 3.3张图片
                            ArrayList<String> urls_2 = new ArrayList<String>();
                            urls_2.add("http://192.168.155.1:8011/webnnn/33.jpg");
                            urls_2.add("http://192.168.155.1:8011/webnnn/1122.jpg");
                            urls_2.add("http://192.168.155.1:8011/webnnn/44.jpg");
                            ItemEntity entity3 = new ItemEntity(//
                                    "http://192.168.155.1:8011/webnnn/3.jpg", test_name[8], null, urls_2);
                            itemEntities.add(entity3);
                            // 4.6张图片
                            ArrayList<String> urls_3 = new ArrayList<String>();
                            urls_3.add("http://192.168.155.1:8011/webnnn/55.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/66.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/77.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/88.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/99.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/55.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/66.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/77.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/88.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/99.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/55.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/66.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/77.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/88.jpg");
                            urls_3.add("http://192.168.155.1:8011/webnnn/99.jpg");
                            ItemEntity entity4 = new ItemEntity(//
                                    "http://192.168.155.1:8011/webnnn/4.jpg",test_name[3], null, urls_3);
                            itemEntities.add(entity4);





                            listview.setAdapter(new ListItemAdapter(MainActivity.this, itemEntities));
                        }
                    });
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }




            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executorService = Executors.newFixedThreadPool(5);

        test();

        mImageThumbSize = getResources().getDimensionPixelSize(
                R.dimen.image_thumbnail_size);
        mImageThumbSpacing = getResources().getDimensionPixelSize(
                R.dimen.image_thumbnail_spacing);
        mPhotoWall = (GridView) findViewById(R.id.photo_wall);
        mAdapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls,
                mPhotoWall);
        mPhotoWall.setAdapter(mAdapter);
        mPhotoWall.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        final int numColumns = (int) Math.floor(mPhotoWall
                                .getWidth()
                                / (mImageThumbSize + mImageThumbSpacing));
                        if (numColumns > 0) {
                            int columnWidth = (mPhotoWall.getWidth() / numColumns)
                                    - mImageThumbSpacing;
                            mAdapter.setItemHeight(columnWidth);
                            mPhotoWall.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        }
                    }
                });

//        listview = (ListView) findViewById(R.id.listview);
//        initData();
//        listview.setAdapter(new ListItemAdapter(this, itemEntities));
    }

    /**
     * 初始化数据
     */
    private void initData() {
//        itemEntities = new ArrayList<ItemEntity>();
//        // 1.无图片
//        ArrayList<String> urls = new ArrayList<String>();
//        urls.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
//        ItemEntity entity1 = new ItemEntity(//
//                "http://192.168.155.1:8011/webnnn/1.jpg", "张三", null, urls);
//        itemEntities.add(entity1);
//        // 2.1张图片
//        ArrayList<String> urls_1 = new ArrayList<String>();
//        urls_1.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
//        ItemEntity entity2 = new ItemEntity(//
//                "http://192.168.155.1:8011/webnnn/2.jpg", "李四", null, urls_1);
//        itemEntities.add(entity2);
//        // 3.3张图片
//        ArrayList<String> urls_2 = new ArrayList<String>();
//        urls_2.add("http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg");
//        urls_2.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
//        urls_2.add("http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg");
//        ItemEntity entity3 = new ItemEntity(//
//                "http://192.168.155.1:8011/webnnn/3.jpg", "王五", null, urls_2);
//        itemEntities.add(entity3);
//        // 4.6张图片
//        ArrayList<String> urls_3 = new ArrayList<String>();
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698837_7507.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698865_3560.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698839_2302.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698839_2302.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698837_7507.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698865_3560.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698867_8323.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698837_5654.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698883_5877.jpg");
//        urls_3.add("http://img.my.csdn.net/uploads/201410/19/1413698839_2302.jpg");
//        ItemEntity entity4 = new ItemEntity(//
//                "http://192.168.155.1:8011/webnnn/4.jpg", "赵六", null, urls_3);
//        itemEntities.add(entity4);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAdapter.fluchCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        mAdapter.cancelAllTasks();
    }

}