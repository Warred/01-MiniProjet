package fr.insy2s.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.Commande} entity.
 */
public class CommandeDTO implements Serializable {
    
    private Long id;

    private LocalDate date;

    private String moyentPaiement;

    private String statutCommande;

    private Double total;


    private Long clientId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMoyentPaiement() {
        return moyentPaiement;
    }

    public void setMoyentPaiement(String moyentPaiement) {
        this.moyentPaiement = moyentPaiement;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandeDTO)) {
            return false;
        }

        return id != null && id.equals(((CommandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandeDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", moyentPaiement='" + getMoyentPaiement() + "'" +
            ", statutCommande='" + getStatutCommande() + "'" +
            ", total=" + getTotal() +
            ", clientId=" + getClientId() +
            "}";
    }
}
