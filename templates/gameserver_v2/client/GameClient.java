package templates.gameserver_v2.client;

import protocol.commands.Command;
import protocol.commands.CommandDataBase;
import tcp.client.TCPClient;
import tcp.connection.Connection;
import tcp.entities.IncomingMessageHandler;
import templates.gameserver_v2.server.ServerOut;
import udp.client_side.UDPClient;
import udp.udp_content.UDPContent;

import java.util.ArrayList;

public abstract class GameClient<S extends UDPContent> {


    String ip;

    CommandDataBase dataBase;

    TCPClient TCP_CIENT;
    UDPClient<ServerOut<S>, S> UDP_CLIENT;

    public GameClient(){

    }

    protected abstract ArrayList<Command> createTCPCommands();

    public void connect(String ip, int port, String loginData){


        this.ip = ip;
        TCP_CIENT = new TCPClient();

        TCP_CIENT.addIncomingMessageHandler(new IncomingMessageHandler<TCPClient>() {
            @Override
            public void incomingMessage(Connection<TCPClient> con, Object msg) {

            }
        });

        TCP_CIENT.connect(ip, port);
        try {
            TCP_CIENT.getConnection().sendMessage("login " + loginData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){

    }


}
