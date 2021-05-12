package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.ProduitCommandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProduitCommande} and its DTO {@link ProduitCommandeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, CommandeMapper.class})
public interface ProduitCommandeMapper extends EntityMapper<ProduitCommandeDTO, ProduitCommande> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "commande.id", target = "commandeId")
    ProduitCommandeDTO toDto(ProduitCommande produitCommande);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "commandeId", target = "commande")
    ProduitCommande toEntity(ProduitCommandeDTO produitCommandeDTO);

    default ProduitCommande fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProduitCommande produitCommande = new ProduitCommande();
        produitCommande.setId(id);
        return produitCommande;
    }
}
