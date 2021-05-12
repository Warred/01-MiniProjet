package fr.insy2s.service.mapper;


import fr.insy2s.domain.*;
import fr.insy2s.service.dto.TypeDeProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeDeProduit} and its DTO {@link TypeDeProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeDeProduitMapper extends EntityMapper<TypeDeProduitDTO, TypeDeProduit> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduits", ignore = true)
    TypeDeProduit toEntity(TypeDeProduitDTO typeDeProduitDTO);

    default TypeDeProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeDeProduit typeDeProduit = new TypeDeProduit();
        typeDeProduit.setId(id);
        return typeDeProduit;
    }
}
