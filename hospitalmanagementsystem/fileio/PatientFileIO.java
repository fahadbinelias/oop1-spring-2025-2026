package hospitalmanagementsystem.fileio;

import hospitalmanagementsystem.entity.Patient;

import java.io.*;

public class PatientFileIO {
    private static final String FILE_NAME =
            "hospitalmanagementsystem/fileio/patients.txt";
    private static final String TEMP_FILE =
            "hospitalmanagementsystem/fileio/temp.txt";
    public static void createFileIfNotExists() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists())
            file.createNewFile();
    }
    public static boolean idExists(String serial) {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Patient p = Patient.fromLine(line);
                if (p != null && p.getSerial().equals(serial))
                    return true;
            }
        }
        catch (IOException ignored) {
        }
        return false;
    }
    public static int countRecords() {
        int count = 0;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (Patient.fromLine(line) != null)
                    count++;
            }
        }
        catch(IOException ignored) {
        }
        return count;
    }
    public static void addPatient(Patient p) throws IOException {
        try (PrintWriter pw =
                     new PrintWriter(
                             new BufferedWriter(
                                     new FileWriter(FILE_NAME, true)))) {

            pw.println(p.toLine());
        }
    }
    public static boolean updatePatient(Patient p) throws IOException {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File(TEMP_FILE);
        boolean found = false;
        try (
                BufferedReader br =
                        new BufferedReader(new FileReader(inputFile));
                BufferedWriter bw =
                        new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                Patient existing = Patient.fromLine(line);
                if (existing != null &&
                        existing.getSerial().equals(p.getSerial())) {
                    bw.write(p.toLine());
                    found = true;
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }
        }
        if (found) {
            if (!inputFile.delete() ||
                    !tempFile.renameTo(inputFile)) {
                throw new IOException("Could not finalize update.");
            }
        } else {
            tempFile.delete();
        }
        return found;
    }
    public static boolean deletePatient(String serial)
            throws IOException {
        File inputFile = new File(FILE_NAME);
        File tempFile = new File(TEMP_FILE);
        boolean found = false;
        try (
                BufferedReader br =
                        new BufferedReader(new FileReader(inputFile));
                BufferedWriter bw =
                        new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                Patient p = Patient.fromLine(line);
                if (p != null &&
                        p.getSerial().equals(serial)) {
                    found = true;
                } else {

                    bw.write(line);

                    bw.newLine();
                }
            }
        }
        if (found) {
            if (!inputFile.delete() ||
                    !tempFile.renameTo(inputFile)) {
                throw new IOException("Could not finalize delete.");
            }
        } else {
            tempFile.delete();
        }
        return found;
    }
    public static Object[][] getAllPatients() {
        int total = countRecords();
        Object[][] rows = new Object[total][4];
        int idx = 0;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null &&
                    idx < total) {
                Patient p = Patient.fromLine(line);
                if (p != null) {
                    Object[] row = p.toRow();
                    rows[idx][0] = row[0];
                    rows[idx][1] = row[1];
                    rows[idx][2] = row[2];
                    rows[idx][3] = row[3];
                    idx++;
                }
            }
        } catch (IOException ignored) {
        }
        return rows;    }
    public static Object[][] searchPatients(String keyword) {
        String kw = keyword.toLowerCase();
        int matchCount = 0;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Patient p = Patient.fromLine(line);
                if (p != null &&
                        (p.getSerial().toLowerCase().contains(kw)
                                || p.getName().toLowerCase().contains(kw))) {
                    matchCount++;
                }
            }
        } catch (IOException ignored) {
        }
        Object[][] results = new Object[matchCount][4];
        int idx = 0;
        try (BufferedReader br =
                     new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null &&
                    idx < matchCount) {
                Patient p = Patient.fromLine(line);
                if (p != null &&
                        (p.getSerial().toLowerCase().contains(kw)
                                || p.getName().toLowerCase().contains(kw))) {
                    Object[] row = p.toRow();
                    results[idx][0] = row[0];
                    results[idx][1] = row[1];
                    results[idx][2] = row[2];
                    results[idx][3] = row[3];
                    idx++;
                }
            }
        } catch (IOException ignored) {
        }
        return results;
    }
}

