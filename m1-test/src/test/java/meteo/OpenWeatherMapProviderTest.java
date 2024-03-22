package meteo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import fr.rouen.mastergil.tptest.meteo.OpenWeatherMapProvider;
import fr.rouen.mastergil.tptest.meteo.Prevision;

public class OpenWeatherMapProviderTest {

    @Test
    public void testGetForecastByCity() {
        // Given
        String city = "Paris,FR";
        OpenWeatherMapProvider weatherProvider = new OpenWeatherMapProvider();

        // When
        List<Prevision> previsions = weatherProvider.getForecastByCity(city);

        // Then
        assertNotNull(previsions);
        assertFalse(previsions.isEmpty());
        for (Prevision prevision : previsions) {
            assertNotNull(prevision.getDate());
            assertNotNull(prevision.getDescription());
            assertNotNull(prevision.getTempMin());
            assertNotNull(prevision.getTempMax());
            assertNotNull(prevision.getTempDay());
            assertNotNull(prevision.getTempNight());
        }
    }
}
