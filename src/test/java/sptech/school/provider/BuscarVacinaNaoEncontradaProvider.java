package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BuscarVacinaNaoEncontradaProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("TETRA"),
                Arguments.of("GRIPE"),
                Arguments.of("HEPA321"),
                Arguments.of("SABIN"),
                Arguments.of("VARIOLA"),
                Arguments.of("DIFTERIA"),
                Arguments.of("DENGUE")
        );
    }
}
