package fr.insy2s.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import fr.insy2s.web.rest.TestUtil;

public class TypeDeProduitTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeDeProduit.class);
        TypeDeProduit typeDeProduit1 = new TypeDeProduit();
        typeDeProduit1.setId(1L);
        TypeDeProduit typeDeProduit2 = new TypeDeProduit();
        typeDeProduit2.setId(typeDeProduit1.getId());
        assertThat(typeDeProduit1).isEqualTo(typeDeProduit2);
        typeDeProduit2.setId(2L);
        assertThat(typeDeProduit1).isNotEqualTo(typeDeProduit2);
        typeDeProduit1.setId(null);
        assertThat(typeDeProduit1).isNotEqualTo(typeDeProduit2);
    }
}
