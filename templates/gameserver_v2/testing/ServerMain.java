package templates.gameserver_v2.testing;

import protocol.commands.Argument;
import protocol.commands.Command;
import protocol.commands.CommandDataBase;
import protocol.commands.Executable;
import tcp.connection.Connection;
import templates.gameserver_v2.server.GameServer;

import java.util.ArrayList;
import java.util.Scanner;

public class ServerMain {

    private static GameServer<ClientData, InData> gameServer;
    private static CommandDataBase dataBase;

    static{
        gameServer = new GameServer<ClientData, InData>() {
            @Override
            protected ClientData acceptLoginRequest(Command c) {
                if(c.getArgument("name").equals("")) return null;
                return new ClientData(c.getArgument("name").getValue());
            }

            @Override
            protected ArrayList<Argument> createLoginArguments() {
                ArrayList<Argument> arguments = new ArrayList<>();
                arguments.add(new Argument("name", true));
                return arguments;
            }

            @Override
            protected ArrayList<Command> createTCPCommands() {
                return new ArrayList<>();
            }
        };

        dataBase = new CommandDataBase();
        dataBase.registerCommand(new Command("startServer").setExecutable(new Executable() {
            @Override
            public void execute(Connection<?> con, Command c) {
                gameServer.start(55555);
            }
        }));
        dataBase.registerCommand(new Command("stopServer").setExecutable(new Executable() {
            @Override
            public void execute(Connection<?> con, Command c) {
                gameServer.close();
                System.exit(-1);
            }
        }));dataBase.registerCommand(new Command("startGame").setExecutable(new Executable() {
            @Override
            public void execute(Connection<?> con, Command c) {
                gameServer.startGame(55566);
            }
        }));dataBase.registerCommand(new Command("stopGame").setExecutable(new Executable() {
            @Override
            public void execute(Connection<?> con, Command c) {
                gameServer.stopGame();
            }
        }));dataBase.registerCommand(new Command("print").setExecutable(new Executable() {
            @Override
            public void execute(Connection<?> con, Command c) {
                gameServer.printClients();
            }
        }));
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(true){
            dataBase.executeCommand(null, sc.next());
        }
    }
}
