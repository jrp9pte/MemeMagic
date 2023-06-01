import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * MemeMagic Graphical User Interface 
 * 
 * This class contains the graphical user interface for the Meme Magic Software
 * 
 */
public class MemeMagic extends JFrame {

    /**
     * Serialization string required by extending JFrame
     */
    private static final long serialVersionUID = 1L;
    
    private User user;
    private GraphicalMeme currentMeme;
    
    private String backgroundImageFilename;

    private BorderLayout panelLayout;
    private JLabel backgroundImageFileNameLabel;
    private JLabel imageDisplayLabel;
    private JPanel controlPanel;
    private JPanel memeViewPanel;
    private JPanel panelPane;
    private String captionVerticalAlign = "top";
    
    
    
    public MemeMagic() {
        this.user = new User();
    }
    
    public MemeMagic(User user) {
        this.user = user;
    }


    /**
     * Main method.  This method initializes a PhotoViewer, loads images into a PhotographContainer, then
     * initializes the Graphical User Interface.
     * 
     * @param args  Optional command-line arguments
     */
    public static void main(String[] args) {
        
        // Create a User object for this instance of Meme Magic
        User user = new User();

        // Instantiate the PhotoViewer Class
        MemeMagic myViewer = new MemeMagic(user);
        
        // Invoke and start the Graphical User Interface
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }

    /**
     * Initialize all the GUI components.  This method will be called by
     * SwingUtilities when the application is started.
     */
    private void initialize() {

        // Tell Java to exit the program when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Tell Java to title the window to Meme Magic
        this.setTitle("Meme Magic");

        // We will use border layout on the main panel, since it is much easier for organizing panels.
        panelLayout = new BorderLayout();
        panelPane = new JPanel(panelLayout);

        // Create a label to display the full image.
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setPreferredSize(new Dimension(550, 550));

        // Create a panel on which to display the full image
        memeViewPanel = new JPanel(new BorderLayout());
        memeViewPanel.setPreferredSize(new Dimension(550, 550));
        memeViewPanel.add(imageDisplayLabel, BorderLayout.CENTER);


        // Create a panel on which to display the controls for building a Meme
        controlPanel = new JPanel(new BorderLayout());
        
        // Create a panel that holds BackgroundImage information and give it a title
        JPanel backgroundImagePanel = new JPanel(new BorderLayout());
        //backgroundImagePanel.setPreferredSize(new Dimension(500,300));
        backgroundImagePanel.setBorder(BorderFactory.createTitledBorder("Background Image"));

        // Create a panel that provides input for the BackgroundImage fileName
        JPanel backgroundImageFilePanel = new JPanel();
        
        // Label
        JLabel backgroundImageFileLabel = new JLabel("Filename: ");
        backgroundImageFileLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageFilePanel.add(backgroundImageFileLabel);
        
        // Button
        JButton backgroundImageButton = new JButton("Browse");
        backgroundImageFilePanel.add(backgroundImageButton);
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        
        backgroundImageButton.addActionListener(new OpenButtonListener());
        
        // Label that will contain the filename of the image
        backgroundImageFileNameLabel = new JLabel("<choose>");
        backgroundImageFilePanel.add(backgroundImageFileNameLabel);
        backgroundImageFileNameLabel.setPreferredSize(new Dimension(265, 20));
        
        // Add the panel about the BackgroundImage fileName to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageFilePanel, BorderLayout.NORTH);
        JPanel something = new JPanel(new BorderLayout());
        JLabel Jlabel1 = new JLabel("Title:");
        JTextField  JTextField =  new JTextField(20);
        something.add(Jlabel1, BorderLayout.WEST);
        something.add(JTextField, BorderLayout.EAST);
        backgroundImagePanel.add(something, BorderLayout.LINE_START );
        JPanel something2 = new JPanel(new BorderLayout());
        JLabel Jlabel2 = new JLabel("Description:");
       // Jlabel2.setPreferredSize(new Dimension(10, 20));
        JTextField  JTextField2 =  new JTextField(30);
        //JTextField2.setPreferredSize(new Dimension(80, 20));
        something2.add(Jlabel2,BorderLayout.WEST );
        something2.add(JTextField2, BorderLayout.EAST);
        something2.setPreferredSize(new Dimension(90, 20));
        backgroundImagePanel.add(something2, BorderLayout.AFTER_LAST_LINE);
        

        
        // The Control Panel implementation (with Background Image and Meme panels)
        JPanel memePanel = new JPanel(new BorderLayout());
        //backgroundImagePanel.setPreferredSize(new Dimension(500,300));
        memePanel.setBorder(BorderFactory.createTitledBorder("Meme"));
      
        JPanel memePanel2 = new JPanel();
        JLabel Jlabel3 = new JLabel("Caption:");
        JTextField  JTextField4 =  new JTextField(30);
        memePanel2.add(Jlabel3, BorderLayout.WEST);
        memePanel2.add(JTextField4,BorderLayout.EAST );
        memePanel.add(memePanel2, BorderLayout.NORTH);
        
        JPanel verticalAlignPanel = new JPanel();
        String[] array = {"top", "middle", "bottom"};
        JLabel Jlabel4 = new JLabel("Vertical Align:");
        JComboBox<String> combobox = new JComboBox<String>(array);
        combobox.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			captionVerticalAlign = (String) combobox.getSelectedItem();
        }});
        verticalAlignPanel.add(Jlabel4);
        verticalAlignPanel.add(combobox);
        //verticalAlignPanel.setPreferredSize(new Dimension(90, 20));
        memePanel.add(verticalAlignPanel, BorderLayout.CENTER);
        
        
        class GeneratingClass implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				if( e.getActionCommand().equals("Generate")){
					String something = JTextField.getText();
					BackgroundImage backgroundImage1 = new BackgroundImage(backgroundImageFilename, something, JTextField2.getText() );
					currentMeme = new GraphicalMeme(backgroundImage1, JTextField4.getText(), user);
					currentMeme.setCaptionVerticalAlign(captionVerticalAlign);
					
					
					
					try {
						ImageIcon name = new ImageIcon(currentMeme.compileMeme());
						imageDisplayLabel.setIcon(name);
						memeViewPanel.repaint();
					} catch (IOException e1) {

						e1.printStackTrace();
						System.err.println("Error in given information");
			
						
					}
					catch(Exception e2) {
						e2.printStackTrace();
					
					}
					
				}
				
			}
        	
        }
        
        
        
        
        
        
        
        JPanel Jpanel2 = new JPanel();
        JButton button = new JButton( "Generate");
        button.addActionListener(new GeneratingClass());
        Jpanel2.add(button);
        
        
        JPanel Jpanel3 = new JPanel();
        JButton button2 = new JButton( "Save");
        Jpanel3.add(button2);
        Jpanel2.add(Jpanel3);       
        controlPanel.add(Jpanel2, BorderLayout.SOUTH);
        button2.addActionListener(new SaveButtonListener());
        
        
        
        controlPanel.add(memePanel);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        // Add the BackgroundImage information panel to the control panel
        controlPanel.add(backgroundImagePanel, BorderLayout.NORTH);
        
        // Add all the panels to the main display based on BorderLayout
        controlPanel.setPreferredSize(new Dimension(500,570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);

        // Add the panelPane to the contentPane of the Frame (Window)
        this.getContentPane().add(panelPane);

        // Set the preferred size and show the main application window
        this.setPreferredSize(new Dimension(1150, 570));
        this.pack();
        this.setVisible(true);
    }
    
    	
   
    
    /**
     * ActionListener for the open button.  When the button is pressed, this ActionListener
     * opens a FileChooser, asks the user to choose a JPG image file, then
     * sets the field backgroundImageFilename in the main class.
     */
    private class OpenButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose a JPG image file, then
         * sets the field backgroundImageFilename in the main class.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Choose a Background Image");
            chooser2.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
            int returnVal = chooser2.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                backgroundImageFilename = chooser2.getSelectedFile().getAbsolutePath();
                backgroundImageFileNameLabel.setText(backgroundImageFilename);
            }

        }
    }
    
    /**
     * ActionListener for the save button.  When the button is pressed, this ActionListener
     * opens a save FileChooser, asks the user to choose a location and filename, then
     * writes the graphical meme data to a PNG image file.
     */
    private class SaveButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose
         * a location and filename, then writes the graphical meme data to a PNG file.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Save Meme");
            chooser2.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int returnVal = chooser2.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String destinationFile = chooser2.getSelectedFile().getAbsolutePath();

                //       Catch the exceptions and provide the user with an appropriate message
                 try {
					ImageIO.write(currentMeme.compileMeme(), "png", new File(destinationFile));
				} catch (IOException e) {
					e.printStackTrace();
					System.err.println("Error in Producing the meme");
				}

            }

        }
    }
}