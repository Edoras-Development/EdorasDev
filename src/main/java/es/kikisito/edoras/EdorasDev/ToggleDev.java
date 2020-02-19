package es.kikisito.edoras.EdorasDev;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ToggleDev extends Command {
    public ToggleDev() {
        super("toggledev");
    }

    @Override
    public void execute(CommandSender cmdSender, String[] strings) {
        if (cmdSender.hasPermission("edoras.toggledev")) {
            if (!Main.devEnabled) {
                Main.devEnabled = true;
                cmdSender.sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "Se ha " + ChatColor.GREEN + "abierto" + ChatColor.AQUA + " el servidor de pruebas."));
            } else {
                Main.devEnabled = false;
                for (ProxiedPlayer p : Main.plugin.getProxy().getPlayers()) {
                    if (!p.hasPermission("edoras.dev") && p.getServer().getInfo().getName().equals("dev")) {
                        ServerInfo target = Main.plugin.getProxy().getServerInfo("survival");
                        p.connect(target);
                        p.sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "Has sido transportado al servidor " + target.getName()));
                    }
                }
                cmdSender.sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "Se ha " + ChatColor.RED + "cerrado" + ChatColor.AQUA + " el servidor de pruebas."));
            }
        }
    }
}
