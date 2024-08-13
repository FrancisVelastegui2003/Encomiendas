package encomiendas.views.facturacion;

import encomiendas.controllers.facturacion.FacturaController;
import encomiendas.controllers.encomiendas.EncomiendaController;
import encomiendas.database.Conexion;
import encomiendas.model.data.facturacion.FacturaRepository;
import encomiendas.model.entity.encomiendas.Encomienda;
import encomiendas.model.entity.facturacion.Factura;
import encomiendas.model.entity.usuarios.Usuario;
import encomiendas.services.facturacion.FacturaService;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class JFFactura extends javax.swing.JFrame {
    
    private Connection myConn;
    private FacturaController facturaController;
    private EncomiendaController encomiendaController;

    public JFFactura() {
        initComponents();
        this.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        this.setTitle("Genera tu Factura");
        
        // Inicializar la conexión y los controladores
        this.myConn = Conexion.getInstance();
        this.facturaController = new FacturaController(new FacturaService(new FacturaRepository(myConn)));
        //this.encomiendaController = new EncomiendaController(null, null); // Necesitarás inicializar EncomiendaService
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnFactura = new javax.swing.JButton();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(37, 37, 58));

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 22)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("GENERA LA FACTURA DE TU COMPRA!");

        btnFactura.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
        btnFactura.setText("Descargar Factura");
        btnFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturaActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.jpg"))); 

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(btnFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(logo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void btnFacturaActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // Aquí debes implementar la lógica para obtener la encomienda y el cliente correctos
        //Encomienda encomienda = encomiendaController.getEncomiendaById(1); // Reemplaza 1 con el ID real
        //Usuario cliente = encomiendaController.obtenerCliente("V12345678"); // Reemplaza con la cédula real
        
        // Llamar al controlador para generar la factura
        //Factura factura = facturaController.generarFacturaDesdeVista(encomienda, cliente);
        // Mostrar la nueva ventana con el detalle de la factura
        JFDetalleFactura detalleFactura = new JFDetalleFactura();
       // detalleFactura.cargarFactura(factura);
        detalleFactura.setVisible(true);
    }                                          

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFFactura().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btnFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logo;
}