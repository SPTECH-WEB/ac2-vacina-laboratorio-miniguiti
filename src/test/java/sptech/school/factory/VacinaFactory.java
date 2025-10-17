package school.sptech.factory;

import school.sptech.Vacina;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class VacinaFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Vacina> clazz = Vacina.class;

        Map<String, Field> mapCampos = new HashMap<>();
        String[] nomeCampos = {"codigo", "nome", "tipo", "preco", "eficacia", "dataLancamento"};

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static Object build(String codigo, String nome, String tipo, Double preco, Double eficacia, LocalDate dataLancamento) throws ReflectiveOperationException {
        Class<Vacina> vacinaClass = Vacina.class;

        Constructor<?> constructor = vacinaClass.getDeclaredConstructors()[0];
        Object[] parameters = new Object[constructor.getParameterCount()];

        Object obj = constructor.newInstance(parameters);

        campos().get("codigo").set(obj, codigo);
        campos().get("nome").set(obj, nome);
        campos().get("tipo").set(obj, tipo);
        campos().get("preco").set(obj, preco);
        campos().get("eficacia").set(obj, eficacia);
        campos().get("dataLancamento").set(obj, dataLancamento);

        return obj;
    }
}
