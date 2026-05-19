package hospitalmanagementsystem;
import hospitalmanagementsystem.gui.PatientGUI;
import javax.swing.SwingUtilities;

public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PatientGUI::new);
    }
    
}
