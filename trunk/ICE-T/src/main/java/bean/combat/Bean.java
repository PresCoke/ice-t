package bean.combat;

import javax.swing.*;
import entity.EntityM;

public interface Bean extends ListCellRenderer{
	public void createPanelFrom(EntityM thisEntity);
	public EntityM getEntity();
}
