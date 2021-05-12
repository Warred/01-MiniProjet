package fr.insy2s.web.rest;

import fr.insy2s.CommerceApp;
import fr.insy2s.domain.TypeDeProduit;
import fr.insy2s.repository.TypeDeProduitRepository;
import fr.insy2s.service.TypeDeProduitService;
import fr.insy2s.service.dto.TypeDeProduitDTO;
import fr.insy2s.service.mapper.TypeDeProduitMapper;

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
 * Integration tests for the {@link TypeDeProduitResource} REST controller.
 */
@SpringBootTest(classes = CommerceApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeDeProduitResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private TypeDeProduitRepository typeDeProduitRepository;

    @Autowired
    private TypeDeProduitMapper typeDeProduitMapper;

    @Autowired
    private TypeDeProduitService typeDeProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeDeProduitMockMvc;

    private TypeDeProduit typeDeProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeDeProduit createEntity(EntityManager em) {
        TypeDeProduit typeDeProduit = new TypeDeProduit()
            .type(DEFAULT_TYPE);
        return typeDeProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeDeProduit createUpdatedEntity(EntityManager em) {
        TypeDeProduit typeDeProduit = new TypeDeProduit()
            .type(UPDATED_TYPE);
        return typeDeProduit;
    }

    @BeforeEach
    public void initTest() {
        typeDeProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeDeProduit() throws Exception {
        int databaseSizeBeforeCreate = typeDeProduitRepository.findAll().size();
        // Create the TypeDeProduit
        TypeDeProduitDTO typeDeProduitDTO = typeDeProduitMapper.toDto(typeDeProduit);
        restTypeDeProduitMockMvc.perform(post("/api/type-de-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeDeProduit in the database
        List<TypeDeProduit> typeDeProduitList = typeDeProduitRepository.findAll();
        assertThat(typeDeProduitList).hasSize(databaseSizeBeforeCreate + 1);
        TypeDeProduit testTypeDeProduit = typeDeProduitList.get(typeDeProduitList.size() - 1);
        assertThat(testTypeDeProduit.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createTypeDeProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeDeProduitRepository.findAll().size();

        // Create the TypeDeProduit with an existing ID
        typeDeProduit.setId(1L);
        TypeDeProduitDTO typeDeProduitDTO = typeDeProduitMapper.toDto(typeDeProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeDeProduitMockMvc.perform(post("/api/type-de-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDeProduit in the database
        List<TypeDeProduit> typeDeProduitList = typeDeProduitRepository.findAll();
        assertThat(typeDeProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = typeDeProduitRepository.findAll().size();
        // set the field null
        typeDeProduit.setType(null);

        // Create the TypeDeProduit, which fails.
        TypeDeProduitDTO typeDeProduitDTO = typeDeProduitMapper.toDto(typeDeProduit);


        restTypeDeProduitMockMvc.perform(post("/api/type-de-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeProduitDTO)))
            .andExpect(status().isBadRequest());

        List<TypeDeProduit> typeDeProduitList = typeDeProduitRepository.findAll();
        assertThat(typeDeProduitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTypeDeProduits() throws Exception {
        // Initialize the database
        typeDeProduitRepository.saveAndFlush(typeDeProduit);

        // Get all the typeDeProduitList
        restTypeDeProduitMockMvc.perform(get("/api/type-de-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeDeProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }
    
    @Test
    @Transactional
    public void getTypeDeProduit() throws Exception {
        // Initialize the database
        typeDeProduitRepository.saveAndFlush(typeDeProduit);

        // Get the typeDeProduit
        restTypeDeProduitMockMvc.perform(get("/api/type-de-produits/{id}", typeDeProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeDeProduit.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }
    @Test
    @Transactional
    public void getNonExistingTypeDeProduit() throws Exception {
        // Get the typeDeProduit
        restTypeDeProduitMockMvc.perform(get("/api/type-de-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeDeProduit() throws Exception {
        // Initialize the database
        typeDeProduitRepository.saveAndFlush(typeDeProduit);

        int databaseSizeBeforeUpdate = typeDeProduitRepository.findAll().size();

        // Update the typeDeProduit
        TypeDeProduit updatedTypeDeProduit = typeDeProduitRepository.findById(typeDeProduit.getId()).get();
        // Disconnect from session so that the updates on updatedTypeDeProduit are not directly saved in db
        em.detach(updatedTypeDeProduit);
        updatedTypeDeProduit
            .type(UPDATED_TYPE);
        TypeDeProduitDTO typeDeProduitDTO = typeDeProduitMapper.toDto(updatedTypeDeProduit);

        restTypeDeProduitMockMvc.perform(put("/api/type-de-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeProduitDTO)))
            .andExpect(status().isOk());

        // Validate the TypeDeProduit in the database
        List<TypeDeProduit> typeDeProduitList = typeDeProduitRepository.findAll();
        assertThat(typeDeProduitList).hasSize(databaseSizeBeforeUpdate);
        TypeDeProduit testTypeDeProduit = typeDeProduitList.get(typeDeProduitList.size() - 1);
        assertThat(testTypeDeProduit.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeDeProduit() throws Exception {
        int databaseSizeBeforeUpdate = typeDeProduitRepository.findAll().size();

        // Create the TypeDeProduit
        TypeDeProduitDTO typeDeProduitDTO = typeDeProduitMapper.toDto(typeDeProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeDeProduitMockMvc.perform(put("/api/type-de-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeDeProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeDeProduit in the database
        List<TypeDeProduit> typeDeProduitList = typeDeProduitRepository.findAll();
        assertThat(typeDeProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeDeProduit() throws Exception {
        // Initialize the database
        typeDeProduitRepository.saveAndFlush(typeDeProduit);

        int databaseSizeBeforeDelete = typeDeProduitRepository.findAll().size();

        // Delete the typeDeProduit
        restTypeDeProduitMockMvc.perform(delete("/api/type-de-produits/{id}", typeDeProduit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeDeProduit> typeDeProduitList = typeDeProduitRepository.findAll();
        assertThat(typeDeProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
