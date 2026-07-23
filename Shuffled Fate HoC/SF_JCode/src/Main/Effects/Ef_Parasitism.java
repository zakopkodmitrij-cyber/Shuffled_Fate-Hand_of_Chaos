package Main.Effects;

import Main.Entities.Dyad.Player;

public class Ef_Parasitism extends Effect {
    private final int appetite = 2;

    public Ef_Parasitism(Player player, int duration) { super(player, duration); }

    @Override
    public void start(double value) {}

    @Override
    public void tick() {
        player.changeMN(-appetite);
    }

    @Override
    public void end() {}

    @Override
    public String presentation() {
        return "\u001B[97m[ " +                                           // Открывающая скобка
                "\u001B[38;2;105;85;0m" +                                // Цвет шрифта
                "Parasite - %dMN".formatted(appetite) +              // Текст
                "\u001B[97m ]";
    }
}
