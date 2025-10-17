package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BuscarVacinaInvalidaProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("   "),
                Arguments.of((String) null),
                Arguments.of("     "),
                Arguments.of("	"),
                Arguments.of("\n"),
                Arguments.of("\t")
        );
    }
}
