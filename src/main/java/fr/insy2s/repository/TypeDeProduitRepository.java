package fr.insy2s.repository;

import fr.insy2s.domain.TypeDeProduit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TypeDeProduit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeDeProduitRepository extends JpaRepository<TypeDeProduit, Long> {
}
