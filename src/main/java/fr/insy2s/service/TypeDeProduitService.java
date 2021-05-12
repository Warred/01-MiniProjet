package fr.insy2s.service;

import fr.insy2s.service.dto.TypeDeProduitDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.TypeDeProduit}.
 */
public interface TypeDeProduitService {

    /**
     * Save a typeDeProduit.
     *
     * @param typeDeProduitDTO the entity to save.
     * @return the persisted entity.
     */
    TypeDeProduitDTO save(TypeDeProduitDTO typeDeProduitDTO);

    /**
     * Get all the typeDeProduits.
     *
     * @return the list of entities.
     */
    List<TypeDeProduitDTO> findAll();


    /**
     * Get the "id" typeDeProduit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeDeProduitDTO> findOne(Long id);

    /**
     * Delete the "id" typeDeProduit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
