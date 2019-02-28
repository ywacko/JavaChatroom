
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveMsg extends Thread {

    private Socket socket;
    private BufferedReader socketReader;

    ReceiveMsg(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        String msg;
        try {
            this.socketReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            while (true) {
                msg = socketReader.readLine();
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}
