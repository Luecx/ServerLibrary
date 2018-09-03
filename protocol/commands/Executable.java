package protocol.commands;

import tcp.connection.Connection;

/**
 * Created by finne on 27.01.2018.
 */
public interface Executable {

    public void execute(Connection<?> con, Command c);

}
