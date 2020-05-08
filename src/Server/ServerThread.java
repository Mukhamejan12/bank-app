package Server;

import Data.Packet;
import Data.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Packet packet = null;

            while((packet = (Packet)ois.readObject()) != null) {
                if(packet.getCode().equals("REG")){
                    User user = (User)packet.getData();
                    boolean reged = Server.registerUser(user);

                    Packet packet2 = null;

                    if(reged == true)
                        packet2 = new Packet("SUCCESS");
                    else
                        packet2 = new Packet("FAIL");

                    oos.writeObject(packet2);
                }
                else
                if(packet.getCode().equals("LOGIN")){
                    User user = (User)packet.getData();
                    User authUser = Server.loginUser(user);

                    Packet packet2 = new Packet("ANSWER", authUser);
                    oos.writeObject(packet2);
                }
            }

            ois.close();
            oos.close();
        }
        catch(Exception e){e.printStackTrace();}
    }
}
