package io.yovelas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.media.MediaRecorder.VideoSource.CAMERA;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class MainActivity extends AppCompatActivity {

    private final static String DEBUG_TAG = "MakePhotoActivity";
    private static final String TAG = "MainActivity";
    private Camera camera;
    private CameraPreview mPreview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();

        // 检查硬件是否存在
        if(checkCameraHardware(this)){

            // 获取指定类型的设备
            // Camera.CameraInfo.CAMERA_FACING_FRONT -- 前置相机
            // Camera.CameraInfo.CAMERA_FACING_BACK-- 后置相机
            int cameraId = getCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);

            // 获取指定CameraId的相机实例
            camera = getCameraInstance(cameraId);

            // 相机参数
//            Camera.Parameters parameters = camera.getParameters();

            // set Camera parameters
//            Camera.Parameters params = camera.getParameters();
//
//            if (params.getMaxNumMeteringAreas() > 0){ // check that metering areas are supported
//                List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
//
//                Rect areaRect1 = new Rect(-100, -100, 100, 100);    // specify an area in center of image
//                meteringAreas.add(new Camera.Area(areaRect1, 600)); // set weight to 60%
//                Rect areaRect2 = new Rect(800, -1000, 1000, -800);  // specify an area in upper right of image
//                meteringAreas.add(new Camera.Area(areaRect2, 400)); // set weight to 40%
//                params.setMeteringAreas(meteringAreas);
//            }
//
//            camera.setParameters(params);

            // 设备相机顺时针旋转90度
            camera.setDisplayOrientation(90);

            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(this, camera);
            FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
            preview.addView(mPreview);

        }

//        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
//            // 设备中存在一个相机设备
//            camera = null;
//            try {
//                // 获取相机实例
//                camera = Camera.open(); // attempt to get a Camera instance
//                Toast.makeText(this, "相机获取成功", Toast.LENGTH_LONG).show();
//
//                camera.setDisplayOrientation(90);
//
//                int numberOfCameras = Camera.getNumberOfCameras();
//
//
//
//                // Create our Preview view and set it as the content of our activity.
//                mPreview = new CameraPreview(this, camera);
//                FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
//                preview.addView(mPreview);
//
//            } catch (Exception e) {
//                // Camera is not available (in use or does not exist)
//                // 相机不可获取，可能在使用中，或不存在
//                Toast.makeText(this, "相机不可获取", Toast.LENGTH_LONG).show();
//            }
//        } else {
//            // 没有相机权限，弹出失败弹窗
//            Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG).show();
//        }
    }

    /** 检查应用是否有使用相机设备、录音设备、数据读写权限，没有则向用户发出请求 */
    private void checkPermission(){

        // 检查是否有写的权限
        int storagePermission = ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        if (storagePermission != PackageManager.PERMISSION_GRANTED) {
            // 没有写的权限，去申请写的权限，会弹出对话框
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
        }

        // 检查是否拥有相机设备使用权限
        int cameraPermission = ActivityCompat.checkSelfPermission(this, "android.permission.android.permission.CAMERA");
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            // 没有写的权限，去申请写的权限，会弹出对话框
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA);
        }
    }

    /** 检测设备是否有相机设备 */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // 这个设备中有相机
            Toast.makeText(getApplicationContext(), "发现相机设备", Toast.LENGTH_LONG).show();
            return true;
        } else {
            // 这个设备中没有相机
            Toast.makeText(getApplicationContext(), "未发现相机设备", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /** 遍历设备中的相机，并返回指定类型的CameraId */
    private int getCameraId(int facing){
        int cameraId = 0;

        // 设备中存在的相机数量
        int numberOfCameras = Camera.getNumberOfCameras();

        // 遍历设备中存在的相机，并匹配指定类型
        for (int i = 0; i < numberOfCameras; i++) {

            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);

            if (info.facing == facing) {
                Log.e(DEBUG_TAG, "找到指定类型的相机设备");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    /** 安全的方式获取一个Camera实例 */
    private Camera getCameraInstance(int cameraId){
        Camera c = null;
        try {
            // 尝试打开一个Camera实例
            c = Camera.open(cameraId);
            Toast.makeText(getApplicationContext(), "相机成功获取", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            // Camera不可获取(可能正在使用或不存在)
           Toast.makeText(getApplicationContext(), "相机不可获取", Toast.LENGTH_LONG).show();
        }
        return c; // returns null if camera is unavailable
    }

    public void onClick(View view) {
        verifyStoragePermissions(this);

        File externalFilesDir = getExternalFilesDir(null);
        Log.e(TAG, "onClick: " + externalFilesDir);

        try {
            camera.startPreview();
            camera.takePicture(null, null, new PhotoHandler(getApplicationContext()));


            Camera.PictureCallback mPicture = new Camera.PictureCallback() {

                @Override
                public void onPictureTaken(byte[] data, Camera camera) {

                    File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File pictureFile =new File(sdDir, "Demo");

                    if (pictureFile == null){
                        Log.d(TAG, "Error creating media file, check storage permissions");
                        return;
                    }

                    try {
                        FileOutputStream fos = new FileOutputStream(pictureFile);
                        fos.write(data);
                        fos.close();
                    } catch (FileNotFoundException e) {
                        Log.d(TAG, "File not found: " + e.getMessage());
                    } catch (IOException e) {
                        Log.d(TAG, "Error accessing file: " + e.getMessage());
                    }
                }
            };

//            camera.takePicture(null, null, mPicture);


        }catch (Exception e){
            Log.e(TAG, "getCameraInstance: "+e.getMessage()+"----"+ e.toString());
            Toast.makeText(this, "预览异常", Toast.LENGTH_LONG).show();
        }
    }



    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    private static String[] PERMISSIONS_STORAGE = { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE" };

    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




//    @Override
//    protected void onPause() {
//        super.onPause();
//        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
//        releaseCamera();              // release the camera immediately on pause event
//    }
//
//    private void releaseMediaRecorder(){
//        if (mediaRecorder != null) {
//            mediaRecorder.reset();   // clear recorder configuration
//            mediaRecorder.release(); // release the recorder object
//            mediaRecorder = null;
//            mCamera.lock();           // lock camera for later use
//        }
//    }
//
//    private void releaseCamera(){
//        if (mCamera != null){
//            mCamera.release();        // release the camera for other applications
//            mCamera = null;
//        }
//    }

}