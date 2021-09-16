package Demo02Foldupdate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeSide {
    public static void main(String ...args) throws IOException {
//        1.创建一个服务器ServerSocket对象,和系统要指定的端口号
            ServerSocket server = new ServerSocket(8080);
//        2.使用ServerSocket对象中的方法accept,获取到请求的客户端Socket对象
            Socket socket = server.accept();
//        3.使用Socket对象中的方法getInputStream,获取到网络字节输入流InputStream对象
            InputStream is = socket.getInputStream();
//        4.判断d:\\upload文件夹是否存在,不存在则创建
            File file = new File(new File("").getAbsolutePath(),"src/upload");
            if(!file.exists()){
                file.mkdir();
            }
//        5.创建一个本地字节输出流FileOutputStream对象,构造方法中绑定要输出的目的地
            FileOutputStream fos = new FileOutputStream(file+"/1.png");
//        6.使用网络字节输入流InputStream对象中的方法read,读取客户端上传的文件
            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = is.read(bytes))!= -1){
                //7.使用本地字节输出流FileOutputStream对象中的方法write,把读取到的文件保存到服务器的硬盘上
                fos.write(bytes,0,len);
            }
//        8.使用Socket对象中的方法getOutputStream,获取到网络字节输出流OutputStream对象
//        9.使用网络字节输出流OutputStream对象中的方法write,给客户端回写"上传成功"
            socket.getOutputStream().write("上传成功".getBytes());
//        10.释放资源(FileOutputStream,Socket,ServerSocket)
            fos.close();
            server.close();
            socket.close();
    }
}
