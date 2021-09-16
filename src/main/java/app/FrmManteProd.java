package app;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.sql.Select;

import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;

import model.Categoria;
import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	private JTextField txtCódigo;
	JComboBox cboCategorias;
	private JTextField txtDescrip;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTextField txtEstado;
	private JTextArea txtSalida;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.setBounds(324, 29, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JButton btnListado = new JButton("Listado");
		btnListado.setBounds(185, 399, 89, 23);
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		contentPane.add(btnListado);
		
		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 30, 188, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(18, 38, 102, 14);
		contentPane.add(lblCodigo);
		
		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 153, 188, 22);
		contentPane.add(cboCategorias);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(18, 161, 102, 14);
		contentPane.add(lblCategora);
		
		JLabel lblDescrip = new JLabel("Descripcion :");
		lblDescrip.setBounds(18, 61, 68, 20);
		contentPane.add(lblDescrip);
		
		txtDescrip = new JTextField();
		txtDescrip.setBounds(122, 61, 188, 20);
		contentPane.add(txtDescrip);
		txtDescrip.setColumns(10);
		
		JLabel lblStock = new JLabel("Stock : ");
		lblStock.setBounds(18, 98, 68, 14);
		contentPane.add(lblStock);
		
		txtStock = new JTextField();
		txtStock.setBounds(122, 92, 188, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(18, 123, 46, 27);
		contentPane.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(122, 123, 188, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(18, 194, 46, 14);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(122, 191, 188, 20);
		contentPane.add(txtEstado);
		txtEstado.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 248, 439, 140);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		llenaCombo();
	}
	
	void llenaCombo() {
		
		EntityManagerFactory fabrica= Persistence.createEntityManagerFactory("mysql");
		
		EntityManager em= fabrica.createEntityManager();
		
		TypedQuery<Categoria>query=em.createQuery("select e from Categoria e", Categoria.class);
		
		List<Categoria> lista= query.getResultList();
		
		cboCategorias.addItem("Seleccione");
		for (Categoria c : lista) {
			
			
			cboCategorias.addItem(c.getDescripcion());
		}
		
		
	}

	void listado() {
		EntityManagerFactory fabrica= Persistence.createEntityManagerFactory("mysql");
		
		EntityManager em= fabrica.createEntityManager();
		
		TypedQuery<Producto>query=em.createQuery("select a from Producto a",Producto.class);
		
		
		List<Producto>lstProducto=query.getResultList();
		
		for(Producto p:lstProducto) {
			

			txtSalida.append("Codigo \t: "+p.getIdprod()+"\n"+
							  "Nombre\t: "+p.getDescripcion()+"\n"+
							  " Stock\t: "+p.getStock()+"\n"+
							  "Precio\t: "+p.getPrecio()+"\n"+
							  "Categoria\t: "+p.getIdcategoria()+"\n"+
							  "Estado\t: "+p.getEstado()+"\n");
			txtSalida.append("------------------------------------------\n");
		}
	}
	
	void registrar() {
		
		//
		
		String codigo, nombre; 
		double precio;
		int stock , categoria, estado;
		codigo= txtCódigo.getText();
		nombre= txtDescrip.getText();
		stock= Integer.parseInt(txtStock.getText());
		precio=Double.parseDouble(txtPrecio.getText());
		categoria = Integer.parseInt(cboCategorias.getSelectedItem().toString());
		estado= Integer.parseInt(txtEstado.getText());
		//
		
		EntityManagerFactory fabrica= Persistence.createEntityManagerFactory("mysql");
		
		EntityManager em= fabrica.createEntityManager();
		
		Producto p = new Producto();
		
		p.setIdprod(codigo);
		p.setDescripcion(nombre);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setIdcategoria(categoria);	
		p.setEstado(estado);
		
		em.getTransaction().begin();
		
		em.persist(p);
		
		em.getTransaction().commit();
		
		System.out.println("Registro exitoso");
		
		em.close();
	}
}
