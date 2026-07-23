package Main.Entities.Dyad;

import Main.Decks.AllDecks.DeckType;
import Main.Decks.Deck;
import Main.Engine.core.PlayerStatuses;
import Main.Entities.Entity;
import Main.Entities.Hand;
import Main.System.Input;

public class Player extends Entity {
    private Deck deck = new Deck(DeckType.FULL);
    protected Hand hand = new Hand();
    protected PlayerStatuses statuses = new PlayerStatuses();

    public Player() { super("*unnamed", "[ico_Chad]", 100, 60); } // Конструктор (по умолчанию)
    public Player(String name) { super(name, "[ico_Chad]", 100, 60); } // Конструктор (по имени)
    public Player(Player sample) {
        super(sample);

        deck = sample.deck;
        hand     = new Hand(sample.hand);
        statuses = new PlayerStatuses(sample.statuses);
    }

    public Deck getDeck() { return deck; }
    public void setDeck(Deck deck) { this.deck = deck; }

    public Hand gotoHand()   { return hand; }
    public PlayerStatuses gotoStatuses() { return statuses; }

    public void setHand(Hand hand) { this.hand = hand; }
    public void setStatuses(PlayerStatuses statuses) { this.statuses = statuses; }

    public String inputCommand() {
        return Input.nextLine();
    }
    public String inputAddCommand() { return inputCommand(); }

    // TODO: Допилить
    public void rebuild(Player sample) {
        super.rebuild((Entity) sample);

        hand     = new Hand(sample.hand);
        statuses = new PlayerStatuses(sample.statuses);
    }
}
