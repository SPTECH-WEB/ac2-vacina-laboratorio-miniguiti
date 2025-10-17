package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.util.VacinaUtil;

import java.time.LocalDate;
import java.util.stream.Stream;

public class AdicionarVacinaValidaProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(VacinaUtil.configurarVacina("Hepa123", "Hepatite A", "Vírus Inativado", 199.99, 94.8, LocalDate.of(2013, 9, 17))),
                Arguments.of(VacinaUtil.configurarVacina("MMR", "Sarampo, Caxumba, Rubéola", "Vírus Atenuado", 249.90, 83.5, LocalDate.of(2020, 12, 10))),
                Arguments.of(VacinaUtil.configurarVacina("Polio", "Poliomelite", "Vírus Inativado", 299.90, 94.9, LocalDate.of(2017, 3, 3))),
                Arguments.of(VacinaUtil.configurarVacina("GRP", "Gripe", "Subunidades", 199.99, 94.7, LocalDate.of(2018, 10, 26))),
                Arguments.of(VacinaUtil.configurarVacina("HPV", "HPV", "Subunidades", 199.99, 94.6, LocalDate.of(2020, 6, 19))),
                Arguments.of(VacinaUtil.configurarVacina("TET", "Tetano", "Toxoides", 249.90, 94.5, LocalDate.of(2020, 4, 10))),
                Arguments.of(VacinaUtil.configurarVacina("COV19", "Covid 19", "Ácidos Nucleicos", 79.90, 94.9, LocalDate.of(2018, 4, 20)))
        );
    }
}
