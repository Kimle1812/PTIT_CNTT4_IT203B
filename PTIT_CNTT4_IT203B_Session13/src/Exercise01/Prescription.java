
package Exercise01;

public class Prescription {
    private int patientId;
    private int medicineId;

    public Prescription(int patientId, int medicineId) {
        this.patientId = patientId;
        this.medicineId = medicineId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getMedicineId() {
        return medicineId;
    }
}
