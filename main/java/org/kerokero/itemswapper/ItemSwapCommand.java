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

import java.util.List;

public class ItemSwapCommand implements CommandExecutor, TabExecutor {
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
            Material item_x = Material.valueOf(args[1].toUpperCase());
            int amount_x = Integer.parseInt(args[2]);
            Material item_y = Material.valueOf(args[3].toUpperCase());
            int amount_y = Integer.parseInt(args[4]);

            if (!player.getInventory().contains(item_x, amount_x)) {
                player.sendMessage("§cYou don't have enough " + item_x.toString().toLowerCase() + "!");
                return true;
            }

            // Remove the items
            player.getInventory().removeItem(new ItemStack(item_x, amount_x));
            //player.getInventory().removeItem(new ItemStack(item_y, amount_y));

            // Add the swapped items
            //player.getInventory().addItem(new ItemStack(item_x, amount_y));
            player.getInventory().addItem(new ItemStack(item_y, amount_y));

            player.sendMessage("§aSuccessfully swapped " + amount_x + " " + item_x.toString().toLowerCase() +
                    " with " + amount_y + " " + item_y.toString().toLowerCase() + "!");
        } catch (IllegalArgumentException e) {
            player.sendMessage("§cInvalid item type or amount specified.");
        }
    return false;
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }


}
