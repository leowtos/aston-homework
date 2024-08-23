package dev.leowtos.aston.homework.core.streams;

import static org.junit.jupiter.api.Assertions.*;
import static dev.leowtos.aston.homework.core.util.CollectionsUtils.*;

import org.junit.jupiter.api.Test;

import java.util.List;

class HomeworkTest {

    private static final Trader RAOUL = new Trader("Raoul", "Cambridge");
    private static final Trader MARIO = new Trader("Mario", "Milan");
    private static final Trader ALAN = new Trader("Alan", "Cambridge");
    private static final Trader BRIAN = new Trader("Brian", "Cambridge");

    private static final List<Transaction> TRANSACTIONS = List.of(
            new Transaction(BRIAN, 2011, 300),
            new Transaction(RAOUL, 2012, 1000),
            new Transaction(RAOUL, 2011, 400),
            new Transaction(MARIO, 2012, 710),
            new Transaction(MARIO, 2012, 700),
            new Transaction(ALAN, 2012, 950)
    );

    private static final Homework HOMEWORK = new Homework();


    @Test
    void getTransactionsByYearAndSortByValue() {
        var expected = List.of(
                new Transaction(BRIAN, 2011, 300),
                new Transaction(RAOUL, 2011, 400)
        );

        var actual = HOMEWORK.getTransactionsByYearAndSortByValue(TRANSACTIONS, 2011);

        assertIterableEquals(expected, actual);
    }

    @Test
    void getUniqueCitiesWithTrader() {
        var expected = List.of("Cambridge", "Milan");

        var actual = HOMEWORK.getUniqueCitiesWithTrader(TRANSACTIONS);

        assertCollectionEquals(expected, actual);
    }

    @Test
    void getTradersByCityAndSortByName() {
        var expected = List.of(ALAN, BRIAN, RAOUL);

        var actual = HOMEWORK.getTradersByCityAndSortByName(TRANSACTIONS, "Cambridge");

        assertIterableEquals(expected, actual);
    }

    @Test
    void getSortedTradersNames() {
        var expected = "Alan, Brian, Mario, Raoul";

        var actual = HOMEWORK.getSortedTradersNames(TRANSACTIONS);

        assertEquals(expected, actual);
    }

    @Test
    void isThereTraderFromCity() {
        var isThereTraderFromMilan = HOMEWORK.isThereTraderFromCity(TRANSACTIONS, "Milan");

        assertTrue(isThereTraderFromMilan);
    }

    @Test
    void getTransactionsValuesForCity() {
        var expected = List.of(300, 1000, 400, 950);

        var actual = HOMEWORK.getTransactionsValuesForCity(TRANSACTIONS, "Cambridge");

        assertCollectionEquals(expected, actual);
    }

    @Test
    void getMaxTransactionValue() {
        var expected = 1000;

        var actual = HOMEWORK.getMaxTransactionValue(TRANSACTIONS);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void getTransactionWithMinValue() {
        var expected = new Transaction(BRIAN, 2011, 300);

        var actual = HOMEWORK.getTransactionWithMinValue(TRANSACTIONS);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }
}