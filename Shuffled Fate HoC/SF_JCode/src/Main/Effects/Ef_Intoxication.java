package Main.Effects;

import Main.Entities.Dyad.Player;

// Цель получает урон в конце хода
public class Ef_Intoxication extends Effect {
    private final int toxicity;
    private final String text = "\u001B[38;2;0;0;255m Intoxication 2";

    public Ef_Intoxication(Player player, int duration, int toxicity) {
        super(player, duration);
        this.toxicity = toxicity;
    }

    @Override
    public void start(double value) {}

    @Override
    public void tick() {
        player.changeHP(-toxicity);
        duration--; }

    @Override
    public void end() {}

    // Возвращает строку для отображения Эффекта в списке эффектов
    @Override
    public String presentation() {
        return  "\u001B[97m[ " +                                           // Открывающая скобка
                "\u001B[38;2;0;150;0m" +                                 // Цвет шрифта
                "Intoxication - %dHP (%d)".formatted(toxicity, duration) +  // Текст
                "\u001B[97m ]";                                         // Закрывающая скобка
    }

}
