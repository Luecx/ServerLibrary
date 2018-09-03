package tcp.entities;

import tcp.connection.Connection;

public abstract class IncomingMessageHandler<T extends Observer<T>> {
	public abstract void incomingMessage(Connection<T> con, Object msg);
}
