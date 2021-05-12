package fr.insy2s.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeDeProduitMapperTest {

    private TypeDeProduitMapper typeDeProduitMapper;

    @BeforeEach
    public void setUp() {
        typeDeProduitMapper = new TypeDeProduitMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(typeDeProduitMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(typeDeProduitMapper.fromId(null)).isNull();
    }
}
