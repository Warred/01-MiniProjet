package fr.insy2s.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link fr.insy2s.domain.ProduitCommande} entity.
 */
public class ProduitCommandeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer quantite;


    private Long produitId;

    private Long commandeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProduitCommandeDTO)) {
            return false;
        }

        return id != null && id.equals(((ProduitCommandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitCommandeDTO{" +
            "id=" + getId() +
            ", quantite=" + getQuantite() +
            ", produitId=" + getProduitId() +
            ", commandeId=" + getCommandeId() +
            "}";
    }
}
