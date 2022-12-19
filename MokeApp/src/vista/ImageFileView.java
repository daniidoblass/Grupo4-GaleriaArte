package vista;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * Clase usada para añadir iconos customs a los archivos para el JFileChooser de subir archivo
 */
public class ImageFileView extends FileView {
    ImageIcon jpgIcon = Utils.createImageIcon("../iconos/jpgIcon.gif");
    ImageIcon gifIcon = Utils.createImageIcon("../iconos/gifIcon.gif");
    ImageIcon tiffIcon = Utils.createImageIcon("../iconos/tiffIcon.gif");
    ImageIcon pngIcon = Utils.createImageIcon("../iconos/pngIcon.png");

    public String getName(File f) {
        return null; //let the L&F FileView figure this out
    }

    public String getDescription(File f) {
        return null; //let the L&F FileView figure this out
    }

    public Boolean isTraversable(File f) {
        return null; //let the L&F FileView figure this out
    }

    public Icon getIcon(File f) {
        String extension = Utils.getExtension(f);
        Icon icon = null;

        if (extension != null) {
            if (extension.equals(Utils.jpeg) ||
                extension.equals(Utils.jpg)) {
                icon = jpgIcon;
            } else if (extension.equals(Utils.gif)) {
                icon = gifIcon;
            } else if (extension.equals(Utils.tiff) ||
                       extension.equals(Utils.tif)) {
                icon = tiffIcon;
            } else if (extension.equals(Utils.png)) {
                icon = pngIcon;
            }
        }
        return icon;
    }
}
