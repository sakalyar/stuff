package fr.rouen.mastergil.tptest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

public class MoneyTestJ {

    @Test
    public void shouldInitiateDefaultMoney() {
        // Given
        int defaultAmount = 0;
        Devise defaultDevise = Devise.EURO;
        // When
        Money money = new Money();
        // Then
        assertThat(defaultAmount).isEqualTo(money.getMontant());
        assertThat(defaultDevise).isEqualTo(money.getDevise());
     }

    @Test
    public void shouldInitializeConstructor() {
        // Given
        int defaultAmount = 100;
        Devise defaultDevise = Devise.EURO;
        // When
        Money money = new Money(defaultAmount, defaultDevise);
        // Then
        assertThat(defaultAmount).isEqualTo(money.getMontant());
        assertThat(defaultDevise).isEqualTo(money.getDevise());
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
        assertThat(montant).isEqualTo(money.getMontant());
        assertThat(devise).isEqualTo(money.getDevise());
    }

    @Test
    public void shouldCheckPositive() {
        // Given
        int defaultAmount = 100;
        Devise defaultDevise = Devise.EURO;
        // When
        Money money = new Money(defaultAmount, defaultDevise);
        // Then
        assertThat(money.isPositif()).isTrue();
        assertThat(money.isPositif()).isTrue();
    }

    @Test
    public void shouldCheckNegative() {
        // Given
        int defaultAmount = -100;
        Devise defaultDevise = Devise.EURO;
        // When
        Money money = new Money(defaultAmount, defaultDevise);
        // Then
        assertThat(money.isPositif()).isFalse();
    }

    @Test
    public void shouldSetPositiveMontant() {
        // Given
        Money money = new Money();
        int amount = 2000;
        // When
        money.setMontant(amount);
        // Then
        assertThat(amount).isEqualTo(money.getMontant());
    }

    @Test
    public void shouldSetNegativeMontant() {
        // Given
        Money money = new Money();
        int amount = -2000;
        // When
        money.setMontant(amount);
        // Then
        assertThat(amount).isEqualTo(money.getMontant());
    }

    @Test
    public void shouldSetDevise() {
        // Given
        Money money = new Money();
        Devise devise = Devise.YEN;
        // When
        money.setDevise(devise);
        // Then
        assertThat(Devise.YEN).isEqualTo(money.getDevise());
        assertThat(Devise.EURO).isEqualTo(new Money().getDevise());
    }

    @Test
    public void shouldSetDeviseWithNull() {
        // Given
        Money money = new Money();
        Devise devise = null;
        // Then
        assertThatThrownBy(() -> {
            // When
            money.setDevise(devise);
        }
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
