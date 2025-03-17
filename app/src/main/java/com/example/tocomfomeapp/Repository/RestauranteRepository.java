package com.example.tocomfomeapp.Repository;

import com.example.tocomfomeapp.Model.Restaurante;
import java.util.ArrayList;
import java.util.List;

public class RestauranteRepository {

    private static List<Restaurante> listaRestaurantes = new ArrayList<>();

    public static List<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }

    public static void adicionarRestaurante(Restaurante restaurante) {
        listaRestaurantes.add(restaurante);
    }

    public static void editarRestaurante(int index, Restaurante novoRestaurante) {
        if(index >= 0 && index < listaRestaurantes.size()) {
            listaRestaurantes.set(index, novoRestaurante);
        }
    }

    public static void removerRestaurante(int index) {
        if(index >= 0 && index < listaRestaurantes.size()) {
            listaRestaurantes.remove(index);
        }
    }
}
