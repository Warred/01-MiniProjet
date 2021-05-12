package fr.insy2s.service;

import fr.insy2s.service.dto.ProduitCommandeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link fr.insy2s.domain.ProduitCommande}.
 */
public interface ProduitCommandeService {

    /**
     * Save a produitCommande.
     *
     * @param produitCommandeDTO the entity to save.
     * @return the persisted entity.
     */
    ProduitCommandeDTO save(ProduitCommandeDTO produitCommandeDTO);

    /**
     * Get all the produitCommandes.
     *
     * @return the list of entities.
     */
    List<ProduitCommandeDTO> findAll();


    /**
     * Get the "id" produitCommande.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProduitCommandeDTO> findOne(Long id);

    /**
     * Delete the "id" produitCommande.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
