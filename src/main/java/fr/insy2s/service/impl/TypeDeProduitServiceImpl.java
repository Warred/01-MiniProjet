package fr.insy2s.service.impl;

import fr.insy2s.service.TypeDeProduitService;
import fr.insy2s.domain.TypeDeProduit;
import fr.insy2s.repository.TypeDeProduitRepository;
import fr.insy2s.service.dto.TypeDeProduitDTO;
import fr.insy2s.service.mapper.TypeDeProduitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TypeDeProduit}.
 */
@Service
@Transactional
public class TypeDeProduitServiceImpl implements TypeDeProduitService {

    private final Logger log = LoggerFactory.getLogger(TypeDeProduitServiceImpl.class);

    private final TypeDeProduitRepository typeDeProduitRepository;

    private final TypeDeProduitMapper typeDeProduitMapper;

    public TypeDeProduitServiceImpl(TypeDeProduitRepository typeDeProduitRepository, TypeDeProduitMapper typeDeProduitMapper) {
        this.typeDeProduitRepository = typeDeProduitRepository;
        this.typeDeProduitMapper = typeDeProduitMapper;
    }

    @Override
    public TypeDeProduitDTO save(TypeDeProduitDTO typeDeProduitDTO) {
        log.debug("Request to save TypeDeProduit : {}", typeDeProduitDTO);
        TypeDeProduit typeDeProduit = typeDeProduitMapper.toEntity(typeDeProduitDTO);
        typeDeProduit = typeDeProduitRepository.save(typeDeProduit);
        return typeDeProduitMapper.toDto(typeDeProduit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeDeProduitDTO> findAll() {
        log.debug("Request to get all TypeDeProduits");
        return typeDeProduitRepository.findAll().stream()
            .map(typeDeProduitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TypeDeProduitDTO> findOne(Long id) {
        log.debug("Request to get TypeDeProduit : {}", id);
        return typeDeProduitRepository.findById(id)
            .map(typeDeProduitMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeDeProduit : {}", id);
        typeDeProduitRepository.deleteById(id);
    }
}
