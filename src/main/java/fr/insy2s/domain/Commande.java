package fr.insy2s.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Commande.
 */
@Entity
@Table(name = "commande")
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "moyent_paiement")
    private String moyentPaiement;

    @Column(name = "statut_commande")
    private String statutCommande;

    @Column(name = "total")
    private Double total;

    @OneToMany(mappedBy = "commande")
    private Set<ProduitCommande> paniers = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "commandes", allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Commande date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMoyentPaiement() {
        return moyentPaiement;
    }

    public Commande moyentPaiement(String moyentPaiement) {
        this.moyentPaiement = moyentPaiement;
        return this;
    }

    public void setMoyentPaiement(String moyentPaiement) {
        this.moyentPaiement = moyentPaiement;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public Commande statutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
        return this;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public Double getTotal() {
        return total;
    }

    public Commande total(Double total) {
        this.total = total;
        return this;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<ProduitCommande> getPaniers() {
        return paniers;
    }

    public Commande paniers(Set<ProduitCommande> produitCommandes) {
        this.paniers = produitCommandes;
        return this;
    }

    public Commande addPanier(ProduitCommande produitCommande) {
        this.paniers.add(produitCommande);
        produitCommande.setCommande(this);
        return this;
    }

    public Commande removePanier(ProduitCommande produitCommande) {
        this.paniers.remove(produitCommande);
        produitCommande.setCommande(null);
        return this;
    }

    public void setPaniers(Set<ProduitCommande> produitCommandes) {
        this.paniers = produitCommandes;
    }

    public Client getClient() {
        return client;
    }

    public Commande client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commande)) {
            return false;
        }
        return id != null && id.equals(((Commande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Commande{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", moyentPaiement='" + getMoyentPaiement() + "'" +
            ", statutCommande='" + getStatutCommande() + "'" +
            ", total=" + getTotal() +
            "}";
    }
}
