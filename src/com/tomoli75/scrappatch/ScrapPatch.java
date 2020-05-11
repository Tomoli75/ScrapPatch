package com.tomoli75.scrappatch;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import static javax.swing.JOptionPane.*;

public class ScrapPatch {
    public static void main(String[] args) {
        new gui();
    }
}
class gui extends JFrame {
    gui()
    {
        AtomicReference<String> path = new AtomicReference<>(System.getenv("ProgramFiles") + "\\Steam\\steamapps\\common\\Scrap Mechanic\\Survival\\Scripts\\game");
        System.out.println(path.get());
        JPanel pa = new JPanel();
        pa.setLayout(new BorderLayout());
        JLabel credit = new JLabel("ScrapPatch by Tomoli75.");
        JButton github = new JButton("Github / Website");
        JButton normal = new JButton("Remove Patch");
        JButton patch = new JButton("Patch Cheats");
        JLabel operatingsystem = new JLabel("Intended for use on Windows.");
        pa.add(credit, BorderLayout.NORTH);
        pa.add(github, BorderLayout.CENTER);
        pa.add(normal, BorderLayout.EAST);
        pa.add(patch,BorderLayout.WEST);
        pa.add(operatingsystem, BorderLayout.SOUTH);
        setTitle("ScrapPatch");
        add(pa);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
        github.addActionListener(event->{
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("https://tomoli75.github.io/ScrapPatch/"));
                } catch (IOException | URISyntaxException e) {
                    showMessageDialog(null,"Couldn't open the Github Link.");
                }
            } else {
                showMessageDialog(null,"Couldn't open the Github Link.");
            }
        });
        normal.addActionListener(event->{
            try {
                String normalLua = new Scanner(new URL("https://raw.githubusercontent.com/Tomoli75/ScrapPatch/master/normal.lua").openStream(), "UTF-8").useDelimiter("\\A").next();
                AtomicReference<File> f = new AtomicReference<>(new File(path + "\\SurvivalGame.lua"));
                if(f.get().exists()) {
                    if (f.get().delete()) {
                        if (f.get().createNewFile()) {
                            FileWriter w = new FileWriter(path + "\\SurvivalGame.lua");
                            w.write(normalLua);
                            w.close();
                            showMessageDialog(null, "Successfully removed patch!");
                        } else {
                            showMessageDialog(null, "Couldn't create the new file.");
                        }
                    } else {
                        showMessageDialog(null, "Couldn't delete old file.");
                    }
                }  else {
                    final JFileChooser fc = new JFileChooser();
                    fc.setDialogTitle("Select the SurvivalGame.lua file.");
                    fc.showOpenDialog(null);
                    fc.addActionListener(event2->{
                        try {
                            f.set(fc.getSelectedFile());
                            path.set((String)fc.getSelectedFile().getParent());
                            if (f.get().delete()) {
                                if (f.get().createNewFile()) {
                                    FileWriter w = new FileWriter(path + "\\SurvivalGame.lua");
                                    w.write(normalLua);
                                    w.close();
                                    showMessageDialog(null, "Successfully patched!");
                                } else {
                                    showMessageDialog(null, "Couldn't create the new file.");
                                }
                            } else {
                                showMessageDialog(null, "Couldn't delete old file.");
                            }
                        } catch(IOException ignored) {
                        }
                    });
                }
            } catch (IOException ignored) {
            }
        });
        patch.addActionListener(event->{
            try {
                String patchLua = new Scanner(new URL("https://raw.githubusercontent.com/Tomoli75/ScrapPatch/master/patch.lua").openStream(), "UTF-8").useDelimiter("\\A").next();
                AtomicReference<File> f = new AtomicReference<>(new File(path + "\\SurvivalGame.lua"));
                if(f.get().exists()) {
                    if (f.get().delete()) {
                        if (f.get().createNewFile()) {
                            FileWriter w = new FileWriter(path + "\\SurvivalGame.lua");
                            w.write(patchLua);
                            w.close();
                            showMessageDialog(null, "Successfully patched!");
                        } else {
                            showMessageDialog(null, "Couldn't create the new file.");
                        }
                    } else {
                        showMessageDialog(null, "Couldn't delete old file.");
                    }
                } else {
                    final JFileChooser fc = new JFileChooser();
                    fc.setDialogTitle("Select the SurvivalGame.lua file.");
                    fc.showOpenDialog(null);
                    fc.addActionListener(event2->{
                        try {
                            f.set(fc.getSelectedFile());
                            path.set((String)fc.getSelectedFile().getParent());
                            if (f.get().delete()) {
                                if (f.get().createNewFile()) {
                                    FileWriter w = new FileWriter(path + "\\SurvivalGame.lua");
                                    w.write(patchLua);
                                    w.close();
                                    showMessageDialog(null, "Successfully patched!");
                                } else {
                                    showMessageDialog(null, "Couldn't create the new file.");
                                }
                            } else {
                                showMessageDialog(null, "Couldn't delete old file.");
                            }
                        } catch(IOException ignored) {
                        }
                    });
                }
            } catch (IOException ignored) {
            }
        });
    }
}
