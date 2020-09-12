package br.com.urso.loginfallcraft.commands.players;

import br.com.urso.loginfallcraft.data.PluginData;
import br.com.urso.loginfallcraft.database.AccountDAO;
import br.com.urso.loginfallcraft.utils.ChatComponent;
import br.com.urso.loginfallcraft.utils.Encriptor;
import br.com.urso.loginfallcraft.utils.MESSAGES;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Login extends Command {
    public Login(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatComponent.playerSendMessage("&cComando apenas para players"));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (!PluginData.getGamePlayerMap().get(player).isLogged()) {
            AccountDAO accountDAO = new AccountDAO();
            if (accountDAO.isPlayer(player.getUniqueId().toString())) {
                if (accountDAO.login(player.getUniqueId(), Encriptor.toMD5(Encriptor.toSHA1(args[0])))) {
                    PluginData.getGamePlayerMap().get(player).setLogged(true);
                    ChatComponent.playerSendMessage(MESSAGES.LOGIN_SUCCESS, player);
                } else {
                    int tent = PluginData.getGamePlayerMap().get(player).getLoginAttempts();

                    if (tent > 0) {
                        PluginData.getGamePlayerMap().get(player).setLoginAttempts(tent - 1);
                        ChatComponent.playerSendMessage(ChatComponent.format(MESSAGES.LOGIN_ATTEMPTS, tent), player);
                    } else {
                        player.disconnect(ChatComponent.playerSendMessage(MESSAGES.PASSWORD_INCORRECT));
                    }
                }
            } else {
                ChatComponent.playerSendMessage(MESSAGES.NEED_REGISTER, player);
            }
        } else {
            ChatComponent.playerSendMessage(MESSAGES.ALREADY_LOGGED, player);
        }


    }
}
