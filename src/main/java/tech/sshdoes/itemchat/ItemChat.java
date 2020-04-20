package tech.sshdoes.itemchat;

import net.md_5.bungee.api.chat.BaseComponent;
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

import java.util.Objects;


public final class ItemChat extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "ItemChat has loaded. Thanks for downloading (https://github.com/ssh-sysadmin/itemchat)");
        getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup message - Plugin made by SSH#4388

    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onChatSend(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        String message = e.getMessage();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(item == null){
            player.sendMessage(ChatColor.AQUA + "You aren't holding anything");
        }
        ItemMeta itemMeta = item.getItemMeta();
        if(message.equalsIgnoreCase("[i]")) {
            TextComponent tc = new TextComponent((itemMeta.getDisplayName()));
            TextComponent hovermessage = new TextComponent((BaseComponent) itemMeta.getLore());
            tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovermessage).create()));
            e.setCancelled(true);
            player.chat(tc.toLegacyText());

        }
    }
}
