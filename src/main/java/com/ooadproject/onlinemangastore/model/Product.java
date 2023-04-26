package com.ooadproject.onlinemangastore.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

/**
 * The type Product.
 *
 * @author Givantha Kalansuriya
 */
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    @Column(name = "balance_quantity", nullable = false)
    private String balanceQuantity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "updated_by", nullable = false)
    @LastModifiedBy
    private String updatedBy;

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
        return id;
    }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
        this.id = id;
    }

  /**
   * Gets product name.
   *
   * @return the product name
   */
  public String getProductName() {
        return productName;
    }

  /**
   * Sets product name.
   *
   * @param productName the product name
   */
  public void setFirstName(String productName) {
        this.productName = productName;
  }




   /**
   * Sets last name.
   *
   * @param productName the product name
   */
   public void setProductName(String productName) {
    this.productName = productName;
  }
 /**
   * Sets last name.
   *
   * @param productPrice the product price
   */
  public void setProductPrice(Integer productPrice) {
    this.productPrice = productPrice;
  }
 /**
   * Sets last name.
   *
   * @param balanceQuantity the balance quantity
   */
  public void setBalanceQuantity(String balanceQuantity) {
    this.balanceQuantity = balanceQuantity;
  }

  /**
   * Gets product price.
   *
   * @return the product price
   */ 
  public Integer getProductPrice() {
    return productPrice;
  }
  /**
   * Gets product price.
   *
   * @return the product price
   */ 
  public String getBalanceQuantity() {
    return balanceQuantity;
  }


  /**
   * Gets created at.
   *
   * @return the created at
   */
  public Date getCreatedAt() {
        return createdAt;
    }

  /**
   * Sets created at.
   *
   * @param createdAt the created at
   */
  public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

  /**
   * Gets created by.
   *
   * @return the created by
   */
  public String getCreatedBy() {
        return createdBy;
    }

  /**
   * Sets created by.
   *
   * @param createdBy the created by
   */
  public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

  /**
   * Gets updated at.
   *
   * @return the updated at
   */
  public Date getUpdatedAt() {
        return updatedAt;
    }

  /**
   * Sets updated at.
   *
   * @param updatedAt the updated at
   */
  public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

  /**
   * Gets updated by.
   *
   * @return the updated by
   */
  public String getUpdatedBy() {
        return updatedBy;
    }

  /**
   * Sets updated by.
   *
   * @param updatedBy the updated by
   */
  public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

  @Override
  public String toString() {
    return "Product [id=" + id + ", productName=" + productName + ", productPrice=" + productPrice
        + ", balanceQuantity=" + balanceQuantity + ", createdAt=" + createdAt + ", createdBy=" + createdBy
        + ", updatedAt=" + updatedAt + ", updatedBy=" + updatedBy + "]";
  }



}
