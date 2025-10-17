package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.VacinaFactory;

import java.time.LocalDate;
import java.util.stream.Stream;

public class BuscarVacinaExistenteProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws ReflectiveOperationException {
        return Stream.of(
                Arguments.of("Hepa123", VacinaFactory.build("Hepa123", "Hepatite A", "Vírus Inativado", 199.99, 94.8, LocalDate.of(2013, 9, 17))),
                Arguments.of("MMR", VacinaFactory.build("MMR", "Sarampo, Caxumba, Rubéola", "Vírus Atenuado", 249.90, 83.5, LocalDate.of(2020, 12, 10))),
                Arguments.of("Polio", VacinaFactory.build("Polio", "Poliomelite", "Vírus Inativado", 299.90, 94.9, LocalDate.of(2017, 3, 3))),
                Arguments.of("GRP", VacinaFactory.build("GRP", "Gripe", "Subunidades", 219.90, 94.9, LocalDate.of(2018, 10, 26))),
                Arguments.of("HPV", VacinaFactory.build("HPV", "HPV", "Subunidades", 149.90, 94.7, LocalDate.of(2013, 6, 14))),
                Arguments.of("TET", VacinaFactory.build("TET", "Tetano", "Toxoides", 199.99, 94.4, LocalDate.of(2020, 11, 10))),
                Arguments.of("COV19", VacinaFactory.build("COV19", "Covid 19", "Ácidos Nucleicos", 89.99, 94.9, LocalDate.of(2020, 9, 17)))
        );
    }
}