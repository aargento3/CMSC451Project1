import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Created by aargento on 8/25/17.
 *
 * Reference: JVM Warmup code adapted from http://www.baeldung.com/java-jvm-warmup
 */

public class SortMain extends JFrame {

    // warm up the Java Virtual Machine
    static {
        jvmLoader.load();
    }

    // JVM warmup classes
    static class jvmWarmUp{
        void m(){
        }
    }//end jvmWarmUp

    private static class jvmLoader {
        static void load(){
            for (int i = 0; i < 10000; i++){
                jvmWarmUp warmUp = new jvmWarmUp();
                warmUp.m();
            }
        }
    }//end jvmLoader

    // Method to set UI font
    public static void setUIFont (javax.swing.plaf.FontUIResource f){

        java.util.Enumeration keys = UIManager.getDefaults().keys();

        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
    }//end setUIFont

    // Constructor
    SortMain() {

        // Set GUI font to more readable format
        setUIFont(new javax.swing.plaf.FontUIResource("Times New Roman",Font.BOLD,14));

        // Define output label components
        JTextArea outputLabel = new JTextArea();
        outputLabel.setEditable(false);

		// Define JPanels
        JPanel panelOutput = new JPanel();
        panelOutput.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        panelOutput.setPreferredSize(new Dimension(690, 175));

        // Add components to Output JPanel
        panelOutput.add(outputLabel);

        // Define container to group panels
        Container containerPnlGrp = new Container();
        containerPnlGrp.setLayout(new BoxLayout(containerPnlGrp, BoxLayout.Y_AXIS));
        containerPnlGrp.add(panelOutput);

        // Create frame and define parameters of GUI
        JFrame frameMainGUI = new JFrame ();
        frameMainGUI.setTitle("CMSC 451 Project 1");
        frameMainGUI.setPreferredSize(new Dimension(970, 395));
        frameMainGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMainGUI.add(containerPnlGrp);
        frameMainGUI.pack ();
        frameMainGUI.setLocationRelativeTo(null);
        frameMainGUI.setVisible(true);

        // Define data set sizes
        int[] dataSetSizes = new int[] {1000, 3000, 6000, 7000, 8000, 9000, 10000, 11000, 12000, 16000};

        BenchmarkSorts benchmarkSorts = new BenchmarkSorts(dataSetSizes);

        // Call BenchmarkSorts --> runSorts method and catch any unhandled exception
        try{
            benchmarkSorts.runSorts();
        } catch (UnsortedException e) {
            System.out.print(e.getMessage());
        }

        // Use StringBuilder to display data on GUI
        StringBuilder report;
        report = benchmarkSorts.displayReport();
        outputLabel.setText(String.valueOf(report));

    }//end constructor

    public static void main(String[] args) {

        // Warm up the Java Virtual Machine
        jvmLoader.load();

        // Call constructor
        new SortMain();

    }//end main

}//end SortMain