package io.yovelas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScanQRCodeActivity extends AppCompatActivity {


    boolean isEmail = false;
    String intentData = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        initViews();
    }

    private void initViews(){

        // 提示文字
        TextView txtBarcodeValue = (TextView) findViewById(R.id.txtBarcodeValue);

        // 摄像头
        SurfaceView surfaceView = (SurfaceView)findViewById(R.id.surfaceView);

        // 添加到邮箱按扭
        Button btnAction = (Button) findViewById(R.id.btnAction);

        // 为添加到邮箱按扭绑定点击事件
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("事件","按扭触发");
                if (intentData.length() > 0) {
                    if (isEmail) {
                        System.out.println("发邮件去吧歌");
                    } else {
                        // 开启摄像头
                        System.out.println("开启摄像头");
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(intentData)));
                    }
                }
            }
        });

    }

//    private void initialiseDetectorsAndSources() {
//
//        Log.d("事件","扫描程序开始");
//        Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();
//        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this)
//                .setBarcodeFormats(Barcode.ALL_FORMATS)
//                .build();
//
//        cameraSource = new CameraSource.Builder(this, barcodeDetector)
//                .setRequestedPreviewSize(1920, 1080)
//                .setAutoFocusEnabled(true) //you should add this feature
//                .build();
//
//        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                try {
//                    if (ActivityCompat.checkSelfPermission(ScannedBarcodeActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                        cameraSource.start(surfaceView.getHolder());
//                    } else {
//                        ActivityCompat.requestPermissions(ScannedBarcodeActivity.this, new
//                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                cameraSource.stop();
//            }
//        });
//
//
//        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//                Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
//                if (barcodes.size() != 0) {
//                    txtBarcodeValue.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            if (barcodes.valueAt(0).email != null) {
//                                txtBarcodeValue.removeCallbacks(null);
//                                intentData = barcodes.valueAt(0).email.address;
//                                txtBarcodeValue.setText(intentData);
//                                isEmail = true;
//                                btnAction.setText("ADD CONTENT TO THE MAIL");
//                            } else {
//                                isEmail = false;
//                                btnAction.setText("LAUNCH URL");
//                                intentData = barcodes.valueAt(0).displayValue;
//                                txtBarcodeValue.setText(intentData);
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
////        cameraSource.release();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
////        initialiseDetectorsAndSources();
//    }



    // 打包ScannedBarcodeActivity内容，并跳转到EmailActivity
//                        startActivity(new Intent(this, EmailActivity.class).putExtra("email_address", intentData));

}
