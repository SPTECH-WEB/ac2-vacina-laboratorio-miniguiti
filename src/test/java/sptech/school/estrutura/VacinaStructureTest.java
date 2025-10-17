package school.sptech.estrutura;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import school.sptech.Vacina;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("1. TESTE DE ESTRUTURA - VACINA")
class VacinaStructureTest {

    @Test
    @DisplayName("1.1. Deve possuir os atributos corretos com os modificadores apropriados")
    void testAtributos() throws NoSuchFieldException {

        Field codigo = Vacina.class.getDeclaredField("codigo");
        assertEquals(String.class, codigo.getType(), "O tipo do atributo 'codigo' deve ser String.");
        assertTrue(Modifier.isPrivate(codigo.getModifiers()), "O atributo 'codigo' deve ser privado.");

        Field nome = Vacina.class.getDeclaredField("nome");
        assertEquals(String.class, nome.getType(), "O tipo do atributo 'nome' deve ser String.");
        assertTrue(Modifier.isPrivate(nome.getModifiers()), "O atributo 'nome' deve ser privado.");

        Field tipo = Vacina.class.getDeclaredField("tipo");
        assertEquals(String.class, tipo .getType(), "O tipo do atributo 'tipo ' deve ser String.");
        assertTrue(Modifier.isPrivate(tipo .getModifiers()), "O atributo 'tipo ' deve ser privado.");

        Field preco = Vacina.class.getDeclaredField("preco");
        assertEquals(Double.class, preco.getType(), "O tipo do atributo 'preco' deve ser Double.");
        assertTrue(Modifier.isPrivate(preco.getModifiers()), "O atributo 'preco' deve ser privado.");

        Field eficacia = Vacina.class.getDeclaredField("eficacia");
        assertEquals(Double.class, eficacia.getType(), "O tipo do atributo 'eficacia' deve ser Double.");
        assertTrue(Modifier.isPrivate(eficacia.getModifiers()), "O atributo 'eficacia' deve ser privado.");

        Field dataLancamento = Vacina.class.getDeclaredField("dataLancamento");
        assertEquals(LocalDate.class, dataLancamento.getType(), "O tipo do atributo 'dataLancamento' deve ser LocalDate.");
        assertTrue(Modifier.isPrivate(dataLancamento.getModifiers()), "O atributo 'dataLancamento' deve ser privado.");
    }

    @Test
    @DisplayName("1.2. Deve possuir os construtores corretos")
    void testConstrutores() throws NoSuchMethodException {
        Constructor<Vacina> construtorPadrao = Vacina.class.getDeclaredConstructor();
        assertTrue(Modifier.isPublic(construtorPadrao.getModifiers()), "O construtor sem argumentos deve ser público.");
    }

    @Test
    @DisplayName("1.3. Deve possuir os métodos getters e setters corretos com modificadores públicos")
    void testGettersSetters() throws NoSuchMethodException {
        Method getCodigo = Vacina.class.getDeclaredMethod("getCodigo");
        assertEquals(String.class, getCodigo.getReturnType(), "O método 'getCodigo' deve retornar String.");
        assertTrue(Modifier.isPublic(getCodigo.getModifiers()), "O método 'getCodigo' deve ser público.");

        Method setCodigo = Vacina.class.getDeclaredMethod("setCodigo", String.class);
        assertTrue(Modifier.isPublic(setCodigo.getModifiers()), "O método 'setCodigo' deve ser público.");

        Method getNome = Vacina.class.getDeclaredMethod("getNome");
        assertEquals(String.class, getNome.getReturnType(), "O método 'getNome' deve retornar String.");
        assertTrue(Modifier.isPublic(getNome.getModifiers()), "O método 'getNome' deve ser público.");

        Method setNome = Vacina.class.getDeclaredMethod("setNome", String.class);
        assertTrue(Modifier.isPublic(setNome.getModifiers()), "O método 'setNome' deve ser público.");

        Method getTipo = Vacina.class.getDeclaredMethod("getTipo");
        assertEquals(String.class, getTipo.getReturnType(), "O método 'getTipo' deve retornar String.");
        assertTrue(Modifier.isPublic(getTipo.getModifiers()), "O método 'getTipo' deve ser público.");

        Method setTipo = Vacina.class.getDeclaredMethod("setTipo", String.class);
        assertTrue(Modifier.isPublic(setTipo.getModifiers()), "O método 'setTipo' deve ser público.");

        Method getPreco = Vacina.class.getDeclaredMethod("getPreco");
        assertEquals(Double.class, getPreco.getReturnType(), "O método 'getPreco' deve retornar Double.");
        assertTrue(Modifier.isPublic(getPreco.getModifiers()), "O método 'getPreco' deve ser público.");

        Method setPreco = Vacina.class.getDeclaredMethod("setPreco", Double.class);
        assertTrue(Modifier.isPublic(setPreco.getModifiers()), "O método 'setPreco' deve ser público.");

        Method getEficacia = Vacina.class.getDeclaredMethod("getEficacia");
        assertEquals(Double.class, getEficacia.getReturnType(), "O método 'getEficacia' deve retornar Double.");
        assertTrue(Modifier.isPublic(getEficacia.getModifiers()), "O método 'getEficacia' deve ser público.");

        Method setEficacia = Vacina.class.getDeclaredMethod("setEficacia", Double.class);
        assertTrue(Modifier.isPublic(setEficacia.getModifiers()), "O método 'setEficacia' deve ser público.");

        Method getDataLancamento = Vacina.class.getDeclaredMethod("getDataLancamento");
        assertEquals(LocalDate.class, getDataLancamento.getReturnType(), "O método 'getDataLancamento' deve retornar LocalDate.");
        assertTrue(Modifier.isPublic(getDataLancamento.getModifiers()), "O método 'getDataLancamento' deve ser público.");

        Method setDataLancamento = Vacina.class.getDeclaredMethod("setDataLancamento", LocalDate.class);
        assertTrue(Modifier.isPublic(setDataLancamento.getModifiers()), "O método 'setDataLancamento' deve ser público.");
    }

    @Test
    @DisplayName("1.4. Deve possuir o método getEficaciaDescricao com modificador público")
    void testMetodoGetEficaciaDescricao() throws NoSuchMethodException {
        Method getEficaciaDescricao = Vacina.class.getDeclaredMethod("getEficaciaDescricao");
        assertEquals(String.class, getEficaciaDescricao.getReturnType(), "O método 'getEficaciaDescricao' deve retornar String.");
        assertTrue(Modifier.isPublic(getEficaciaDescricao.getModifiers()), "O método 'getEficaciaDescricao' deve ser público.");
    }
}