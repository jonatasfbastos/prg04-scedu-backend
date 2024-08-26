package br.com.ifba.scedu.infrastructure.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Matheus Mendes
 * Classe auxiliar para manipulação de propriedades de objetos.
 * Contém métodos auxiliares que permitem encontrar quais campos de um objeto estão vazios (nulos).
 *
 */
public class BeanUtilsHelper {

    /**
     * @author Matheus Mendes
     * Retorna um array de nomes de propriedades que são nulas no objeto fornecido.
     *
     * @param source Objeto a ser verificado
     * @return Array de nomes de propriedades nulas
     */
    public static String[] getNullPropertyNames(Object source) {
        // Wrapper que permite acessar as propriedades do objeto fornecido
        final BeanWrapper src = new BeanWrapperImpl(source);

        // Obtém as informações sobre cada campo do objeto (como nome e tipo).
        // Essas informações são chamadas de "descritores de propriedade", e são usadas para acessar os valores dos campos do objeto.
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Cria um conjunto para armazenar os nomes de propriedades nulas
        Set<String> emptyNames = new HashSet<>();

        // Itera sobre os descritores de propriedade
        for (java.beans.PropertyDescriptor pd : pds) {
            // Obtém o valor da propriedade
            Object srcValue = src.getPropertyValue(pd.getName());

            // Verifica se a propriedade é nula
            if (srcValue == null) {
                // Adiciona o nome da propriedade ao conjunto
                emptyNames.add(pd.getName());
            }
        }

        // Cria um array para armazenar os nomes de propriedades nulas
        String[] result = new String[emptyNames.size()];

        // Converte o conjunto para um array
        return emptyNames.toArray(result);
    }
}

