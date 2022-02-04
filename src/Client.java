import java.io.*;
import java.net.Socket;

public class Client {
    private static final String HOST = "netology.homework";
    private static int port = 8080;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String text = "";
            String serv;
            while (!clientSocket.isOutputShutdown()) {
                if (clientSocket.getInputStream().available() > 0) {
                    serv = in.readLine();
                    System.out.println(serv);
                    text = reader.readLine();
                    out.println(text);
                }
                if (text.equalsIgnoreCase("q")) {
                    Thread.sleep(200); // Даем немного времени на ответ. Не успел - досвидание)
                    if (clientSocket.getInputStream().available() > 0) {
                        serv = in.readLine();
                        System.out.println(serv);
                    }
                    break;
                }
            }
        } catch (Exception err) {
            System.out.println("!!!!!!! = " + err);
        } finally {
            System.out.println("Client closed...");
        }
    }
}
