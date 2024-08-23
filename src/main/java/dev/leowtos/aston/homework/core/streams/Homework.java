package dev.leowtos.aston.homework.core.streams;

import java.util.*;
import java.util.stream.Collectors;

public class Homework {

    /**
     * Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
     */
    public List<Transaction> getTransactionsByYearAndSortByValue(Collection<Transaction> transactions, int year) {
        return transactions.stream()
                .filter(transaction -> year == transaction.getYear())
                .sorted(Comparator.comparing(Transaction::getValue))
                .toList();
    }

    /**
     * Вывести список неповторяющихся городов, в которых работают трейдеры.
     */
    public Set<String> getUniqueCitiesWithTrader(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
    }

    /**
     * Найти всех трейдеров из Кембриджа и отсортировать их по именам.
     */
    public List<Trader> getTradersByCityAndSortByName(Collection<Transaction> transactions, String city) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> city.equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
    }

    /**
     * Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
     */
    public String getSortedTradersNames(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.joining(", "));
    }

    /**
     * Выяснить, существует ли хоть один трейдер из Милана.
     */
    public boolean isThereTraderFromCity(Collection<Transaction> transactions, String city) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> city.equals(trader.getCity()));
    }

    /**
     * Вывести суммы всех транзакций трейдеров из Кембриджа.
     */
    public List<Integer> getTransactionsValuesForCity(Collection<Transaction> transactions, String city) {
        return transactions.stream()
                .filter(transaction -> city.equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .toList();
    }

    /**
     * Какова максимальная сумма среди всех транзакций?
     */
    public Optional<Integer> getMaxTransactionValue(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }

    /**
     * Найти транзакцию с минимальной суммой.
     */
    public Optional<Transaction> getTransactionWithMinValue(Collection<Transaction> transactions) {
        return transactions.stream()
                .reduce((a, b) -> a.getValue() <= b.getValue() ? a : b);
    }
}
