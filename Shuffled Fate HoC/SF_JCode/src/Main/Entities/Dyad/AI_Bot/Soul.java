package Main.Entities.Dyad.AI_Bot;

import Main.Decks.AllDecks.DeckType;
import Main.Decks.Deck;
import Main.Engine.core.Config;
import Main.Entities.Dyad.Player;
import Main.System.Profiler;

import java.util.*;

/// Сущьность, действующая независимо
public class Soul extends Player {
    private DecisionMaking decisionMaking; // Блок принятия решений
    private Player player;

    public Soul(Player player) {
        super("Soul");
        setIcoKey("[ico_Bot]");
        setDeck(new Deck(DeckType.BOT));

        this.player = player;
        this.decisionMaking = new DecisionMaking(this, player);
    }

    public Soul(Player sample, Player player) {
        super(sample);
        setName(sample.getName() + "'s Soul");
        setIcoKey("[ico_Bot]");
        setDeck(new Deck(DeckType.BOT));

        this.player = player;
        this.decisionMaking = new DecisionMaking(this, player);
    } // Создание Духа из Игрока

    public DecisionMaking gotoDecisionMaking() { return decisionMaking; }

    @Override
    public String inputCommand() {
        decisionMaking.makeBestDecision();

        String command = decisionMaking.getCurrentDecision().command;
        System.out.print(command);
        wait(Config.BOT_WAITING_MILLIS);

        Profiler.recordInputTime();
        System.out.println();

        return command;
    }

    @Override
    public String inputAddCommand() {
        String input;

        try {
            input = decisionMaking.getCurrentDecision().additionalImputs.removeFirst(); }
        catch (NoSuchElementException e) {
            input = "";
        } // [ВОЗМОЖНО СТОИТ ПЕРЕПИСАТЬ В if-else ДЛЯ УПРОЩЕНИЯ]

        System.out.println(input);
        return input;
    }

    // Функции класса
    private void wait(int millis) {
        try { Thread.sleep(millis); }
        catch (InterruptedException e) { throw new RuntimeException(e); }
    }

}