package es.kikisito.edoras.EdorasDev;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collections;

public class Main extends Plugin implements Listener {

    public static String prefix = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "Edoras" + ChatColor.WHITE + "] ";
    public static ArrayList<String> authorized = new ArrayList<>(Collections.emptyList());
    public static Main plugin;
    public static boolean devEnabled = false;

    @Override
    public void onEnable() {
        plugin = this;
        this.getProxy().getPluginManager().registerListener(this, new PostLogin());
        this.getProxy().getPluginManager().registerCommand(this, new ToggleDev());
        this.getProxy().getPluginManager().registerCommand(this, new Dev());
        this.getProxy().getPluginManager().registerCommand(this, new AuthorizeDev());
    }

}