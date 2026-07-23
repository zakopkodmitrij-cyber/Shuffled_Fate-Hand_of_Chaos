package Main.Engine;

import Main.Engine.core.PlayerStatuses;
import Main.Entities.Dyad.Dyad;
import Main.Entities.Dyad.Player;

import Main.Engine.gameFunctions.*;
import Main.Entities.Hand;
import Main.System.Input;
import Main.UI.UI;

// GameRound отввечает за возобновлене Ходов игроков, пока один из них не проиграет
public class GameRound {
    private static int loopN = 1; // Номер хода - счетчик

    private final Dyad dyad = new Dyad(new Player("Hero"), new Player("Evil"));
    private final UI ui;
    private final Commands commands;

    public GameRound(UI ui) {
        this.ui = ui;
        ui.gotoTexturing().setDyad(dyad);

        commands = new Commands(ui);
    }

    public Dyad getDyad() { return dyad; }
    public UI getUi() { return ui; }

    public void GameCycle() {
        ui.gotoTexturing().startInterfaceElements();

        plusCards(dyad.Hero, 2);
        plusCards(dyad.Evil, 2);

        while (true) {
            ui.gotoRenderer().overlay(0,0, "Ход " + loopN, false);

            if (GameLoop()) break;

            loopN++;
        }
        resetPlayers();
        loopN = 0;

        Input.waitConfirm();
    }

    private boolean GameLoop() {

        PlayerTurn(dyad.Hero, dyad.Evil);
        dyad.Hero.gotoStatuses().tickEffects();
        if (victoryCheck()) return true;

        PlayerTurn(dyad.Evil, dyad.Hero);
        dyad.Evil.gotoStatuses().tickEffects();
        if (victoryCheck()) return true;

        return false;
    } // Функция итерации игрового цикла

    private void PlayerTurn(Player self, Player enemy) {
        removeUsedCardsForPlayers();
        plusCards(self, 3);
        updateTurnInterface(self);
        commands.callCommand(self, enemy);
    } // Функция хода игрока



// Функции класса
    private void plusCards(Player player, int cnt) {
        if (player == dyad.Hero) {
            for (int i = 0; i < cnt; i++)
                dyad.Hero.gotoHand().addCard(dyad.Hero.getDeck().draw(), dyad.Hero, dyad.Evil);
        } else {
            for (int i = 0; i < cnt; i++)
                dyad.Evil.gotoHand().addCard(dyad.Evil.getDeck().draw(), dyad.Evil, dyad.Hero);
        }

    }

    private void updateTurnInterface(Player player) {
        ui.gotoRenderer().overlay(0,7, "=>                ", false);      //  Имя
        ui.gotoRenderer().overlay(0,7, "=> " + player.getName(), false); // Ходящего

        String[] marker_YourTurn = ui.gotoAssets().getTexture("[marker_YourTurn]");
        if (player == dyad.Hero) {
            ui.gotoRenderer().overlay(0,160, "             ", false);
            ui.gotoRenderer().overlay(31,5,  marker_YourTurn, false);
        } else {
            ui.gotoRenderer().overlay(31,5,  "             ", false);
            ui.gotoRenderer().overlay(0,160, marker_YourTurn, false);
        } // Надпись ⟪ YOUR TURN ⟫ над одним из инициализированых Игроков
    }

    private boolean victoryCheck() {

        if (dyad.Hero.getHP() <= 0 && dyad.Evil.getHP() <= 0) {
            String[] txrDraw = ui.gotoAssets().getTexture("[Draw-Terrace]");
            ui.gotoRenderer().overlay(22, 43, txrDraw, false);
            ui.updateScreen();
            return true; }
        if (dyad.Hero.getHP() <= 0) {
            String[] txrEvilWins = ui.gotoAssets().getTexture("[Evil_wins-Terrace]");
            ui.gotoRenderer().overlay(22, 43, txrEvilWins, false);
            ui.updateScreen();
            return true; }
        if (dyad.Evil.getHP() <= 0) {
            String[] txrHeroWins = ui.gotoAssets().getTexture("[Hero_wins-Terrace]");
            ui.gotoRenderer().overlay(22, 43, txrHeroWins, false);
            ui.updateScreen();
            return true; }

        return false;
    }

    private void removeUsedCardsForPlayers() {
        dyad.Hero.gotoHand().removeUsedCards();
        dyad.Evil.gotoHand().removeUsedCards();
    }

    private void resetPlayers() {
        dyad.Hero = new Player("Hero");
        dyad.Evil = new Player("Evil");
//        resetPlayer(dyad.Hero);
//        resetPlayer(dyad.Evil);
    }
    private void resetPlayer(Player player) {
        player.setHP(player.getMaxHP());
        player.setMN(player.getMaxMN());
        player.setHand(new Hand());
        player.setStatuses(new PlayerStatuses());
    }


}
