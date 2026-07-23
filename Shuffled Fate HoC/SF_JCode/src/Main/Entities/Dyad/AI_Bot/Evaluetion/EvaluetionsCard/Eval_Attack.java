package Main.Entities.Dyad.AI_Bot.Evaluetion.EvaluetionsCard;

import Main.Cards.Card;
import Main.Entities.Dyad.AI_Bot.Soul;
import Main.Entities.Dyad.Player;

public class Eval_Attack extends EvaluationCard {
    public Eval_Attack(Card card, Soul bot, Player player) {
        super(card, bot, player);
    }

    @Override
    public double playUtility() {
        return 50;
    }
}
