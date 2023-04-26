package la.vengryyy.testforgolem.managers;

import org.bukkit.ChatColor;

public class TEXT {
    public String onlyForPlayer = "This command may be used only by player";

    public String questsGuiTitle = "Квесты игрока";
    public String newQuestTitle = ChatColor.YELLOW + "Взять квест #";
    public String availableQuestTitle = ChatColor.GREEN + "Выполняется квест #";
    public String questAlreadyCompleted = ChatColor.AQUA + "Квест выполнен";
    public String questAlreadyCompletedLore = ChatColor.GRAY + "Дожидайтесь следующего";
    public String questsInfoAboutPlayerTitle = ChatColor.AQUA + "Информация";
    public String takeQuest = ChatColor.YELLOW + "Нажмите" + ChatColor.GOLD + " ПКМ " + ChatColor.YELLOW + "чтобы взять квест";
    public String cancelQuest = ChatColor.RED + "Нажмите" + ChatColor.GOLD + " ПКМ " + ChatColor.RED + "чтобы отменить квест";
    public String COCQ = ChatColor.GRAY + "Кол-во выполненных квестов: " + ChatColor.WHITE;
    public String timeToUpdateQuests = ChatColor.GRAY + "До обновления квеста: " + ChatColor.WHITE;
    public String questInfo = ChatColor.GRAY + "Описание: " + ChatColor.WHITE;
    public String questCompleted = ChatColor.GREEN + "Вы завершили квест!";
}
