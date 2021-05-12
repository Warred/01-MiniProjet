package fr.insy2s.web.rest;

import fr.insy2s.CommerceApp;
import fr.insy2s.domain.ProduitCommande;
import fr.insy2s.domain.Produit;
import fr.insy2s.domain.Commande;
import fr.insy2s.repository.ProduitCommandeRepository;
import fr.insy2s.service.ProduitCommandeService;
import fr.insy2s.service.dto.ProduitCommandeDTO;
import fr.insy2s.service.mapper.ProduitCommandeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ProduitCommandeResource} REST controller.
 */
@SpringBootTest(classes = CommerceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProduitCommandeResourceIT {

    private static final Integer DEFAULT_QUANTITE = 1;
    private static final Integer UPDATED_QUANTITE = 2;

    @Autowired
    private ProduitCommandeRepository produitCommandeRepository;

    @Autowired
    private ProduitCommandeMapper produitCommandeMapper;

    @Autowired
    private ProduitCommandeService produitCommandeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProduitCommandeMockMvc;

    private ProduitCommande produitCommande;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProduitCommande createEntity(EntityManager em) {
        ProduitCommande produitCommande = new ProduitCommande()
            .quantite(DEFAULT_QUANTITE);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        produitCommande.setProduit(produit);
        // Add required entity
        Commande commande;
        if (TestUtil.findAll(em, Commande.class).isEmpty()) {
            commande = CommandeResourceIT.createEntity(em);
            em.persist(commande);
            em.flush();
        } else {
            commande = TestUtil.findAll(em, Commande.class).get(0);
        }
        produitCommande.setCommande(commande);
        return produitCommande;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProduitCommande createUpdatedEntity(EntityManager em) {
        ProduitCommande produitCommande = new ProduitCommande()
            .quantite(UPDATED_QUANTITE);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createUpdatedEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        produitCommande.setProduit(produit);
        // Add required entity
        Commande commande;
        if (TestUtil.findAll(em, Commande.class).isEmpty()) {
            commande = CommandeResourceIT.createUpdatedEntity(em);
            em.persist(commande);
            em.flush();
        } else {
            commande = TestUtil.findAll(em, Commande.class).get(0);
        }
        produitCommande.setCommande(commande);
        return produitCommande;
    }

    @BeforeEach
    public void initTest() {
        produitCommande = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduitCommande() throws Exception {
        int databaseSizeBeforeCreate = produitCommandeRepository.findAll().size();
        // Create the ProduitCommande
        ProduitCommandeDTO produitCommandeDTO = produitCommandeMapper.toDto(produitCommande);
        restProduitCommandeMockMvc.perform(post("/api/produit-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitCommandeDTO)))
            .andExpect(status().isCreated());

        // Validate the ProduitCommande in the database
        List<ProduitCommande> produitCommandeList = produitCommandeRepository.findAll();
        assertThat(produitCommandeList).hasSize(databaseSizeBeforeCreate + 1);
        ProduitCommande testProduitCommande = produitCommandeList.get(produitCommandeList.size() - 1);
        assertThat(testProduitCommande.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
    }

    @Test
    @Transactional
    public void createProduitCommandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = produitCommandeRepository.findAll().size();

        // Create the ProduitCommande with an existing ID
        produitCommande.setId(1L);
        ProduitCommandeDTO produitCommandeDTO = produitCommandeMapper.toDto(produitCommande);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProduitCommandeMockMvc.perform(post("/api/produit-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitCommandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProduitCommande in the database
        List<ProduitCommande> produitCommandeList = produitCommandeRepository.findAll();
        assertThat(produitCommandeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkQuantiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitCommandeRepository.findAll().size();
        // set the field null
        produitCommande.setQuantite(null);

        // Create the ProduitCommande, which fails.
        ProduitCommandeDTO produitCommandeDTO = produitCommandeMapper.toDto(produitCommande);


        restProduitCommandeMockMvc.perform(post("/api/produit-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitCommandeDTO)))
            .andExpect(status().isBadRequest());

        List<ProduitCommande> produitCommandeList = produitCommandeRepository.findAll();
        assertThat(produitCommandeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProduitCommandes() throws Exception {
        // Initialize the database
        produitCommandeRepository.saveAndFlush(produitCommande);

        // Get all the produitCommandeList
        restProduitCommandeMockMvc.perform(get("/api/produit-commandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produitCommande.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE)));
    }
    
    @Test
    @Transactional
    public void getProduitCommande() throws Exception {
        // Initialize the database
        produitCommandeRepository.saveAndFlush(produitCommande);

        // Get the produitCommande
        restProduitCommandeMockMvc.perform(get("/api/produit-commandes/{id}", produitCommande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(produitCommande.getId().intValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE));
    }
    @Test
    @Transactional
    public void getNonExistingProduitCommande() throws Exception {
        // Get the produitCommande
        restProduitCommandeMockMvc.perform(get("/api/produit-commandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduitCommande() throws Exception {
        // Initialize the database
        produitCommandeRepository.saveAndFlush(produitCommande);

        int databaseSizeBeforeUpdate = produitCommandeRepository.findAll().size();

        // Update the produitCommande
        ProduitCommande updatedProduitCommande = produitCommandeRepository.findById(produitCommande.getId()).get();
        // Disconnect from session so that the updates on updatedProduitCommande are not directly saved in db
        em.detach(updatedProduitCommande);
        updatedProduitCommande
            .quantite(UPDATED_QUANTITE);
        ProduitCommandeDTO produitCommandeDTO = produitCommandeMapper.toDto(updatedProduitCommande);

        restProduitCommandeMockMvc.perform(put("/api/produit-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitCommandeDTO)))
            .andExpect(status().isOk());

        // Validate the ProduitCommande in the database
        List<ProduitCommande> produitCommandeList = produitCommandeRepository.findAll();
        assertThat(produitCommandeList).hasSize(databaseSizeBeforeUpdate);
        ProduitCommande testProduitCommande = produitCommandeList.get(produitCommandeList.size() - 1);
        assertThat(testProduitCommande.getQuantite()).isEqualTo(UPDATED_QUANTITE);
    }

    @Test
    @Transactional
    public void updateNonExistingProduitCommande() throws Exception {
        int databaseSizeBeforeUpdate = produitCommandeRepository.findAll().size();

        // Create the ProduitCommande
        ProduitCommandeDTO produitCommandeDTO = produitCommandeMapper.toDto(produitCommande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProduitCommandeMockMvc.perform(put("/api/produit-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(produitCommandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProduitCommande in the database
        List<ProduitCommande> produitCommandeList = produitCommandeRepository.findAll();
        assertThat(produitCommandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduitCommande() throws Exception {
        // Initialize the database
        produitCommandeRepository.saveAndFlush(produitCommande);

        int databaseSizeBeforeDelete = produitCommandeRepository.findAll().size();

        // Delete the produitCommande
        restProduitCommandeMockMvc.perform(delete("/api/produit-commandes/{id}", produitCommande.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProduitCommande> produitCommandeList = produitCommandeRepository.findAll();
        assertThat(produitCommandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
