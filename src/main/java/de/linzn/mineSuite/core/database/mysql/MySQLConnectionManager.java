package de.linzn.mineSuite.core.database.mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class MySQLConnectionManager {

    public final static MySQLConnectionManager DEFAULT = new MySQLConnectionManager();

    private final Map<String, MySQLConnectionHandler> map;

    public MySQLConnectionManager() {
        this.map = new ConcurrentHashMap<>();
    }

    public MySQLConnectionHandler getHandler(String key, MySQLConnectionFactory f) {
        MySQLConnectionHandler handler = new MySQLConnectionHandler(key, f);
        map.put(key, handler);
        return handler;
    }

    public Connection getConnection(String handle) throws SQLException {
        return map.get(handle).getConnection();
    }

    public void release(String handle, Connection c) {
        map.get(handle).release(c);
    }

    public MySQLConnectionHandler getHandler(String key) {
        MySQLConnectionHandler handler = map.get(key);
        if (handler == null) {
            throw new NoSuchElementException();
        }
        return handler;
    }

    public void shutdown() {
        for (MySQLConnectionHandler handler : map.values()) {
            handler.shutdown();
        }
        map.clear();
    }
}