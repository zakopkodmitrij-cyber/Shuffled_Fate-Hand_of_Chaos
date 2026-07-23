package Main.Entities.Dyad.AI_Bot.Evaluetion.EvaluetionsCard;

import Main.Cards.Card;
import Main.Entities.Dyad.AI_Bot.Soul;
import Main.Entities.Dyad.Player;

public class Eval_Heal extends EvaluationCard {
    public Eval_Heal(Card card, Soul bot, Player player) {
        super(card, bot, player);
    }

    @Override
    public double playUtility() {
        if (bot.getHP() > 90) return 0;

        double inverseRatioOfHP = 1 - ((double) bot.getHP() / bot.getMaxHP());
        return  100 * inverseRatioOfHP;
    }

}
