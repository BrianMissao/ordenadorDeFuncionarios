package aplicacao;

import dominio.entidades.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OrdenadorDeFuncionarios {
    private static List<Funcionario> listaDeFuncionarios = new ArrayList<>();
    private static List<String> listaDeEMailsDeFuncionariosEmOrdemAlfabetica;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String caminhoDoArquivoDeFuncionarios = obterDados("Entre com o caminho do arquivo de funcionários:");
        listaDeFuncionarios = ObtemListaDeFuncionariosAPartirDeUmArquivoInformadoPeloUsuario.obter(caminhoDoArquivoDeFuncionarios);
        BigDecimal salarioBase = new BigDecimal(obterDados("Qual o salário base?")).setScale(2, RoundingMode.HALF_EVEN);
        ordenarPorOrdemAlfabeticaEComBaseNoSalario(salarioBase);
        listaDeEMailsDeFuncionariosEmOrdemAlfabetica.forEach(System.out::println);
        BigDecimal somaDosSalariosDosFuncionariosCujoONomeComecamComALetraM = somarSalariosDosFuncionariosCujoONomeComecamComALetraM();
        System.out.println("A soma dos funcionários que começam com a letra 'M' é: "+somaDosSalariosDosFuncionariosCujoONomeComecamComALetraM);
    }

    private static String obterDados(String mensagemAoUsuario) {
        System.out.println(mensagemAoUsuario);
        return scanner.nextLine();
    }

    private static void ordenarPorOrdemAlfabeticaEComBaseNoSalario(BigDecimal salarioBase) {
        listaDeEMailsDeFuncionariosEmOrdemAlfabetica = listaDeFuncionarios.stream().filter(funcionario -> funcionario.getSalario().compareTo(salarioBase) > 0).map(funcionario -> funcionario.getEmail()).sorted((x, y) -> x.compareTo(y)).collect(Collectors.toList());
    }

    private static BigDecimal somarSalariosDosFuncionariosCujoONomeComecamComALetraM() {
        return listaDeFuncionarios.stream().filter(funcionario -> funcionario.getNome().charAt(0) == 'M').map(funcionario -> funcionario.getSalario()).reduce(new BigDecimal("0.00"), (x, y) ->  x.add(y)).setScale(2, RoundingMode.HALF_EVEN);
    }
}
