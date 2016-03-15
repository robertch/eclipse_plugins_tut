package com.ht.clock;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.ht.clock"; //$NON-NLS-1$
	private TrayItem trayItem;
	private Image image;
	

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		final Display display = Display.getDefault();
		display.asyncExec(new Runnable(){
			public void run() {
				image = new Image(display,Activator.class.getResourceAsStream("/icons/sample.gif"));
				Tray tray = display.getSystemTray();
				if(tray != null && image != null){
					trayItem = new TrayItem(tray, SWT.NONE);
					trayItem.setToolTipText("Text");
					trayItem.setVisible(true);
					trayItem.setText("Text");
					trayItem.setImage(new Image(trayItem.getDisplay(),Activator.class.getResourceAsStream("/icons/sample.gif")));
				}
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
		if(trayItem != null && !trayItem.isDisposed()){
			Display.getDefault().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					if(trayItem != null && !trayItem.isDisposed()){
						trayItem.dispose();
					}
				}
			});
		}
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}