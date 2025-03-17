package com.example.tocomfomeapp.Repository;
// Esse pacote contém as classes responsáveis por armazenar e gerenciar os dados (nesse caso, os restaurantes) em memória.

import com.example.tocomfomeapp.Model.Restaurante;
import java.util.ArrayList;
import java.util.List;

public class RestauranteRepository {

    // Declaro uma lista estática para armazenar os objetos Restaurante.
    // Como é static, ela existe enquanto o app estiver rodando e pode ser acessada de qualquer lugar.
    private static List<Restaurante> listaRestaurantes = new ArrayList<>();

    // Método getter que retorna a lista de restaurantes.
    // Isso permite que outras classes acessem a lista para, por exemplo, exibi-la em um RecyclerView.
    public static List<Restaurante> getListaRestaurantes() {
        return listaRestaurantes;
    }

    // Método para adicionar um novo restaurante na lista.
    // Recebe um objeto Restaurante e o adiciona à lista.
    public static void adicionarRestaurante(Restaurante restaurante) {
        listaRestaurantes.add(restaurante);
    }

    // Método para editar um restaurante já existente na lista.
    // Recebe um índice (posição na lista) e um novo objeto Restaurante com os dados atualizados.
    // Se o índice for válido (maior ou igual a 0 e menor que o tamanho da lista),
    // ele substitui o restaurante antigo pelo novo.
    public static void editarRestaurante(int index, Restaurante novoRestaurante) {
        if(index >= 0 && index < listaRestaurantes.size()) {
            listaRestaurantes.set(index, novoRestaurante);
        }
    }

    // Método para remover um restaurante da lista.
    // Recebe o índice do restaurante a ser removido.
    // Se o índice for válido, remove o restaurante da lista.
    public static void removerRestaurante(int index) {
        if(index >= 0 && index < listaRestaurantes.size()) {
            listaRestaurantes.remove(index);
        }
    }
}