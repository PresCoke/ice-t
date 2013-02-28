package bean.combat;

import javax.swing.*;

public abstract class Bean implements ListCellRenderer{
	public abstract void createPanelFrom(Object thisEntity);
	public abstract Object getEntity();
}
