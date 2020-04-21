package tech.sshdoes.itemchat;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;


class Main extends JavaPlugin implements Listener {


    private static Main instance;

    public void onEnable() {
        instance=this;
        super.onEnable();

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {
        super.onDisable();
    }

    public static Main getInstance() {
        return instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();
        if (message.equalsIgnoreCase("[i]")) {
            e.setCancelled(true);
            if (item == null || item.getType().equals(Material.AIR)) {
                e.getPlayer().sendMessage(ChatColor.AQUA + "You aren't holding anything");
                return;
            }
            TextComponent comp = new TextComponent(String.format(e.getFormat(), e.getPlayer().getName(), ""));
            TextComponent tc = new TextComponent(itemMeta.getDisplayName().equals("") ? item.getType().toString() : itemMeta.getDisplayName());
            BaseComponent hover = new TextComponent(itemMeta.hasLore() ? String.join("\n", itemMeta.getLore()) : "No Lore");
            BaseComponent enchants = new TextComponent(convertEnchants(item));
            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(enchants).append("\n").append(hover).create()));
            Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(comp, tc));
        }
    }

    private String convertEnchants(ItemStack item) {
        if (!item.hasItemMeta()) return "";
        if (!item.getItemMeta().hasEnchants()) return "";
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Enchantment, Integer> entry : item.getItemMeta().getEnchants().entrySet()) {
            builder.append(entry.getKey().getKey().getKey() + " " + toRoman(entry.getValue()) + "\n");
        }
        return builder.toString().trim();
    }

    private String toRoman(Integer value) {
        switch (value) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            default:
                return "";
        }
    }

}