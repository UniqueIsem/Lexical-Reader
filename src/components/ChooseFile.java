package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChooseFile {

    private static JFrame frame;
    private static JPanel panelContenedor;
    private static JFileChooser fileChooser;
    private static File archivo;
    private static String rutaSeleccionada;

    public ChooseFile() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(700, 500));
        frame.setResizable(false);
        frame.setUndecorated(false);
        frame.setLocationRelativeTo(null);

        fileChooser = new JFileChooser();

        panelContenedor = new JPanel(new BorderLayout());
        panelContenedor.add(fileChooser, BorderLayout.CENTER);

        frame.add(panelContenedor);

        addEvents();
    }

    private void addEvents() {
        fileChooser.addActionListener(e -> {
            if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
                archivo = null;
                rutaSeleccionada = null;
            } else if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
                archivo = fileChooser.getSelectedFile();
                rutaSeleccionada = archivo.isDirectory() ? archivo.getAbsolutePath() : null;
            }
            frame.dispose();
        });
    }

    public String abrirArchivo() {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (.txt)", "txt", "java");
        fileChooser.setFileFilter(filtro);

        frame.setVisible(true);
        while (archivo == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        return archivo.getAbsolutePath();
    }

    public String seleccionarRuta() {
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        frame.setVisible(true);
        while (rutaSeleccionada == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return rutaSeleccionada;
    }
    
     public boolean borrarArhivo(){
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        frame.setVisible(true);
        
        while (archivo == null){
            try{
                Thread.sleep(100);
            }catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        boolean success = archivo.delete();
        archivo = null; //Reiniciamos la seleccion despues de borrar el archivo.
        return success;
    }
    //Esta función nos permitirá seleccionar el archivo necesario.

    public static void main(String[] args) {
        String selectedFilePath = new ChooseFile().seleccionarRuta();
        if (selectedFilePath != null) {
            System.out.println("Carpeta seleccionada: " + selectedFilePath);
        } else {
            System.out.println("No se seleccionó ninguna carpeta.");
        }
    }
}
