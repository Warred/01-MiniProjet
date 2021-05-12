package fr.insy2s.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class TypeDeProduitDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeDeProduitDTO.class);
        TypeDeProduitDTO typeDeProduitDTO1 = new TypeDeProduitDTO();
        typeDeProduitDTO1.setId(1L);
        TypeDeProduitDTO typeDeProduitDTO2 = new TypeDeProduitDTO();
        assertThat(typeDeProduitDTO1).isNotEqualTo(typeDeProduitDTO2);
        typeDeProduitDTO2.setId(typeDeProduitDTO1.getId());
        assertThat(typeDeProduitDTO1).isEqualTo(typeDeProduitDTO2);
        typeDeProduitDTO2.setId(2L);
        assertThat(typeDeProduitDTO1).isNotEqualTo(typeDeProduitDTO2);
        typeDeProduitDTO1.setId(null);
        assertThat(typeDeProduitDTO1).isNotEqualTo(typeDeProduitDTO2);
    }
}
