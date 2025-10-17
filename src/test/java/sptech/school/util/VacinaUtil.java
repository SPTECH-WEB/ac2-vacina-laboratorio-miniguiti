package school.sptech.util;

import school.sptech.Vacina;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacinaUtil {

    public static void verificarAtributoViaReflection(Vacina esperada, Vacina encontrada, String nomeCampo, String mensagem) throws Exception {
        Field campoEsperado = esperada.getClass().getDeclaredField(nomeCampo);
        campoEsperado.setAccessible(true);

        Field campoEncontrado = encontrada.getClass().getDeclaredField(nomeCampo);
        campoEncontrado.setAccessible(true);

        Object valorEsperado = campoEsperado.get(esperada);
        Object valorEncontrado = campoEncontrado.get(encontrada);

        assertEquals(valorEsperado, valorEncontrado, mensagem);
    }

    public static Vacina configurarVacina(String codigo, String nome, String tipo, double preco, double eficacia, LocalDate dataLancamento) {
        try {
            // Cria uma nova instância de Vacina usando o construtor default
            Vacina vacina = Vacina.class.getDeclaredConstructor().newInstance();

            // Define os campos individualmente usando reflection
            configurarCampo(vacina, "codigo", codigo);
            configurarCampo(vacina, "nome", nome);
            configurarCampo(vacina, "tipo", tipo);
            configurarCampo(vacina, "preco", preco);
            configurarCampo(vacina, "eficacia", eficacia);
            configurarCampo(vacina, "dataLancamento", dataLancamento);

            return vacina;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar o objeto Vacina", e);
        }
    }

    public static void configurarCampo(Object objeto, String nomeCampo, Object valor) throws Exception {
        Field campo = objeto.getClass().getDeclaredField(nomeCampo);
        campo.setAccessible(true); // Permite o acesso a campos privados
        campo.set(objeto, valor);  // Define o valor do campo
    }
}
