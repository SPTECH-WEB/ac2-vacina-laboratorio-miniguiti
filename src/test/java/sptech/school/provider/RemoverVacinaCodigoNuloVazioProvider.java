package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class RemoverVacinaCodigoNuloVazioProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(null, "Argumento não pode ser nulo ou vazio"),
                Arguments.of("", "Argumento não pode ser nulo ou vazio"),
                Arguments.of("   ", "Argumento não pode ser nulo ou vazio")
        );
    }
}
