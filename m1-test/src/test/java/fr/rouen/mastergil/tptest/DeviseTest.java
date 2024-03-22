package fr.rouen.mastergil.tptest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeviseTest {

    @Test
    public void checkDeviseValueOf() {
        // Given
        String deviseString = "YEN";

        // When
        Devise devise = Devise.valueOf(deviseString);

        // Then
        assertEquals(Devise.YEN, devise);
    }

    @Test
    public void checkDeviseValueOfInvalid() {
        // Given
        String invalidDeviseString = "INVALID";

        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            // When
            Devise.valueOf(invalidDeviseString);
        });
        
    }
}
