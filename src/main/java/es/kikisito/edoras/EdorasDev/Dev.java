package es.kikisito.edoras.EdorasDev;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class Dev extends Command {
    public Dev() {
        super("dev");
    }

    @Override
    public void execute(CommandSender cmdSender, String[] strings){
        if(cmdSender.hasPermission("edoras.dev") || Main.devEnabled || Main.authorized.contains(cmdSender.getName())) {
            cmdSender.sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "Accediendo al servidor de pruebas."));
            Main.plugin.getProxy().getPlayer(cmdSender.getName()).connect(Main.plugin.getProxy().getServerInfo("dev"));
        } else {
            cmdSender.sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "El servidor de pruebas se encuentra " + ChatColor.RED + "cerrado" + ChatColor.AQUA + "."));
        }
    }
}
