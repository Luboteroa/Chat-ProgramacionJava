import java.net.ServerSocket;
import java.util.LinkedList;

public class MultiServer {
    private boolean mustEndNow;
    LinkedList<ConnectionThread> connections;
    public MultiServer(int PORT) {
        mustEndNow = false;
        connections = new LinkedList<>();
        try (ServerSocket ss = new ServerSocket(PORT)) {
            while (!mustEndNow) {
                try {
                    ConnectionThread myNewConnection = new ConnectionThread(ss.accept(),
                            this);
                    connections.add(myNewConnection);
                    myNewConnection.start();
                } catch (Exception e) {
                    System.out.println("Client socket creation failed.");
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (Exception e) {
            System.out.println("Server socket creation failed.");
            System.out.println(e.getMessage());
        }
    }
    private void terminatePool() {
        synchronized (this) {
            mustEndNow = true;
        }
    }

    public void postToAllButMe(ConnectionThread sender, String s) {
        for (ConnectionThread connection: connections) {
            if (connection != null && connection != sender)
                connection.post(s);
        }
    }
}
