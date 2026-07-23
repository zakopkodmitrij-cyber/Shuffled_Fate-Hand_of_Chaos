package Main.UI.Texturing;

import Main.Effects.Effect;
import Main.Engine.core.PlayerStatuses;
import Main.Entities.Dyad.Dyad;
import Main.Entities.Dyad.Player;
import Main.Engine.core.Config;
import Main.UI.Assets.AssetsManager;
import Main.UI.Render.Renderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class Texturing {
    private final Renderer renderer;
    private final AssetsManager assets;
    private Dyad dyad;

    public Texturing(Renderer renderer, AssetsManager assets) {
        this.renderer = renderer;
        this.assets = assets;
    } // Конструктор - Инициализирует Игроков и Контекст,
     // выводит стартовые элементы интерфейса

    public void setDyad(Dyad dyad) { this.dyad = dyad; }

    public void updateFrameData() {
        printIcons();
        printHPMN();
        printHands();
        printStats();
        printStatuses();
    } // Пересчитывает динамические (часто меняющиеся) данные Фрейма

    public void printIcons() {
        String[] spaceForIco = new String[15];
        Arrays.fill(spaceForIco, " ".repeat(20));

        renderer.overlay(33, 2,  spaceForIco, false);
        renderer.overlay(33, 2,  assets.getTexture(dyad.Hero.getIcoKey()), false); // Маска Hero
        renderer.overlay(2, 157, spaceForIco, false);
        renderer.overlay(2, 157, assets.getTexture(dyad.Evil.getIcoKey()), false); // Маска Evil
    }

    private void printHPMN() {
        printPlayerHPMN(49, 2,   dyad.Hero); // HPMN Hero
        printPlayerHPMN(18, 157, dyad.Evil); // HPMN Evil
    }

    private void printHands() {
        String[] voidCard = assets.getTexture("[VoidCard]");

        renderer.clearColorMap(44, 25, 50, 123, false);
        String[] MeHandNames = dyad.Hero.gotoHand().showHand();
        int nCard = 0;
        for (int i = 0; i < MeHandNames.length; i++, nCard++) { // Hand Hero
            String[] txrCard = assets.getTexture( "[Cd_%s]".formatted( MeHandNames[i] ) );
            renderer.overlay(44, 25 + i*11, txrCard, false); }
        for (int i = nCard; i < Config.HAND_CAPACITY; i++, nCard++) {
            renderer.overlay(44, 25 + nCard*11, voidCard, false); }

        renderer.clearColorMap(3, 55, 9, 153, false);
        String[] HeHandNames = dyad.Evil.gotoHand().showHand();
        nCard = 0;
        for (int i = 0; i < HeHandNames.length; i++, nCard++) { // Hand Evil
            String[] txrCard = assets.getTexture( "[Cd_%s]".formatted( HeHandNames[i] ) );
            renderer.overlay(3, 143 - i*11, txrCard, false); }
        for (int i = nCard; i < Config.HAND_CAPACITY; i++, nCard++) {
            renderer.overlay(3, 143 - nCard*11, voidCard, false); }

    } // Обновляет изображение Карт из Рук Игроков

    private void printStats() {
        printPlayerStat(38, 24,  dyad.Hero.gotoStatuses());
        printPlayerStat(12, 145, dyad.Evil.gotoStatuses());
    }

    private void printStatuses() {
        renderer.clearColorMap(38, 40, 43, 123, false);                       // Чистка зоны под панель
        renderer.overlay(38, 40, assets.getTexture("[panelEffectsLClear]"), false);
        if (!dyad.Hero.gotoStatuses().toList().isEmpty()) {
            List<String> buferMePanel = buferPanel(dyad.Hero.gotoStatuses());
            int stroke = 42 - buferMePanel.size() + 1; // Рассчет с какой строки начинать рисовать панель Эффектов
            for (int i = 0; i < buferMePanel.size(); i++) {
                renderer.overlay(stroke + i, 40, assets.getTexture("[panelEffectsL]"), false);
                renderer.overlay(stroke + i, 42, buferMePanel.get(i), false);
            }
        }

        renderer.clearColorMap(11, 55, 16, 138, false);                       // Чистка зоны под панель
        renderer.overlay(11, 55, assets.getTexture("[panelEffectsRClear]"), false);
        if (!dyad.Evil.gotoStatuses().toList().isEmpty()) {
            List<String> buferHePanel = buferPanel(dyad.Evil.gotoStatuses());
            for (int i = 0; i < buferHePanel.size(); i++) {
                renderer.overlay(11 + i, 55, assets.getTexture("[panelEffectsR]"), false);
                renderer.overlay(11 + i, 56, buferHePanel.get(i), false);
            }
        }

    } // Обновляет Статы и Панель Эффектов Игроков

    public void startInterfaceElements() {
        renderer.clearAll();

        renderer.overlay(38, 23,   assets.getTexture("[color_panelStat]"), false);
        renderer.overlay(12, 144,  assets.getTexture("[color_panelStat]"), false);

        renderer.printInputZone("Приветствую в Shuffled Fate: Hand of Chaos !!!");
    }


// Функции класса ------------------------------------------------------------------------------------------------------
    private void printPlayerHPMN(int stroke, int column, Player player) {
        String[] clrHPMN = new String[] {" ".repeat(22), " ".repeat(16)};
        String[] txrNPMN = new String[] {
                "HP: " + "█".repeat(player.getHP() /10) + "░".repeat(10 - player.getHP() /10) + " " + player.getHP() + "/" + player.getMaxHP(),
                "MN: " + "█".repeat(player.getMN() /10) + "░".repeat( 6 - player.getMN() /10) + " " + player.getMN() + "/" + player.getMaxMN()
        };

        renderer.overlay(stroke, column,   clrHPMN, false); // Очистка зоны HPMN
        renderer.overlay(stroke, column,   txrNPMN, false); // Отрисовка HPMN
    } // Выводит HPMN переданного Игрока на фрейме по координатам

    private void printPlayerStat(int stroke, int column, PlayerStatuses statuses) {
        String statAtk = (int)(statuses.getScaleDamage() * 100) + "%";
        String statDef = (int)(statuses.getScaleProtection() * 100) + "%";
        String statCon = (statuses.isMaxCon() ? "MAX" :
                (int)(statuses.getScaleConduction() * 100) + "%");

        String[] clrValues = new String[] {"     ", "     ", "", "     "};
        String[] stats = new String[] {
                "Atk: " + statAtk,
                "Def: " + statDef,
                "",
                "Con: " + statCon };

        renderer.overlay(stroke, column + 4, clrValues, false);
        renderer.overlay(stroke, column, stats, false);
    } // Выводит Статы переданного Игрока на фрейме по координатам

    private List<String> buferPanel(PlayerStatuses statuses) {
        List<String> buferPanel = new ArrayList<>(); buferPanel.add("");
        int iStr = 0, lenStr = 0;
        for (Effect effect : statuses.toList()) {
            String presEffect = effect.presentation(); // 'Презентация' текущего итерируемого Эффекта
            int lenPresEffect = factLen(presEffect);

            if (lenStr + lenPresEffect > 82) {
                buferPanel.add("");
                iStr++; lenStr = 0;
            } // Если пора начать новую строку - добавляет в буфер новый элемент пустой строки

            buferPanel.set(iStr, buferPanel.get(iStr) + presEffect + "  ");
            lenStr += lenPresEffect +2;
        }
        return buferPanel;
    } // Возвращает список строк для формирования панели Эффектов

    private int factLen(String str) {
        Matcher matcher; // Матчер для переданной строки
        while (true) {
            matcher = Config.ANSI_ESCAPE_PATTERN.matcher(str);
            if (!matcher.find()) break; // Если нет совпадений - выходим

            str = matcher.replaceFirst("");
        }
        return str.length();
    } // Возвращает чистую длину строки без Цветовых кодов

}
