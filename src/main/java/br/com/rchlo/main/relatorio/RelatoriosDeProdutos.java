package br.com.rchlo.main.relatorio;

import br.com.rchlo.domain.Cor;
import br.com.rchlo.domain.ListaDeProdutos;
import br.com.rchlo.domain.Produto;
import br.com.rchlo.domain.Tamanho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatoriosDeProdutos {

    public static void main(String[] args) {
        List<Produto> produtos = ListaDeProdutos.lista();

        System.out.println("Quantidade produtos por cor");
        Map<Cor, Integer> quantidadesPorCor = new HashMap<>();
        for (Produto produto : produtos) {
            Cor cor = produto.getCor();
            Integer quantidade = quantidadesPorCor.get(cor);
            if(quantidade != null) {
                quantidadesPorCor.put(cor, quantidade + 1);
            } else {
                quantidadesPorCor.put(cor, 1);
            }
        }
        quantidadesPorCor.entrySet().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        System.out.println("\nLista de produtos por tamanho");
        Map<Tamanho, List<Produto>> produtosPorTamanho = new HashMap<>();
        for (Produto produto : produtos) {
            List<Tamanho> tamanhosDisponiveis = produto.getTamanhosDisponiveis();
            tamanhosDisponiveis.forEach(tamanho -> {
                List<Produto> produtosDoTamanho = produtosPorTamanho.get(tamanho);
                if(produtosDoTamanho != null) {
                    produtosDoTamanho.add(produto);
                } else {
                    produtosDoTamanho = new ArrayList<>();
                    produtosDoTamanho.add(produto);
                    produtosPorTamanho.put(tamanho, produtosDoTamanho);
                }
            });
        }
        produtosPorTamanho.entrySet().forEach(entry -> System.out.println("\n" + entry.getKey() + ":\n" + entry.getValue().stream().map(Produto::getNome).collect(Collectors.joining("\n"))));

    }

}
