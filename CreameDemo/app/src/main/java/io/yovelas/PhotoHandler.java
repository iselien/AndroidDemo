package io.yovelas;

import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;


public class PhotoHandler extends SurfaceView implements Camera.PictureCallback {

    private static final String TAG = "PhotoHandler";
    private final Context context;

    public PhotoHandler(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {

//        File pictureFileDir = getDir();
//        Log.e(TAG, "getDir: 获取路径+do:"+ pictureFileDir );
//
//        // 检查目录是否存在，及是否是个目录
//        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
//            // 不存在，否打印错误日志，并显示错误弹框,并返回
//            Log.e(TAG, "Can't create directory to save image.");
//            Toast.makeText(context, "Can't create directory to save image.", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        // 日期格式, 图片文件名格式为： Picture_yyyymmddhhmmss.jpg
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
//        String date = dateFormat.format(new Date());
//        String photoFile = "Picture_" + date + ".jpg";
//
//        String filename = pictureFileDir.getPath() + File.separator + photoFile;

//        File pictureFile = new File(filename);
//        File picture = getExternalFilesDir();
        File pictureFile = new File("/storage/emulated/0/Android/data/io.yovelas/files/aaa.jpg");
        Log.e(TAG, "File"+pictureFile.toString());

        try {
            // 建立输出流，并写入图片数据
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(bytes);
            fos.close();
            Toast.makeText(context, "New Image saved:" + pictureFile, Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            // 输出失败，打印日志，并显示弹窗
            for ( StackTraceElement stackTrace : error.getStackTrace()) {
                Log.e(TAG, "onPictureTaken: "+stackTrace.getFileName());
            }
            Log.d(TAG, "File" + pictureFile + "not saved: " + error.getMessage()+ error.getStackTrace());
            Toast.makeText(context, "Image could not be saved.", Toast.LENGTH_LONG).show();
        }

    }


    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

    private File getDir() {
        File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Log.e(TAG, "getDir: 获取路径:"+ sdDir );
        return new File(sdDir, "Demo");
    }
}
