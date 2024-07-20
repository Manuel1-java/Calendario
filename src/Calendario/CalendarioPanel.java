package calendario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CalendarioPanel extends JPanel {
    private JComboBox<String> comboMes;
    private JComboBox<Integer> comboAnio;
    private JPanel panelDias;
    private Calendar calendario;
    private Font fuenteArial;
    private static final HashMap<String, Set<Integer>> FESTIVOS = new HashMap<>();

    public CalendarioPanel() {
        calendario = new GregorianCalendar();
        fuenteArial = new Font("Arial", Font.PLAIN, 12);
        initComponents();
        inicializarFestivos();
        actualizarCalendario();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(102, 102, 102));

        comboMes = new JComboBox<>(new String[]{
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        });

        comboAnio = new JComboBox<>();
        for (int i = 1900; i <= 2100; i++) {
            comboAnio.addItem(i);
        }

        comboMes.setSelectedIndex(calendario.get(Calendar.MONTH));
        comboAnio.setSelectedItem(calendario.get(Calendar.YEAR));

        comboMes.setFont(fuenteArial);
        comboAnio.setFont(fuenteArial);

        comboMes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int mesSeleccionado = comboMes.getSelectedIndex();
                calendario.set(Calendar.MONTH, mesSeleccionado);
                actualizarCalendario();
            }
        });

        comboAnio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int anioSeleccionado = (Integer) comboAnio.getSelectedItem();
                calendario.set(Calendar.YEAR, anioSeleccionado);
                actualizarCalendario();
            }
        });

        panelSuperior.add(comboMes);
        panelSuperior.add(comboAnio);
        add(panelSuperior, BorderLayout.NORTH);

        panelDias = new JPanel(new GridLayout(0, 7));
        panelDias.setBackground(new Color(102, 102, 102));
        add(panelDias, BorderLayout.CENTER);
    }

    private void actualizarCalendario() {
        panelDias.removeAll();

        String[] dias = {"D", "L", "Ma", "Mi", "J", "V", "S"};
        for (String dia : dias) {
            JLabel lblDia = new JLabel(dia, SwingConstants.CENTER);
            lblDia.setFont(fuenteArial); 
            lblDia.setForeground(Color.WHITE);
            panelDias.add(lblDia);
        }

        calendario.set(Calendar.DAY_OF_MONTH, 1);
        int primerDiaDeLaSemana = calendario.get(Calendar.DAY_OF_WEEK);
        int diasEnElMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i < primerDiaDeLaSemana; i++) {
            panelDias.add(new JLabel(""));
        }

        for (int i = 1; i <= diasEnElMes; i++) {
            JButton btnDia = new JButton(String.valueOf(i));
            btnDia.setFont(fuenteArial);
            btnDia.setBackground(Color.WHITE);
            btnDia.setForeground(Color.BLACK);

            // Marcar día festivo
            if (esDiaFestivo(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), i)) {
                btnDia.setBackground(Color.GRAY);
                btnDia.setForeground(Color.WHITE);
            }
            panelDias.add(btnDia);
        }
            panelDias.revalidate();
            panelDias.repaint();
    }

    private void inicializarFestivos() {
        agregarFestivo(0, 1);  
        agregarFestivo(1, 5); 
        agregarFestivo(2, 21); 
        agregarFestivo(4, 1); 
        agregarFestivo(8, 16);
        agregarFestivo(10, 1);
        agregarFestivo(10, 2);
        agregarFestivo(10, 20); 
        agregarFestivo(11, 25);
    }

    private void agregarFestivo(int mes, int dia) {
         // Convierte el mes a String y agrega un nuevo HashSet si no existe
        FESTIVOS.putIfAbsent(String.valueOf(mes), new HashSet<>());
        // Agrega el día al conjunto de festivos para el mes dado
        FESTIVOS.get(String.valueOf(mes)).add(dia);
    }

    private boolean esDiaFestivo(int anio, int mes, int dia) {
        // Devuelve true si el día está en el conjunto de festivos para el mes dado
        return FESTIVOS.getOrDefault(String.valueOf(mes), Collections.emptySet()).contains(dia);
    }
}