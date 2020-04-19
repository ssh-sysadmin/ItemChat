package tech.sshdoes.itemchat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import tech.sshdoes.itemchat.commands.i;

import java.lang.annotation.Annotation;

public final class ItemChat extends JavaPlugin implements EventHandler, Listener {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "ItemChat has loaded. Thanks for downloading (https://github.com/ssh-sysadmin/itemchat)");
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("i").setExecutor(new i());
        // Plugin startup message

    }
    @EventHandler
    public void onChatSend(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        String message = e.getMessage();
        ItemStack item = player.getItemInHand();
        if(message.equals("[i]")){
            player.chat(item.toString());
        }
    }

    @Override
    public EventPriority priority() {
        return null;
    }

    @Override
    public boolean ignoreCancelled() {
        return false;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
