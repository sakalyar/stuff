package fr.rouen.mastergil.tptest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    public void shouldInitiateDefaultMoney() {
        // Given
        int defaultAmount = 0;
        Devise defaultDevise = Devise.EURO;
        // When
        Money money = new Money();
        // Then
        assertEquals(defaultAmount, money.getMontant());
        assertEquals(defaultDevise, money.getDevise());
     }

    @Test
    public void shouldInitializeConstructor() {
        // Given
        int defaultAmount = 100;
        Devise defaultDevise = Devise.EURO;
        // When
        Money money = new Money(defaultAmount, defaultDevise);
        // Then
        assertEquals(defaultAmount, money.getMontant());
        assertEquals(defaultDevise, money.getDevise());
    }


    @Test
    public void shouldSetValue() {
        // Given
        Money money = new Money();
        int montant = 50;
        Devise devise = Devise.EURO;
        // When
        money.setMontant(montant);
        // Then
        assertEquals(montant, money.getMontant());
        assertEquals(devise, money.getDevise());
    }

    @Test
    public void shouldCheckPositive() {
        // Given
        int defaultAmount = 100;
        Devise defaultDevise = Devise.EURO;
        // When
        Money money = new Money(defaultAmount, defaultDevise);
        // Then
        assertTrue(money.isPositif());
    }

    @Test
    public void shouldCheckNegative() {
        // Given
        int defaultAmount = -100;
        Devise defaultDevise = Devise.EURO;
        // When
        Money money = new Money(defaultAmount, defaultDevise);
        // Then
        assertTrue(!money.isPositif());
    }

    @Test
    public void shouldSetPositiveMontant() {
        // Given
        Money money = new Money();
        int amount = 2000;
        // When
        money.setMontant(amount);
        // Then
        assertEquals(amount, money.getMontant());
    }

    @Test
    public void shouldSetNegativeMontant() {
        // Given
        Money money = new Money();
        int amount = -2000;
        // When
        money.setMontant(amount);
        // Then
        assertEquals(amount, money.getMontant());
    }

    @Test
    public void shouldSetDevise() {
        // Given
        Money money = new Money();
        Devise devise = Devise.YEN;
        // When
        money.setDevise(devise);
        // Then
        assertEquals(Devise.YEN, money.getDevise());
        assertEquals(Devise.EURO, new Money().getDevise());
    }

    @Test
    public void shouldSetDeviseWithNull() {
        // Given
        Money money = new Money();
        Devise devise = null;
        // then
        assertThrows(IllegalArgumentException.class, 
            // When
            () -> money.setDevise(devise)
        );
    }

}
