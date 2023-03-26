package ru.netology;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    GameStore store = new GameStore();
    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game2 = store.publishGame("Ведьмак", "Action-adventure");
    Game game3 = store.publishGame("Ведьмак 2", "Action-adventure");

    @Test
    @DisplayName("Двойное добавление игры")
    public void addDoubleGame() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 1);
        player.installGame(game1);

        int expected = 1;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Должен вызывать исключение, если количество часов отрицательное")
    public void shouldThrowRunTimeExceptionIfTheNumberOfHoursIsNegative() {
        Player player = new Player("Petya");
        player.installGame(game3);

        assertThrows(RuntimeException.class, () -> {
            player.play(game3, -1);

        });
    }
    @Test
    @DisplayName("Должен суммировать время, если одна игра и ноль часов")
    public void shouldSumGenreIfOneGameAndZeroTime() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 0);

        int expected = 0;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Должен суммировать время, если одна игра")
    public void shouldSumGenreIfOneGame() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("Должен суммировать время, если несколько игр")
    public void shouldSumGenreIfSomeGame() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 1);

        int expected = 5;
        int actual = player.sumGenre("Action-adventure");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Должен вызывать исключение, если игра не установлена")
    public void shouldThrowRunTimeExceptionIfGameNotInstall() {
        Player player = new Player("Petya");
        player.installGame(game1);

        assertThrows(RuntimeException.class, () -> {
            player.play(game3, 1);

        });
    }

    @Test
    @DisplayName("Должен найти самую играбельную по жанру, не установлена")
    public void shouldFindMostPlayerByGenreNoInstallGame() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 1);


        String expected = null;
        Game actual = player.mostPlayerByGenre("РПГ");
        Assertions.assertEquals(expected, actual);

    }
    @Test
    @DisplayName("Должен найти самую играбельную по жанру")
    public void shouldFindMostPlayerByGenre() {
        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 1);


        Game expected = game2;
        Game actual = player.mostPlayerByGenre("Action-adventure");
        Assertions.assertEquals(expected, actual);
    }


}
