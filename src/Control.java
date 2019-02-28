
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;

@SuppressWarnings("unchecked")

class Control {

    private static ArrayList<ServerThread> stList = new ArrayList();
    private static int clientNumber = 0;

    static void addClient(ServerThread newSt) {
        stList.add(newSt);
        clientNumber++;
        String msg = newSt.getUser().getName();
        msg += " 上线了！（目前聊天室人数：" + clientNumber + "）";
        for (ServerThread st : stList) {
            st.sendMsg(msg);
        }
    }

    static void pushMsg(UserInfo sender, String msg) {
        msg = sender.getName() + "说：" + msg;
        for (ServerThread st : stList) {
            st.sendMsg(msg);
        }
    }

    static void logOut(UserInfo sender) {
        String msg = sender.getName() + " 下线了。";
        clientNumber--;
        msg += "（目前聊天室人数：" + clientNumber + "）";
        for (ServerThread st : stList) {
            st.sendMsg(msg);
        }
    }
}
