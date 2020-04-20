package tech.sshdoes.itemchat;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.annotation.Annotation;;

public final class ItemChat extends JavaPlugin implements EventHandler, Listener {

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "ItemChat has loaded. Thanks for downloading (https://github.com/ssh-sysadmin/itemchat)");
        getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup message - Plugin made by SSH#4388

    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onChatSend(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        String message = e.getMessage();
        ItemMeta item = player.getItemInHand().getItemMeta();
        //TextComponent header = new TextComponent("");
        if(message.equals("[i]")) {
            TextComponent message2 = new TextComponent(item.getDisplayName());
            message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(item.getEnchants().toString()).create()));
            //header.addExtra(message2);
            //TextComponent finalmessage = header;
            e.setCancelled(true);

            getServer().spigot().broadcast(message2);

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
