
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

    public class servidor {
        private int port =4444;
        private ServerSocket serverSocket;

        private ExecutorService executorService=Executors.newCachedThreadPool();

        public servidor() throws IOException{
            serverSocket =new ServerSocket(4444);
            System.out.println(port);

        }

        private PrintWriter getWriter(Socket socket) throws IOException{

            OutputStream socketOut=socket.getOutputStream();

            return new PrintWriter(new OutputStreamWriter(socketOut,"utf-8"),true);
        }

        private BufferedReader getReader(Socket socket) throws IOException{

            InputStream socketIn=socket.getInputStream();
            return new BufferedReader(new InputStreamReader(socketIn,"utf-8"));
        }


        public void Service() throws IOException {
            while (true){
                Socket socket=null;
                socket=serverSocket.accept();

                Handler handler=new Handler(socket);
                executorService.execute(handler);
            }
        }


        class Handler implements Runnable {
            private Socket socket;

            public Handler(Socket socket) {
                this.socket = socket;
            }

            public void run() {

                System.out.println("New connection accept:" + socket.getInetAddress());
                try {
                    BufferedReader br = getReader(socket);
                    PrintWriter pw = getWriter(socket);

                    pw.println("From ");

                    String msg = null;
                    while ((msg = br.readLine()) != null) {
                        if (msg.trim().equalsIgnoreCase("bye")) {
                            pw.println("From ");

                            System.out.println("");
                            break;
                        }

                        pw.println("From " + msg);
                        pw.println(""+msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (socket != null)
                            socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        public static void main(String[] args) throws IOException{
            new servidor().Service();
        }

    }


