package fr.insy2s.web.rest;

import fr.insy2s.service.TypeDeProduitService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.TypeDeProduitDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.TypeDeProduit}.
 */
@RestController
@RequestMapping("/api")
public class TypeDeProduitResource {

    private final Logger log = LoggerFactory.getLogger(TypeDeProduitResource.class);

    private static final String ENTITY_NAME = "typeDeProduit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeDeProduitService typeDeProduitService;

    public TypeDeProduitResource(TypeDeProduitService typeDeProduitService) {
        this.typeDeProduitService = typeDeProduitService;
    }

    /**
     * {@code POST  /type-de-produits} : Create a new typeDeProduit.
     *
     * @param typeDeProduitDTO the typeDeProduitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeDeProduitDTO, or with status {@code 400 (Bad Request)} if the typeDeProduit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/type-de-produits")
    public ResponseEntity<TypeDeProduitDTO> createTypeDeProduit(@Valid @RequestBody TypeDeProduitDTO typeDeProduitDTO) throws URISyntaxException {
        log.debug("REST request to save TypeDeProduit : {}", typeDeProduitDTO);
        if (typeDeProduitDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeDeProduit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TypeDeProduitDTO result = typeDeProduitService.save(typeDeProduitDTO);
        return ResponseEntity.created(new URI("/api/type-de-produits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /type-de-produits} : Updates an existing typeDeProduit.
     *
     * @param typeDeProduitDTO the typeDeProduitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeDeProduitDTO,
     * or with status {@code 400 (Bad Request)} if the typeDeProduitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeDeProduitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/type-de-produits")
    public ResponseEntity<TypeDeProduitDTO> updateTypeDeProduit(@Valid @RequestBody TypeDeProduitDTO typeDeProduitDTO) throws URISyntaxException {
        log.debug("REST request to update TypeDeProduit : {}", typeDeProduitDTO);
        if (typeDeProduitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TypeDeProduitDTO result = typeDeProduitService.save(typeDeProduitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeDeProduitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /type-de-produits} : get all the typeDeProduits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeDeProduits in body.
     */
    @GetMapping("/type-de-produits")
    public List<TypeDeProduitDTO> getAllTypeDeProduits() {
        log.debug("REST request to get all TypeDeProduits");
        return typeDeProduitService.findAll();
    }

    /**
     * {@code GET  /type-de-produits/:id} : get the "id" typeDeProduit.
     *
     * @param id the id of the typeDeProduitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeDeProduitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/type-de-produits/{id}")
    public ResponseEntity<TypeDeProduitDTO> getTypeDeProduit(@PathVariable Long id) {
        log.debug("REST request to get TypeDeProduit : {}", id);
        Optional<TypeDeProduitDTO> typeDeProduitDTO = typeDeProduitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeDeProduitDTO);
    }

    /**
     * {@code DELETE  /type-de-produits/:id} : delete the "id" typeDeProduit.
     *
     * @param id the id of the typeDeProduitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/type-de-produits/{id}")
    public ResponseEntity<Void> deleteTypeDeProduit(@PathVariable Long id) {
        log.debug("REST request to delete TypeDeProduit : {}", id);
        typeDeProduitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
