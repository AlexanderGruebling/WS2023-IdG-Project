package isg.meditrack.service.impl;

import isg.meditrack.entity.Effect;
import isg.meditrack.entity.Entry;
import isg.meditrack.repository.EffectRepository;
import isg.meditrack.service.EffectService;
import isg.meditrack.service.EntryService;
import isg.meditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EffectServiceImpl implements EffectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final EffectRepository effectRepository;
    private final EntryService entryService;

    public EffectServiceImpl(EffectRepository effectRepository,
                             EntryService entryService, UserService userService) {
        this.effectRepository = effectRepository;
        this.entryService = entryService;
    }

    @Override
    public Effect create(Effect newEffect, Entry entry) {
        LOGGER.debug("Create new effect {}", newEffect);
        newEffect.setEntry(entry);
        newEffect.setDate(entry.getDate());
        newEffect = effectRepository.save(newEffect);
        return newEffect;
    }

    @Override
    public Effect update(Effect updatedEff, Entry entry) {
        LOGGER.debug("Update effect {}", updatedEff);

        Effect effToUpdate = effectRepository.getReferenceById(updatedEff.getId());

        effToUpdate.setDate(entry.getDate());
        effToUpdate.setDescription(updatedEff.getDescription());
        effToUpdate.setDesired(updatedEff.getDesired());
        effToUpdate.setIntensity(updatedEff.getIntensity());
        effToUpdate.setMedication(updatedEff.getMedication());

        effectRepository.save(effToUpdate);

        return updatedEff;
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

    @Override
    public List<String> getAllEffectNames() {
        List<Entry> entries = entryService.getByUser();
        List<String> effectNames = new ArrayList<>();

        for (Entry entry : entries) {
            List<Effect> effects = this.getByEntry(entry.getId());
            for (Effect effect: effects) {
                effectNames.add(effect.getName());
            }
        }

        List<String> distinctEffectNames = effectNames.stream().distinct().collect(Collectors.toList());

        Collections.sort(distinctEffectNames);

        return distinctEffectNames;
    }

    @Override
    public List<Effect> getByName(String name) {
        LOGGER.debug("Get effects by Name for the current user");
        List<Entry> entries = entryService.getByUser();
        List<Effect> effectsByName = new ArrayList<>();

        for (Entry entry : entries) {
            List<Effect> effects = this.getByEntry(entry.getId());
            for (Effect effect: effects) {
                if (effect.getName().equals(name)){
                    effectsByName.add(effect);
                }
            }
        }
        return effectsByName;
    }

    @Override
    public void deleteForEntry(Long entryId) {
        List<Effect> effects = getByEntry(entryId);

        effectRepository.deleteAll(effects);
    }
}
