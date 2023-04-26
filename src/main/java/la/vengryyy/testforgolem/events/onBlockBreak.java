package la.vengryyy.testforgolem.events;

import la.vengryyy.testforgolem.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class onBlockBreak implements Listener {
    private final Main plugin;

    public onBlockBreak(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Event(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if ((this.plugin.db.getPerformedQuestID(player.getName()) + 1) == 1) {
            if (event.getBlock().getType().equals(Material.GRASS_BLOCK)) {
                this.plugin.db.setPerformedQuestID(player.getName(), -1);
                int COCQ = this.plugin.db.getCOCQ(player.getName()) + 1;
                this.plugin.db.setCOCQ(player.getName(), COCQ);
                player.sendMessage(this.plugin.textManager.questCompleted);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);

                ItemStack award = new ItemStack(Material.GOLDEN_APPLE);
                ItemMeta awardMeta = award.getItemMeta();
                assert awardMeta != null;
                awardMeta.setDisplayName(ChatColor.YELLOW + "Награда");
                award.setItemMeta(awardMeta);
                player.getInventory().addItem(award);

                this.plugin.questsManager.lastQuestTaken.add(player);
            }
        }
    }
}
