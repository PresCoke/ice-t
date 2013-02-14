package presentation;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import controller.App_Root;

public class Prefs_Window implements ActionListener {
	
	private JButton save_button, cancel_button;
	private JFrame prefs_frame;
	private JPanel prefs_panel;
	private JTextField dbPath_field;
	private DefaultListModel language_model;
	private JList language_list;
	
	public Prefs_Window() {
		
	}
	
	public void showFrame() {
		this.createPanel();
		
		ResourceBundle prefs_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.PrefsWindow", App_Root.language_locale);

		prefs_frame = new JFrame(prefs_l10n.getString("Prefs_title"));
		prefs_frame.setPreferredSize( prefs_panel.getPreferredSize() );
		prefs_frame.setContentPane(prefs_panel);
		prefs_frame.pack();
		prefs_frame.validate();
		prefs_frame.setVisible(true);
	}

	private void createPanel() {
		ResourceBundle prefs_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.PrefsWindow", App_Root.language_locale);
		
		prefs_panel = new JPanel();
				
		save_button = new JButton(prefs_l10n.getString("Save_button"));
		save_button.addActionListener(this);
		cancel_button = new JButton(prefs_l10n.getString("Cancel_button"));
		cancel_button.addActionListener(this);
		
		JLabel dbPath_label = new JLabel(prefs_l10n.getString("dbPath_label"));
		dbPath_label.setBorder( BorderFactory.createEmptyBorder(0, 8, 0, 0) );
		dbPath_field = new JTextField();
		dbPath_field.setPreferredSize( new Dimension(200, 0) );
			
		this.getSupportedLanguages();
		language_list = new JList(language_model);
		language_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane name_pane = new JScrollPane(language_list);
		name_pane.setBorder( BorderFactory.createEtchedBorder() );
		name_pane.setPreferredSize( new Dimension(150, 0) ); //TODO: find better way then specifying exact sizes
		name_pane.setMinimumSize( new Dimension(150, 0) );
		
		JPanel listSelection_panel = new JPanel();
		listSelection_panel.setLayout( new BoxLayout(listSelection_panel, BoxLayout.PAGE_AXIS) );
		javax.swing.border.TitledBorder title = BorderFactory.createTitledBorder( 
				BorderFactory.createEmptyBorder(), 
				prefs_l10n.getString("langauge_label"));
		title.setTitleJustification(javax.swing.border.TitledBorder.LEFT);
		listSelection_panel.setBorder(title);
		listSelection_panel.add(name_pane);
		listSelection_panel.setPreferredSize( new Dimension(200, 100) );
		
		GroupLayout prefs_layout = new GroupLayout(prefs_panel);
		prefs_layout.setAutoCreateGaps(true);
		prefs_layout.setAutoCreateContainerGaps(true);
		prefs_layout.setHorizontalGroup( prefs_layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addGroup( prefs_layout.createSequentialGroup()
						.addComponent(save_button)
						.addComponent(cancel_button))
				.addGroup( prefs_layout.createSequentialGroup()
						.addComponent(dbPath_label)
						.addComponent(dbPath_field))
				.addComponent(listSelection_panel)
				);
		prefs_layout.setVerticalGroup( prefs_layout.createSequentialGroup()
				.addGroup( prefs_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(save_button)
						.addComponent(cancel_button))
				.addGroup( prefs_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(dbPath_label)
						.addComponent(dbPath_field))
				.addComponent(listSelection_panel)
				);
		prefs_layout.linkSize(SwingConstants.VERTICAL, dbPath_label, dbPath_field);
		prefs_panel.setLayout(prefs_layout);
		
	}

	private void getSupportedLanguages() {
		this.language_model = new DefaultListModel();
		ResourceBundle langs = ResourceBundle.getBundle("filters.mainGUI_l10n.SupportedLanguages", App_Root.language_locale);
		Enumeration<String> lang_keys = langs.getKeys();
		for (int index = 0; lang_keys.hasMoreElements(); index++) {
			String key = (String) lang_keys.nextElement();
			language_model.addElement(langs.getString(key));
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == save_button) {
			this.savePreferences();
			this.prefs_frame.dispose();
		} else if (ae.getSource() == cancel_button) {
			this.prefs_frame.dispose();
		}
	}

	private void savePreferences() {
		try {
			File f = new File("src/main/resources/filters/ApplicationSettings.properties");
			FileInputStream fis = new FileInputStream(f);
			Properties properties = new Properties();
			properties.load(fis);
			
			int index = language_list.getSelectedIndex(); 
			if (index != -1) {
				if (index == 0) { //francais
					properties.setProperty("Language", "fr_CA");
				} else if (index == 1) {//english
					properties.setProperty("Language", "en_CA");
				}
			}
			properties.setProperty("DbPath", dbPath_field.getText());
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(f);
			properties.store(fos, null);
			fos.close();
			
			App_Root.changePreferences();
		} catch (Exception e) {
			/*TODO: perhaps create default properties file or something... I don't know...*/
			Exception_Window.showException(e);
		}
	}
}
