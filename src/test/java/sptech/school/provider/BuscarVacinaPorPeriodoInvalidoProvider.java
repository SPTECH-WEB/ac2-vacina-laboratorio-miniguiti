package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.stream.Stream;

public class BuscarVacinaPorPeriodoInvalidoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(null, LocalDate.of(2020, 12, 31), "Data de início e data de fim não podem ser nulas"),
                Arguments.of(LocalDate.of(2020, 1, 1), null, "Data de início e data de fim não podem ser nulas"),
                Arguments.of(LocalDate.of(2021, 12, 31), LocalDate.of(2020, 1, 1), "Data de início não pode ser maior que a data de fim")
        );
    }
}
