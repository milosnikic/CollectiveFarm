/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kicnilec
 */
public class RedemptionBillItem implements GeneralEntity {

    private RedemptionBill redemptionBill;
    private int itemNO;
    private BigDecimal quantity;
    private BigDecimal amount;
    private BigDecimal price;
    private Product product;
    private ProductCategory category;

    public RedemptionBillItem() {
    }

    public RedemptionBillItem(int itemNO, BigDecimal quantity, BigDecimal amount, BigDecimal price, Product product, ProductCategory category) {
        this.itemNO = itemNO;
        this.quantity = quantity;
        this.amount = amount;
        this.price = price;
        this.product = product;
        this.category = category;
    }

    public RedemptionBillItem(RedemptionBill redemptionBill, int itemNO, BigDecimal quantity, BigDecimal amount, BigDecimal price, Product product, ProductCategory category) {
        this.redemptionBill = redemptionBill;
        this.itemNO = itemNO;
        this.quantity = quantity;
        this.amount = amount;
        this.price = price;
        this.product = product;
        this.category = category;
    }

    public int getItemNO() {
        return itemNO;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public RedemptionBill getRedemptionBill() {
        return redemptionBill;
    }

    public void setRedemptionBill(RedemptionBill redemptionBill) {
        this.redemptionBill = redemptionBill;
    }

    public void setItemNO(int itemNO) {
        this.itemNO = itemNO;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @Override
    public String getTableName() {
        return "redemption_bill_item";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet resultSet) throws Exception {
        List<GeneralEntity> list = new LinkedList<>();
        while (resultSet.next()) {
            Long rbId = resultSet.getLong("redemption_bill_id");
            RedemptionBill rb = new RedemptionBill();
            rb.setId(rbId);

            int itemNo = resultSet.getInt("item_no");
            BigDecimal amount = resultSet.getBigDecimal("amount");
            BigDecimal quantity = resultSet.getBigDecimal("quantity");
            BigDecimal price = resultSet.getBigDecimal("price");
            ProductCategory category = ProductCategory.valueOf(resultSet.getString("category"));
            Long product_id = resultSet.getLong("product_id");
            Product product = new Product();
            product.setId(product_id);
            list.add(new RedemptionBillItem(rb, itemNo, quantity, amount, price, product, category));
        }
        return list;
    }

    @Override
    public String getColumnsForInsert() {
        return "redemption_bill_id, item_no, amount, quantity, price, category, product_id";
    }

    @Override
    public String getValuesForInsert() {
        return "'" + redemptionBill.getId() + "', '" + itemNO + "', '" + amount + "', '" + quantity + "', '" + price + "', '" + category + "', '" + product.getId() + "'";
    }

    @Override
    public String getPrimaryKey() {
        return "redemption_bill_id=" + redemptionBill.getId() + " AND item_no=" + itemNO;
    }

    @Override
    public GeneralEntity getObjectFromRs(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Long rbId = resultSet.getLong("redemption_bill_id");
                RedemptionBill rb = new RedemptionBill();
                rb.setId(rbId);

                int itemNo = resultSet.getInt("item_no");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                BigDecimal quantity = resultSet.getBigDecimal("quantity");
                BigDecimal price = resultSet.getBigDecimal("price");
                ProductCategory category = ProductCategory.valueOf(resultSet.getString("category"));
                Long product_id = resultSet.getLong("product_id");
                Product product = new Product();
                product.setId(product_id);
                return new RedemptionBillItem(rb, itemNo, quantity, amount, price, product, category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RedemptionBillItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getValuesForUpdate() {
        return "amount=" + amount + " , quantity=" + quantity + ", price=" + price + ", category='" + String.valueOf(category) + "' ,product_id=" + product.getId();
    }

    @Override
    public String getPrimaryKeyName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getLastId(ResultSet resultSet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
