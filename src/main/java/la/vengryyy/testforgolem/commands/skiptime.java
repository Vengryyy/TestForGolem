package la.vengryyy.testforgolem.commands;

import la.vengryyy.testforgolem.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.time.LocalTime;

public class skiptime implements CommandExecutor {
    private final Main plugin;

    public skiptime(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender commandSender, @Nonnull Command command, @Nonnull String s, @Nonnull String[] strings) {
        this.plugin.questsManager.timeOfLastQuestUpdate = LocalTime.now().minusHours(1);
        commandSender.sendMessage(ChatColor.GREEN + "Время ожидания успешно пропущенно!");
        return true;
    }
}
