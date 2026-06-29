package com.smarthospital.platform.service;

import com.smarthospital.platform.dto.request.MedicineRequest;
import com.smarthospital.platform.dto.response.MedicineResponse;

import java.util.List;

/**
 * Service contract for Medicine catalogue management.
 */
public interface MedicineService {

    /** Add a new medicine to the catalogue. Throws {@code DuplicateResourceException} if name exists. */
    MedicineResponse createMedicine(MedicineRequest request);

    /** Retrieve a medicine by ID. */
    MedicineResponse getMedicineById(Long id);

    /** List all medicines in the catalogue. */
    List<MedicineResponse> getAllMedicines();

    /** Update medicine details. Null fields are preserved. */
    MedicineResponse updateMedicine(Long id, MedicineRequest request);

    /** Delete a medicine from the catalogue. */
    void deleteMedicine(Long id);
}
