package fr.insy2s.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TypeDeProduit.
 */
@Entity
@Table(name = "type_de_produit")
public class TypeDeProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(mappedBy = "type")
    private Set<Produit> produits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public TypeDeProduit type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public TypeDeProduit produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public TypeDeProduit addProduits(Produit produit) {
        this.produits.add(produit);
        produit.setType(this);
        return this;
    }

    public TypeDeProduit removeProduits(Produit produit) {
        this.produits.remove(produit);
        produit.setType(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeDeProduit)) {
            return false;
        }
        return id != null && id.equals(((TypeDeProduit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeDeProduit{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            "}";
    }
}
