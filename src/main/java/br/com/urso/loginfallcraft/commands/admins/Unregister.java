package br.com.urso.loginfallcraft.commands.admins;

import br.com.urso.loginfallcraft.database.AccountDAO;
import br.com.urso.loginfallcraft.utils.ChatComponent;
import br.com.urso.loginfallcraft.utils.MESSAGES;
import br.com.urso.loginfallcraft.utils.UUIDResolver;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

public class Unregister extends Command {
    public Unregister(String name, String permission, String... aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        AccountDAO accountDAO = new AccountDAO();


        if (UUIDResolver.isValidName(args[0])) {
            if (accountDAO.isPlayer(UUIDResolver.getUUIDFromNick(args[0]))) {
                accountDAO.unregister(UUIDResolver.getUUIDFromNick(args[0]));
                sender.sendMessage(ChatComponent.playerSendMessage(MESSAGES.PLAYER_UNREGISTER));
            } else {
                sender.sendMessage(ChatComponent.playerSendMessage(MESSAGES.PLAYER_NOT_REGISTRED));
            }
        } else {
            sender.sendMessage(ChatComponent.playerSendMessage(MESSAGES.PLAYER_NOT_REGISTRED));
        }
    }
}
