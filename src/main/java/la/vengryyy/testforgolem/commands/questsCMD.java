package la.vengryyy.testforgolem.commands;

import la.vengryyy.testforgolem.Main;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nonnull;

public class questsCMD implements CommandExecutor {
    private final Main plugin;

    public questsCMD(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String string, @Nonnull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.plugin.textManager.onlyForPlayer);
            return true;
        }

        Player player = (Player) sender;
        Inventory inventory = this.plugin.guiManager.createQuestsGUI(player);
        player.openInventory(inventory);
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
        return true;
    }
}
