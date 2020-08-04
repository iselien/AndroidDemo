package io.yovelas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String DEBUG_TAG = "MakePhotoActivity";
    private static final String TAG = "MainActivity";
    private Camera camera;
    private CameraPreview mPreview;
    private int cameraId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // 设备中存在一个相机设备
            camera = null;
            try {
                // 获取相机实例
                camera = Camera.open(); // attempt to get a Camera instance
                Toast.makeText(this, "相机获取成功", Toast.LENGTH_LONG).show();

                camera.setDisplayOrientation(90);


                // Create our Preview view and set it as the content of our activity.
                mPreview = new CameraPreview(this, camera);
                FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
                preview.addView(mPreview);

            } catch (Exception e) {
                // Camera is not available (in use or does not exist)
                // 相机不可获取，可能在使用中，或不存在
                Toast.makeText(this, "相机不可获取", Toast.LENGTH_LONG).show();
            }
        } else {
            // 没有相机权限，弹出失败弹窗
            Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick(View view) {
        try {

            camera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean b, Camera camera) {
                    camera.startPreview();
                    camera.takePicture(null, null, new PhotoHandler(getApplicationContext()));
                }
            });

        }catch (Exception e){
            Log.e(TAG, "getCameraInstance: "+e.getMessage()+"----"+ e.toString());
            Toast.makeText(this, "预览异常", Toast.LENGTH_LONG).show();
        }
    }



    private int findFrontFacingCamera() {
        // 默认为没找到相机
        int cameraId = -1;

        // 获取相机数量
        int numberOfCameras = Camera.getNumberOfCameras();
        // 遍历相机
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                Log.d(DEBUG_TAG, "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

}