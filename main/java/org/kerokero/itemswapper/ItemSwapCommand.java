package org.kerokero.itemswapper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemSwapCommand implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        // return false;
        int amount_x = 0;

        // arg[0] Player Name
        // arg[1] Item x to trade away
        // arg[2] Item x Amount
        // arg[3] Item y to get
        // arg[4] Item y Amount
        try {
            amount_x = Integer.parseInt(args[2]);
            System.out.println("The variable is an integer.");
        } catch (NumberFormatException e) {
            System.out.println("The variable is not an integer.");
            return false;
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }


}
