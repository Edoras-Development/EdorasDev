package es.kikisito.edoras.EdorasDev;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLogin implements Listener {
    @EventHandler
    public void onLogin(final ServerConnectEvent e){
        if(e.getTarget().getName().equals("dev") && !(Main.devEnabled || e.getPlayer().hasPermission("edoras.dev") || Main.authorized.contains(e.getPlayer().getName()))) {
            if (e.getPlayer().getServer() == null) {
                e.getPlayer().disconnect(new TextComponent(ChatColor.RED + "El servidor de pruebas se encuentra actualmente cerrado."));
            }
            e.getPlayer().sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "El servidor de pruebas se encuentra " + ChatColor.RED + "cerrado" + ChatColor.AQUA + "."));
            e.setCancelled(true);
        }
    }
}
