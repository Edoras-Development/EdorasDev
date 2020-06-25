package es.kikisito.edoras.EdorasDev;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Dev extends Command {
    public Dev() {
        super("dev");
    }

    @Override
    public void execute(CommandSender cmdSender, String[] strings){
        if(!(cmdSender instanceof ProxiedPlayer)){
            cmdSender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.config.getString("not-a-player")));
            return;
        }
        if(cmdSender.hasPermission("edoras.dev") || Main.devEnabled || Main.authorized.contains(((ProxiedPlayer) cmdSender).getUniqueId())) {
            cmdSender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.config.getString("connecting")));
            Main.plugin.getProxy().getPlayer(cmdSender.getName()).connect(Main.plugin.getProxy().getServerInfo("dev"));
        } else {
            cmdSender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.config.getString("chat-kickreason")));
        }
    }
}
