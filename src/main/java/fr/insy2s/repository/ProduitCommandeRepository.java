package fr.insy2s.repository;

import fr.insy2s.domain.ProduitCommande;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProduitCommande entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProduitCommandeRepository extends JpaRepository<ProduitCommande, Long> {
}
