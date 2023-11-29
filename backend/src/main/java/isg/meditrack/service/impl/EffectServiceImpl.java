package isg.meditrack.service.impl;

import isg.meditrack.entity.Effect;
import isg.meditrack.entity.Entry;
import isg.meditrack.repository.EffectRepository;
import isg.meditrack.service.EffectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class EffectServiceImpl implements EffectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final EffectRepository effectRepository;

    public EffectServiceImpl (EffectRepository effectRepository)
    {
        this.effectRepository = effectRepository;
    }

    @Override
    public Effect create(Effect newEffect, Entry entry) {
        LOGGER.debug("Create new effect {}", newEffect);
        newEffect.setEntry(entry);
        newEffect = effectRepository.save(newEffect);
        return newEffect;
    }
}
