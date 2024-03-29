/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.redemptionbill;

import controller.Controller;
import domain.Manufacturer;
import domain.Product;
import domain.RedemptionBill;
import domain.RedemptionBillItem;
import domain.User;
import form.FormMode;
import form.manufacturer.FormManufacturer;
import form.product.model.TableProductModel;
import form.redemptionbill.redemptionbillitems.model.TableRedemptionBillItemsModel;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import session.Session;

/**
 *
 * @author kicnilec
 */
public class FormRedemptionBill extends javax.swing.JDialog {

    private BigDecimal totalAmount;
    private LinkedList<RedemptionBillItem> items;
    private RedemptionBill rb;

    /**
     * Creates new form FormRedemptionBill
     */
    public FormRedemptionBill(java.awt.Frame parent, boolean modal, FormMode mode) {
        super(parent, modal);
        initComponents();
        items = new LinkedList<>();
        totalAmount = new BigDecimal(0);
        rb = new RedemptionBill();
        populateForm();
        adjustForm(mode);
        setLocationRelativeTo(null);
    }

    public FormRedemptionBill(java.awt.Frame parent, boolean modal, RedemptionBill redemptionBill) {
        super(parent, modal);
        initComponents();
        try {
            totalAmount = redemptionBill.getTotal();
            items = Controller.getInstance().getRedemptionBillItems(redemptionBill);
            rb = redemptionBill;
        } catch (Exception ex) {
            Logger.getLogger(FormRedemptionBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateForm();
        populateRedemptionBillInfo(rb);
        setLocationRelativeTo(null);
    }

    public FormRedemptionBill(java.awt.Frame parent, boolean modal, RedemptionBill redemptionBill, FormMode mode) {
        super(parent, modal);
        initComponents();
        try {
            totalAmount = redemptionBill.getTotal();
            items = Controller.getInstance().getRedemptionBillItems(redemptionBill);
            rb = redemptionBill;
        } catch (Exception ex) {
            Logger.getLogger(FormRedemptionBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        populateForm();
        adjustForm(mode);
        populateRedemptionBillInfo(rb);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtRedemptionBillId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRedemptionBillItems = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        lblProductid = new javax.swing.JLabel();
        txtProductId = new javax.swing.JTextField();
        lblProductName = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        txtProductPrice = new javax.swing.JTextField();
        lblProductPrice = new javax.swing.JLabel();
        lblProductQuantity = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cmbManufacturer = new javax.swing.JComboBox();
        lblUser = new javax.swing.JLabel();
        btnRemove = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Redemption Bill");

        jLabel1.setText("Redemption Bill ID:");

        txtRedemptionBillId.setEditable(false);

        jLabel2.setText("Date (dd.mm.yyyy.):");

        txtAmount.setEditable(false);

        jLabel3.setText("Amount:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Redemption Bill Items"));

        tblRedemptionBillItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblRedemptionBillItems);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Products"));

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProducts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProducts);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lblProductid.setText("Product ID:");

        txtProductId.setEditable(false);

        lblProductName.setText("Name:");

        txtProductName.setEditable(false);

        txtProductPrice.setEditable(false);

        lblProductPrice.setText("Price:");

        lblProductQuantity.setText("Quantity:");

        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        txtQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnSave.setText("Save Redemption Bill");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel8.setText("Manufacturer:");

        cmbManufacturer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnRemove.setText("Remove Item");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblUser)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtRedemptionBillId)
                                            .addComponent(txtAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                                            .addComponent(txtDate)
                                            .addComponent(cmbManufacturer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNew)
                            .addComponent(btnRemove)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblProductQuantity)
                                        .addGap(27, 27, 27)
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(93, 93, 93)
                                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnBack)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEdit))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblProductName)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblProductPrice)
                                                .addGap(46, 46, 46)
                                                .addComponent(txtProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblProductid)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSave)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancel)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRedemptionBillId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnRemove)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductid)
                    .addComponent(txtProductId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductName)
                    .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblProductPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblProductQuantity))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnEdit)
                                .addComponent(btnUpdate)
                                .addComponent(btnBack)
                                .addComponent(btnCancel))
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductsMouseClicked
        LinkedList<Product> products = (LinkedList<Product>) ((TableProductModel) tblProducts.getModel()).getProducts();
        int i = tblProducts.getSelectedRow();
        Product product = products.get(i);
        txtProductId.setText(String.valueOf(product.getId()));
        txtProductName.setText(String.valueOf(product.getName()));
        txtProductPrice.setText(String.valueOf(product.getPrice()));
        txtQuantity.setText("");
    }//GEN-LAST:event_tblProductsMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String s = txtQuantity.getText().trim();
        //Provera da li je uneseno nesto za quantity
        if (!s.isEmpty()) {
            BigDecimal quantity = new BigDecimal(s);
            if (quantity.compareTo(new BigDecimal(0)) == 1) {
                LinkedList<RedemptionBillItem> items = ((TableRedemptionBillItemsModel) tblRedemptionBillItems.getModel()).getItems();
                LinkedList<Product> products = (LinkedList<Product>) ((TableProductModel) tblProducts.getModel()).getProducts();
                int row = tblProducts.getSelectedRow();
                //Provera da li je izabran red
                if (row != -1) {
                    Product product = products.get(row);
                    //Status ima vrednost false jer pretpostavljamo da se proizvod ne nalazi u stavkama, ukoliko se ne nalazi
                    //Dodacemo ga, u suprotnom update-ovati
                    boolean status = false;

                    BigDecimal amount = quantity.multiply(product.getPrice());
                    totalAmount = totalAmount.add(amount);
                    for (RedemptionBillItem item : items) {
                        if (item.getProduct().equals(product)) {
                            ((TableRedemptionBillItemsModel) tblRedemptionBillItems.getModel()).updateItem(item, quantity, amount);
                            status = true;
                            break;
                        }
                    }
                    if (status == false) {
                        //Potrebno je dodati stavku
                        int no = 1;
                        if (!items.isEmpty()) {
                            no = items.getLast().getItemNO() + 1;
                        }
                        RedemptionBillItem item = new RedemptionBillItem(rb, no, quantity, amount, product.getPrice(), product, product.getCategory());
                        ((TableRedemptionBillItemsModel) tblRedemptionBillItems.getModel()).addItem(item);
                    }
                    txtAmount.setText(String.valueOf(totalAmount));
                    txtQuantity.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Please select product!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter quantity greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please enter quantity!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        int row = tblRedemptionBillItems.getSelectedRow();
        LinkedList<RedemptionBillItem> items = ((TableRedemptionBillItemsModel) tblRedemptionBillItems.getModel()).getItems();
        if (row != -1) {
            RedemptionBillItem item = items.get(row);
            ((TableRedemptionBillItemsModel) tblRedemptionBillItems.getModel()).deleteItem(item);
            totalAmount = totalAmount.subtract(item.getAmount());
            System.out.println("PROVERA:");
            System.out.println(totalAmount);
            System.out.println(item.getAmount());
            System.out.println("-----------");
            txtAmount.setText(String.valueOf(totalAmount));
        } else {
            JOptionPane.showMessageDialog(this, "Please select item!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String dateString = txtDate.getText().trim();
        Manufacturer manufacturer = (Manufacturer) cmbManufacturer.getSelectedItem();
        LinkedList<RedemptionBillItem> items = ((TableRedemptionBillItemsModel) tblRedemptionBillItems.getModel()).getItems();
        if (!dateString.isEmpty()) {
            if (manufacturer != null) {
                try {
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("dd.mm.yyyy.").parse(dateString);
                        if (items.size() > 0) {
                            User user = Session.getInstance().getUser();
                            RedemptionBill redemptionBill = new RedemptionBill(date, totalAmount, items, user, manufacturer);
                            rb = redemptionBill;
                            boolean saved = Controller.getInstance().saveRedemptionBill(rb);
                            if (saved) {
                                JOptionPane.showMessageDialog(this, "Redemption bill saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "Redemption bill has not been saved!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Please add at least one item!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter date in propriate format!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FormRedemptionBill.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please select manufacturer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter date!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        JDialog formNewManufacturer = new FormManufacturer((JFrame) SwingUtilities.getWindowAncestor(this), true, FormMode.NEW);
        formNewManufacturer.setVisible(true);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        adjustForm(FormMode.EDIT);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        adjustForm(FormMode.VIEW);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String dateString = txtDate.getText().trim();
        Manufacturer manufacturer = (Manufacturer) cmbManufacturer.getSelectedItem();
        LinkedList<RedemptionBillItem> items = ((TableRedemptionBillItemsModel) tblRedemptionBillItems.getModel()).getItems();
        if (!dateString.isEmpty()) {
            if (manufacturer != null) {
                try {
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("dd.mm.yyyy.").parse(dateString);
                        if (items.size() > 0) {
                            User user = Session.getInstance().getUser();
                            Long id = new Long(txtRedemptionBillId.getText());
                            RedemptionBill redemptionBill = new RedemptionBill(id, date, totalAmount, items, user, manufacturer);
                            rb = redemptionBill;
                            boolean updated = Controller.getInstance().updateRedemptionBill(rb);
                            if (updated) {
                                JOptionPane.showMessageDialog(this, "Redemption bill saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(this, "Redemption bill has not been saved!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(this, "Please add at least one item!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter date in propriate format!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FormRedemptionBill.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please select manufacturer!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter date!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbManufacturer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblProductName;
    private javax.swing.JLabel lblProductPrice;
    private javax.swing.JLabel lblProductQuantity;
    private javax.swing.JLabel lblProductid;
    private javax.swing.JLabel lblUser;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTable tblRedemptionBillItems;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtProductId;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtProductPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtRedemptionBillId;
    // End of variables declaration//GEN-END:variables

    private void populateForm() {
        populateProductsTable();
        setRedemptionBillItemsModel();
        populateCmbManufacturer();
        populateUser();
    }

    private void populateProductsTable() {
        try {
            LinkedList<Product> products = (LinkedList<Product>) Controller.getInstance().getAllProducts();
            TableProductModel tpm = new TableProductModel(products);
            tblProducts.setModel(tpm);
            tblProducts.removeColumn(tblProducts.getColumnModel().getColumn(6));
            tblProducts.removeColumn(tblProducts.getColumnModel().getColumn(0));
        } catch (Exception ex) {
            Logger.getLogger(FormRedemptionBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateCmbManufacturer() {
        try {
            LinkedList<Manufacturer> manufactures = (LinkedList<Manufacturer>) Controller.getInstance().getAllManufactures();
            cmbManufacturer.removeAllItems();
            for (Manufacturer manufacturer : manufactures) {
                cmbManufacturer.addItem(manufacturer);
            }
        } catch (Exception ex) {
            Logger.getLogger(FormRedemptionBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populateUser() {
        lblUser.setText(String.valueOf(Session.getInstance().getUser()));
    }

    private void setRedemptionBillItemsModel() {
        TableRedemptionBillItemsModel trbim = new TableRedemptionBillItemsModel(items);
        tblRedemptionBillItems.setModel(trbim);
    }

    private void populateRedemptionBillInfo(RedemptionBill redemptionBill) {
        txtRedemptionBillId.setText(String.valueOf(redemptionBill.getId()));
        txtAmount.setText(String.valueOf(redemptionBill.getTotal()));
        txtDate.setText(new SimpleDateFormat("dd.MM.yyyy.").format(redemptionBill.getDate()));
        cmbManufacturer.setSelectedItem(redemptionBill.getManufacturer());
        lblUser.setText(String.valueOf(redemptionBill.getUserCreated()));
    }

    private void adjustForm(FormMode mode) {
        switch (mode) {
            case NEW:
                btnRemove.setVisible(true);
                btnAdd.setEnabled(true);
                txtQuantity.setEnabled(true);
                btnEdit.setVisible(false);
                btnCancel.setVisible(true);
                btnUpdate.setVisible(false);
                btnBack.setVisible(false);
                btnSave.setVisible(true);
                txtDate.setEnabled(true);
                cmbManufacturer.setEnabled(true);
                setTitle("New Redemption Bill");
                break;
            case EDIT:
                btnRemove.setVisible(true);
                btnAdd.setEnabled(true);
                txtQuantity.setEnabled(true);
                btnEdit.setVisible(false);
                btnCancel.setVisible(true);
                btnUpdate.setVisible(true);
                btnBack.setVisible(true);
                btnSave.setVisible(false);
                txtDate.setEnabled(true);
                cmbManufacturer.setEnabled(true);
                setTitle("Edit Redemption Bill");
                break;
            case VIEW:
                btnRemove.setVisible(false);
                btnAdd.setEnabled(false);
                txtQuantity.setEnabled(false);
                btnEdit.setVisible(true);
                btnCancel.setVisible(true);
                btnUpdate.setVisible(false);
                btnBack.setVisible(false);
                txtDate.setEnabled(false);
                cmbManufacturer.setEnabled(false);
                btnSave.setVisible(false);
                setTitle("View Redemption Bill");
                break;
        }
    }

}
