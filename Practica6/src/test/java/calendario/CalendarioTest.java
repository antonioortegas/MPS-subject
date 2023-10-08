package calendario;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

import static es.uma.informatica.mps.Calendario.diaSemana;
import static java.time.DayOfWeek.*;
import static org.assertj.core.api.Assertions.*;

public class CalendarioTest {

    /*

     */

    @Nested
    @DisplayName("Valid dates")
    class ValidDates {

        @Test
        @DisplayName("Anyo 2023 (no bisiesto, no divisible entre 4)")
        @Tag("21")
        public void shouldAnyoNotDivisibleBy4ReturnCorrectDayOfWeek() {
            DayOfWeek actualDay = diaSemana(1, 3, 2023);
            assertThat(actualDay)
                    .isEqualTo(WEDNESDAY);
        }

        @Test
        @DisplayName("Anyo 2100 (no bisiesto, divisible entre 4 y entre 100 pero no entre 400)")
        @Tag("22")
        public void shouldAnyoDivisibleBy4AndBy100ButNotBy400ReturnCorrectDayOfWeek() {
            DayOfWeek actualDay = diaSemana(1, 3, 2100);
            assertThat(actualDay)
                    .isEqualTo(MONDAY);
        }

        @Test
        @DisplayName("Anyo 1004 (bisiesto) 1/1 y 29/2")
        @Tag("1")
        @Tag("5")
        @Tag("6")
        @Tag("14")
        @Tag("17")
        @Tag("19")
        public void shouldAnyo1004Mes1Dia1AndMes2Dia29ReturnCorrectDayOfWeek() {
            DayOfWeek actualDay = diaSemana(1, 1, 1004);
            assertThat(actualDay)
                    .isEqualTo(SATURDAY);

            assertThatCode(() -> {
                diaSemana(29, 2, 1004);
            })
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("Anyo 1600 (bisiesto) 30/4 y 31/3")
        @Tag("2")
        @Tag("7")
        @Tag("8")
        @Tag("14")
        @Tag("17")
        @Tag("20")
        public void shouldAnyo1600Mes4Dia30AndMes3Dia31ReturnCorrectDayOfWeek() {
            DayOfWeek actualDay = diaSemana(30, 4, 1600);
            assertThat(actualDay)
                    .isEqualTo(SUNDAY);

            actualDay = diaSemana(31, 3, 1600);
            assertThat(actualDay)
                    .isEqualTo(FRIDAY);
        }
    }

    @Nested
    @DisplayName("Invalid dates")
    class Errors {

        @Test
        @DisplayName("Dia anterior al 1 de marzo del anyo 4")
        @Tag("3")
        public void shouldDiaLessThan1March4RiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(15, 2, 4);
            })
                    .isInstanceOf(Exception.class);
            assertThatThrownBy(() -> {
                diaSemana(29, 2, 4);
            })
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("Dias entre el 5 y el 14 de octubre de 1582")
        @Tag("4")
        public void shouldDiaBetween5And14October1582RiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(5, 10, 1582);
            })
                    .isInstanceOf(Exception.class);
            assertThatThrownBy(() -> {
                diaSemana(14, 10, 1582);
            })
                    .isInstanceOf(Exception.class);
            assertThatThrownBy(() -> {
                diaSemana(10, 10, 1582);
            })
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("Dia menor que 1")
        @Tag("9")
        public void shouldDiaLessThan1RiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(0, 5, 2023);
            })
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("Dia mayor que 28 en febrero no bisiesto")
        @Tag("10")
        public void shouldDiaGreaterThan28InFebruaryNotLeapYearRiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(29, 2, 2021);
            })
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("Dia mayor que 29 en febrero bisiesto")
        @Tag("11")
        public void shouldDiaGreaterThan29InFebruaryLeapYearRiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(30, 2, 2020);
            })
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("Dia mayor que 30 en abril")
        @Tag("12")
        public void shouldDiaGreaterThan30InAprilRiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(31, 4, 2023);
            })
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("Dia mayor que 31")
        @Tag("13")
        public void shouldDiaGreaterThan31RiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(32, 5, 2023);
            })
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("Mes menor que 1")
        @Tag("15")
        public void shouldMesLessThan1RiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(15, 0, 2023);
            })
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("Mes mayor que 12")
        @Tag("16")
        public void shouldMesGreaterThan12RiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(15, 13, 2023);
            })
                    .isInstanceOf(Exception.class);
        }

        @Test
        @DisplayName("Anyo < 4")
        @Tag("18")
        public void shouldAnyoLessThan4RiseAnException () {
            assertThatThrownBy(() -> {
                diaSemana(15, 5, 3);
            })
                    .isInstanceOf(Exception.class);
        }

    }
}
