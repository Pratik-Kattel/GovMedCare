package com.govmedcare.service;
import com.govmedcare.dao.MedicineDao;
import com.govmedcare.exception.InvalidQuantityException;
import com.govmedcare.exception.MedicineAlreadyExistsException;
import com.govmedcare.model.Medicine;
import java.util.List;

public class MedicineService {
    MedicineDao medicineDao = new MedicineDao();

    public boolean saveMedicineService(Medicine medicine,Long user_id,int quantity) {
        boolean existsByName = medicineDao.existByName(medicine.getName());
        if(existsByName){
            throw new MedicineAlreadyExistsException("This medicine already exists please add the quantity");
        }
        if(quantity<=0){
            throw new InvalidQuantityException("Please enter a valid quantity");
        }
        medicine.setIs_verified(false);
        return  medicineDao.SaveMedicine(medicine);
    }
    public List<Medicine> getAllMedicines(){
        try{
            return medicineDao.getAllMedicine();
        }
        catch (Exception e){
            throw new RuntimeException("Unable to fetch the medicines");
        }
    }
    public List<Medicine> getAllMedicineByCategory(Long category_id){
        if(category_id==null || category_id<=0) throw new IllegalArgumentException("Invalid category id");
        try{
            return medicineDao.getAllMedicineByCategory(category_id);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to fetch the medicines by category");
        }
    }
    public int getPendingCount() {
        return medicineDao.countPendingMedicines();
    }

    public int getApprovedCount() {
        return medicineDao.countApprovedMedicines();
    }

    public boolean addStockService(int quantity, Medicine medicine){
        if(quantity<=0){
            throw new InvalidQuantityException("Please enter valid medicine quantity");
        }
        if(medicine.getMedicineID()<=0){
            throw new RuntimeException("Unknown medicine, please try again");
        }
        return medicineDao.addStock(quantity,medicine);
    }

    public List<Medicine> getAllApprovedMedicines(){
        try{
            return medicineDao.getApprovedMedicines();
        }
        catch (Exception e){
            throw new RuntimeException("Unable to fetch the approved medicines");
        }
    }
    public boolean ReduceMedicineStock(Long medicine_id, int quantity) {
        if(medicine_id<=0){
            throw new RuntimeException("Invalid medicine, please try again !!");
        }
        if(quantity<=0){
            throw new InvalidQuantityException("Please enter valid quantity");
        }
        return medicineDao.ReduceMedicineStock(medicine_id,quantity);
    }
}
