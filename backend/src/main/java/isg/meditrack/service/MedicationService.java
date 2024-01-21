package isg.meditrack.service;


import isg.meditrack.endpoint.dto.DosagePlotDataDto;
import isg.meditrack.entity.Medication;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.exception.ValidationException;

import java.util.List;

public interface MedicationService {

    /**
     * Create a medication with a certain id.
     *
     * @param newMed  new medication that should be added to the database
     * @throws ValidationException if medication has invalid values
     */
    Medication create(Medication newMed) throws ValidationException;

    /**
     * Get a medication with a certain id.
     *
     * @param id  id of medication that is looked for
     * @throws NotFoundException if medication with given id doesn't exist
     */
    Medication getById(Long id) throws NotFoundException;

    /**
     * Get all medications for the user sending the request.
     *
     */
    List<Medication> getByUser();


    /**
     * Get all dosages for a medication with a name.
     *
     * @param name String of name of medication
     * */
    List<DosagePlotDataDto> getDosagePlotData(String name);

    /**
     * Create default medications for a user after registering.
     */
    void createOnRegister(String email);

    void delete(Long medId);
}
