package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Laboratorio;
import school.sptech.Vacina;
import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.factory.LaboratorioFactory;
import school.sptech.provider.BuscarVacinaPorPeriodoInvalidoProvider;
import school.sptech.provider.BuscarVacinaPorPeriodoValidoProvider;
import school.sptech.util.VacinaUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("8. TESTE DE BUSCAR VACINA POR PERÍODO - LABORATORIO")
class LaboratorioBuscarVacinaPorPeriodoTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarVacinaPorPeriodoValidoProvider.class)
    @DisplayName("8.1. Quando as datas são válidas, deve retornar as vacinas dentro do período especificado")
    void testBuscarVacinaPorPeriodoValido(LocalDate dataInicio, LocalDate dataFim, List<Vacina> vacinas, List<Vacina> vacinasEsperadas) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio com vacinas
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", vacinas);

        // Usa reflection para chamar o método buscarVacinaPorPeriodo
        Method methodBuscarVacinaPorPeriodo = Laboratorio.class.getDeclaredMethod("buscarVacinaPorPeriodo", LocalDate.class, LocalDate.class);
        List<Vacina> vacinasEncontradas = (List<Vacina>) methodBuscarVacinaPorPeriodo.invoke(laboratorio, dataInicio, dataFim);

        // Verifica se as vacinas retornadas são as esperadas
        assertEquals(vacinasEsperadas.size(), vacinasEncontradas.size(), "O número de vacinas encontradas deve ser o esperado.");

        for (int i = 0; i < vacinasEsperadas.size(); i++) {
            Vacina esperada = vacinasEsperadas.get(i);
            Vacina encontrada = vacinasEncontradas.get(i);

            // Valida os atributos das vacinass encontrados via reflection
            VacinaUtil.verificarAtributoViaReflection(esperada, encontrada, "codigo", "O código da vacina deve ser o esperado.");
            VacinaUtil.verificarAtributoViaReflection(esperada, encontrada, "nome", "O nome da vacina deve ser o esperado.");
        }
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarVacinaPorPeriodoInvalidoProvider.class)
    @DisplayName("8.2. Quando as datas são inválidas, deve lançar ArgumentoInvalidoException")
    void testBuscarVacinaPorPeriodoInvalido(LocalDate dataInicio, LocalDate dataFim, String mensagemEsperada) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", List.of());

        // Usa reflection para chamar o método buscarVacinaPorPeriodo
        Method methodBuscarVacinaPorPeriodo = Laboratorio.class.getDeclaredMethod("buscarVacinaPorPeriodo", LocalDate.class, LocalDate.class);

        // Captura a exceção e verifica se é ArgumentoInvalidoException
        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodBuscarVacinaPorPeriodo.invoke(laboratorio, dataInicio, dataFim),
                "Deveria lançar uma InvocationTargetException com ArgumentoInvalidoException como causa."
        );

        // Obtém a causa da InvocationTargetException
        Throwable causa = invocationTargetException.getCause();
        assertTrue(causa instanceof ArgumentoInvalidoException, "A causa deve ser ArgumentoInvalidoException.");
    }
}
