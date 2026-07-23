package Main.Decks;

import Main.Cards.Card;
import Main.Decks.AllDecks.DeckType;
import Main.Decks.AllDecks.WeightedCard;

import java.util.List;
import java.util.Random;

import Main.Cards.CardBuilder;

public class Deck {
    private final Random random = new Random();
    private DeckType type;

    public Deck(DeckType deckType) {
        this.type = deckType;
    }

    public void setDeckType(DeckType deckType) {
        this.type = deckType;
    }

    public Card draw() {
        int choise = random.nextInt(sumWeigths());

        int currSum = 0;
        for (WeightedCard weightedCard : type.getPool()) {
            currSum += weightedCard.getWeight();
            if (choise < currSum)
                return CardBuilder.buildCard(weightedCard);
        }

        throw new RuntimeException();
    }

    public List<WeightedCard> getPool() { return type.getPool(); }

    public int size() { return type.getPool().size(); }

// Функции класса
    public int sumWeigths() {
        return type.getPool().stream().
                mapToInt(WeightedCard::getWeight).sum();
    }
}
