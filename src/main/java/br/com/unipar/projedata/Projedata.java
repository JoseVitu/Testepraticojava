package br.com.unipar.projedata;

 

import br.com.unipar.projedata.model.Funcionario;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.math.BigDecimal;

import java.text.DecimalFormat;

import java.util.ArrayList;

import java.util.List;

import java.util.Map;

import java.util.stream.Collectors;

 

public class Projedata {

 

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

 

        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), BigDecimal.valueOf(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 15), BigDecimal.valueOf(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), BigDecimal.valueOf(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), BigDecimal.valueOf(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), BigDecimal.valueOf(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), BigDecimal.valueOf(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), BigDecimal.valueOf(2799.93), "Coordenador"));

 

        // 3.2 – Remover o funcionário “João” da lista.

        funcionarios.removeIf(f -> f.getNome().equals("João"));

 

        /* 3.3 –  Imprimir todos os funcionários com todas suas informações, sendo que:

              • informação de data deve ser exibido no formato dd/mm/aaaa;

              • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.

        */

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DecimalFormat df = new DecimalFormat("#,###.00");

        for (Funcionario f : funcionarios) {

            System.out.println(f.getNome() + " - " + dtf.format(f.getDataNascimento()) + " - " + df.format(f.getSalario()) + " - " + f.getFuncao());

        }

 

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.

        for (Funcionario f : funcionarios) {

            f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.10)));

        }

 

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.

        Map<String, List<Funcionario>> agrupadosPorFuncao = funcionarios.stream()

            .collect(Collectors.groupingBy(Funcionario::getFuncao));

 

        // 3.6 – Imprimir os funcionários, agrupados por função

        agrupadosPorFuncao.forEach((funcao, listaFuncionarios) -> {

            System.out.println(funcao + ":");

            listaFuncionarios.forEach(f -> System.out.println(f.getNome()));

        });

 

        // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12

        funcionarios.stream()

            .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)

            .forEach(f -> System.out.println(f.getNome()));

 

        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.

        Funcionario funcMaisAntigo = funcionarios.stream()

            .min((f1, f2) -> f1.getDataNascimento().compareTo(f2.getDataNascimento()))

            .orElse(null);

        if (funcMaisAntigo != null) {

            System.out.println("Funcionário mais velho: " + funcMaisAntigo.getNome() + " - Idade: " + (LocalDate.now().getYear() - funcMaisAntigo.getDataNascimento().getYear()));

        }

 

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética

        funcionarios.stream()

            .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))

            .forEach(f -> System.out.println(f.getNome()));

 

        // 3.11 – Imprimir o total dos salários dos funcionários

        BigDecimal totalSalarios = funcionarios.stream()

            .map(Funcionario::getSalario)

            .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total da somatoria dos salários: " + df.format(totalSalarios));

 

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00

        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);

        for (Funcionario f : funcionarios) {

            BigDecimal quantidadeSalarios = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);

            System.out.println(f.getNome() + " ganha um totalde: " + quantidadeSalarios + " salarios mìnimos.");

        }

    }

}

 