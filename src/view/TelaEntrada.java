package view;

import model.ModalidadeEnum;
import model.TiposEnum;
import view.util.AuxLayout;
import view.util.Navegador;

import javax.swing.*;
import java.awt.*;

import java.util.List;
public class TelaEntrada implements ITela{

    private JPanel panel = new JPanel();
    private IDEnum ID = IDEnum.ENTRADA;

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

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        configurarMolas(gbc);
        configurarLabels(gbc);
        List<JComboBox> listaCB = configurarComboBox(gbc);

        List<JSpinner> listaSP = configurarSpinners(gbc);

        //-----------------  TextField placa  -------------------//
        JTextField textoPlaca = new JTextField();
        AuxLayout.setup(gbc,2,4,2,1,0.0,0.0);
        gbc.insets = new Insets(8,12,0,0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(textoPlaca, gbc);

        //-----------------  Botão registrar -------------------//
        JButton botaoRegistrar = new JButton("REGISTRAR ENTRADA");
        AuxLayout.reset(gbc);
        AuxLayout.setup(gbc,1,5,3,1,0.0,0.0);
        gbc.insets = new Insets(15,0,0,0);

        botaoRegistrar.addActionListener(e -> {
            /*for(JComboBox box : listaCB){
                if(box.getSelectedItem().toString().equals("")){
                    //todo: Lançar uma exceção JDialog aqui
                    System.out.println("Campo vazio."); //Retirar isso aqui depois
                }
            }
            */
            System.out.println(getTipo(listaCB));
            System.out.println(getModalidade(listaCB));
            System.out.println(getHoras(listaSP));
            System.out.println(getMinutos(listaSP));
            System.out.println(getPlaca(textoPlaca));
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
        //----------------- Label Tipo Veículo -------------------//
        JLabel labelVeiculo = new JLabel("Tipo de Veículo:");
        AuxLayout.setup(gbc,1,1,1,1,0.0,0.0); // 1 1
        gbc.anchor = GridBagConstraints.LINE_END;
        panel.add(labelVeiculo, gbc);

        //----------------- Label Modalidade -------------------//
        JLabel labelModalidade = new JLabel("Modalidade:");
        AuxLayout.setup(gbc,1,2,1,1,0.0,0.0); // 1 2

        panel.add(labelModalidade, gbc);

        //----------------- Label Horário de entrada -------------------//
        JLabel labelHorario = new JLabel("Horário de entrada:");
        AuxLayout.setup(gbc,1,3,1,1,0.0,0.0);

        panel.add(labelHorario,gbc);

        //----------------- Label Placa -------------------//
        JLabel labelPlaca = new JLabel("Placa:");
        AuxLayout.setup(gbc,1,4,1,1,0.0,0.0);

        panel.add(labelPlaca, gbc);
    }

    private List<JComboBox> configurarComboBox(GridBagConstraints gbc){

        //-----------------  comboBox tipo  -------------------//
        JComboBox<String> boxTipo = new JComboBox<>();
        boxTipo.setPreferredSize(new Dimension(120, boxTipo.getPreferredSize().height));

        AuxLayout.reset(gbc);
        AuxLayout.setup(gbc,2,1,2,1,0.0,0.0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,12,0,0);

        boxTipo.addItem("");
        for(TiposEnum tipo : TiposEnum.values()){
            boxTipo.addItem(tipo.toString());
        }
        panel.add(boxTipo, gbc);

        //-----------------  comboBox modalidade  -------------------//
        JComboBox<String> boxModalidade = new JComboBox<>();
        AuxLayout.setup(gbc,2,2,2,1,0.0,0.0);
        gbc.insets = new Insets(8,12,0,0);

        boxModalidade.addItem("");
        for(ModalidadeEnum modalidade : ModalidadeEnum.values()){
            boxModalidade.addItem(modalidade.toString());
        }
        panel.add(boxModalidade, gbc);


        return List.of(boxTipo,boxModalidade);
    }

    private List<JSpinner> configurarSpinners(GridBagConstraints gbc){
        JPanel subpanelSpinners = new JPanel(new FlowLayout(FlowLayout.LEFT, 5 , 0));
        AuxLayout.setup(gbc,2,3,2,1,0.0,0.0);
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

    private String getTipo(List<JComboBox> listaCB){
        return listaCB.getFirst().getSelectedItem().toString();
    }
    private String getModalidade(List<JComboBox> listaCB){
        return listaCB.getLast().getSelectedItem().toString();
    }
    private int getHoras(List <JSpinner> listaSP){
        return (Integer) listaSP.getFirst().getModel().getValue();
    }
    private int getMinutos(List <JSpinner> listaSP){
        return (Integer) listaSP.getLast().getModel().getValue();
    }
    private String getPlaca(JTextField labelPlaca){
        return labelPlaca.getText();
    }
}
