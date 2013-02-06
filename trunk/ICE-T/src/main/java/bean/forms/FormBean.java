package bean.forms;

import javax.swing.*;

public interface FormBean {

	public JPanel createEntityPanel();
	public JPanel createPanelFromExistingEntity(Object usingThis);
	public Object getEntity();
}
