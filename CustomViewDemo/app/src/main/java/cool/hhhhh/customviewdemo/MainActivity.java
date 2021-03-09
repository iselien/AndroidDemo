package cool.hhhhh.customviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import java.util.List;

import cool.hhhhh.customviewdemo.view.CarView;
import cool.hhhhh.customviewdemo.view.CircleProgress;

public class MainActivity extends AppCompatActivity {

    private CircleProgress mCircleProgress;
//    private List<AdBean> mAdList;
    private List<ImageView> mImageViewList;
    private int mMaxProgressVal;//进度条最大值
    private int mCurProgressVal;//当前进度
    private int mAdIndex;
    private int mInterval = 2000;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new CarView(this));

//        mCircleProgress = findViewById(R.id.progress);

//        mHandler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                        updateCountDown();
//            }
//        };
//        mHandler.sendEmptyMessageDelayed(1, 100);
    }


    /**
     * 图片倒计时
     */
    private void updateCountDown() {
        //广告进度由大到小倒计时
        mCurProgressVal += 100;
        if (mCurProgressVal > mMaxProgressVal) {
            return;
        }
        if (mCircleProgress != null) {
            mCircleProgress.setCurProgress(mCurProgressVal);
        }
        int index = mCurProgressVal / mInterval;
//        if (index < mAdList.size() && mAdIndex != index) {
//            View v = mImageViewList.get(mAdIndex);
//            if (v != null && v.getVisibility() == View.VISIBLE) {
//                v.setVisibility(View.INVISIBLE);
//            }
//            mAdIndex = mCurProgressVal / mInterval;
//        }
        if (mCurProgressVal < mMaxProgressVal) {
            if (mHandler != null) {
                mHandler.sendEmptyMessageDelayed(1, 100);
            }
        } else if (mCurProgressVal == mMaxProgressVal) {
        }
    }
}