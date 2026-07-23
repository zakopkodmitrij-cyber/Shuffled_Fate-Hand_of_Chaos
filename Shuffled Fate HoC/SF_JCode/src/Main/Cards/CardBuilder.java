package Main.Cards;

import Main.Cards.AllCards.*;
import Main.Decks.AllDecks.WeightedCard;

public class CardBuilder {


// Возвращает конкретную карту по весовому типу
    public static Card buildCard(WeightedCard weightedCard) {
        return buildCard(weightedCard.getType());
    }

// Возвращает конкретную карту по типу
    public static Card buildCard(CardType cardType) {
        return switch (cardType) {
            case ATTACK -> new Cd_Attack();
            case BOMB -> new Cd_Bomb();
            case EXPLOTION -> new Cd_Explosion();
            case HEAL -> new Cd_Heal();
            case TOXIN -> new Cd_Toxin();
            case CRUELTY -> new Cd_Cruelty();
            case PROTECTION -> new Cd_Protection();
            case CONDUCTION -> new Cd_Conduction();
            case MIMIC -> new Cd_Mimic();
            case RIFT -> new Cd_Rift();
            case DISARM -> new Cd_Disarm();
            case PARASITE -> new Cd_Parasite();
            case EXCHANGE -> new Cd_Exchange();
        };
    }

// Возвращает конкретную карту по названию
    public static Card buildCard(String name) {
        return switch (name) {
            case "Attack"     -> new Cd_Attack();
            case "Bomb"       -> new Cd_Bomb();
            case "Explotion"  -> new Cd_Explosion();
            case "Heal"       -> new Cd_Heal();
            case "Toxin"      -> new Cd_Toxin();
            case "Cruelty"    -> new Cd_Cruelty();
            case "Protection" -> new Cd_Protection();
            case "Conduction" -> new Cd_Conduction();
            case "Mimic"      -> new Cd_Mimic();
            case "Rift"       -> new Cd_Rift();
            case "Disarm"     -> new Cd_Disarm();
            case "Parasite"   -> new Cd_Parasite();
            case "Exchange"   -> new Cd_Exchange();

            default -> throw new IllegalArgumentException();
        };
    }


}
