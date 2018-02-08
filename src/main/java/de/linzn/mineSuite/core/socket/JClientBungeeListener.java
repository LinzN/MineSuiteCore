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

import de.linzn.jSocket.core.IncomingDataListener;
import de.linzn.mineSuite.core.CoreManager;
import de.linzn.mineSuite.core.MineSuiteCorePlugin;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.UUID;


public class JClientBungeeListener implements IncomingDataListener {


    @Override
    public void onEvent(String s, UUID uuid, byte[] bytes) {
        // TODO Auto-generated method stub
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            String servername;
            String subChannel;
            servername = in.readUTF();

            if (!servername.equalsIgnoreCase(MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.BUNGEE_SERVER_NAME)) {
                return;
            }

            subChannel = in.readUTF();

            if (subChannel.equals("server_confirm-teleport")) {
                UUID playerUUID = UUID.fromString(in.readUTF());
                String targetServer = in.readUTF();
                CoreManager.confirmTeleport(playerUUID, targetServer);
            }
            if (subChannel.equals("server_cancel-teleport")) {
                UUID playerUUID = UUID.fromString(in.readUTF());
                String targetServer = in.readUTF();
                CoreManager.cancelTeleport(playerUUID, targetServer);
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
