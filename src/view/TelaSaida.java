package view;

import view.util.AuxLayout;
import view.util.Navegador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaSaida implements ITela{
    private JPanel panel = new JPanel();
    private IDEnum ID = IDEnum.SAIDA;

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

        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
       // panel.add(new JButton("REGISTRAR SAIDA"));

        configurarMolas(gbc);
        configurarLabels(gbc);
        configurarSpinners(gbc);
        //-----------------  TextField placa  -------------------//
        //todo: Modificar para JFormmated (7 caracteres máximo)
        JTextField textoPlaca = new JTextField();
        textoPlaca.setPreferredSize(new Dimension(120, textoPlaca.getPreferredSize().height));
        AuxLayout.setup(gbc,2,1,2,1,0.0,0.0);
        gbc.anchor = GridBagConstraints.LINE_START;

        panel.add(textoPlaca, gbc);

        //-----------------  Botão registrar -------------------//
        AuxLayout.reset(gbc);
        JButton botaoRegistrar = new JButton("REGISTRAR SAÍDA");
        AuxLayout.reset(gbc);
        AuxLayout.setup(gbc,1,3,3,1,0.0,0.0);
        gbc.insets = new Insets(15,0,0,0);

        botaoRegistrar.addActionListener(e -> {
            /*for(JComboBox box : listaCB){
                if(box.getSelectedItem().toString().equals("")){
                    //todo: Lançar uma exceção JDialog aqui
                    System.out.println("Campo vazio."); //Retirar isso aqui depois
                }
            }
            */
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

        SpinnerNumberModel modelHora = new SpinnerNumberModel(0,0,23,1);
        JSpinner spinnerHora = new JSpinner(modelHora);
        spinnerHora.setPreferredSize(new Dimension(40, spinnerHora.getPreferredSize().height));

        subpanelSpinners.add(spinnerHora);
        subpanelSpinners.add(new JLabel("h"));

        SpinnerNumberModel modelMinutos = new SpinnerNumberModel(0,0,59,1);
        JSpinner spinnerMinutos = new JSpinner(modelMinutos);
        spinnerMinutos.setPreferredSize(new Dimension(40, spinnerHora.getPreferredSize().height));

        subpanelSpinners.add(spinnerMinutos);
        subpanelSpinners.add(new JLabel("min"));

        return List.of(spinnerHora, spinnerMinutos);
    }
}
