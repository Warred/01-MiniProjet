package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class ProduitCommandeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProduitCommandeDTO.class);
        ProduitCommandeDTO produitCommandeDTO1 = new ProduitCommandeDTO();
        produitCommandeDTO1.setId(1L);
        ProduitCommandeDTO produitCommandeDTO2 = new ProduitCommandeDTO();
        assertThat(produitCommandeDTO1).isNotEqualTo(produitCommandeDTO2);
        produitCommandeDTO2.setId(produitCommandeDTO1.getId());
        assertThat(produitCommandeDTO1).isEqualTo(produitCommandeDTO2);
        produitCommandeDTO2.setId(2L);
        assertThat(produitCommandeDTO1).isNotEqualTo(produitCommandeDTO2);
        produitCommandeDTO1.setId(null);
        assertThat(produitCommandeDTO1).isNotEqualTo(produitCommandeDTO2);
    }
}
