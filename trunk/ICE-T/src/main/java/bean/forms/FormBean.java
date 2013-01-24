package bean.forms;

import entity.EntityM;
import javax.swing.*;

public abstract class FormBean {

	public abstract JPanel createEntityPanel();
	public abstract JPanel createPanelFromExistingEntity(EntityM usingThis);
	public abstract EntityM getEntity();
}
