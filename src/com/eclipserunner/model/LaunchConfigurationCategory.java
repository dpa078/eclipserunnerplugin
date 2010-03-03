package com.eclipserunner.model;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.debug.core.ILaunchConfiguration;

/**
 * Container of launch configurations presented in RunnerView tree.
 * 
 * @author vachacz
 */
public class LaunchConfigurationCategory {

	private String name;
	private Set<ILaunchConfiguration> launchConfigurationSet = new HashSet<ILaunchConfiguration>();

	public void setLaunchConfigurationSet(Set<ILaunchConfiguration> launchConfigurationList) {
		this.launchConfigurationSet = launchConfigurationList;
	}

	public Set<ILaunchConfiguration> getLaunchConfigurationSet() {
		return launchConfigurationSet;
	}

	public void add(ILaunchConfiguration launchConfiguration) {
		launchConfigurationSet.add(launchConfiguration);
	}

	public Object[] toArray() {
		return launchConfigurationSet.toArray();
	}

	public boolean contains(Object object) {
		return launchConfigurationSet.contains(object);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int size() {
		return launchConfigurationSet.size();
	}
}
