
import java.io.*;
import java.net.*;

public class ServerThread extends Thread {

    private Socket socketToHandle;
    private BufferedReader streamReader;
    private PrintWriter socketWriter;
    private UserInfo user;

    ServerThread(Socket aSocket) throws IOException {
        this.socketToHandle = aSocket;
        InputStream ins = socketToHandle.getInputStream();
        OutputStream ous = socketToHandle.getOutputStream();
        this.streamReader
                = new BufferedReader(new InputStreamReader(ins));
        this.socketWriter = new PrintWriter(ous);
    }

    UserInfo getUser() {
        return this.user;
    }

    private void welcome(String name) {
        user = new UserInfo();
        user.setName(name);
        Control.addClient(this);
        sendMsg("服务器消息：你好，" + user.getName());
    }

    void sendMsg(String msg) {
        socketWriter.println(msg);
        socketWriter.flush();
    }

    private void outputMsg(String input) {
        System.out.println(this.user.getName() + "说：" + input);
        Control.pushMsg(this.user, input);
    }

    public void run() {
        try {
            sendMsg("服务器消息：欢迎来到聊天室，请输入用户名：");
            String name = streamReader.readLine();
            welcome(name);
            String input = streamReader.readLine();
            while (!input.equals("再见")) {
                outputMsg(input);
                input = streamReader.readLine();
            }
            outputMsg(input);
            Control.logOut(this.user);
            this.clientClose();
        } catch (IOException e) {
            System.out.println(user.getName() + " 聊天发生错误！");
        }
    }

    private void clientClose() throws IOException {
        socketToHandle.close();
    }
}
