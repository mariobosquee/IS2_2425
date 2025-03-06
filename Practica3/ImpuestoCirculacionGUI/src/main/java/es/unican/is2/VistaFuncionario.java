package es.unican.is2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

import javax.swing.JList;

import javax.swing.border.LineBorder;

import es.unican.is2.impuesto_circulacion.business.IInfoImpuestoCirculacion;
import es.unican.is2.impuesto_circulacion.domain.Contribuyente;
import es.unican.is2.impuesto_circulacion.domain.Vehiculo;
import es.unican.is2.impuesto_circulacion.exceptions.DataAccessException;

import java.awt.Color;
import javax.swing.JButton;

/**
 * Clase que implementa la vista del funcionario dentro de la capa de
 * presentacion de la aplicacion usando Swing
 */
@SuppressWarnings("serial")
public class VistaFuncionario extends JFrame {

	private JPanel contentPane;
	private JTextField txtDniContribuyente;
	private JTextField txtTotalContribuyente;
	private JTextField txtNombreContribuyente;
	private JList<String> listMatriculasVehiculos;
	private DefaultListModel<String> listModel;
	private JButton btnBuscar;

	private IInfoImpuestoCirculacion info;

	/**
	 * Create the frame.
	 */
	public VistaFuncionario(IInfoImpuestoCirculacion info) {

		this.info = info;
		init();
	}

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		listModel = new DefaultListModel<String>();

		txtTotalContribuyente = new JTextField();
		txtTotalContribuyente.setBounds(230, 251, 86, 20);
		contentPane.add(txtTotalContribuyente);
		txtTotalContribuyente.setColumns(10);
		txtTotalContribuyente.setName("txtTotalContribuyente");

		JLabel lblTotalContribuyente = new JLabel("Total A Pagar");
		lblTotalContribuyente.setBounds(137, 254, 99, 14);
		contentPane.add(lblTotalContribuyente);

		listMatriculasVehiculos = new JList<String>();
		listMatriculasVehiculos.setBounds(230, 98, 121, 116);
		contentPane.add(listMatriculasVehiculos);
		listMatriculasVehiculos.setBorder(new LineBorder(new Color(0, 0, 0)));
		listMatriculasVehiculos.setModel(listModel);
		listMatriculasVehiculos.setName("listMatriculasVehiculos");

		JLabel lblVehiculos = new JLabel("Vehiculos");
		lblVehiculos.setBounds(149, 93, 65, 14);
		contentPane.add(lblVehiculos);

		JLabel lblNombreContribuyente = new JLabel("Nombre");
		lblNombreContribuyente.setBounds(155, 54, 65, 14);
		contentPane.add(lblNombreContribuyente);

		txtNombreContribuyente = new JTextField();
		txtNombreContribuyente.setBounds(230, 51, 121, 20);
		contentPane.add(txtNombreContribuyente);
		txtNombreContribuyente.setColumns(10);
		txtNombreContribuyente.setName("txtNombreContribuyente");

		JLabel lblDatosContribuyente = new JLabel("Datos Contribuyente");
		lblDatosContribuyente.setBounds(230, 11, 149, 14);
		contentPane.add(lblDatosContribuyente);

		txtDniContribuyente = new JTextField();
		txtDniContribuyente.setBounds(10, 51, 113, 20);
		contentPane.add(txtDniContribuyente);
		txtDniContribuyente.setColumns(10);

		JLabel lblDniContribuyente = new JLabel("DNI Contribuyente");
		lblDniContribuyente.setBounds(21, 27, 139, 14);
		contentPane.add(lblDniContribuyente);
		lblDniContribuyente.setName("lblDniContribuyente");

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rellenaDatosContribuyente(txtDniContribuyente.getText());
			}
		});
		btnBuscar.setBounds(21, 122, 89, 23);
		contentPane.add(btnBuscar);
		btnBuscar.setName("btnBuscar");
		listMatriculasVehiculos.setVisible(true);
	}

	private void rellenaDatosContribuyente(String dni) {
		DecimalFormat df = new DecimalFormat("0.00");
		Contribuyente c;
		try {
			c = info.contribuyente(dni);
			if (c != null) {
				txtNombreContribuyente.setText(c.getNombre() + " " + c.getApellido2() + " " + c.getApellido1());
				txtTotalContribuyente.setText(df.format(c.totalImpuestoCirculacion()));
				listModel.removeAllElements();
				for (int i = 1; i < c.getVehiculos().size(); i++) {
					Vehiculo v = c.getVehiculos().get(i);
					listModel.addElement(v.getMatricula());
				}
			} else {
				txtNombreContribuyente.setText("DNI Incorrecto");
				txtTotalContribuyente.setText("0");
				listModel.removeAllElements();
			}

		} catch (DataAccessException e) {
			txtNombreContribuyente.setText("Error BBDD");
			e.printStackTrace();
		}

	}
}
