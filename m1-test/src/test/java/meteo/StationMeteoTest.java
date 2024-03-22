package meteo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import fr.rouen.mastergil.tptest.meteo.IWeatherProvider;
import fr.rouen.mastergil.tptest.meteo.OpenWeatherMapProvider;
import fr.rouen.mastergil.tptest.meteo.Prevision;

public class StationMeteoTest {

    @Test
    public void ShouldReturnTheSameForecast() {
        // Given
        IWeatherProvider weatherProvider = new OpenWeatherMapProvider(); // Use real implementation
        List<Prevision> actualPrevisions;
        
        // When
        actualPrevisions = weatherProvider.getForecastByCity("Paris,FR");

        // Then
        assertThat(actualPrevisions).isNotEmpty();
    }

    @Test
    public void ShouldReturnEmptyForecast() {
        // Given
        IWeatherProvider weatherProvider = new OpenWeatherMapProvider(); // Use real implementation
        List<Prevision> actualPrevisions = null;

        // When
        try {
            actualPrevisions = weatherProvider.getForecastByCity("Manralan,MR");
        } catch (Exception ex) {
        }

        // Then
        assertThat(actualPrevisions).isNull();
    }
}
