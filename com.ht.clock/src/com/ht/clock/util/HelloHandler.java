package com.ht.clock.util;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.progress.IProgressConstants2;
import org.eclipse.ui.statushandlers.StatusManager;

import com.ht.clock.Activator;


public class HelloHandler extends AbstractHandler {

	public static String ID = "com.ht.clock.commands.TestComm";
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Job job = new Job("Wątek w Eclipse"){
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					SubMonitor subMonitor = SubMonitor.convert(monitor,"Rozpoczęcie",5000);
//					subMonitor = null; //błąd tylko do testów okna
					for (int i = 0; i < 50 && !monitor.isCanceled(); i++) {
						if(i == 10){
							subMonitor.subTask("Podzadanie 1");
						}else if(i == 12){
							checkDozen(subMonitor.newChild(100));
							continue;
						}else if(i == 25){
							subMonitor.subTask("Podzadanie 2");
						}else if(i == 40){
							subMonitor.subTask("Podzadanie 3");
						}
						Thread.sleep(100);
						monitor.worked(100);
					}
				} catch (NullPointerException e) {
					StatusManager statusManager = StatusManager.getManager();
					statusManager.handle(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Błąd programisty"), 
										StatusManager.LOG);
//					StatusManager.LOG | StatusManager.SHOW - zalogowanie i pokazanie okna błędu (antywzorzec)
				}catch (InterruptedException e) {
				} finally{
					monitor.done();
				}
				if(!monitor.isCanceled()){
					Display.getDefault().asyncExec(new Runnable(){
						@Override
						public void run() {
							MessageDialog.openInformation(null, "Wątek", "w Eclipse");
						}
					});
				}
				return Status.OK_STATUS;
			}

			private void checkDozen(IProgressMonitor monitor) {
				try {
					monitor.beginTask("Sprawdzanie dwunastki", 12);
					for(int i = 0; i < 12; i++) {
						Thread.sleep(10);
						monitor.worked(1);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally{
					monitor.done();
				}
			}
			
		};
		ICommandService service = (ICommandService) PlatformUI.getWorkbench().getService(ICommandService.class);
		Command command = service == null ? null : service.getCommand(ID);
		if(command != null){
			job.setProperty(IProgressConstants2.COMMAND_PROPERTY, 
							ParameterizedCommand.generateCommand(command, null));
			job.setProperty(IProgressConstants2.ICON_PROPERTY, 
					ImageDescriptor.createFromURL(HelloHandler.class.getResource("/icons/sample.gif")));
			job.setProperty(IProgressConstants2.SHOW_IN_TASKBAR_ICON_PROPERTY, true);
		}
		job.schedule();
		return null;
	}

}
