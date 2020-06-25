package es.kikisito.edoras.EdorasDev;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Main extends Plugin implements Listener {

    public static String prefix = ChatColor.WHITE + "[" + ChatColor.DARK_AQUA + "Edoras" + ChatColor.WHITE + "] ";
    public static List<UUID> authorized = new ArrayList<>(Collections.emptyList());
    public static Main plugin;
    public static boolean devEnabled = false;
    public static Configuration config;
    private static File dataFolder;

    @Override
    public void onEnable() {
        try {
            loadConfig();
            for(Object obj : config.getList("allowed")){
                UUID uuid = UUID.fromString(obj.toString());
                authorized.add(uuid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        plugin = this;
        dataFolder = getDataFolder();
        this.getProxy().getPluginManager().registerListener(this, new PostLogin());
        this.getProxy().getPluginManager().registerCommand(this, new ToggleDev());
        this.getProxy().getPluginManager().registerCommand(this, new Dev());
        this.getProxy().getPluginManager().registerCommand(this, new AuthorizeDev());
    }

    public void loadConfig() throws IOException {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
    }

    public static void saveConfig(){
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(dataFolder, "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}