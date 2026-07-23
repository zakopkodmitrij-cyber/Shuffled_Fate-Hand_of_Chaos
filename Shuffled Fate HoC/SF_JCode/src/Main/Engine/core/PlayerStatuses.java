package Main.Engine.core;

import Main.Effects.Effect;

import java.util.ArrayList;
import java.util.List;

// Класс PlayerStatuses содержит Статы игрока (множители и значения с учётом всех Эффектов)
//  и список всех наложенных Эффектов
public class PlayerStatuses {
    private boolean canMove = true;
    private double scaleDamage = 1;
    private boolean maxAtk = false;
    private double scaleProtection = 1;
    private boolean maxProtect = false;
    private double scaleConduction = 1;
    private boolean maxCon = false;
    private List<Effect> effects = new ArrayList<>();

    public PlayerStatuses() {}

    public PlayerStatuses(PlayerStatuses sample) {
        this.canMove         = sample.canMove;
        this.scaleDamage     = sample.scaleDamage;
        this.maxAtk          = sample.maxAtk;
        this.scaleProtection = sample.scaleProtection;
        this.maxProtect      = sample.maxProtect;
        this.scaleConduction = sample.scaleConduction;
        this.maxCon          = sample.maxCon;
        this.effects         = new ArrayList<>(sample.effects);
    }

    public boolean isCanMove() { return canMove; }
    public double getScaleDamage() { return scaleDamage; }
    public boolean isMaxAtk() { return maxAtk; }
    public double getScaleProtection() { return scaleProtection; }
    public boolean isMaxProtect() { return maxProtect; }
    public double getScaleConduction() { return scaleConduction; }
    public boolean isMaxCon() { return maxCon; }
    public List<Effect> toList() { return effects; }


    public void setCanMove(boolean canMove) { this.canMove = canMove; }
//---
    public void setScaleDamage(double scaleDamage) {
        this.scaleDamage = scaleDamage;
        if (this.scaleDamage < 0) this.scaleDamage = 0;
    }
    public void multScaleDamage(double multiply) {
        this.scaleDamage *= multiply;
    }
    public void setMaxAtk(boolean maxAtk) { this.maxAtk = maxAtk; }
//---
    public void setScaleProtection(double scaleProtection) {
        this.scaleProtection = scaleProtection;
        if (this.scaleProtection < 0) this.scaleProtection = 0;
    }
    public void multScaleProtection(double multiply) {
        this.scaleProtection *= multiply;
    }
    public void setMaxProtect(boolean maxProtect) { this.maxProtect = maxProtect; }
//---
    public void setScaleConduction(double scaleConduction) {
        this.scaleConduction = scaleConduction;
        if (this.scaleConduction < 0) this.scaleConduction = 0;
    }
    public void multScaleConduction(double multiply) {
        this.scaleConduction *= multiply;
    }
    public void setMaxCon(boolean maxCon) { this.maxCon = maxCon; }
//---
    public void setEffects(List<Effect> effects) { this.effects = effects; }


// Управление списком Эффектов
    public void addEffect(Effect effect) { effects.add(effect); }

    public Effect delEffect(int i) {return effects.remove(i); }
    public void   delEffect(Effect effect) { effects.remove(effect); }

    // 'Протикивает' все эффекты Игрока
    public void tickEffects() {
        for (int i = effects.size() - 1; i >= 0; i--) { // Проходимся по индексам Эффектов в обратном порядке
            Effect effect = effects.get(i);            // Получаем Эффект по его индексу
            effect.tick();                            // Вызываем его Тик
            if (effect.getDuration() <= 0) {          // Если его длительность закончилось...
                effect.end();                        // вызывает окончание эффекта
                effects.remove(i);                  // и удаляет
            }
        }

        setMaxAtk(false); setMaxProtect(false); setMaxCon(false); // Снимает моментные усиления
    }

// Функции класса

}
