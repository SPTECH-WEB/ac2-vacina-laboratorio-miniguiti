package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.VacinaFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class BuscarVacinaPorPeriodoValidoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(
                        LocalDate.of(2015, 1, 1), LocalDate.of(2020, 12, 31),
                        List.of(
                                VacinaFactory.build("Hepa123", "Hepatite A", "Vírus Inativado", 199.99, 94.8, LocalDate.of(2013, 9, 17)),
                                VacinaFactory.build("Polio", "Poliomelite", "Vírus Inativado", 299.90, 94.9, LocalDate.of(2017, 3, 3)),
                                VacinaFactory.build("MMR", "Sarampo, Caxumba, Rubéola", "Vírus Atenuado", 249.90, 83.5, LocalDate.of(2020, 12, 10))
                        ),
                        List.of(
                                VacinaFactory.build("Polio", "Poliomelite", "Vírus Inativado", 299.90, 94.9, LocalDate.of(2017, 3, 3)),
                                VacinaFactory.build("MMR", "Sarampo, Caxumba, Rubéola", "Vírus Atenuado", 249.90, 83.5, LocalDate.of(2020, 12, 10))
                        )
                )
        );
    }
}
