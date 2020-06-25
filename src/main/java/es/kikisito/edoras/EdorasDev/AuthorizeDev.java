package es.kikisito.edoras.EdorasDev;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AuthorizeDev extends Command {
    public AuthorizeDev() {
        super("authorizedev");
    }

    @Override
    public void execute(CommandSender cmdSender, String[] strings) {
        if (cmdSender.hasPermission("edoras.authorizedev")) {
            if (strings.length != 1) {
                cmdSender.sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "Uso: /authorizedev <jugador>"));
                return;
            }
            try {
                Main.plugin.getProxy().getPlayer(strings[0]).getPing();
            } catch (NullPointerException e) {
                cmdSender.sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "El usuario indicado se encuentra " + ChatColor.RED + "desconectado" + ChatColor.AQUA + "."));
                return;
            }

            ProxiedPlayer target = Main.plugin.getProxy().getPlayer(strings[0]);
            if (!Main.authorized.contains(target.getUniqueId())) {
                Main.authorized.add(target.getUniqueId());
                Main.config.set("allowed", Main.authorized);
                Main.saveConfig();
                cmdSender.sendMessage(new TextComponent(Main.prefix + ChatColor.GRAY + target.getName() + ChatColor.AQUA + " ha sido " + ChatColor.GREEN + "añadido" + ChatColor.AQUA + " a la lista de autorizados."));
                target.sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "Se te ha " + ChatColor.GREEN + "otorgado" + ChatColor.AQUA + " permiso para acceder al servidor de pruebas."));
            } else {
                Main.authorized.remove(target.getUniqueId());
                Main.config.set("allowed", Main.authorized);
                Main.saveConfig();
                cmdSender.sendMessage(new TextComponent(Main.prefix + ChatColor.GRAY + target.getName() + ChatColor.AQUA + " ha sido " + ChatColor.RED + "eliminado" + ChatColor.AQUA + " de la lista de autorizados."));
                target.sendMessage(new TextComponent(Main.prefix + ChatColor.AQUA + "Se te ha " + ChatColor.RED + "revocado" + ChatColor.AQUA + " el permiso para acceder al servidor de pruebas."));
            }
        }
    }
}
