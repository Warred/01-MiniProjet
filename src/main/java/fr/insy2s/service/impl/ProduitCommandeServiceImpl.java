package fr.insy2s.service.impl;

import fr.insy2s.service.ProduitCommandeService;
import fr.insy2s.domain.ProduitCommande;
import fr.insy2s.repository.ProduitCommandeRepository;
import fr.insy2s.service.dto.ProduitCommandeDTO;
import fr.insy2s.service.mapper.ProduitCommandeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ProduitCommande}.
 */
@Service
@Transactional
public class ProduitCommandeServiceImpl implements ProduitCommandeService {

    private final Logger log = LoggerFactory.getLogger(ProduitCommandeServiceImpl.class);

    private final ProduitCommandeRepository produitCommandeRepository;

    private final ProduitCommandeMapper produitCommandeMapper;

    public ProduitCommandeServiceImpl(ProduitCommandeRepository produitCommandeRepository, ProduitCommandeMapper produitCommandeMapper) {
        this.produitCommandeRepository = produitCommandeRepository;
        this.produitCommandeMapper = produitCommandeMapper;
    }

    @Override
    public ProduitCommandeDTO save(ProduitCommandeDTO produitCommandeDTO) {
        log.debug("Request to save ProduitCommande : {}", produitCommandeDTO);
        ProduitCommande produitCommande = produitCommandeMapper.toEntity(produitCommandeDTO);
        produitCommande = produitCommandeRepository.save(produitCommande);
        return produitCommandeMapper.toDto(produitCommande);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProduitCommandeDTO> findAll() {
        log.debug("Request to get all ProduitCommandes");
        return produitCommandeRepository.findAll().stream()
            .map(produitCommandeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProduitCommandeDTO> findOne(Long id) {
        log.debug("Request to get ProduitCommande : {}", id);
        return produitCommandeRepository.findById(id)
            .map(produitCommandeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProduitCommande : {}", id);
        produitCommandeRepository.deleteById(id);
    }
}
