import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ControleVacinacao {

    public static void main(String[] args) throws ParseException {

        Enfermeira enfermeira = new Enfermeira();
        Cidadao cidadao = new Cidadao();
        Vacinacao vacinacao = new Vacinacao();
        Date vacinas[] = new Date[4];
        List<Cidadao> cidadaoList = new ArrayList<Cidadao>();

        enfermeira.setNome(JOptionPane.showInputDialog("Digite o nome da enfermeira: "));
        enfermeira.setCpf(JOptionPane.showInputDialog("Digite o CPF da enfermeira: "));

        int opcao = 0;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Vacinar cidadão" + "\n" + "2 - Listar cidadãos vacinados" + "\n" + "3 - Sair" + "\nEscolha uma opção: "));

            switch (opcao) {
                case 1:
                    cidadao.setNome(JOptionPane.showInputDialog("Digite o nome: "));
                    cidadao.setCpf(JOptionPane.showInputDialog("Digite o CPF: "));

                    for (int i = 0; i < 4; i++) {

                        vacinas[i] = new SimpleDateFormat("dd/MM/yyyy").parse(JOptionPane.showInputDialog("Digite a data da " + (i + 1) + "ª" + "vacina: "));

                        if (i != 0) {
                            int qtdMeses = calcularDiferencaMeses(vacinas[i - 1], vacinas[i]);
                            if (qtdMeses < 4) {
                                JOptionPane.showMessageDialog(null, "Não tem direito a uma nova data");
                                break;
                            }
                        }
                    }

                    vacinacao.setDataVacinacao(vacinas);
                    cidadao.setVacinacao(vacinacao);
                    cidadaoList.add(cidadao);
                    break;
                case 2:
                    String msg = "";
                    Vacinacao vacinacaoDatas = cidadao.getVacinacao();
                    Date[] datas = vacinacaoDatas.getDataVacinacao();
                    for (Cidadao cidadao1 : cidadaoList) {
                        msg += "Nome: " + cidadao.getNome() //
                                + "\nCpf: " + cidadao.getCpf() //
                                + "\nData primeira dose: " + new SimpleDateFormat("dd/MM/yyyy").format(datas[0])//
                                + "\nData segunda dose: " + new SimpleDateFormat("dd/MM/yyyy").format(datas[1])//
                                + "\nData terceira dose: " + new SimpleDateFormat("dd/MM/yyyy").format(datas[2])//
                                + "\nData quarta dose: " + new SimpleDateFormat("dd/MM/yyyy").format(datas[3])//
                                + "\n\n"
                        ;
                    }

                    JOptionPane.showMessageDialog(null, msg);
                    break;
                case 3://sair
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (opcao != 3);
    }

    public static int calcularDiferencaMeses(Date data1, Date data2) {
        Calendar i = Calendar.getInstance();
        i.setTime(data1);

        Calendar f = Calendar.getInstance();
        f.setTime(data2);

        int qtdMesesIni = (i.get(Calendar.YEAR) * 12) + i.get(Calendar.MONTH);
        int qtdMesesFim = (f.get(Calendar.YEAR) * 12) + f.get(Calendar.MONTH);

        return qtdMesesFim - qtdMesesIni;
    }
}
