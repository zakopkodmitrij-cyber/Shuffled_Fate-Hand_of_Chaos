package Main.Entities.Dyad.AI_Bot.Evaluetion;

import Main.Entities.Dyad.AI_Bot.Decision;
import Main.Entities.Dyad.AI_Bot.Soul;
import Main.Entities.Dyad.Player;
import Main.Cards.Card;
import Main.Cards.AllCards.*;
import Main.Entities.Dyad.AI_Bot.Evaluetion.EvaluetionsCard.*;

public class CalculateDecision {
    private Soul bot;
    private Player player;

    public CalculateDecision(Soul bot, Player player) {
        this.bot = bot;
        this.player = player;
    }

    public void setPlayers(Soul bot, Player player) {
        this.bot = bot;
        this.player = player;
    }

    public Decision calculate(Card card, int nCard) {
        Decision decision =  new Decision();
        EvaluationCard evalCard = convertToEvaluated(card);

        if (evalCard == null) return decision;

        double playScore = evalCard.getPlayUtility();
        double dropScore = evalCard.getDropUtility();

        if (playScore > dropScore) {
            decision.command = "play ";
            decision.additionalImputs = evalCard.getPlayAddImputs();
            decision.utility = playScore;
        } else {
            decision.command = "drop ";
            decision.additionalImputs = evalCard.getDropAddImputs();
            decision.utility = dropScore;
        }
        decision.command += String.valueOf(nCard);

        return decision;
    }

// Функции класса
    private EvaluationCard convertToEvaluated(Card card) {
        if      (card instanceof Cd_Attack)   return new Eval_Attack(card, bot, player);
        else if (card instanceof Cd_Heal)     return new Eval_Heal(card, bot, player);
        else if (card instanceof Cd_Disarm)   return new Eval_Disarm(card, bot, player);
        else return null;
    }


}
