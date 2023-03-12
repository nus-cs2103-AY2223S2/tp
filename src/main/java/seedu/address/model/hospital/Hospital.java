package seedu.address.model.hospital;

public class Hospital {
    private final String hospitalName;
    private final String hotline;

    public Hospital(String hospitalName, String hotline) {
        this.hospitalName = hospitalName;
        this.hotline = hotline;
    }

    public String getHospitalName() {
        return this.hospitalName;
    }

    public String getHotline() {
        return this.hotline;
    }

    public boolean isSameHospital(Hospital otherHospital) {
        if (otherHospital == this) {
            return true;
        }
        return otherHospital != null
                && otherHospital.getHospitalName().equals(getHospitalName());
    }
}
