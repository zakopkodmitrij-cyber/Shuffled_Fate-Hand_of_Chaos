package Main.Cards;

import Main.Decks.AllDecks.WeightedCard;

public enum CardType {
    ATTACK,
    BOMB,
    EXPLOTION,
    HEAL,
    TOXIN,
    CRUELTY,
    PROTECTION,
    CONDUCTION,
    MIMIC,
    RIFT,
    DISARM,
    PARASITE,
    EXCHANGE;

    public WeightedCard weight(int weight) {
        return new WeightedCard(this, weight);
    }
}
