package Main.Cards;

import Main.Entities.Dyad.Player;

public abstract class Card {
    public static final Card USED_CARD = new Card("UsedCard", 0) {
        @Override public void play(Player caster, Player enemy, int iCard) {}
    };

    protected final String name;
    protected final int manaCost;

    public Card(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;
    }

    public String getName()  { return name; }
    public int getManaCost() { return manaCost; }

    public boolean manaAbsorption(Player caster, boolean isChangeMN) {
        int expenses = manaExpensesBy(caster);

        int finalMN = caster.getMN() - expenses; // Оставшаяся после использования мана

        if (isEnoughMana(caster)) {

            if (isChangeMN)
                caster.setMN(finalMN);

            return true; }
        else
            return false;
    } // Возвращает успешно ли влита мана

    public boolean isEnoughMana(Player caster) {
        return caster.getMN() - manaExpensesBy(caster) >= 0;
    }

    public int manaExpensesBy(Player caster) {
        if (caster.gotoStatuses().isMaxCon())
            return  0;
        else
            return  (int)Math.ceil(this.manaCost / caster.gotoStatuses().getScaleConduction());
    }

    public abstract void play(Player caster, Player enemy, int iCard);

    public void drop(int iCard, Player caster, Player enemy) {
        caster.changeMN(+manaCost/2);
    }

    public void opening(Player owner, Player enemy) {} // Действие при добавлении карты
    public void ending (Player owner, Player enemy) {} // Действие при удалении карты

    private String massage;
    protected void setMassage(String massage) { this.massage = massage; }
    public String getMassage() { return massage; }

}


