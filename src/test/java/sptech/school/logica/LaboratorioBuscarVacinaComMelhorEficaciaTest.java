package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Laboratorio;
import school.sptech.Vacina;
import school.sptech.exception.VacinaNaoEncontradaException;
import school.sptech.factory.LaboratorioFactory;
import school.sptech.provider.BuscarMelhorEficaciaComSucessoProvider;
import school.sptech.provider.BuscarMelhorEficaciaEmpateProvider;
import school.sptech.provider.BuscarMelhorEficaciaSemVacinasProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static school.sptech.util.VacinaUtil.verificarAtributoViaReflection;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("7. TESTE DE BUSCAR VACINA COM MELHOR EFICACIA - LABORATORIO")
class LaboratorioBuscarVacinaComMelhorEficaciaTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarMelhorEficaciaComSucessoProvider.class)
    @DisplayName("7.1. Quando existem vacinas, deve retornar a vacina com a melhor eficácia")
    void testBuscarMelhorEficaciaComSucesso(List<Vacina> vacinas, Vacina vacinaEsperada) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio com vacinas
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Lab Teste", vacinas);

        // Usa reflection para chamar o método buscarVacinaComMelhorEficacia
        Method methodBuscarMelhorEficacia = Laboratorio.class.getDeclaredMethod("buscarVacinaComMelhorEficacia");
        Vacina vacinaEncontrada = (Vacina) methodBuscarMelhorEficacia.invoke(laboratorio);

        // Valida os atributos da vacina encontrada via reflection
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "codigo", "O código da vacina deve ser o esperado.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "nome", "O nome da vacina deve ser o esperado.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "tipo", "O tipo da vacina deve ser o esperado.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "preco", "O preço da vacina deve ser o esperado.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "eficacia", "A eficácia da vacina deve ser a esperada.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "dataLancamento", "A data de lançamento da vacina deve ser a esperada.");
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarMelhorEficaciaSemVacinasProvider.class)
    @DisplayName("7.2. Quando não existem vacinas, deve lançar VacinaNaoEncontradaException")
    void testBuscarMelhorEficaciaSemVacinas(List<Vacina> vacinasVazias, String mensagemEsperada) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio sem vacinas
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", vacinasVazias);

        // Usa reflection para chamar o método buscarVacinaComMelhorEficacia
        Method methodBuscarMelhorEficacia = Laboratorio.class.getDeclaredMethod("buscarVacinaComMelhorEficacia");

        // Captura a exceção e verifica se é VacinaNaoEncontradaException
        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodBuscarMelhorEficacia.invoke(laboratorio),
                "Deveria lançar uma InvocationTargetException com VacinaNaoEncontradaException como causa."
        );

        // Obtém a causa da InvocationTargetException
        Throwable causa = invocationTargetException.getCause();
        assertTrue(causa instanceof VacinaNaoEncontradaException, "A causa deve ser VacinaNaoEncontradaException.");
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarMelhorEficaciaEmpateProvider.class)
    @DisplayName("7.3. Quando duas vacinas têm a mesma eficácia, deve retornar a mais recente")
    void testBuscarMelhorEficaciaComEmpate(List<Vacina> vacinas, Vacina vacinaEsperada) throws Exception {
        // Usa a LaboratorioFactory para criar uma instância de Laboratorio com vacinas
        Laboratorio laboratorio = (Laboratorio) LaboratorioFactory.build("Laboratorio Teste", vacinas);

        // Usa reflection para chamar o método buscarVacinaComMelhorEficacia
        Method methodBuscarMelhorEficacia = Laboratorio.class.getDeclaredMethod("buscarVacinaComMelhorEficacia");
        Vacina vacinaEncontrada = (Vacina) methodBuscarMelhorEficacia.invoke(laboratorio);

        // Valida os atributos da vacina encontrada via reflection
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "codigo", "O código da vacina deve ser o esperado.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "nome", "O nome da vacina deve ser o esperado.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "tipo", "O tipo da vacina deve ser o esperado.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "preco", "O preço da vacina deve ser o esperado.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "eficacia", "A eficacia da vacina deve ser a esperada.");
        verificarAtributoViaReflection(vacinaEsperada, vacinaEncontrada, "dataLancamento", "A data de lançamento da vacina deve ser a esperada.");
    }
}
