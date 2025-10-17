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
import school.sptech.factory.VacinaFactory;
import school.sptech.provider.BuscarVacinaExistenteProvider;
import school.sptech.provider.BuscarVacinaInvalidaProvider;
import school.sptech.provider.BuscarVacinaNaoEncontradaProvider;
import school.sptech.util.VacinaUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("5. TESTE DE BUSCAR VACINA POR CÓDIGO - LABORATORIO")
public class LaboratorioBuscarVacinaPorCodigoTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarVacinaExistenteProvider.class)
    @DisplayName("5.1. Quando o código é válido, deve retornar a vacina corretamente")
    void testBuscarVacinaPorCodigoSucesso(String codigo, Vacina vacinaEsperada) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio com as vacinas fornecidas pelo provider
        List<Vacina> vacinas = new ArrayList<>();
        vacinas.add(vacinaEsperada);  // Reutiliza o objeto Vacina diretamente do provider

        // Cria a instância do Laboratorio com as vacinas
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", vacinas);

        // Usa reflection para chamar o método buscarVacinaPorCodigo
        Method method = Laboratorio.class.getDeclaredMethod("buscarVacinaPorCodigo", String.class);
        Vacina vacinaEncontrada = (Vacina) method.invoke(laboratorio, codigo);

        // Valida os atributos da vacina encontrada via reflection
        VacinaUtil.verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "codigo", "O código da vacina deve ser o esperado.");
        VacinaUtil.verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "nome", "O nome da vacina deve ser o esperado.");
        VacinaUtil.verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "tipo", "O tipo da vacina deve ser o esperado.");
        VacinaUtil.verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "preco", "O preço da vacina deve ser o esperado.");
        VacinaUtil.verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "eficacia", "A eficacia da vacina deve ser a esperada.");
        VacinaUtil.verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "dataLancamento", "A data de lançamento da vacina deve ser a esperada.");
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarVacinaInvalidaProvider.class)
    @DisplayName("5.2. Quando o código é inválido, deve lançar ArgumentoInvalidoException")
    void testBuscarVacinaPorCodigoInvalido(String codigoInvalido) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio sem vacinas
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", new ArrayList<>());

        // Usa reflection para chamar o método buscarVacinaPorCodigo
        Method method = Laboratorio.class.getDeclaredMethod("buscarVacinaPorCodigo", String.class);

        // Verifica se ArgumentoInvalidoException é lançada
        assertThrows(InvocationTargetException.class, () -> {
            try {
                method.invoke(laboratorio, codigoInvalido);
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof ArgumentoInvalidoException) {
                    throw e;
                } else {
                    throw new RuntimeException("Exceção inesperada.", e);
                }
            }
        }, "Deveria lançar ArgumentoInvalidoException para um código inválido.");
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarVacinaNaoEncontradaProvider.class)
    @DisplayName("5.3. Quando a vacina não for encontrada, deve lançar VacinaNaoEncontradaException")
    void testBuscarVacinaNaoEncontrada(String codigoInexistente) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio com apenas 1 vacina
        List<Vacina> vacinas = new ArrayList<>();
        vacinas.add((Vacina) VacinaFactory.build("Hepa123", "Hepatite A", "Vírus Inativado", 199.99, 94.8, LocalDate.of(2013, 9, 17)));

        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", vacinas);

        // Usa reflection para chamar o método buscarVacinaPorCodigo
        Method method = Laboratorio.class.getDeclaredMethod("buscarVacinaPorCodigo", String.class);

        // Verifica se VacinaNaoEncontradaException é lançada
        assertThrows(InvocationTargetException.class, () -> {
            try {
                method.invoke(laboratorio, codigoInexistente);
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof VacinaNaoEncontradaException) {
                    throw e;
                } else {
                    throw new RuntimeException("Exceção inesperada.", e);
                }
            }
        }, "Deveria lançar VacinaNaoEncontradaException quando a vacina não for encontrada.");
    }
}
