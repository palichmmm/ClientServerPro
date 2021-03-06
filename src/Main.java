import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            System.out.println("Server started");
            while (true) {
                int index = 0;
                String username = "";
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    System.out.printf("New connection accepted. Port: %d%n", clientSocket.getPort());
                    while (!clientSocket.isClosed()) {
                        if (index == 0) {
                            out.println("Write your name");
                        }
                        if (index == 1) {
                            out.println("Are you child? (yes/no)");
                        }
                        if (index == 2) {
                            out.println("Welcome to the kids area, " + username + "! Let's play! Press <q> - Exit");
                        }
                        if (index == 3) {
                            out.println("Welcome to the adult zone, " + username + "! Have a good rest, or a good working day! Press <q> - Exit");
                        }
                        if (index > 3) {
                            out.println(username + "! Press <q> - Exit");
                        }
                        String text = in.readLine();// ждём пока клиент что-нибудь нам напишет
                        if (text.equalsIgnoreCase("q")) {
                            out.println("Server reply - " + text + " - OK");
                            break;
                        }
                        System.out.println("Incoming request - " + text);
                        switch (index) {
                            case 0: {
                                username = text;
                                index = 1;
                                break;
                            }
                            case 1: {
                                if (text.equalsIgnoreCase("no")) {
                                    index = 3;
                                } else {
                                    index = 2;
                                }
                                break;
                            }
                            default:
                                index++;
                        }
                    }
                }
            }
        } catch (Exception err) {
            System.out.println("!!!!!!! = " + err);
        } finally {
            System.out.println("Server closed!");
        }
    }
}
