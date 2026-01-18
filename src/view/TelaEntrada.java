package view;

import exceptions.EstacionamentoLotadoException;
import exceptions.PlacaInvalidaException;
import exceptions.TipoVeiculoInvalidoException;
import exceptions.VeiculoJaEstacionadoException;
import model.TiposEnum;
import repository.TicketRepository;
import service.EntradaService;
import service.FormatHorarioService;
import view.util.AuxLayout;
import view.util.Navegador;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public class TelaEntrada implements ITela {

    private JPanel panel = new JPanel();
    private IDEnum ID = IDEnum.ENTRADA;
    private TicketRepository ticketRepository;

    public TelaEntrada(TicketRepository ticketRepository){this.ticketRepository = ticketRepository;
    }
    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public IDEnum getID() {
        return ID;
    }

    @Override
    public void configurar(Navegador nav) {
        EntradaService entradaService = new EntradaService(ticketRepository);
        FormatHorarioService horarioService = new FormatHorarioService();

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //-------- Configuração  por métodos auxiliares ---------//

        configurarMolas(gbc);
        configurarLabels(gbc);
        List<JComboBox> listaCB = configurarComboBox(gbc);
        List<JSpinner> listaSP = configurarSpinners(gbc);

        //-----------------  TextField placa  -------------------//
        MaskFormatter mascaraPlaca = null;
        try {
            mascaraPlaca = new MaskFormatter("AAAAAAA");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JFormattedTextField textoPlaca = new JFormattedTextField(mascaraPlaca);
        AuxLayout.setup(gbc, 2, 4, 2, 1, 0.0, 0.0);
        gbc.insets = new Insets(8, 12, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(textoPlaca, gbc);

        //-----------------  Botão registrar -------------------//
        JButton botaoRegistrar = new JButton("REGISTRAR ENTRADA");
        AuxLayout.reset(gbc);
        AuxLayout.setup(gbc, 1, 5, 3, 1, 0.0, 0.0);
        gbc.insets = new Insets(15, 0, 0, 0);

        botaoRegistrar.addActionListener(e -> {
            LocalDateTime horarioEntrada = horarioService.criarHorario(getDia(listaSP), getHoras(listaSP), getMinutos(listaSP));
            try {
                entradaService.registrarEntrada(textoPlaca.getText(),getTipo(listaCB), horarioEntrada);
            } catch (EstacionamentoLotadoException ex) {
                throw new RuntimeException(ex);
            } catch (VeiculoJaEstacionadoException ex) {
                throw new RuntimeException(ex);
            } catch (TipoVeiculoInvalidoException ex) {
                throw new RuntimeException(ex);
            } catch (PlacaInvalidaException ex) {
                throw new RuntimeException(ex);
            }

            System.out.println(getTipo(listaCB));
            System.out.println(getDia(listaSP));
            System.out.println(getHoras(listaSP));
            System.out.println(getMinutos(listaSP));
            System.out.println(getPlaca(textoPlaca));

        });

        panel.add(botaoRegistrar, gbc);
    }

    private void configurarMolas(GridBagConstraints gbc) {

        //----------------- Mola superior -------------------//
        AuxLayout.setup(gbc, 1, 0, 1, 1, 0.0, 0.5);
        panel.add(Box.createGlue(), gbc);

        //----------------- Mola esquerda -------------------//
        AuxLayout.setup(gbc, 0, 1, 1, 1, 0.5, 0.0);
        panel.add(Box.createGlue(), gbc);

        //-----------------  Mola direita -------------------//
        AuxLayout.setup(gbc, 4, 0, 1, 1, 0.5, 0.0);
        panel.add(Box.createGlue(), gbc);

        //-----------------  Mola inferior -------------------//
        AuxLayout.setup(gbc, 1, 6, 1, 1, 0.0, 0.5);
        panel.add(Box.createGlue(), gbc);
    }

    private void configurarLabels(GridBagConstraints gbc) {

        //----------------- Label Tipo Veículo -------------------//
        JLabel labelVeiculo = new JLabel("Tipo de Veículo:");
        AuxLayout.setup(gbc, 1, 1, 1, 1, 0.0, 0.0);
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelVeiculo, gbc);

        //----------------- Label Horário de entrada -------------------//
        JLabel labelHorario = new JLabel("Horário de entrada:");
        AuxLayout.setup(gbc, 1, 3, 1, 1, 0.0, 0.0);
        panel.add(labelHorario, gbc);

        //----------------- Label Placa -------------------//
        JLabel labelPlaca = new JLabel("Placa:");
        AuxLayout.setup(gbc, 1, 4, 1, 1, 0.0, 0.0);
        panel.add(labelPlaca, gbc);
    }

    private List<JComboBox> configurarComboBox(GridBagConstraints gbc) {

        //-----------------  comboBox tipo  -------------------//
        JComboBox<String> boxTipo = new JComboBox<>();
        boxTipo.setPreferredSize(new Dimension(120, boxTipo.getPreferredSize().height));

        AuxLayout.reset(gbc);
        AuxLayout.setup(gbc, 2, 1, 2, 1, 0.0, 0.0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 12, 0, 0);

        boxTipo.addItem("");
        for (TiposEnum tipo : TiposEnum.values()) {
            boxTipo.addItem(tipo.toString());
        }
        panel.add(boxTipo, gbc);

        return List.of(boxTipo);
    }

    private List<JSpinner> configurarSpinners(GridBagConstraints gbc) {

        JPanel subpanelSpinners = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        AuxLayout.setup(gbc, 2, 3, 2, 1, 0.0, 0.0);
        gbc.insets = new Insets(8,12,0,0);
        panel.add(subpanelSpinners, gbc);

        //----------- Configuração spinner dia -----------//
        SpinnerNumberModel modelDia = new SpinnerNumberModel(1, 1, 30, 1);
        JSpinner spinnerDia = new JSpinner(modelDia);
        spinnerDia.setPreferredSize(new Dimension(40, spinnerDia.getPreferredSize().height));

        subpanelSpinners.add(spinnerDia);
        subpanelSpinners.add(new JLabel("dia"));

        //----------- Configuração spinner hora -----------//
        SpinnerNumberModel modelHora = new SpinnerNumberModel(0, 0, 23, 1);
        JSpinner spinnerHora = new JSpinner(modelHora);
        spinnerHora.setPreferredSize(new Dimension(40, spinnerHora.getPreferredSize().height));

        subpanelSpinners.add(spinnerHora);
        subpanelSpinners.add(new JLabel("h"));

        //----------- Configuração spinner minutos -----------//
        SpinnerNumberModel modelMinutos = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner spinnerMinutos = new JSpinner(modelMinutos);
        spinnerMinutos.setPreferredSize(new Dimension(40, spinnerHora.getPreferredSize().height));

        subpanelSpinners.add(spinnerMinutos);
        subpanelSpinners.add(new JLabel("min"));

        return List.of(spinnerDia,spinnerHora, spinnerMinutos);
    }

    private String getTipo(List<JComboBox> listaCB) {
        return listaCB.getFirst().getSelectedItem().toString();
    }
    private int getDia(List<JSpinner> listaSP) {
        return (Integer) listaSP.getFirst().getModel().getValue();
    }
    private int getHoras(List<JSpinner> listaSP) {
        return (Integer) listaSP.get(1).getModel().getValue();
    }

    private int getMinutos(List<JSpinner> listaSP) {
        return (Integer) listaSP.getLast().getModel().getValue();
    }

    private String getPlaca(JFormattedTextField labelPlaca) {
        return labelPlaca.getText();
    }
}
