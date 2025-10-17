package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Laboratorio;
import school.sptech.Vacina;
import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.VacinaNaoEncontradaException;
import school.sptech.factory.LaboratorioFactory;
import school.sptech.provider.RemoverVacinaCodigoInvalidoProvider;
import school.sptech.provider.RemoverVacinaCodigoNuloVazioProvider;
import school.sptech.provider.RemoverVacinaCodigoValidoProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("6. TESTE DE REMOVER VACINA POR CÓDIGO - LABORATORIO")
class LaboratorioRemoverVacinaPorCodigoTest {

    @ParameterizedTest
    @ArgumentsSource(RemoverVacinaCodigoValidoProvider.class)
    @DisplayName("6.1. Quando o código da vacina é válido, deve removê-la corretamente da lista de vacinas")
    void testRemoverVacinaComSucesso(String codigoValido, Vacina vacinaRemovidaEsperada) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio com vacinas
        List<Vacina> vacinas = new ArrayList<>(List.of(vacinaRemovidaEsperada));
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", vacinas);

        // Usa reflection para chamar o método removerVacinaPorCodigo
        Method methodRemoverVacina = Laboratorio.class.getDeclaredMethod("removerVacinaPorCodigo", String.class);
        methodRemoverVacina.invoke(laboratorio, codigoValido);

        // Usa reflection para verificar se a vacina foi removida da lista de vacinas
        Method methodGetVacinas = Laboratorio.class.getDeclaredMethod("getVacinas");
        List<Vacina> vacinasAtualizadas = (List<Vacina>) methodGetVacinas.invoke(laboratorio);

        assertFalse(vacinasAtualizadas.contains(vacinaRemovidaEsperada), "A vacina deve ser removida com sucesso da lista de vacinas.");
    }

    @ParameterizedTest
    @ArgumentsSource(RemoverVacinaCodigoInvalidoProvider.class)
    @DisplayName("6.2. Quando a vacina não for encontrada pelo código, deve lançar VacinaNaoEncontradaException")
    void testRemoverVacinaNaoEncontrada(String codigoInvalido, String mensagemEsperada) throws Exception {
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", List.of());

        Method methodRemoverVacina = Laboratorio.class.getDeclaredMethod("removerVacinaPorCodigo", String.class);

        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodRemoverVacina.invoke(laboratorio, codigoInvalido),
                "Deveria lançar uma InvocationTargetException com VacinaNaoEncontradaException como causa."
        );

        Throwable causa = invocationTargetException.getCause();
        assertTrue(causa instanceof VacinaNaoEncontradaException, "A causa deve ser VacinaNaoEncontradaException.");
    }

    @ParameterizedTest
    @ArgumentsSource(RemoverVacinaCodigoNuloVazioProvider.class)
    @DisplayName("6.3. Quando o código é nulo, vazio ou em branco, deve lançar ArgumentoInvalidoException")
    void testRemoverVacinaComCodigoInvalido(String codigoInvalido, String mensagemEsperada) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio sem vacinas
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", List.of());

        // Usa reflection para chamar o método removerJogoPorCodigo
        Method methodRemoverVacina = Laboratorio.class.getDeclaredMethod("removerVacinaPorCodigo", String.class);

        // Captura a exceção e verifica se é ArgumentoInvalidoException
        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodRemoverVacina.invoke(laboratorio, codigoInvalido),
                "Deveria lançar uma InvocationTargetException com ArgumentoInvalidoException como causa."
        );

        // Obtém a causa da InvocationTargetException
        Throwable causa = invocationTargetException.getCause();
        assertTrue(causa instanceof ArgumentoInvalidoException, "A causa deve ser ArgumentoInvalidoException.");

    }
}
