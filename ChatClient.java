import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Utilizzo: java ChatClient <indirizzo_ip_del_server>");
            System.exit(1);
        }

        String hostName = args[0];
        System.out.println("=== Chat Client Avviato ===");
        System.out.println("Tentativo di connessione a " + hostName + ":" + PORT + "...");

        try (Socket socket = new Socket(hostName, PORT)) {
            System.out.println("Connesso al server! Ora potete scrivervi. (Scrivi e premi Invio per inviare)");

            // Thread per leggere i messaggi in arrivo dal Server
            Thread receiveThread = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println("\n[Coinquilino]: " + inputLine);
                        System.out.print("[Tu]: ");
                    }
                } catch (IOException e) {
                    System.out.println("\nConnessione persa con il server.");
                }
            });
            receiveThread.start();

            // Thread principale per leggere da tastiera e inviare al Server
            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
                
                System.out.print("[Tu]: ");
                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    System.out.print("[Tu]: ");
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto: " + hostName);
        } catch (IOException e) {
            System.err.println("Impossibile ottenere I/O per la connessione a: " + hostName);
        }
    }
}
