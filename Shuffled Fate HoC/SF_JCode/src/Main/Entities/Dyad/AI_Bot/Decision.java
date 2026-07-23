package Main.Entities.Dyad.AI_Bot;

import java.util.LinkedList;

public class Decision {
    public String command = "end";                      // Основная команда принятого Решения
    public LinkedList<String> additionalImputs = null; // Для Карт, требующих доп. ввода после активации
    public double utility = 1;                        // Полезность Решения
}

