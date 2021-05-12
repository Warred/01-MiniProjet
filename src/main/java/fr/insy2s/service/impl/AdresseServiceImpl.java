package fr.insy2s.service.impl;

import fr.insy2s.service.AdresseService;
import fr.insy2s.domain.Adresse;
import fr.insy2s.repository.AdresseRepository;
import fr.insy2s.service.dto.AdresseDTO;
import fr.insy2s.service.mapper.AdresseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link Adresse}.
 */
@Service
@Transactional
public class AdresseServiceImpl implements AdresseService {

    private final Logger log = LoggerFactory.getLogger(AdresseServiceImpl.class);

    private final AdresseRepository adresseRepository;

    private final AdresseMapper adresseMapper;

    public AdresseServiceImpl(AdresseRepository adresseRepository, AdresseMapper adresseMapper) {
        this.adresseRepository = adresseRepository;
        this.adresseMapper = adresseMapper;
    }

    @Override
    public AdresseDTO save(AdresseDTO adresseDTO) {
        log.debug("Request to save Adresse : {}", adresseDTO);
        Adresse adresse = adresseMapper.toEntity(adresseDTO);
        adresse = adresseRepository.save(adresse);
        return adresseMapper.toDto(adresse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdresseDTO> findAll() {
        log.debug("Request to get all Adresses");
        return adresseRepository.findAll().stream()
            .map(adresseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  Get all the adresses where Client is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AdresseDTO> findAllWhereClientIsNull() {
        log.debug("Request to get all adresses where Client is null");
        return StreamSupport
            .stream(adresseRepository.findAll().spliterator(), false)
            .filter(adresse -> adresse.getClient() == null)
            .map(adresseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AdresseDTO> findOne(Long id) {
        log.debug("Request to get Adresse : {}", id);
        return adresseRepository.findById(id)
            .map(adresseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Adresse : {}", id);
        adresseRepository.deleteById(id);
    }
}
