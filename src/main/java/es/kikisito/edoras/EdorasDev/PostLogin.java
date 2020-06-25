package es.kikisito.edoras.EdorasDev;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLogin implements Listener {
    @EventHandler
    public void onConnect(final ServerConnectEvent e){
        if(e.getTarget().getName().equals("dev") && !(Main.devEnabled || e.getPlayer().hasPermission("edoras.dev") || Main.authorized.contains(e.getPlayer().getUniqueId()))) {
            if (e.getPlayer().getServer() == null) {
                e.getPlayer().disconnect(ChatColor.translateAlternateColorCodes('&', Main.config.getString("kickreason").replace("\\n", "\n")));
            }
            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Main.config.getString("chat-kickreason")));
            e.setCancelled(true);
        }
    }
}
