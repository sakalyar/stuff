package fr.rouen.mastergil.tptest;

import java.text.MessageFormat;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class BankAccountUnitTest {
    
    @Test
    public void creerCompteVideTest() {
        // Givenwhen
        BankAccount b = new BankAccount();
        // When
        b.creerCompte();
        // Then
        // getDevise/Montrant
        assertThat(b).isNotNull();
    }

    @Test
    public void shouldCreerCompteNonVide() {
        // Given
        int m = 30;
        // When 
        BankAccount b = new BankAccount();
        b.creerCompte(30, Devise.YEN);

        // Then
        assertThat(b.solde).isNotNull();
        assertThat(b.solde.getMontant()).isEqualTo(m);
    }

    @Test
    public void shouldCheckConsulterSoldeBeEqual() {
        // Given
        int m = 30;
        Devise d = Devise.EURO;
        // When
        BankAccount b = new BankAccount();
        b.creerCompte(m, d);
        // Then
        assertThat(b.consulterSolde()).isEqualTo(MessageFormat.format("Votre solde actuel est de {0} {1}", b.solde.getMontant(), d));
        
    }

    @Test
    public void shouldDeposerArgentSucces() {
        // Given
        int m1 = 50, m2 = 30;
        Devise d = Devise.DOLLAR;
        //When
        BankAccount b = new BankAccount();
        b.creerCompte(m1, d);
        b.deposerArgent(m2);

        // Then
        assertThat(b.consulterSolde()).isEqualTo(MessageFormat.format("Votre solde actuel est de {0} {1}", m1+m2, d));
    }

    @Test
    public void shoudRetirerArgentSucces() {
        int m1 = 50, m2 = 30;
        Devise d = Devise.DOLLAR;
        //When
        BankAccount b = new BankAccount();
        b.creerCompte(m1, d);
        b.retirerArgent(m2);
        // Then
        assertThat(b.consulterSolde()).isEqualTo(MessageFormat.format("Votre solde actuel est de {0} {1}", m1-m2, d));
    }

    @Test
    public void shouldSoldeBePositive() {
        int m1 = 50, m2 = 20;
        Devise d = Devise.DOLLAR;
        //When
        BankAccount b = new BankAccount();
        b.creerCompte(m1, d);
        b.retirerArgent(m2);
        // Then
        assertThat(b.isSoldePositif()).isTrue();
        assertThat(b.solde.getMontant()).isNotZero();
    }
  
    @Test
    public void shouldSoldeBeNegatif() {
        int m1 = 50, m2 = 80;
        Devise d = Devise.DOLLAR;
        //When
        BankAccount b = new BankAccount();
        b.creerCompte(m1, d);
        b.retirerArgent(m2);
        // Then
        System.out.println(b.consulterSolde());
        assertThat(b.isSoldePositif()).isFalse();
    }

    @Test
    public void shoudSoldeBeZero() {
        int m1 = 50, m2 = 50;
        Devise d = Devise.DOLLAR;
        //When
        BankAccount b = new BankAccount();
        b.creerCompte(m1, d);
        b.retirerArgent(m2);
        // Then
        assertThat(b.solde.getMontant()).isZero();
    }

}
