package la.vengryyy.testforgolem.events;

import la.vengryyy.testforgolem.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class onInventoryClick implements Listener {
    private final Main plugin;

    public onInventoryClick(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Event(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.getOpenInventory().getTitle().equals(this.plugin.textManager.questsGuiTitle + " " + player.getName())) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null && clickedItem.hasItemMeta() && Objects.requireNonNull(clickedItem.getItemMeta()).hasCustomModelData()) {
                int customModelData = clickedItem.getItemMeta().getCustomModelData();
                if (customModelData == 9002) {
                    if (this.plugin.db.getPerformedQuestID(player.getName()) == -1 && !this.plugin.questsManager.lastQuestTaken.contains(player)) {
                        this.plugin.db.setPerformedQuestID(player.getName(), this.plugin.questsManager.AvailableQuestID);
                        Inventory inventory = this.plugin.guiManager.createQuestsGUI(player);
                        player.openInventory(inventory);
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                    } else if (!this.plugin.questsManager.lastQuestTaken.contains(player)) {
                        this.plugin.db.setPerformedQuestID(player.getName(), -1);
                        Inventory inventory = this.plugin.guiManager.createQuestsGUI(player);
                        player.openInventory(inventory);
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
                    }
                }
            }
        }
    }
}
