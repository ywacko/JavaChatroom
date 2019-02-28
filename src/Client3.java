
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client3 {
    private String hostIP;
    private int hostPort;
    private Socket socket;
    private PrintWriter socketWriter;

    private Client3(String hIP, int hPort) {
        this.hostIP = hIP;
        this.hostPort = hPort;
    }

    private void setUpConnection() {
        try {
            this.socket = new Socket(this.hostIP, this.hostPort);
            this.socketWriter = new PrintWriter(socket.getOutputStream());
        } catch (UnknownHostException e) {
            System.out.println("IP地址和端口发生错误");
        } catch (IOException e) {
            System.out.println("创建Socket错误");
        }
    }

    private void sendMsg(String msg) {
        this.socketWriter.println(msg);
        this.socketWriter.flush();
    }

    private void tearDownConnection(){
        socketWriter.close();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Client3 client = new Client3("127.0.0.1", 3000);
        client.setUpConnection();
        ReceiveMsg receive = new ReceiveMsg(client.socket);
        receive.start();
        String msgToSend = input.nextLine();
        do {
            client.sendMsg(msgToSend);
            msgToSend = input.nextLine();
        } while (!msgToSend.equals("再见"));
        client.sendMsg(msgToSend);
        input.close();
        client.tearDownConnection();
    }
}
