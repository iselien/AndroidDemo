package io.yovelas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*通过调用隐式Intent启动系统中已存在的相机软件，并返回结果，显示在页面中*/
public class TakeAPhotoActivitiy extends AppCompatActivity {

    private static final String TAG = "TakeAPhotoActivity";
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_a_photo);

        Log.e(TAG, "进入“获取一张照片” 程序");

        // 获取元素 ImageView
        imageView = findViewById(R.id.imageview1);

        // 获取按钮元素，并绑定事件
        findViewById(R.id.btnScanBarcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "“Take a Photo” 点击事件");

                // 隐匿 Intent启动相机
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }


//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });
    }

    // 获取照片结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // 获取缩略图并填充到ImageView中
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
}
