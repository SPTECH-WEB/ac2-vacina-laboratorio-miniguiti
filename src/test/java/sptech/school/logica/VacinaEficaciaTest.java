package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Vacina;
import school.sptech.provider.EficaciaArgumentProvider;
import school.sptech.util.VacinaUtil;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("3. TESTE DE EFICACIA - VACINA")
class VacinaEficaciaTest {

    @ParameterizedTest
    @ArgumentsSource(EficaciaArgumentProvider.class)
    @DisplayName("3.1. Deve retornar a descrição correta com base na eficácia")
    void testGetEficaciaDescricao(Double eficacia, String descricaoEsperada) throws Exception {
        // Usa reflection para criar uma instância de Vacina
        Vacina vacina = Vacina.class.getDeclaredConstructor().newInstance();

        // Usa reflection para definir o valor do campo 'eficacia'
        VacinaUtil.configurarCampo(vacina, "eficacia", eficacia);

        // Usa reflection para chamar o método 'getEficaciaDescricao'
        Method methodGetEficaciaDescricao = Vacina.class.getDeclaredMethod("getEficaciaDescricao");
        String descricaoRetornada = (String) methodGetEficaciaDescricao.invoke(vacina);

        // Compara a descrição retornada com a esperada
        assertEquals(descricaoEsperada, descricaoRetornada,
                "A descrição retornada não está correta para a eficácia: " + eficacia);
    }
}
