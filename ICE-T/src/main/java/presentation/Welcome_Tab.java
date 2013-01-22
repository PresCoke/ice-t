package presentation;
/**
 * Welcome Tab
 * @author jamesbegg
 *
 */

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import controller.Welcome;

public class Welcome_Tab {

	private class Welcome_Package {
		private JLabel picture;
		private JTextPane text;
		private String title;
		
		public Welcome_Package() {
			
			picture = new JLabel();
			text = new JTextPane(); text.setEditable(false); text.setOpaque(false); //makes an uneditable text pane that has a transparent background
			title = "";
			this.setFields("src/main/resources/new_hat.jpg", "This is something worth noting", "Something About Nothing");
			
		}
		
		public void setFields (String pictureURL, String story_text, String story_title) {
			
			//TODO: set text font and picture size
			//TODO: very difficult to localize this!
			text.setText("    "+story_text);
			//text.setVerticalTextPosition(SwingConstants.TOP);
			
			title = story_title;
			ImageIcon icon =  new ImageIcon(pictureURL);
			picture.setIcon(icon);
		}
		
		public JPanel getWelcomePackagePanel(Dimension pictureSize) {
			JPanel temp_panel = new JPanel();
			JPanel txt_panel = new JPanel();
			
			temp_panel.setLayout( new BorderLayout() );
			txt_panel.setLayout( new BoxLayout(txt_panel, BoxLayout.PAGE_AXIS) );
			//create and set border
			//TODO: better if size measurements are dynamic so not based on pixels
			//TODO: colour needs to be changed
			TitledBorder title_border = BorderFactory.createTitledBorder( BorderFactory.createMatteBorder(1, 0, 0, 1, Color.GRAY), title );
			title_border.setTitleJustification(TitledBorder.LEFT);
			temp_panel.setBorder( title_border );
			txt_panel.setBorder( BorderFactory.createEmptyBorder(0, 10, 0, 0) );
			
			picture.setPreferredSize(pictureSize);
			picture.setAlignmentY(Component.TOP_ALIGNMENT);
			picture.setBorder( BorderFactory.createEmptyBorder(0, 0, 0, 10) ); // creates space to the right of the picture
			
			//TODO: colour needs to be changed
			text.setAlignmentY(Component.TOP_ALIGNMENT);
			
			txt_panel.add(text);
			
			temp_panel.add(picture, BorderLayout.LINE_START);
			temp_panel.add(txt_panel, BorderLayout.CENTER);
			
			return temp_panel;
		}
	}
	
	private JPanel welcome_panel;
	private Welcome controller_reference;
	
	public Welcome_Tab(Welcome welcome_controller) {
		// TODO Auto-generated constructor stub
		controller_reference = welcome_controller;
	}

	public Component getPanel() {
		/* Start Hack
		 * - this behaviour may be better implemented by inheriting from component
		 *   or creating a custom class with a super getPanel() method.
		 */
		//TODO: create an abstract super class with methods that must be called when a tab gains/loses focus
		//the reason being that this could be a major memory saver
		
		/*welcome_panel = new JPanel();
		welcome_panel.add( new JTextField("WELCOME") );*/
		this.createPanel();
		return welcome_panel;
	}
	
	private void createPanel() {
		welcome_panel = new JPanel();

		Welcome_Package first_pkg = new Welcome_Package();		//TODO: change this back to get RandomPackage... 
		Welcome_Package second_pkg = getRandomPackage();
		
		//TODO: make sizing dynamic
		Dimension size = new Dimension(450, 300); // should become more dynamic
		
		
		welcome_panel.setLayout( new BoxLayout(welcome_panel, BoxLayout.PAGE_AXIS) );
		welcome_panel.setBorder( BorderFactory.createEmptyBorder(0, 10, 15, 10) ); // a little less on the top
																				   // and a little more on the bottom
		
		welcome_panel.add( first_pkg.getWelcomePackagePanel(size) );
		welcome_panel.add( Box.createGlue() );
		welcome_panel.add( second_pkg.getWelcomePackagePanel(size) );
		
	}
	
	private final Welcome_Package getRandomPackage()
	{
		Welcome_Package pkg = new Welcome_Package();
		try {
			//TODO: this method should get some random stuff from the DB
			controller_reference.generateRandomStory();
			pkg.setFields( controller_reference.getPictureURL(), controller_reference.getStory(), controller_reference.getTitle() );
			return pkg;
		} catch (Exception e) {
			Exception_Window.showException(e);
			
			return pkg; 
		}
	}
	
}
