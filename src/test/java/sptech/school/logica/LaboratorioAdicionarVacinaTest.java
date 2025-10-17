package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Laboratorio;
import school.sptech.Vacina;
import school.sptech.exception.VacinaInvalidaException;
import school.sptech.factory.LaboratorioFactory;
import school.sptech.provider.AdicionarVacinaInvalidaProvider;
import school.sptech.provider.AdicionarVacinaValidaProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("4. TESTE DE ADICIONAR VACINA - LABORATORIO")
class LaboratorioAdicionarVacinaTest {

    @ParameterizedTest
    @ArgumentsSource(AdicionarVacinaValidaProvider.class)
    @DisplayName("4.1. Quando a vacina é válida, deve adicioná-la corretamente à lista de vacinas")
    void testAdicionarVacinaComSucesso(Vacina vacinaValida) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Lab Teste", new ArrayList<>());

        // Usa reflection para chamar o método adicionarVacina
        Method methodAdicionarVacina = Laboratorio.class.getDeclaredMethod("adicionarVacina", Vacina.class);
        methodAdicionarVacina.invoke(laboratorio, vacinaValida);

        // Usa reflection para verificar se a vacina foi adicionada à lista de vacinas
        Method methodGetVacinas = Laboratorio.class.getDeclaredMethod("getVacinas");
        List<Vacina> vacinas = (List<Vacina>) methodGetVacinas.invoke(laboratorio);

        assertTrue(vacinas.contains(vacinaValida), "A vacina deve ser adicionada com sucesso à lista de vacinas.");
    }

    @ParameterizedTest
    @ArgumentsSource(AdicionarVacinaInvalidaProvider.class)
    @DisplayName("4.2. Quando a vacina é inválida, deve lançar ArgumentoInvalidoException")
    void testAdicionarVacinaComFalha(Vacina vacinaInvalida, String mensagemEsperada) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", List.of());

        // Usa reflection para chamar o método adicionarVacina
        Method methodAdicionarVacina = Laboratorio.class.getDeclaredMethod("adicionarVacina", Vacina.class);

        // Captura a exceção e verifica se é ArgumentoInvalidoException
        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodAdicionarVacina.invoke(laboratorio, vacinaInvalida),
                "Deveria lançar uma InvocationTargetException com ArgumentoInvalidoException como causa."
        );

        // Obtém a causa da InvocationTargetException
        Throwable causa = invocationTargetException.getCause();

        assertTrue(causa instanceof VacinaInvalidaException, "A causa deve ser VacinaInvalidaException.");

    }
}
