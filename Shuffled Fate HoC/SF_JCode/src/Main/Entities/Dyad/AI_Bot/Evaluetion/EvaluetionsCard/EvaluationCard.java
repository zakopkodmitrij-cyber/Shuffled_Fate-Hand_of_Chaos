package Main.Entities.Dyad.AI_Bot.Evaluetion.EvaluetionsCard;

import java.util.LinkedList;

import Main.Cards.Card;
import Main.Entities.Dyad.AI_Bot.Soul;
import Main.Entities.Dyad.Player;

public abstract class EvaluationCard {
    protected final Card card;
    protected final Soul bot;
    protected final Player player;

    public EvaluationCard(Card card, Soul bot, Player player) {
        this.card = card;
        this.bot = bot;
        this.player = player;
    }


    public final double getPlayUtility() {
        if (checkForSufficientMana(card, bot))
            return playUtility();
        else
            return 0;
    }

    public abstract double playUtility();


    public final double getDropUtility() {
        double utilityOfDroping = baseUtilityOfDroping(card, bot);

        return utilityOfDroping + dropPeculiarities();
    }

    protected double dropPeculiarities() { return 0; }


    public LinkedList<String> getPlayAddImputs(){ return null; }
    public LinkedList<String> getDropAddImputs(){ return null; }

// Функции класса и наследников
    protected double baseUtilityOfDroping(Card card, Soul bot) {
        double ratioOfMN = 1 - ((double) bot.getMN() / bot.getMaxMN());
        int manaRecovery = card.getManaCost() / 2;

        return (10 * manaRecovery) * ratioOfMN; // TODO [ПЕРЕОСМЫСЛИТЬ]
    }

    private boolean checkForSufficientMana(Card card, Soul bot) {
        return card.manaAbsorption(bot, false);
    }

}
