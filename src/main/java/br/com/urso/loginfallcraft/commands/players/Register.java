package br.com.urso.loginfallcraft.commands.players;

import br.com.urso.loginfallcraft.data.PluginBungeeData;
import br.com.urso.loginfallcraft.database.AccountDAO;
import br.com.urso.loginfallcraft.utils.ChatComponent;
import br.com.urso.loginfallcraft.utils.CommunicationChannel;
import br.com.urso.loginfallcraft.utils.Encriptor;
import br.com.urso.loginfallcraft.utils.MESSAGES;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Register extends Command {
    public Register(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatComponent.playerSendMessage("&cComando apenas para players"));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;


        AccountDAO accountDAO = new AccountDAO();
        if (accountDAO.isPlayer(player.getUniqueId().toString())) {
            if (PluginBungeeData.getGamePlayerMap().get(player).isLogged()) {
                ChatComponent.playerSendMessage(MESSAGES.ALREADY_REGISTRED, player);
            } else {
                ChatComponent.playerSendMessage(MESSAGES.NEED_LOGIN, player);
            }

        } else {
            if (args.length == 2) {
                if (args[0].equals(args[1])) {
                    accountDAO.register(player.getUniqueId(), Encriptor.toMD5(Encriptor.toSHA1(args[0])));
                    ChatComponent.playerSendMessage(MESSAGES.REGISTER_SUCCESS, player);
                    PluginBungeeData.getGamePlayerMap().get(player).setLogged(true);
                    CommunicationChannel.loginAction(player, player.getUniqueId().toString(), "unlock");
                } else {
                    ChatComponent.playerSendMessage(MESSAGES.PASSWORD_DONT_MATCH, player);
                }
            } else {
                ChatComponent.playerSendMessage(MESSAGES.NEED_REGISTER, player);
            }


        }

    }
}
