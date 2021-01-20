package com.mytest.myapplication8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gametool.myapplication8.R;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ExecutorService theradPool;
    ServerSocket server;
    Socket client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theradPool = Executors.newCachedThreadPool();//创建一个线程池，方便清理线程

        //region [创建Socket服务,并启动]
        try {
            server = new ServerSocket(6382);//创建一个Socket服务
            theradPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println("Socket服务创建完成，等待连接。。");
                            Socket client = server.accept();
                            System.out.println("有客户端上线："+client.getPort());
                            theradPool.execute(new ClientService(client));
                        }
                    } catch (Exception e) {
                    }
                }
            });
        } catch (Exception e) {
        }
        //endregion

        //region [模拟客户端与连接服务端]
        theradPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("模拟客户端连接服务端");
                    client = new Socket("127.0.0.1", 6382);
                    client.setSoTimeout(60000);//设置链接超时时间
                    /**客户端接收的代码不写了**/
                } catch (Exception e) {
                }
            }
        });
        //endregion


        //region 【模拟客户端发送按钮单击】
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientSendToServer();
            }
        });
        //endregion
    }

    //region [Socket服务端处理Client逻辑类]
    class ClientService implements Runnable {
        Socket client;

        public ClientService(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                byte[] buffer = new byte[4096];
                DataInputStream inStream = new DataInputStream(client.getInputStream());

                while (true) {//这里期望是，循环读取客户端发送过来的字节信息
                    ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                    System.out.println("等待客户端发送信息。。。");//这里加个断点调试方便
                    int length;
                    //下面是分段读取接收到客户端发送的数据
                    while ((length = inStream.read(buffer)) != -1) {//第一次接收时，这里会阻塞等待客户端发送数据，第二次就没有阻塞了，很怪
                        bytestream.write(buffer, 0, length);
                        bytestream.flush();
                    }
                    System.out.println("接收客户端信息完成:" + bytestream.size());//第一次是成功的，之后就是失败了
                    if (bytestream.size() > 0) {
                        byte[] imageData = bytestream.toByteArray();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                        bytestream.flush();
                        bytestream.close();
                        /** ....... **/
                    }
                }
            } catch (Exception e) {
            }

        }
    }
    //endregion

    //region [模拟客户端发送图片给服务端]
    public void clientSendToServer() {
        try {
            System.out.println("模拟客户端发送信息");
            Bitmap bitmap = getScreenShot(findViewById(R.id.button1));
            //Bitmap转换成byte[]
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] datas = baos.toByteArray();
            OutputStream os = client.getOutputStream();
            os.write(datas);
            os.flush();//刷新缓冲区
            os.close();
        }catch (Exception e){}
    }
    //endregion

    //region [获取APP截图]
    private static Bitmap getScreenShot(View arg0) {
        View view = arg0.getRootView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
    //endregion
}

