package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStoreTest {
    GameStore store = new GameStore();

    @Test //Добавление игры
    public void shouldAddGame() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        
        assertTrue(store.containsGame(game));
    }

    @Test //Добавление нескольких игр
    public void shouldAddSomeGame() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Диабло", "РПГ");
        Game game3 = store.publishGame("ГТА", "Гонки");

        assertTrue(store.containsGame(game1) && store.containsGame(game2) && store.containsGame(game3));
    }

    @Test //Поиск добавленной игры в каталоге
    public void shouldContainsAddedGameInStore() {
        Game game = store.publishGame("Диабло", "РПГ");

        assertTrue(store.containsGame(game));
    }

    @Test // Поиск игры не добавленной в каталог
    public void shouldContainsNotAddedGameInStore() {
        GameStore anotherStore = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game expectedGame = anotherStore.publishGame("Ведьмак3", "РПГ");

        assertFalse(store.containsGame(expectedGame));
    }

    @Test // Первое добавление игрового времени
    public void shouldFirstAddPlayTime() {
        store.addPlayTime("Petya", 5);

        int expected = 5;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test // Следующее добавление игрового времени. Время должно суммироваться с предыдущим
    public void shouldNextAddPlayTimeForOnePlayer() {
        store.addPlayTime("Petya", 5);
        store.addPlayTime("Petya", 4);

        int expected = 9;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test // Добавление отрицательного игрового времени
    public void shouldFirstAddNegativePlayTime() {
        store.addPlayTime("Petya", -5);

        int expected = 0;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test // Поиск игрока с большим игровым временем
    public void shouldGetMostPlayer() {
        store.addPlayTime("Petya", 5);
        store.addPlayTime("Anya", 4);

        String expected = "Petya";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test // Поиск игрока с большим игровым временем для игроков с одинаковым игровым временем
    public void shouldGetMostPlayerWithTheSameTime() {
        store.addPlayTime("Petya", 5);
        store.addPlayTime("Anya", 5);

        String expected = "Petya"; // Поиск выдает первого игрока которого нашел в списке.
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test // Поиск игроков с большим игровым временем, если игроков нет.
    public void shouldGetMostPlayerWithoutPlayer() {

        Assertions.assertEquals(null, store.getMostPlayer());
    }

    @Test // Сумма игрового времени всех игроков
    public void shouldSumPlayedTime() {
        store.addPlayTime("Petya", 5);
        store.addPlayTime("Anya", 4);
        store.addPlayTime("Ivan", 7);

        int expected = 16;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

}
