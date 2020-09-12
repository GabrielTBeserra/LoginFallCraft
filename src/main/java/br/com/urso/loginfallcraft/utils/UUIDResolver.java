package br.com.urso.loginfallcraft.utils;

import br.com.urso.loginfallcraft.core.LoginFallCraft;
import br.com.urso.loginfallcraft.data.ConfigFile;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import sun.security.krb5.Config;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

public class UUIDResolver {
    public static boolean isValidName(String name){
        try {
            getUUIDFromNick(name);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static String getUUIDFromNick(String name) {
        LoginFallCraft pl = LoginFallCraft.pluginInstance;

        // Verifica se o servidor esta rodando com online mode on ou off
        if (ConfigFile.getConfigFile().getBoolean("online-mode")) {
            return returnFromWebSite(name);
        } else {
            return UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8)).toString();
        }
    }

    private static String returnFromWebSite(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        String uuidFormated = "";
        String uuid = "";
        try {
            String UUIDJson = IOUtils.toString(new URL(url));
            if (UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            uuid = UUIDObject.get("id").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        uuidFormated = uuid.replaceAll(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5");


        return uuidFormated;
    }
}
