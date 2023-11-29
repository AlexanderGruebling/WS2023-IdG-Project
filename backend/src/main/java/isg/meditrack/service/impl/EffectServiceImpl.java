package isg.meditrack.service.impl;

import isg.meditrack.entity.Effect;
import isg.meditrack.repository.EffectRepository;
import isg.meditrack.service.EffectService;
import isg.meditrack.service.EntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class EffectServiceImpl implements EffectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final EffectRepository effectRepository;
    private final EntryService entryService;

    public EffectServiceImpl (EffectRepository effectRepository, EntryService entryService)
    {
        this.effectRepository = effectRepository;
        this.entryService = entryService;
    }

    @Override
    public Effect create(Effect newEffect, Long entryId) {
        LOGGER.debug("Create new effect {}", newEffect);
        newEffect.setEntry(entryService.getById(entryId));
        newEffect = effectRepository.save(newEffect);
        return newEffect;
    }
}
