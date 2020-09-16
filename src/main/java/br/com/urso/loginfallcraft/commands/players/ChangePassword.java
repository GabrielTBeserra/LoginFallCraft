package br.com.urso.loginfallcraft.commands.players;

import br.com.urso.loginfallcraft.data.PluginBungeeData;
import br.com.urso.loginfallcraft.database.AccountDAO;
import br.com.urso.loginfallcraft.utils.ChatComponent;
import br.com.urso.loginfallcraft.utils.Encriptor;
import br.com.urso.loginfallcraft.utils.MESSAGES;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ChangePassword extends Command {
    public ChangePassword(String name) {
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
                if (args.length == 3) {
                    if (accountDAO.login(player.getUniqueId(), Encriptor.toMD5(Encriptor.toSHA1(args[0])))) {
                        if (args[1].equals(args[2])) {
                            accountDAO.changePassword(player.getUniqueId(), Encriptor.toMD5(Encriptor.toSHA1(args[1])));
                            ChatComponent.playerSendMessage(MESSAGES.CHANGED_PASSWORD, player);
                        } else {
                            ChatComponent.playerSendMessage(MESSAGES.PASSWORD_DONT_MATCH, player);
                        }
                    } else {
                        ChatComponent.playerSendMessage(MESSAGES.PASSWORD_INCORRECT, player);
                    }

                } else {
                    ChatComponent.playerSendMessage(MESSAGES.CHANGE_PASSWORD, player);
                }

            } else {
                ChatComponent.playerSendMessage(MESSAGES.NEED_LOGIN, player);
            }
        } else {
            ChatComponent.playerSendMessage(MESSAGES.NEED_REGISTER, player);
        }

    }


}
