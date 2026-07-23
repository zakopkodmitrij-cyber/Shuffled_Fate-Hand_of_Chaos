package Main.Decks.AllDecks;

import static Main.Cards.CardType.*;

import java.util.List;

public enum DeckType {
    FULL(
            ATTACK    .weight(1),
            BOMB      .weight(1),
            EXPLOTION .weight(1),
            HEAL      .weight(1),
            TOXIN     .weight(1),
            CRUELTY   .weight(1),
            PROTECTION.weight(1),
            CONDUCTION.weight(1),
            MIMIC     .weight(1),
            RIFT      .weight(1),
            DISARM    .weight(1),
            PARASITE  .weight(1),
            EXCHANGE  .weight(1)
    ),
    BOT(
            ATTACK    .weight(5),
            HEAL      .weight(4),
            DISARM    .weight(1)
    );

    DeckType(WeightedCard... pool) { this.pool = List.of(pool); }

    private final List<WeightedCard> pool;

    public List<WeightedCard> getPool() { return pool; }
}
