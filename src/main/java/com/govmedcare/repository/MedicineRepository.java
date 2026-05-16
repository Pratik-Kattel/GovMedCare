package com.govmedcare.repository;

import com.govmedcare.model.Medicine;

import java.util.List;

public interface MedicineRepository {
    boolean SaveMedicine(Medicine medicine);
    boolean existByName(String name);
    List<Medicine> getAllMedicine();
    List<Medicine> getAllMedicineByCategory(Long category_id);
     int countPendingMedicines();
     int countApprovedMedicines();
     boolean addStock(int totalMedicine, Medicine medicine);
     List<Medicine> getApprovedMedicines();
     boolean ReduceMedicineStock(Long medicine_id,int quantity);
    List<Medicine> getApprovedByCategoryAsc(Long category_id);
    List<Medicine> getApprovedByCategoryDesc(Long category_id);
    Medicine getMedicineById(Long medicineId);

}
