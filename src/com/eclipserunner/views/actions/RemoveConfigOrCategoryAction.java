package com.eclipserunner.views.actions;

import static com.eclipserunner.Messages.Message_removeCategoryConfirm;
import static com.eclipserunner.Messages.Message_removeConfigurationConfirm;
import static com.eclipserunner.Messages.Message_removeConfirm;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;

import com.eclipserunner.RunnerPlugin;
import com.eclipserunner.model.ILaunchConfigurationCategory;
import com.eclipserunner.model.ILaunchConfigurationNode;
import com.eclipserunner.model.ILaunchConfigurationSelection;
import com.eclipserunner.model.IRunnerModel;
import com.eclipserunner.model.impl.RunnerModel;

public class RemoveConfigOrCategoryAction extends Action {

	private ILaunchConfigurationSelection launchConfigurationSelection;
	private IRunnerModel runnerModel;

	public RemoveConfigOrCategoryAction(ILaunchConfigurationSelection launchConfigurationSelection,
			IRunnerModel runnerModel) {
		this.launchConfigurationSelection = launchConfigurationSelection;
		this.runnerModel = runnerModel;
	}

	@Override
	public void run() {
		Object selectedObject = launchConfigurationSelection.getSelectedObject();

		if (selectedObject instanceof ILaunchConfigurationNode) {
			removeLaunchConfiguration((ILaunchConfigurationNode) selectedObject);
		} else if (selectedObject instanceof ILaunchConfigurationCategory) {
			removeLaunchConfigurationCategory((ILaunchConfigurationCategory) selectedObject);
		}
		// else do nothing
	}

	private void removeLaunchConfigurationCategory(ILaunchConfigurationCategory selectedCategory) {
		if (RunnerModel.getDefault().getUncategorizedCategory() == selectedCategory) {
			return;
		}

		boolean confirmed = MessageDialog.openConfirm(RunnerPlugin.getRunnerShell(), Message_removeConfirm, Message_removeCategoryConfirm);
		if (confirmed) {
			runnerModel.removeLaunchConfigurationCategory(selectedCategory);
		}
	}

	private void removeLaunchConfiguration(ILaunchConfigurationNode selectedConfiguration) {
		boolean confirmed = MessageDialog.openConfirm(RunnerPlugin.getRunnerShell(), Message_removeConfirm, Message_removeConfigurationConfirm);
		if (confirmed) {
			ILaunchConfigurationCategory category = launchConfigurationSelection.getSelectedLaunchConfigurationCategory();
			category.remove(selectedConfiguration);
		}
	}

}