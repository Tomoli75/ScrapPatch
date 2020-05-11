package com.tomoli75.scrappatch;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import static javax.swing.JOptionPane.*;

public class ScrapPatch {
    public static void main(String[] args) {
        new gui();
    }
}
class gui extends JFrame {
    gui()
    {
        String path = System.getenv("ProgramFiles")+"\\Steam\\steamapps\\common\\Scrap Mechanic\\Survival\\Scripts\\game";
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
                File f = new File(path+"\\SurvivalGame.lua");
                if(f.delete()) {
                    if(f.createNewFile()) {
                        FileWriter w = new FileWriter(path + "\\SurvivalGame.lua");
                        w.write(normalLua);
                        w.close();
                        showMessageDialog(null,"Successfully removed patch!");
                    }
                }
            } catch (IOException ignored) {
            }
        });
        patch.addActionListener(event->{
            try {
                String patchLua = new Scanner(new URL("https://raw.githubusercontent.com/Tomoli75/ScrapPatch/master/patch.lua").openStream(), "UTF-8").useDelimiter("\\A").next();
                File f = new File(path+"\\SurvivalGame.lua");
                if(f.delete()) {
                    if(f.createNewFile()) {
                        FileWriter w = new FileWriter(path + "\\SurvivalGame.lua");
                        w.write(patchLua);
                        w.close();
                        showMessageDialog(null,"Successfully patched!");
                    }
                }
            } catch (IOException ignored) {
            }
        });
    }
}