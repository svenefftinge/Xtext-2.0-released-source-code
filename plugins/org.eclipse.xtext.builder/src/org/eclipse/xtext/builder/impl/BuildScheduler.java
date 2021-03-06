/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.builder.impl;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.xtext.builder.internal.Activator;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Sets;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Schedules extraordinary Xtext builds, e.g. on project open/close. Keeps a set of scheduled jobs to avoid duplicate
 * builds.
 * 
 * @author Jan Koehnlein - Initial contribution and API
 */
@Singleton
public class BuildScheduler {

	private final static Logger log = Logger.getLogger(BuildScheduler.class);

	private Set<IProject> projectsScheduledForBuild = Sets.newHashSet();

	@Inject
	private IWorkspace workspace;

	public void scheduleBuildIfNecessary(final Iterable<IProject> toUpdate, IBuildFlag... buildFlags) {
		for (IProject project : toUpdate) {
			if (isBuildable(project)) {
				synchronized (projectsScheduledForBuild) {
					if (!projectsScheduledForBuild.contains(project)) {
						projectsScheduledForBuild.add(project);
						new BuildJob(Messages.BuildScheduler_JobName, project, buildFlags).schedule();
					}
				}
			}
		}
	}

	protected boolean isBuildable(IProject project) {
		return XtextProjectHelper.hasNature(project);
	}

	protected class BuildJob extends Job {

		private IProject project;

		private Map<String, String> builderArguments;

		public BuildJob(String name, IProject project, IBuildFlag... buildFlags) {
			super(name);
			this.project = project;
			if (buildFlags != null && buildFlags.length > 0) {
				builderArguments = Maps.newHashMap();
				for (IBuildFlag buildFlag : buildFlags) {
					buildFlag.addToMap(builderArguments);
				}
			} else {
				builderArguments = null;
			}
			setRule(workspace.getRuleFactory().buildRule());
		}

		@Override
		public boolean belongsTo(Object family) {
			return ResourcesPlugin.FAMILY_AUTO_BUILD == family;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			SubMonitor progress = SubMonitor.convert(monitor, 1);
			try {
				synchronized (projectsScheduledForBuild) {
					projectsScheduledForBuild.remove(project);
				}
				try {
					// project could have been modified in the meantime, so check again
					if (isBuildable(project)) {
						project.build(IncrementalProjectBuilder.FULL_BUILD, XtextBuilder.BUILDER_ID, builderArguments,
								progress.newChild(1));
//						for(IProject referencing: project.getReferencingProjects()) {
//							synchronized (projectsScheduledForBuild) {
//								projectsScheduledForBuild.remove(referencing);
//								if (isBuildable(referencing)) {
//									referencing.build(IncrementalProjectBuilder.FULL_BUILD, XtextBuilder.BUILDER_ID, builderArguments,
//											progress.newChild(1));
//								}
//							}
//						}
					} else {
						progress.worked(1);
					}
				} catch (OperationCanceledException e) {
					return Status.CANCEL_STATUS;
				} catch (Exception x) {
					log.error(x.getMessage(), x);
					return new Status(IStatus.ERROR, Activator.getDefault().getBundle().getSymbolicName(), Messages.BuildScheduler_BuildOf
							+ Strings.notNull(project.getName()) + Messages.BuildScheduler_FailedEtc);
				}
			} finally {
				if (monitor != null)
					monitor.done();
			}
			return Status.OK_STATUS;
		}
	}
}
