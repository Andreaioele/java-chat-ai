import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static final int PORT = 5001;

    public static void main(String[] args) {
        System.out.println("=== Chat Server Avviato ===");
        System.out.println("In attesa che il coinquilino si connetta alla porta " + PORT + "...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Coinquilino (Client) connesso: " + clientSocket.getInetAddress().getHostAddress());
            System.out.println("Ora potete scrivervi! (Scrivi e premi Invio per inviare)");

            // Thread per leggere i messaggi in arrivo dal Client
            Thread receiveThread = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("\n[Coinquilino]: " + inputLine);
                        System.out.print("[Tu]: ");
                    }
                } catch (IOException e) {
                    System.out.println("\nConnessione chiusa dal client.");
                }
            });
            receiveThread.start();

            // Thread principale per leggere da tastiera e inviare al Client
            try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.print("[Tu]: ");
                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    System.out.print("[Tu]: ");
                }
            }
        } catch (IOException e) {
            System.err.println("Errore nel server: " + e.getMessage());
        }
    }
}
