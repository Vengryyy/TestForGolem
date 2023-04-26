package la.vengryyy.testforgolem.managers;

import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class QUESTS {
    public List<Player> lastQuestTaken = new ArrayList<>();
    public int AvailableQuestID = -1;
    public int CountOfQuests;

    public int Quest1ID = 0;
    public String Quest1Info = "Вскопать один блок дёрна.";

    public int Quest2ID = 1;
    public String Quest2Info = "Убить одну корову.";

    public int Quest3ID = 2;
    public String Quest3Info = "Убить одного зомби.";

    public List<Integer> Quests = new ArrayList<>();

    public void loadQuests() {
        Quests.add(Quest1ID);
        Quests.add(Quest2ID);
        Quests.add(Quest3ID);
    }

    public LocalTime timeOfLastQuestUpdate;

    public void updateTime() {
        LocalTime currentTime = LocalTime.now();
        Duration timeSinceLastUpdate = Duration.between(timeOfLastQuestUpdate, currentTime);
        if (timeSinceLastUpdate.toMinutes() >= 60) {
            timeOfLastQuestUpdate = LocalTime.now().minusMinutes(timeSinceLastUpdate.toMinutes() - 60);
            if (AvailableQuestID == CountOfQuests - 1) {
                AvailableQuestID = 0;
            } else {
                AvailableQuestID += 1;
            }
            lastQuestTaken.clear();
        }
    }
}
