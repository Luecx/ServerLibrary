package tcp.server;

import tcp.connection.Connection;

public abstract class ServerLoginHandler{
	public abstract void logInEvent(Connection<TCPServer> con);
}
