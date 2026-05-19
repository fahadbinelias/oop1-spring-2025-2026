package hospitalmanagementsystem.entity;
public class Patient {
    private String serial;
    private String name;
    private String age;
    private String disease;
    public Patient(String serial, String name, String age, String disease) {
        this.serial = serial;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }
    public String getSerial() {
        return serial;
    }
    public String getName() {
        return name;
    }
    public String getAge() {
        return age;
    }
    public String getDisease() {
        return disease;
    }
    public void setSerial(String serial) {
        this.serial = serial;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public void setDisease(String disease) {
        this.disease = disease;
    }
    public String toLine() {
        return serial + "," + name + "," + age + "," + disease;
    }
    public static Patient fromLine(String line) {
        if (line == null)
            return null;
        String[] data = line.split(",",-1);
        if (data.length != 4)
            return null;
        return new Patient(data[0], data[1], data[2], data[3]);
    }
    public Object[] toRow() {

        return new Object[] {serial, name, age, disease};
    }
}