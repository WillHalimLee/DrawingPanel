/*
 * TCSS 305 - Assignment 5
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import tools.EllipseTool;
import tools.LineTool;
import tools.RectangleTool;

/**
 * Presents the GUI for the PowerPaint application.
 * 
 * @author Alan Fowler (acfowler@uw.edu) leeh10
 * @version Autumn 2022
 */
public final class PaintGUI {
    /** UW purple color.*/
    private static final Color UW_PURPLE = new Color(51, 0, 111);
    
    /** UW gold color.*/
    private static final Color UW_GOLD = new Color(232, 211, 162);
    
    /** Value for the sliders tick spacing.*/
    private static final int MINOR_TICK_SPACING = 1;
    
    /** Value for the sliders major tick spacing. */
    
    private static final int MAJOR_TICK_SPACING = 5;
    
    /** The maximum value of the slider. */
    private static final int MAX_THICKNESS = 15;
    
    /** The value the slider is at when the program first runs.*/
    private static final int SLIDER_START = 3;
    
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /** Width of the JFrame that should be one-third of the screen's width.*/
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width / 3;
    
    /** Height of the JFrame that should be one-third of the screen's height.*/
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height / 3;
    
    /** A Image Icon of a purple w, the UW Icon.*/
    private static final ImageIcon UW_ICON = new ImageIcon("files/w_small.png");
    
    /** A JFrame. */
    private final JFrame myFrame;
    
    /** A JPanel that is the Drawing Panel.*/
    private final DrawPanel myDrawPanel;
    
    /** The Actions for an Ellipse.*/
    private final EllAction myEllAction;
    
    /** The Actions for a Line.*/
    private final LineAction myLineAction;
    
    /** The Actions for a Rectangle. */
    private final RecAction myRecAction;
    
    /** Constructor to initialize my fields.*/
    public PaintGUI() {
        super();
        // initialize instance fields here
        myFrame = new JFrame("TCSS 305 Paint - Autumn 2022");
        final LineTool lineTool = new LineTool();
        final RectangleTool recTool = new RectangleTool();
        final EllipseTool ellTool = new EllipseTool();
        myDrawPanel = new DrawPanel(SLIDER_START, UW_PURPLE, UW_GOLD, lineTool);
        myLineAction = new LineAction(myDrawPanel, lineTool);
        myRecAction = new RecAction(myDrawPanel, recTool);
        myEllAction = new EllAction(myDrawPanel, ellTool);
        // setup and display the GUI
        start();
    }

    /**
     * Performs all tasks necessary to display the UI.
     */
    protected void start() {
        myFrame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        myFrame.setLocation(SCREEN_WIDTH, SCREEN_HEIGHT);
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setIconImage(UW_ICON.getImage());
        final JToolBar toolBar = createToolBar();
        myFrame.add(toolBar, BorderLayout.SOUTH);
       
        final JMenuBar menuBar = createMenuBar();
        myFrame.setJMenuBar(menuBar);
        myFrame.add(myDrawPanel, BorderLayout.CENTER);
        myFrame.pack();
        

    }
    
    /**
     * A Method that creates a JToolBar.
     * @return A JToolBar.
     */
    private JToolBar createToolBar() {
        final ButtonGroup bG = new ButtonGroup();
        final JToolBar bar = new JToolBar();
        final JToggleButton line = new JToggleButton(myLineAction);
        final JToggleButton rectangle = new JToggleButton(myRecAction);
        final JToggleButton ellipse = new JToggleButton(myEllAction);
        
        bG.add(line);
        bG.add(rectangle);
        bG.add(ellipse);
        bar.add(line);
        bar.add(rectangle);
        bar.add(ellipse);
        return bar;
    }
    /**
     * A Method that creates a JMenuBar.
     * @return A JMenuBar
     */
    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu optionTab = new JMenu("Options");
        // Make the Thickness Slider.
        final JMenu thick = new JMenu("Thickness");
        final JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, MAX_THICKNESS, 0);
        slider.setMajorTickSpacing(MAJOR_TICK_SPACING);
        slider.setMinorTickSpacing(MINOR_TICK_SPACING);
        slider.setValue(SLIDER_START);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = slider.getValue();
                myDrawPanel.setStrokeSize(value);
            }
        });
        thick.add(slider);
        optionTab.add(thick);
        optionTab.addSeparator();

        // Make the color item.
        final JMenuItem color = new JMenuItem("Color...");
        color.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, 
                        "A Color Chooser", myDrawPanel.getColor());
                if (result == null) {
                    myDrawPanel.setCurrentColor(myDrawPanel.getColor());
                } else {
                    myDrawPanel.setCurrentColor(result);
                } 
            }
        });
        optionTab.add(color);
        final JMenuItem fillColor = new JMenuItem("Fill Color...");
        fillColor.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result2 = JColorChooser.showDialog(null, 
                        "A Fill Color Chooser", myDrawPanel.getFillColor());
                if (result2 == null) {
                    myDrawPanel.setFillColor(myDrawPanel.getFillColor());
                } else {
                    myDrawPanel.setFillColor(result2);
                } 
            }
        });
        optionTab.add(fillColor);
        optionTab.addSeparator();
       
        optionTab.add(myDrawPanel.getFillButton());
        optionTab.addSeparator();
        
        // Make a disabled clear item.
        optionTab.add(myDrawPanel.getClearButton());
        menuBar.add(optionTab);

        // Tools tab for the Menu Bar.
        final JMenu toolsTab = new JMenu("Tools");
        final ButtonGroup bG = new ButtonGroup();
        final JRadioButtonMenuItem line = new JRadioButtonMenuItem(myLineAction);
        final JRadioButtonMenuItem rectangle = new JRadioButtonMenuItem(myRecAction);
        final JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem(myEllAction);

        bG.add(line);
        bG.add(rectangle);
        bG.add(ellipse);

        toolsTab.add(line);
        toolsTab.add(rectangle);
        toolsTab.add(ellipse);
    

        menuBar.add(toolsTab);

        // Make the Help Tab
        final JMenu helpTab = new JMenu("Help");
        final JMenuItem about = new JMenuItem("About...");
        about.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "leeh10\nAutumn 2022", 
                        "TCSS 305 Paint", JOptionPane.PLAIN_MESSAGE,
                        UW_ICON);
            }
        });

        helpTab.add(about);

        // Add Help Tab to the Menu Bar.
        menuBar.add(helpTab);
        return menuBar;
    }  
  
}
