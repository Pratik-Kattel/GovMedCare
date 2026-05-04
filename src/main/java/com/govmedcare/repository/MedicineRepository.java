package com.govmedcare.repository;

import com.govmedcare.model.Medicine;

import java.util.List;

public interface MedicineRepository {
    boolean SaveMedicine(Medicine medicine);
    boolean existByName(String name);
    List<Medicine> getAllMedicine();
    List<Medicine> getAllMedicineByCategory(Long category_id);
}
