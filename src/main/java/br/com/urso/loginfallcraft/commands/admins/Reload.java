package br.com.urso.loginfallcraft.commands.admins;

import br.com.urso.loginfallcraft.data.ConfigFile;
import br.com.urso.loginfallcraft.utils.ChatComponent;
import br.com.urso.loginfallcraft.utils.MESSAGES;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class Reload extends Command {
    public Reload(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ConfigFile.setupConfigFile();
        MESSAGES.loadMessages();
        sender.sendMessage(ChatComponent.playerSendMessage(MESSAGES.CONFIG_RELOADED));
    }
}
