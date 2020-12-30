package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

//    private static final int CAMERA_REQUEST = 1888;
//    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    // 进入 "获取一张照片" Activity
    public void takeAPhoto(View view){
        startActivity(new Intent(this, TakeAPhotoActivitiy.class));
    }

    // 进入 "使用Camera" Activity
    public void camera(View view){
        startActivity(new Intent(this, CameraActivity.class));
    }


    // 进入 "扫一扫" Activity
    public void scan(View view){
        startActivity(new Intent(this, ScanQRCodeActivity.class));
    }

    // 进入 "打开链接" Activity
    public void openURL(View view){
        startActivity(new Intent(this, OpenURLActivity.class));
    }

    // 进入 "计算" Activity
    public void compute(View view){
        startActivity(new Intent(this, ComputeActivity.class));
    }


//    Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
//        startActivityForResult( intent,CAMERA_REQUEST );

//        // 获取 "扫一扫" 按钮
//        Button btnScanBarcode = (Button)findViewById(R.id.btnScanBarcode);

//        // 获取 "照片"
//        imageView = (ImageView) this.findViewById(R.id.imageView1);

//        // 为 "扫一扫" 按扭添加点击事件，点击进入ScannedBarcodeActivity
//        btnScanBarcode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, CAMERA_REQUEST);
//            }
//        });
//    }

//    // 在 ImageView中显示缩略图
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult( requestCode, resultCode, data );
//        if (requestCode == CAMERA_REQUEST) {
//            Bitmap photo = (Bitmap) data.getExtras().get( "data" );
//            imageView.setImageBitmap( photo );
//        }
//    }

//                startActivity(new Intent(MainActivity.this, ScannedBarcodeActivity.class));


}
