package view;

import exceptions.HorarioInvalidoException;
import exceptions.VeiculoNaoEncontradoException;
import repository.TicketRepository;
import service.FormatHorarioService;
import service.SaidaService;
import view.util.AuxLayout;
import view.util.Navegador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

public class TelaSaida implements ITela{
    private JPanel panel = new JPanel();
    private IDEnum ID = IDEnum.SAIDA;
    private TicketRepository ticketRepository;
    private PanelDisponibilidade panelDisponibilidade;

    public TelaSaida(TicketRepository ticketRepository, PanelDisponibilidade panelDisponiblidade){
        this.ticketRepository = ticketRepository;
        this.panelDisponibilidade = panelDisponiblidade;

    }

    @Override
    public JPanel getPanel(){
        return this.panel;
    }

    @Override
    public IDEnum getID(){
        return ID;
    }

    @Override
    public void configurar(Navegador nav){
        SaidaService saidaService = new SaidaService(ticketRepository);
        FormatHorarioService horarioService = new FormatHorarioService();

        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());

        //-------- Configuração  por métodos auxiliares ---------//

        configurarMolas(gbc);
        configurarLabels(gbc);
        List<JSpinner> listaSP = configurarSpinners(gbc);

        //-----------------  TextField placa  -------------------//
        MaskFormatter mascaraPlaca = null;
        try {
            mascaraPlaca = new MaskFormatter("AAAAAAA");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JFormattedTextField textoPlaca = new JFormattedTextField(mascaraPlaca);
        textoPlaca.setPreferredSize(new Dimension(120, textoPlaca.getPreferredSize().height));
        AuxLayout.setup(gbc,2,1,2,1,0.0,0.0);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(textoPlaca, gbc);

        //-----------------  Botão registrar -------------------//
        AuxLayout.reset(gbc);
        JButton botaoRegistrar = new JButton("REGISTRAR SAÍDA");
        AuxLayout.reset(gbc);
        AuxLayout.setup(gbc,1,3,3,1,0.0,0.0);
        gbc.insets = new Insets(15,0,0,0);

        botaoRegistrar.addActionListener(e -> {
            //TODO: Exceção com campos vazios
                LocalDateTime horarioSaida = horarioService.criarHorario(getDia(listaSP), getHoras(listaSP), getMinutos(listaSP));
            try {
                saidaService.registrarSaida(textoPlaca.getText(), horarioSaida);
                panelDisponibilidade.saidaPorTipo(ticketRepository.getTicketFinalizado(textoPlaca.getText()).getVeiculo().getTipo());

            } catch (HorarioInvalidoException | VeiculoNaoEncontradoException ex) {
                throw new RuntimeException(ex);
            }

        });
        panel.add(botaoRegistrar, gbc);

    }

    private void configurarMolas(GridBagConstraints gbc){
        //----------------- Mola superior -------------------//
        AuxLayout.setup(gbc,1,0,1,1,0.0,0.5);
        panel.add(Box.createGlue(), gbc);

        //----------------- Mola esquerda -------------------//
        AuxLayout.setup(gbc,0,1,1,1,0.5,0.0);
        panel.add(Box.createGlue(), gbc);

        //-----------------  Mola direita -------------------//
        AuxLayout.setup(gbc,4,0,1,1,0.5,0.0);
        panel.add(Box.createGlue(), gbc);

        //-----------------  Mola inferior -------------------//
        AuxLayout.setup(gbc,1,6,1,1,0.0,0.5);
        panel.add(Box.createGlue(), gbc);
    }

    private void configurarLabels(GridBagConstraints gbc){

        //----------------- Label Placa -------------------//
        AuxLayout.reset(gbc);
        JLabel labelPlaca = new JLabel("Placa:");
        AuxLayout.setup(gbc,1,1,1,1,0.0,0.0);
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelPlaca, gbc);

        //----------------- Label Horário de saída -------------------//
        JLabel labelHorario = new JLabel("Horário de saída:");
        AuxLayout.setup(gbc,1,2,1,1,0.0,0.0);

        panel.add(labelHorario,gbc);
    }

    private java.util.List<JSpinner> configurarSpinners(GridBagConstraints gbc){
        JPanel subpanelSpinners = new JPanel(new FlowLayout(FlowLayout.LEFT, 5 , 0));
        AuxLayout.setup(gbc,2,2,2,1,0.0,0.0);
        gbc.insets = new Insets(8,10,0,0);
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

        return List.of(spinnerDia, spinnerHora, spinnerMinutos);
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


}
