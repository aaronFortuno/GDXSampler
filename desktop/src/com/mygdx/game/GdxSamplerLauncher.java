package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.mygdx.game.common.SampleFactory;
import com.mygdx.game.common.SampleInfos;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GdxSamplerLauncher extends JFrame {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 780;

    private static final int CELL_WIDTH = 200;
    private static final int CANVAS_WIDTH = WIDTH - CELL_WIDTH;

    private LwjglAWTCanvas lwjglAWTCanvas;
    private JList sampleList;
    private JPanel controlPanel;


    public GdxSamplerLauncher() throws HeadlessException {
        init();
    }

    private void init() {

        createControlPanel();

        Container container = getContentPane();
        container.add(controlPanel, BorderLayout.WEST);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (lwjglAWTCanvas != null) {
                    // stop will call our dispose and stop libgdx application
                    lwjglAWTCanvas.stop();
                }
            }
        });

        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // tell window (jframe) to resize and layout our components)
        pack();
        setVisible(true);

        // enables us to embed libgdx app/game into java desktop app
        //launchSample("com.mygdx.game.InputPollingSample");
    }

    private void createControlPanel() {
        controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // constraints for scroll pane
        c.gridx = 0; // column
        c.gridy = 0; // row
        c.fill = GridBagConstraints.VERTICAL; // fill vertically
        c.weighty = 1; // weight used when fill empty space

        sampleList = new JList(SampleInfos.getSampleNames().toArray());
        sampleList.setFixedCellWidth(CELL_WIDTH);
        sampleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sampleList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    launchSelectedSample();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(sampleList);
        controlPanel.add(scrollPane, c);

        // constraints for a button
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;

        JButton launchButton = new JButton("Launch Sample");
        launchButton.addActionListener(actionEvent -> launchSelectedSample());

        // add control panel to pane
        controlPanel.add(launchButton, c);
    }

    private void launchSelectedSample() {
        String sampleName = (String) sampleList.getSelectedValue();

        if (sampleName == null ||sampleName.isEmpty() ) {
            System.out.println("Sample name is empty, cannot launch");
            return;
        }
        launchSample(sampleName);
    }

    public void launchSample(String name) {
        System.out.println("launching sample name = " + name);
        Container container = getContentPane();
        if(lwjglAWTCanvas != null) {
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        ApplicationListener sample = SampleFactory.newSample(name);

        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(CANVAS_WIDTH, HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        pack();
    }

    public static void main(String[] args) {

        // must be used to run our jframe properly
        SwingUtilities.invokeLater(GdxSamplerLauncher::new);
    }
}
