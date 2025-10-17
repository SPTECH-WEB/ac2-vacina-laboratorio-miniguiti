package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class EficaciaArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(94.5, "EXCELENTE"),
                Arguments.of(100.0, "EXCELENTE"),
                Arguments.of(84.0, "BOM"),
                Arguments.of(83.5, "BOM"),
                Arguments.of(73.0, "REGULAR"),
                Arguments.of(62.5, "REGULAR"),
                Arguments.of(22.0, "RUIM"),
                Arguments.of(15.0, "RUIM")
        );
    }
}