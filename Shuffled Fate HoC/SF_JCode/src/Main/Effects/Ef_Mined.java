package Main.Effects;

import Main.Entities.Dyad.Player;

// Заминирован - получает урон по истечении эффекта
public class Ef_Mined extends Effect {
    private final int power;
    private final String text = "\u001B[38;2;0;0;255m Intoxication 2";

    public Ef_Mined(Player player, int duration, int power) {
        super(player, duration);
        this.power = power;
    }

    @Override
    public void start(double value) {}

    @Override
    public void tick() { duration--; }

    @Override
    public void end() {
        int damage = (int)Math.round(power / player.gotoStatuses().getScaleProtection());
        player.changeHP(-damage);
    }

    @Override
    public String presentation() {
        return "\u001B[97m[ " +                                  // Открывающая скобка
               "\u001B[38;2;120;0;0m" +                          // Цвет шрифта
               "Mined - %dHP (%d)".formatted(power, duration) +  // Текст
               "\u001B[97m ]";
    }
}
