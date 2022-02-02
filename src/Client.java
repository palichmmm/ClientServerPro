import java.io.*;
import java.net.Socket;

public class Client {
    private static final String HOST = "netology.homework";
    private static int port = 8080;
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket(HOST, port);
                reader = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                while(!clientSocket.isOutputShutdown()) {
                    if(in.read() > -1) {
                        String serv = in.readLine();
                        System.out.println(serv);
                    }
                    String text = reader.readLine();
                    out.println(text);
                    if (text.equalsIgnoreCase("q")) {
                        if(in.read() > -1) {
                            String serv = in.readLine();
                            System.out.println(serv);
                        }
                        break;
                        }
                }
            } finally {
                System.out.println("Client closed...");
                clientSocket.close();
                in.close();
                out.close();
            }

        } catch (Exception err) {
            System.out.println("!!!!!!! = " + err);
        }
    }
}
