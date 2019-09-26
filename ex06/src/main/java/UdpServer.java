import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UdpServer extends Thread {
    private DatagramSocket socket;
    private DatagramPacket packet;
    private int bufferSize = 1024;
    private byte[] buffer = new byte[this.bufferSize];
    private Gson gson = new GsonBuilder().setLenient().create();
    @Getter
    private List<String> messages = new ArrayList<>();


    @SuppressWarnings("ThrowableNotThrown")
    public UdpServer(int port) {
        try {
            this.socket = new DatagramSocket(port);
            log.info("Udp Server started");
        } catch (SocketException e) {
            new RuntimeException("Error creating socket!\n" + e.getMessage());
        }
    }

    @Override
    public void run() {
        while (true) {
            this.buffer = new byte[this.bufferSize];
            this.packet = new DatagramPacket(this.buffer, this.buffer.length);
            try {
                this.socket.receive(this.packet);
                log.info("Some message arrived!");
                log.debug(new String(this.packet.getData()));
            } catch (IOException e) {
                throw new RuntimeException("Error while receiving a message!" + e.getMessage());
            }
            this.messages.add(new String(this.packet.getData()));
        }
    }
}
