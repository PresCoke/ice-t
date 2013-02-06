package bean.combat;

import javax.swing.*;

public interface Bean extends ListCellRenderer{
	public void createPanelFrom(Object thisEntity);
	public Object getEntity();
}
