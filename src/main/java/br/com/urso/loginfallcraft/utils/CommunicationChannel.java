package br.com.urso.loginfallcraft.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class CommunicationChannel {
    public static void loginAction(ProxiedPlayer player, String data1 , String type) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("uuid");
        out.writeUTF(type);
        out.writeUTF(data1);
        player.getServer().getInfo().sendData("flogin", out.toByteArray());
    }
}
