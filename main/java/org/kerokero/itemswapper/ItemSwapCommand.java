package org.kerokero.itemswapper;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class ItemSwapCommand implements CommandExecutor, TabExecutor {
    private static final Logger log = LoggerFactory.getLogger(ItemSwapCommand.class);
    // arg[0] Player Name
    // arg[1] Item x to trade away
    // arg[2] Item x Amount
    // arg[3] Item y to get
    // arg[4] Item y Amount

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        // return false;
        Player player = Bukkit.getPlayer(args[0]);

        // Making sure player is not null in any case for any null-pointer exception
        if (player == null) {
            if (commandSender instanceof Player) {
                player = (Player) commandSender;
            } else {
                return true;
            }
        }

        if (args.length != 5) {
            // player.sendMessage("§cUsage: /swap PLAYER <item1> <amount1> <item2> <amount2>");
            return true;
        }

        try {
            String item_x = args[1].toLowerCase();
            int amount_x = Integer.parseInt(args[2]);
            String item_y = args[3].toLowerCase();
            int amount_y = Integer.parseInt(args[4]);

            if (!removeItem(player, item_x, amount_x)) {
                player.sendMessage("§cYou don't have enough " + item_x + "!");
                return true;
            }

            // Add item
            addItem(player, item_y, amount_y);

            player.sendMessage("§aSuccessfully swapped " + amount_x + " " + item_x +
                    " with " + amount_y + " " + item_y + "!");
            return true;
        } catch (IllegalArgumentException e) {
            player.sendMessage("§cInvalid item type or amount specified.");
        }
    return false;
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }

    private boolean removeItem(Player player, String itemName, int amount) {
        ItemStack itemStack = createItemStack(itemName, amount);
        return player.getInventory().removeItem(itemStack).isEmpty();
    }

    private void addItem(Player player, String itemName, int amount) {
        //ItemStack itemStack = createItemStack(player, itemName, amount);
        //player.getInventory().addItem(itemStack);
        String command = "minecraft:give" + " " + player.getName() + " " + itemName + " " + amount;
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);

    }

    private ItemStack createItemStack(String itemName, int amount) {
        Material material = Material.matchMaterial(itemName);
        ItemStack itemStack = null;

        if (material != null) {
            // Handle vanilla items
            itemStack = new ItemStack(material, amount);
        } else {
            // Try to create an item stack for modded items using a command
            //String command = "give" + " " + player.getName() + " " + itemName + " " + amount;
            //Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            // This assumes you can execute a command to give the item to the player
            // and that it's handled server-side
            log.debug("Ran into else Statement in createItemStack");
        }
        if (itemStack == null) {
            itemStack = new ItemStack(Material.AIR); // Default to AIR if creation fails
        }
        return itemStack;
    }

}
