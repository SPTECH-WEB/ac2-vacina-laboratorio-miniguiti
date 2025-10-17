package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.VacinaFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class BuscarMelhorEficaciaEmpateProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(
                        List.of(
                                VacinaFactory.build("Hepa123", "Hepatite A", "Vírus Inativado", 199.99, 94.9, LocalDate.of(2013, 9, 17)),
                                VacinaFactory.build("GRP", "Gripe", "Subunidades", 219.90, 94.9, LocalDate.of(2018, 10, 26))
                        ),
                        VacinaFactory.build("GRP", "Gripe", "Subunidades", 219.90, 94.9, LocalDate.of(2018, 10, 26))
                )
        );
    }
}