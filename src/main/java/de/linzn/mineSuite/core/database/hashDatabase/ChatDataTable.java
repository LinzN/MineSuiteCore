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

package de.linzn.mineSuite.core.database.hashDatabase;

import java.util.HashMap;

public class ChatDataTable {
    private static HashMap<String, Boolean> isafk = new HashMap<>();

    public static boolean isAfk(String player) {
        if (isafk.containsKey(player)) {
            return true;
        }
        return false;

    }

    public static void setAfk(String player, boolean value) {
        if (value) {
            isafk.put(player, true);
        } else {
            isafk.remove(player);
        }
    }

}