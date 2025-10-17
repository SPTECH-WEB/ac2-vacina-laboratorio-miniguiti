package school.sptech.factory;

import school.sptech.Laboratorio;
import school.sptech.Vacina;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaboratorioFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Laboratorio> clazz = Laboratorio.class;

        Map<String, Field> mapCampos = new HashMap<>();
        String[] nomeCampos = {"nome", "vacinas"};

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static Object build(String nome, List<Vacina> vacinas) throws ReflectiveOperationException {
        Class<Laboratorio> laboratorioClass = Laboratorio.class;

        Constructor<?> constructor = laboratorioClass.getDeclaredConstructors()[0];
        Object[] parameters = Arrays.stream(constructor.getParameters()).map(param -> null).toArray();

        Object obj = constructor.newInstance(parameters);

        campos().get("nome").set(obj, nome);
        campos().get("vacinas").set(obj, vacinas);

        return obj;
    }
}
