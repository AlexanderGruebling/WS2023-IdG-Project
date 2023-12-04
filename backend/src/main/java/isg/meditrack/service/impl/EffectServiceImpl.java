package isg.meditrack.service.impl;

import isg.meditrack.entity.Effect;
import isg.meditrack.entity.Entry;
import isg.meditrack.entity.Medication;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.repository.EffectRepository;
import isg.meditrack.service.EffectService;
import isg.meditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class EffectServiceImpl implements EffectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final EffectRepository effectRepository;

    public EffectServiceImpl(EffectRepository effectRepository) {
        this.effectRepository = effectRepository;
    }

    @Override
    public Effect create(Effect newEffect, Entry entry) {
        LOGGER.debug("Create new effect {}", newEffect);
        newEffect.setEntry(entry);
        newEffect = effectRepository.save(newEffect);
        return newEffect;
    }

    @Override
    public Effect getById(Long id) {
        LOGGER.debug("Get medication {}", id);

        return effectRepository.getReferenceById(id);
    }

    @Override
    public List<Effect> getByMedication(Long medId) {
        LOGGER.debug("Get effects by Medication");


        return effectRepository.findAllByMedId(medId);
    }
    @Override
    public List<Effect> getByEntry(Long entryId) {
        LOGGER.debug("Get effects by Entry");


        return effectRepository.findAllByEntryId(entryId);
    }
}
