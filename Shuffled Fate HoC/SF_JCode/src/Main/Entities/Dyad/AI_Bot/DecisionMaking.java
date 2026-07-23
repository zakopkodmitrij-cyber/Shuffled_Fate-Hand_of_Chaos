package Main.Entities.Dyad.AI_Bot;

import Main.Cards.Card;
import Main.Entities.Dyad.AI_Bot.Evaluetion.CalculateDecision;
import Main.Entities.Dyad.Player;

/// Блок принятия решений ботом
public class DecisionMaking {
    private Soul bot;
    private Player player;
    private final CalculateDecision calculateDecision;

    private Decision currentDecision;

    public DecisionMaking(Soul bot, Player player) {
        this.bot = bot;
        this.player = player;
        calculateDecision = new CalculateDecision(bot, player);
    }

    public Decision getCurrentDecision() { return currentDecision; }

    public void setPlayers(Soul bot, Player player) {
        this.bot = bot;
        this.player = player;
        calculateDecision.setPlayers(bot, player);
    }

    public CalculateDecision gotoCalcScore() { return calculateDecision; } // [Возможно не нужен]

    public void makeBestDecision() {
        currentDecision = new Decision();

        if (isDead(player)) return;

        Decision tempDecision;
        for (int nCard = 1; nCard <= bot.gotoHand().toList().size(); nCard++) {
            Card curCard = bot.gotoHand().getCard(nCard - 1);
             tempDecision = calculateDecision.calculate(curCard, nCard);

            if (tempDecision.utility > currentDecision.utility) {
                currentDecision = tempDecision;
            }

        }

    }

    // Функции класса
    private boolean isDead(Player player){
        return player.getHP() == 0;
    }

}
