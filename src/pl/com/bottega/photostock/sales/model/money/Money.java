package pl.com.bottega.photostock.sales.model.money;

public interface Money extends Comparable<Money>{

    Currency DEFAULT_CURRENCY = Currency.CREDIT;

    Money ZERO = valueOf(0, DEFAULT_CURRENCY);

    enum Currency {CREDIT}

    Money add(Money addend);

    Money subtract(Money subtrahend);

    Money multiply(long factor);

    Money opposite();

    RationalMoney convertToRational();

    IntegerMoney convertToInteger();

    static Money valueOf(Rational value, Currency currency) {
        return new RationalMoney(value, currency);
    }
    static Money valueOf(long value, Currency currency) {
        return new RationalMoney(Rational.valueOf(value), currency);
    }
    static Money valueOf(long value) {
        return new RationalMoney(Rational.valueOf(value), DEFAULT_CURRENCY);
    }

    default boolean gte(Money money) {
        return compareTo(money) >= 0;
    }

    default boolean gt(Money money) {
        return compareTo(money) > 0;
    }

    default boolean lte(Money money) {
        return compareTo(money) <= 0;
    }

    default boolean lt(Money money) {
        return compareTo(money) < 0;
    }
}
