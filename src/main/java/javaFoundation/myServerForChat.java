package javaFoundation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class myServerForChat {

    ExecutorService executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws IOException, InterruptedException {
        try(ServerSocket server = new ServerSocket(9093) ){
            Socket user = server.accept();
            DataOutputStream output = new DataOutputStream(user.getOutputStream());
            DataInputStream input = new DataInputStream(user.getInputStream());

            while (!user.isClosed()) {

                System.out.println("Please, enter your name: ");
                String userName = input.readUTF();

                System.out.println("Write your message: ");
                String newMessage = input.readUTF();
                System.out.println("Message from " + userName + ": " + newMessage);

                if (input.equals("stop")) {
                    output.writeUTF("User" + userName + "initialize connections suicide ... ");
                    output.writeUTF("Server reply - " + input + " - OK");
                    output.flush();
                    Thread.sleep(3000);
                    break;
                } else {
                    output.writeUTF("Server reply - " + input + " - OK");
                    System.out.println("Server Wrote message to client.");
                    output.flush();
                }

                System.out.println("Client disconnected");
                System.out.println("Closing connections & channels.");

                input.close();
                output.close();
            }
                user.close();

                System.out.println("Closing connections & channels - DONE.");

            } catch (IOException e) {
                e.printStackTrace();
        }
    }
}

