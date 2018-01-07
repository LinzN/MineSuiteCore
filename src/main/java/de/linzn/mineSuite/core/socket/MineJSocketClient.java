package de.linzn.mineSuite.core.socket;

import de.linzn.jSocket.client.JClientConnection;
import de.linzn.mineSuite.core.MineSuiteCorePlugin;

public class MineJSocketClient {

    public JClientConnection jClientConnection1;

    public MineJSocketClient() {
        String hostName = MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.JSOCKET_HOST;
        int port = MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.JSOCKET_PORT;
        jClientConnection1 = new JClientConnection(hostName, port);
    }
}
