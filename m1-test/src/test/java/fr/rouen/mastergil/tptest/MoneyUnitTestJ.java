package fr.rouen.mastergil.tptest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

public class MoneyUnitTestJ {

    @Test
    public void constructeur_vide_objet_0_EURO() {
        int m = 0;
        Devise d = Devise.EURO;

        final Money money = new Money();
        
        assertThat(money.getDevise()).isEqualTo(d);
        assertThat(money.getMontant()).isEqualTo(m);
    }

    @Test
    public void constructeur_non_vide_objet_avec_valeurs() {
        int m = 20;
        Devise d = Devise.DOLLAR;

        final Money money = new Money(20, Devise.DOLLAR);
        assertThat(money.getDevise()).isEqualTo(d);
        assertThat(money.getMontant()).isEqualTo(m);
    }

    @Test
    public void getMontant_retourne_correct() {
        int m = 20;

        final Money money = new Money(20, Devise.EURO);

        assertThat(money.getMontant()).isEqualTo(m);
    }

    @Test
    public void getDevise_retourne_correct() {
        Devise d = Devise.EURO;

        final Money money = new Money(20, Devise.EURO);
        
        assertThat(money.getDevise()).isEqualTo(d);
    }

    @Test
    public void isPositif_vrai_superieur_zero() {
        final Money money = new Money(20, Devise.EURO);
        assertThat(money.isPositif()).isTrue();
    }

    @Test
    public void isPositif_vrai_egal_zero() {
        final Money money = new Money(0, Devise.EURO);
        assertThat(money.isPositif()).isTrue();
    }

    @Test
    public void isPositif_faux_inferieur_zero() {
        final Money money = new Money(-20, Devise.EURO);
        assertThat(money.isPositif()).isFalse();
    }

    @Test
    public void devise_non_nulle() {
        final Money money = new Money(20, Devise.EURO);
        assertThatThrownBy(() -> money.setDevise(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Devise doit être spécifiée");
    }
}
