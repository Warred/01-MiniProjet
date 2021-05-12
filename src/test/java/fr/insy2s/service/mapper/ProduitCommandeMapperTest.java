package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ProduitCommandeMapperTest {

    private ProduitCommandeMapper produitCommandeMapper;

    @BeforeEach
    public void setUp() {
        produitCommandeMapper = new ProduitCommandeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(produitCommandeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(produitCommandeMapper.fromId(null)).isNull();
    }
}
