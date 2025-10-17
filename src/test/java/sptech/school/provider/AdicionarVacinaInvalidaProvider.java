package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.stream.Stream;

import static school.sptech.util.VacinaUtil.configurarVacina;

public class AdicionarVacinaInvalidaProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(

                // Vacina nulo
                Arguments.of(null, "Vacina não pode ser nulo"),

                // Código inválido
                Arguments.of(configurarVacina(null, "Hepatite A", "Vírus inativado", 199.99, 94.9, LocalDate.of(2018, 10, 26)), "Código da vacina não pode ser nulo ou vazio"),
                Arguments.of(configurarVacina("", "Hepatite A", "Vírus inativado", 199.99, 94.9, LocalDate.of(2018, 10, 26)), "Código da vacina não pode ser nulo ou vazio"),

                // Nome inválido
                Arguments.of(configurarVacina("Hepa123", null, "Vírus inativado", 199.99, 94.9, LocalDate.of(2018, 10, 26)), "Nome da vacina não pode ser nulo ou vazio"),
                Arguments.of(configurarVacina("Hepa123", "", "Vírus inativado", 199.99, 94.9, LocalDate.of(2018, 10, 26)), "Nome da vacina não pode ser nulo ou vazio"),

                // Tipo inválido
                Arguments.of(configurarVacina("Hepa123", "Hepatite A", null, 199.99, 94.9, LocalDate.of(2018, 10, 26)), "Tipo da vacina não pode ser nulo ou vazio"),
                Arguments.of(configurarVacina("Hepa123", "Hepatite A", "", 199.99, 94.9, LocalDate.of(2018, 10, 26)), "Tipo da vacina não pode ser nulo ou vazio"),

                // Preço inválido
                Arguments.of(configurarVacina("Hepa123", "Hepatite A", "Vírus inativado", 0.0, 94.9, LocalDate.of(2018, 10, 26)), "Preço da vacina não pode ser nulo ou menor ou igual a zero"),
                Arguments.of(configurarVacina("Hepa123", "Hepatite A", "Vírus inativado", -100.0, 94.9, LocalDate.of(2018, 10, 26)), "Preço da vacina não pode ser nulo ou menor ou igual a zero"),

                // Eficácia inválida
                Arguments.of(configurarVacina("Hepa123", "Hepatite A", "Vírus inativado", 199.99, 102.0, LocalDate.of(2018, 10, 26)), "Eficácia da vacina não pode ser nulo ou menor que zero ou maior que cem"),
                Arguments.of(configurarVacina("Hepa123", "Hepatite A", "Vírus inativado", 199.99, -1.0, LocalDate.of(2018, 10, 26)), "Eficácia da vacina não pode ser nulo ou menor que zero ou maior que cem"),

                // Data de lançamento inválida
                Arguments.of(configurarVacina("Hepa123", "Hepatite A", "Vírus inativado", 199.99, 94.9, LocalDate.now().plusDays(1)), "Data de lançamento da vacina não pode ser maior que a data atual"),
                Arguments.of(configurarVacina("Hepa123", "Hepatite A", "Vírus inativado", 199.99, 94.9, null), "Data de lançamento da vacina não pode ser nula")
        );
    }


}
