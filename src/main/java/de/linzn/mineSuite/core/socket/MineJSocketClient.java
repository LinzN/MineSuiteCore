/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

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
