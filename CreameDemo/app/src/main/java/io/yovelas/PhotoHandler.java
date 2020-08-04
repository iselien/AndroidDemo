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

public class PhotoHandler extends SurfaceView implements Camera.PictureCallback {

    private static final String TAG = "PhotoHandler";
    private final Context context;

    public PhotoHandler(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {

        File pictureFileDir = getDir();

        // 检查目录是否存在，及是否是个目录
        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
            // 不存在，否打印错误日志，并显示错误弹框,并返回
            Log.e(TAG, "Can't create directory to save image.");
            Toast.makeText(context, "Can't create directory to save image.", Toast.LENGTH_LONG).show();
            return;
        }

        // 日期格式, 图片文件名格式为： Picture_yyyymmddhhmmss.jpg
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        String filename = pictureFileDir.getPath() + File.separator + photoFile;

        File pictureFile = new File(filename);

        try {
            // 建立输出流，并写入图片数据
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(bytes);
            fos.close();
            Toast.makeText(context, "New Image saved:" + photoFile, Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            // 输出失败，打印日志，并显示弹窗
            Log.d(TAG, "File" + filename + "not saved: " + error.getMessage());
            Toast.makeText(context, "Image could not be saved.", Toast.LENGTH_LONG).show();
        }

    }

    private File getDir() {
        File sdDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(sdDir, "Demo");
    }
}
