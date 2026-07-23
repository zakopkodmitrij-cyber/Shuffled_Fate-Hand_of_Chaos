package Main.Decks.AllDecks;


import Main.Cards.CardType;

public class WeightedCard {
    private final CardType type;
    private final int weight;

    public WeightedCard(CardType type, int weight) {
        this.type = type;
        this.weight = weight;
    }

    public CardType getType() { return type; }
    public int getWeight() { return weight; }
}
