package fr.rouen.mastergil.tptest;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BankAccountWithDAOUnitTest {
    // Given
    JdbcDAO bankDao;

    @Test
    public void shouldCreerCompteVide() {       
        // When, Then
        assertThatThrownBy(() -> {
            bankDao.creerCompte();
        }).isNotInstanceOf(SQLException.class);
        assertThatThrownBy(() -> {
            bankDao.creerCompte();
        }).isNotInstanceOf(ConnectException.class);
    }


    // 

    @Test
    public void shouldCreerCompteNonVide() {
        // Given
        int montant = 50;
        Devise devise = Devise.DOLLAR;

        assertThatThrownBy(() -> {
            bankDao.creerCompte(montant, devise);
        }).isNotInstanceOf(SQLException.class);
        assertThatThrownBy(() -> {
            bankDao.creerCompte(montant, devise);
        }).isNotInstanceOf(ConnectException.class);
        // bankDao.creerCompte(montant, devise);
        assertEquals(bankDao.getSolde().getMontant(), montant);
        assertEquals(bankDao.getSolde().getDevise(), devise);
    }

    // @Test
    // public String consulterSolde() throws SQLException, ConnectException {
    //     setupConnection();
    //     Money solde = bankDao.getSolde();
    //     return MessageFormat.format("Votre solde actuel est de {0} {1}", solde.getMontant(), solde.getDevise().name());
    // }

    // @Test
    // public Money deposerArgent(int montant) throws SQLException, ConnectException {
    //     setupConnection();
    //     Money solde = bankDao.getSolde();
    //     solde.setMontant(solde.getMontant() + montant);
    //     bankDao.saveMoney(solde);
    //     return solde;
    // }

    // public Money retirerArgent(int montant) throws SQLException, ConnectException {
    //     setupConnection();
    //     Money solde = bankDao.getSolde();
    //     solde.setMontant(solde.getMontant() - montant);
    //     bankDao.saveMoney(solde);
    //     return solde;
    // }

    // public boolean isSoldePositif() throws SQLException, ConnectException {
    //     setupConnection();
    //     Money solde = bankDao.getSolde();
    //     return solde.isPositif();
    // }

    // private void setupConnection() throws SQLException, ConnectException {
    //     Connection connection = bankDao.setUpConnection();
    //     connection.setAutoCommit(true);
    //     if (connection.isReadOnly() || connection.isClosed()) {
    //         throw new ConnectException("Impossible d'avoir accès à la base de données");
    //     }
    // }
}


// Examen:
// Dernier tp + TP4 optionnel
// INTERDIT: ChatGPT
// Pas utiliser chat, messenger, mais on a droit a l'Internet