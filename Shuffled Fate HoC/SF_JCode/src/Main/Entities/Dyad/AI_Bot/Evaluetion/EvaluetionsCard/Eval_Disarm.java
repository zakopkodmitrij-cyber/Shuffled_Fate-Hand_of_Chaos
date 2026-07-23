package Main.Entities.Dyad.AI_Bot.Evaluetion.EvaluetionsCard;

import Main.Cards.Card;
import Main.Entities.Dyad.AI_Bot.Soul;
import Main.Entities.Dyad.Player;
import Main.Engine.core.Config;

public class Eval_Disarm extends EvaluationCard {
    public Eval_Disarm(Card card, Soul bot, Player player) {
        super(card, bot, player);
    }

    @Override
    public double playUtility() {
        double ratioOfCountEnemysCards = (double) player.gotoHand().cntNotUsedCards() / Config.HAND_CAPACITY;

        return 100 * ratioOfCountEnemysCards;
    }

    @Override
    protected double dropPeculiarities() {
        double inverseRatioOfCountSelfsCards = 1 - ((double) bot.gotoHand().toList().size() / Config.HAND_CAPACITY);


        return -(baseUtilityOfDroping(card, bot) * inverseRatioOfCountSelfsCards);
    }

}
