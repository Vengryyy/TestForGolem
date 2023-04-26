package la.vengryyy.testforgolem.events;

import la.vengryyy.testforgolem.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class onEntityDeath implements Listener {

    private final Main plugin;

    public onEntityDeath(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Event(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player player = event.getEntity().getKiller();
            if ((this.plugin.db.getPerformedQuestID(player.getName()) + 1) == 2) {
                if (event.getEntityType().equals(EntityType.COW)) {
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
            } else if ((this.plugin.db.getPerformedQuestID(player.getName()) + 1) == 3) {
                if (event.getEntityType().equals(EntityType.ZOMBIE)) {
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
}
