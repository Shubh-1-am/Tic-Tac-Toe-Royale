import java.io. * ;
import java.net.Socket;
public class Player {
    public String name;
    public char choice;
    public Socket socket;
    public PrintWriter out;
    public BufferedReader in ;

    public Player(Socket socket) {
        this.socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean equals(Player p) {
        return this.name.equals(p.name) && this.choice == p.choice;
    }

    public String getName() {
        return name;
    }

    public char getChoice() {
        return choice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChoice(char choice) {
        this.choice = choice;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setIn(BufferedReader in ) {
        this. in =in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in ;
    }
    public void listen() {
        new Thread(new Runnable() {@Override
        public void run() {
            while (socket.isConnected()) {
                try {
                    String line = in.readLine();
                    if (line != null) {
                        System.out.println(line);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        }).start();
    }

}