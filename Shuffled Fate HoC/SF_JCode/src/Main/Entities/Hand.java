package Main.Entities;

import Main.Cards.Card;
import Main.Cards.NotEnoughManaException;
import Main.Engine.core.Config;
import Main.Entities.Dyad.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();
    private Card lastUsed = null;

    public Hand() {}

    public Hand(Hand sample) {
        this.cards = new ArrayList<>(sample.cards);
        this.lastUsed = sample.lastUsed;
    }

    public List<Card> toList() { return cards; }
    public Card getLastUsed() { return lastUsed; }

    public void setHandArray(ArrayList<Card> hand) { this.cards = hand; }
    public void setLastUsed(Card lastUsed) { this.lastUsed = lastUsed; }

    public String[] showHand() {
        String[] allCards = new String[cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            allCards[i] = cards.get(i).getName();
        }
        return allCards;
    } // Получение списка названий всех карт в руке

    public Card getCard(int i) throws IndexOutOfBoundsException {
//        checkForUsedCard(hand.get(i));
        return cards.get(i);
    }

    public void addCard(Card card, Player owner, Player enemy) {
        if (!isHandFull()) {
            card.opening(owner, enemy);
            cards.add(card);
        }
    }

    public void delCard(Card card, Player owner, Player enemy) {
        card.ending(owner, enemy);
        cards.remove(card);
    }
    public Card delCard(int iCard, Player owner, Player enemy) {
        getCard(iCard).ending(owner, enemy);
        return cards.remove(iCard);
    }

    public void delCardAsUsed(Card card, Player owner, Player enemy) {
        int iCard = cards.indexOf(card);
        card.ending(owner, enemy);
        cards.set(iCard, Card.USED_CARD);
    }
    public Card delCardAsUsed(int iCard, Player owner, Player enemy) {
        Card deleted = getCard(iCard);
        deleted.ending(owner, enemy);
        cards.set(iCard, Card.USED_CARD);
        return deleted;
    }

    public int capacity() { return Config.HAND_CAPACITY; }

    public int cntAllCards() {
        return cards.size(); }

    public int cntNotUsedCards() {
        int cnt = 0;
        for (Card card : cards)
            if (card != Card.USED_CARD)
                cnt++;

        return cnt;
    }

    public void removeUsedCards() {
//        for (int i = cards.size() - 1; i >= 0; i--)
//            if (cards.get(i) instanceof UsedCard)
//                cards.remove(i);

        cards.removeIf(x -> x == Card.USED_CARD);
    }

    public void playCard(int iCard, Player caster, Player enemy) throws IndexOutOfBoundsException {
        Card currentCard = getCard(iCard);

        if (currentCard.isEnoughMana(caster)) {
            lastUsed = currentCard;

            caster.changeMN(-currentCard.manaExpensesBy(caster));
            currentCard.play(caster, enemy, iCard);
            delCardAsUsed(currentCard, caster, enemy);
        } else
            throw new NotEnoughManaException();

    } // Сыграть карту по индексу

    public void dropCard(int iCard, Player caster, Player enemy) throws IndexOutOfBoundsException {
        Card currentCard = getCard(iCard);
        lastUsed = currentCard;

        currentCard.drop(iCard, caster, enemy);
        delCardAsUsed(currentCard, caster, enemy);
    }

    public void swapCards(int iCard_1, int iCard_2) throws IndexOutOfBoundsException {
        Collections.swap(cards, iCard_1, iCard_2);
    } // Свап 2 карт в руке по их индексам

// Функции класса
    private boolean isHandFull() { return cards.size() >= Config.HAND_CAPACITY; }

    private boolean isIndexCardCorrect(int i) { return 0 <= i && i < cards.size(); }

}
