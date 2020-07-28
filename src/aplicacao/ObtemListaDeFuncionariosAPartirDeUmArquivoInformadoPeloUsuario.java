package aplicacao;

import dominio.entidades.Funcionario;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ObtemListaDeFuncionariosAPartirDeUmArquivoInformadoPeloUsuario {
    private static List<Funcionario> listaDeFuncionarios = new ArrayList<>();

    public static List<Funcionario> obter(String caminhoDoArquivo) {
        int campoDoNome = 0;
        int campoDoEmail = 1;
        int campoDoSalario = 2;
        try (BufferedReader arquivoDeFuncionarios = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            String linhaDoArquivoDeFuncionariosSendoLida = arquivoDeFuncionarios.readLine();
            while (linhaDoArquivoDeFuncionariosSendoLida != null) {
                String informacoesDoFuncionarioParaAdicao[] = linhaDoArquivoDeFuncionariosSendoLida.split(",");
                listaDeFuncionarios.add(new Funcionario(informacoesDoFuncionarioParaAdicao[campoDoNome], informacoesDoFuncionarioParaAdicao[campoDoEmail], new BigDecimal(informacoesDoFuncionarioParaAdicao[campoDoSalario]).setScale(2, RoundingMode.HALF_EVEN)));
                linhaDoArquivoDeFuncionariosSendoLida = arquivoDeFuncionarios.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return listaDeFuncionarios;
    }
}
