package presentation;

//-- Project Imports --//
import controller.*;
//-- Class Imports --//
import javax.swing.*;

import entity.CombatEncounter;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;


/**
 * Root_Window
 * 
 * @author jamesbegg
 *
 */

public class Root_Window {
	
	JFrame main_window; // main application window
	JComponent content;
	
	Combat_Tab combat_tab; // holds all GUI related functions for the Combat tab
	Edit_Tab editEntity_tab; // holds all GUI related functions for the Edit tab
	Help_Tab help_tab; // holds all GUI related functions for the Help tab
	New_Tab newEntity_tab; // holds all GUI related functions for the New tab
	
	String[] entity_names;
	
	public Root_Window() {
		main_window = new JFrame(); 
		combat_tab = new Combat_Tab(App_Root.combat_controller); 
		
	}
	
	public int start () {
		/*
		 * Pre: Application has just started
		 * Post: Window Created and ready to accept 
		 */
		
		/*
		 * The following section of code will need to be implemented more fully
		 * currently all that has been implemented is when the window is closed the application closes.
		 */
		main_window.addWindowListener(new WindowListener(){

			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosed(WindowEvent arg0) {
				
				
				
			}

			public void windowClosing(WindowEvent arg0) {
				close();
				App_Root.exit();
			}

			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			} 
			
		});
		
		
		/*
		 *  Creation and addition of tabs
		 */
		
		//This image icon is a hack... should be updated with custom icons and should double check for null values
		ImageIcon test_icon = new ImageIcon ("src/main/resources/new_hat.jpg");
		//TODO: create better icons 
		
		ResourceBundle root_window_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.RootWindow", App_Root.language_locale);
		
		/*major_tabs.addTab(root_window_l10n.getString("Home_Tab"), test_icon, welcome_tab.getPanel());
		major_tabs.addTab(root_window_l10n.getString("New_Tab"), test_icon, newEntity_tab.getPanel());
		major_tabs.addTab(root_window_l10n.getString("Edit_Tab"), test_icon, editEntity_tab.getPanel());
		major_tabs.addTab(root_window_l10n.getString("Combat_Tab"), test_icon, combat_tab.getPanel());
		major_tabs.addTab(root_window_l10n.getString("Help_Tab"), test_icon, help_tab.getPanel());*/
		
		JMenuBar app_menubar = new JMenuBar();
		JMenu file_menu, new_menu, edit_menu;
		JMenuItem save_item, prefs_item, help_item, quit_item;
		
		file_menu = new JMenu(root_window_l10n.getString("File_menu"));
		
		prefs_item = new JMenuItem(root_window_l10n.getString("Preferences_menuitem"));
		prefs_item.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Prefs_Window aPreferencesWindow = new Prefs_Window();
				aPreferencesWindow.showFrame();
			}
		});
		file_menu.add(prefs_item);
		save_item = new JMenuItem(root_window_l10n.getString("Save_menuitem"));
		save_item.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int CE_id;
				if (App_Root.combat_controller.getID() < 0) {
					ResourceBundle save_l10n = ResourceBundle.getBundle("filters.MainGUI_l10n.RootWindow", App_Root.language_locale);
					String name = "";
					do {
						name = (String) JOptionPane.showInputDialog(
								main_window,
								save_l10n.getString("SaveCE_message"),
								save_l10n.getString("SaveCE_title"),
								JOptionPane.PLAIN_MESSAGE,
								null,
								null,
								""
								);
					} while (name == null || name.length() <= 0);
					App_Root.combat_controller.saveOpenCombatEncounter(name);
				} else {
					App_Root.combat_controller.updateOpenCombatEncounter();
				}
			}
		});
		file_menu.add(save_item);
		help_item = new JMenuItem(root_window_l10n.getString("Help_menuitem"));
		help_item.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//TODO: launch new JFrame pop-up with help in it
			}
		});
		file_menu.add(help_item);
		quit_item = new JMenuItem(root_window_l10n.getString("Quit_menuitem"));
		quit_item.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
//				if (App_Root.combat_controller.getID() < 0) {
//					ResourceBundle save_l10n = ResourceBundle.getBundle("filters.MainGUI_l10n.RootWindow", App_Root.language_locale);
//					String name = "";
//					do {
//						name = (String) JOptionPane.showInputDialog(
//								main_window,
//								save_l10n.getString("SaveCE_message"),
//								save_l10n.getString("SaveCE_title"),
//								JOptionPane.PLAIN_MESSAGE,
//								null,
//								null,
//								""
//								);
//					} while (name == null || name.length() <= 0);
//					App_Root.combat_controller.saveOpenCombatEncounter(name);
//				} else {
//					App_Root.combat_controller.updateOpenCombatEncounter();
//				}
				App_Root.exit();
			}
		});
		file_menu.add(quit_item);
		app_menubar.add(file_menu);
		
		new_menu = new JMenu(root_window_l10n.getString("New_menu"));
		entity_names = App_Root.newEntity_controller.getEntityTypeNames();
		for (int index = 0; index < this.entity_names.length; index++) {
			JMenuItem aMenuItem = new JMenuItem(entity_names[index]);
			aMenuItem.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String entityToBeCreated = ((JMenuItem) ae.getSource()).getText();
					String ce = ResourceBundle.getBundle("filters.MainGUI_l10n.EntityTypeName", App_Root.language_locale)
							.getString("CombatEncounter_entity");
					if (entityToBeCreated.equals(ce)) {
						String [] options = {"Yes", "No", "Cancel"};
						int x = JOptionPane.showOptionDialog(App_Root.mainWindow.getFrame(),
								  "Do you wish to save the currently open Encounter?",//TODO: make french
								  "ICE-T",
								  JOptionPane.YES_NO_CANCEL_OPTION,
								  JOptionPane.WARNING_MESSAGE,
								  null,
								  options,
								  options[0]
								  );
						if (x == JOptionPane.CANCEL_OPTION || x == JOptionPane.CLOSED_OPTION) {
							return;
						}
						else if (x == JOptionPane.YES_OPTION) {
							if (App_Root.combat_controller.getID() < 0) {
								ResourceBundle save_l10n = ResourceBundle.getBundle("filters.MainGUI_l10n.RootWindow", App_Root.language_locale);
								String name = "";
								do {
									name = (String) JOptionPane.showInputDialog(
											App_Root.mainWindow.getFrame(),
											save_l10n.getString("SaveCE_message"),
											save_l10n.getString("SaveCE_title"),
											JOptionPane.PLAIN_MESSAGE,
											null,
											null,
											""
											);
								} while (name == null || name.length() <= 0);
								App_Root.combat_controller.saveOpenCombatEncounter(name);
							} else {
								App_Root.combat_controller.updateOpenCombatEncounter();
							}
						}
						App_Root.combat_controller.setCombatEncounter(new CombatEncounter());
						combat_tab = new Combat_Tab(App_Root.combat_controller);
						content.removeAll();
						content = (JComponent) combat_tab.getAndCreatePanel();
						main_window.setContentPane(content);
						main_window.pack();
						main_window.validate();
						main_window.setVisible(true);
						return;
					}
					New_Tab aNewEntityFrame = new New_Tab(App_Root.newEntity_controller, entityToBeCreated);
					aNewEntityFrame.showFrame();
				}
			});
			new_menu.add(aMenuItem);
		}
		app_menubar.add(new_menu);
		
		//TODO: may need to make specific sub-menus
		edit_menu = new JMenu(root_window_l10n.getString("Edit_menu"));
		entity_names = App_Root.editEntity_controller.getEntityTypeNames();
		for (int index = 0; index < this.entity_names.length; index++) {
			JMenuItem aMenuItem = new JMenuItem(entity_names[index]);
			aMenuItem.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					//TODO create edit entity frame from following string
					String entityTypeToBeEdited = ((JMenuItem) ae.getSource()).getText();
					Edit_Tab aEditEntityTab = new Edit_Tab(App_Root.editEntity_controller, entityTypeToBeEdited);
					aEditEntityTab.showFrame();
				}
			});
			edit_menu.add(aMenuItem);
		}
		app_menubar.add(edit_menu);
		
		main_window.setJMenuBar(app_menubar);
		
		content = (JComponent)combat_tab.getAndCreatePanel();
		
		main_window.setTitle(root_window_l10n.getString("App_title"));
		//main_window.setDefaultLookAndFeelDecorated(true);
		main_window.setContentPane(content);
	
		main_window.setResizable(false);  //should be a property
	
		main_window.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize() ); // sets window size to the screen resolution
		main_window.pack();
		main_window.validate();
		main_window.setVisible(true);
		
		return 1;// I'm not sure what this value means
	}
	
	public void close() {
		main_window.setVisible(false);
		main_window.dispose();
	}

	public Component getFrame() {
		return combat_tab.getPanel();
	}
}
