package tech.sshdoes.itemchat.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import tech.sshdoes.itemchat.ItemChat;

public class i implements CommandExecutor {
    public ItemChat plugin;
    public void i(ItemChat plugin){this.plugin = plugin;}
    public Player CommandSender;

    // Made by SSH#4388
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
     Player player = (Player) sender;
     ItemStack item = player.getItemInHand();
        player.chat(item.getType().toString());
        return false;
    }


}

