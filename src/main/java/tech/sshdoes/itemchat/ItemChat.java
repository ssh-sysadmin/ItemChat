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
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;


public final class ItemChat extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "ItemChat has loaded. Thanks for downloading (https://github.com/ssh-sysadmin/itemchat)");
        getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup message - Plugin made by SSH#4388

    }
    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.LOWEST)
    public void onChatSend(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        String message = e.getMessage();
        ItemStack item = player.getItemInHand();
        //TextComponent header = new TextComponent("");
        if(message.equalsIgnoreCase("[i]")) {
            TextComponent tc = new TextComponent(Objects.requireNonNull(item.getItemMeta()).getDisplayName());
            TextComponent hovermessage = new TextComponent((net.md_5.bungee.api.chat.BaseComponent) item.getItemMeta().getLore());
            tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovermessage).create()));
            //header.addExtra(message2);
            //TextComponent finalmessage = header;
            e.setCancelled(true);
            player.chat(tc.toLegacyText());

        }
    }
}
