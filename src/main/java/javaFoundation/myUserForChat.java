package javaFoundation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class myUserForChat {
    public static void main(String[] args) throws InterruptedException, IOException {
        try (Socket socket = new Socket("localhost",9093)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            while (!socket.isOutputShutdown()) {
                if (reader.ready()) {

                    String message = reader.readLine();
                    Thread.sleep(1000);

                    output.writeUTF(message);
                    output.flush();
                    Thread.sleep(1000);


                    if (message.equals("stop")) {
                        System.out.println("User kill connections");
                        Thread.sleep(2000);

                        if (input.read() > -1) {
                            System.out.println("reading...");
                            String in = input.readUTF();
                            System.out.println(in);
                        }
                        break;
                    }
                    Thread.sleep(2000);
                    if (input.read() > -1) {

                        System.out.println("reading...");
                        String in = input.readUTF();
                        System.out.println(in);

                        }
                    }
                }
            } catch(IOException e){
                    e.printStackTrace();
        }
    }
}

