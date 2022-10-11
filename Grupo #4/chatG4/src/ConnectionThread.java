import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionThread extends Thread {
    private final Socket cs;
    private final MultiServer parent;
    private PrintWriter printWriter=null;
    public ConnectionThread(Socket cs, MultiServer parent) {
        this.cs = cs;
        this.parent = parent;
    }
    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(cs.getInputStream())) ) {
            printWriter = new PrintWriter(cs.getOutputStream(), true);
            printWriter.println("""
                    Welcome to the bestest chat service in the web
                    *--------------------------------------------*
                    Feel free to write anything you want, knowing 
                    that probably half of the world will hear what
                    you have to say.
                    Enjoy!!
                                        
                    """);
            String message;
            try {
                while ((message = bufferedReader.readLine()) != null) {
                    parent.postToAllButMe(this,
                            ConsoleColors.BLUE + this + " : " + ConsoleColors.RESET + message);
                }
            } catch (Exception e) {
                System.out.println("There was an IO exception in the client thread.");
                System.out.println(e.getMessage());
            }
        }
        catch (Exception e) {
            System.out.println("There was a problem in thread " + this);
            System.out.println(e.getMessage());
        }
    }

    public void post(String s) {
        if (printWriter != null)
            printWriter.println(s);
    }
}
