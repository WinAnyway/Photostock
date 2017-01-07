package pl.com.bottega.photostock.sales.model.money;

public class MoneyEqualsTest {
    public static void main(String[] args) {
        Money.Currency currency = Money.Currency.CREDIT;
        System.out.println(new IntegerMoney(0, currency).equals(new RationalMoney(Rational.valueOf(0), currency)));
        System.out.println(new IntegerMoney(100, currency).equals(new RationalMoney(Rational.valueOf(1), currency)));
        System.out.println(new IntegerMoney(150, currency).equals(new RationalMoney(Rational.valueOf(3, 2), currency)));
        System.out.println(new RationalMoney(Rational.valueOf(2), currency).equals(new RationalMoney(Rational.valueOf(2), currency)));
        System.out.println(new IntegerMoney(100, currency).equals(new IntegerMoney(100, currency)));
    }
}
