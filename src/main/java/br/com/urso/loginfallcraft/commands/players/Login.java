package br.com.urso.loginfallcraft.commands.players;

import br.com.urso.loginfallcraft.data.PluginData;
import br.com.urso.loginfallcraft.database.AccountDAO;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Login extends Command {
    public Login(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) sender;
        player.getServer().sendData();

        AccountDAO accountDAO = new AccountDAO();
        if (accountDAO.isPlayer(player.getUniqueId())) {
            if (accountDAO.login(player.getUniqueId(), args[0])) {
                PluginData.getGamePlayerMap().get(player).setLogged(true);
            }
        }


    }
}
