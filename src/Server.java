
import java.io.*;
import java.net.*;

public class Server {

    private static final int LISTEN_PORT = 3000;
    private ServerSocket ss;

    public Server(int listenPort) {
        try {
            this.ss = new ServerSocket(listenPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void acceptConnections() {
        System.out.println("服务器已创建，端口为3000");
        try {
            while (true) {
                Socket incomingConnection = this.ss.accept();
                System.out.println("进入了一个客户连接： "
                        + incomingConnection.getRemoteSocketAddress().toString());
                ServerThread st = new ServerThread(incomingConnection);
                st.start();
            }
        } catch (BindException e) {
            System.out.println("绑定端口：" + LISTEN_PORT + " 错误");
        } catch (IOException e) {
            System.out.println("无法在端口： " + LISTEN_PORT + "上建立套接字");
        }
    }

    public static void main(String[] args) {
        Server chatServer = new Server(LISTEN_PORT);
        chatServer.acceptConnections();
    }

}
