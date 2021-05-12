package fr.insy2s.web.rest;

import fr.insy2s.service.ProduitCommandeService;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.service.dto.ProduitCommandeDTO;

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
 * REST controller for managing {@link fr.insy2s.domain.ProduitCommande}.
 */
@RestController
@RequestMapping("/api")
public class ProduitCommandeResource {

    private final Logger log = LoggerFactory.getLogger(ProduitCommandeResource.class);

    private static final String ENTITY_NAME = "produitCommande";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProduitCommandeService produitCommandeService;

    public ProduitCommandeResource(ProduitCommandeService produitCommandeService) {
        this.produitCommandeService = produitCommandeService;
    }

    /**
     * {@code POST  /produit-commandes} : Create a new produitCommande.
     *
     * @param produitCommandeDTO the produitCommandeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new produitCommandeDTO, or with status {@code 400 (Bad Request)} if the produitCommande has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/produit-commandes")
    public ResponseEntity<ProduitCommandeDTO> createProduitCommande(@Valid @RequestBody ProduitCommandeDTO produitCommandeDTO) throws URISyntaxException {
        log.debug("REST request to save ProduitCommande : {}", produitCommandeDTO);
        if (produitCommandeDTO.getId() != null) {
            throw new BadRequestAlertException("A new produitCommande cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProduitCommandeDTO result = produitCommandeService.save(produitCommandeDTO);
        return ResponseEntity.created(new URI("/api/produit-commandes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /produit-commandes} : Updates an existing produitCommande.
     *
     * @param produitCommandeDTO the produitCommandeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated produitCommandeDTO,
     * or with status {@code 400 (Bad Request)} if the produitCommandeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the produitCommandeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/produit-commandes")
    public ResponseEntity<ProduitCommandeDTO> updateProduitCommande(@Valid @RequestBody ProduitCommandeDTO produitCommandeDTO) throws URISyntaxException {
        log.debug("REST request to update ProduitCommande : {}", produitCommandeDTO);
        if (produitCommandeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProduitCommandeDTO result = produitCommandeService.save(produitCommandeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, produitCommandeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /produit-commandes} : get all the produitCommandes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of produitCommandes in body.
     */
    @GetMapping("/produit-commandes")
    public List<ProduitCommandeDTO> getAllProduitCommandes() {
        log.debug("REST request to get all ProduitCommandes");
        return produitCommandeService.findAll();
    }

    /**
     * {@code GET  /produit-commandes/:id} : get the "id" produitCommande.
     *
     * @param id the id of the produitCommandeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the produitCommandeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/produit-commandes/{id}")
    public ResponseEntity<ProduitCommandeDTO> getProduitCommande(@PathVariable Long id) {
        log.debug("REST request to get ProduitCommande : {}", id);
        Optional<ProduitCommandeDTO> produitCommandeDTO = produitCommandeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(produitCommandeDTO);
    }

    /**
     * {@code DELETE  /produit-commandes/:id} : delete the "id" produitCommande.
     *
     * @param id the id of the produitCommandeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/produit-commandes/{id}")
    public ResponseEntity<Void> deleteProduitCommande(@PathVariable Long id) {
        log.debug("REST request to delete ProduitCommande : {}", id);
        produitCommandeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
