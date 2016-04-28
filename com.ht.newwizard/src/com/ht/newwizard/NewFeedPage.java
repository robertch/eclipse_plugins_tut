package com.ht.newwizard;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PerspectiveAdapter;

public class NewFeedPage extends WizardPage {
	
	private Text descriptionText;
	private Text urlText;
	
	public String getDescription() {
		return getTextFrom(descriptionText);
	}

	public String getUrl() {
		return getTextFrom(urlText);
	}

	private String getTextFrom(Text text){
		return text==null || text.isDisposed() ? null : text.getText();
	}
	
	protected NewFeedPage() {
		super("NewFeedPage");
		setTitle("Add New Feed");
		setMessage("a message");
	}

	@Override
	public void createControl(Composite parent) {
		Composite page = new Composite(parent,SWT.NONE);
		page.setLayout(new GridLayout(2,false));
		page.setLayoutData(new GridData(GridData.FILL_BOTH));
		Label urlLabel = new Label(page, SWT.NONE);
		urlLabel.setText("Feed URL:");
		urlText = new Text(page, SWT.BORDER);
		urlText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Label descriptionLabel = new Label(page, SWT.NONE);
		descriptionLabel.setText("Feed description:");
		descriptionText = new Text(page, SWT.BORDER);
		descriptionText.setLayoutData(
		new GridData(GridData.FILL_HORIZONTAL));
		
		CompletedListener listener = new CompletedListener();
		descriptionText.addKeyListener(listener);
		urlText.addKeyListener(listener);
		
		setControl(page);
		setPageComplete(false);
	}

	@Override
	public void performHelp(){
//		setMessage("To jest pomoc do wizarda",IMessageProvider.INFORMATION);
		MessageDialog.openInformation(getShell(), "Pomoc do wizarda", "Weź i wypełnij poniższe pola");
	}
	private class CompletedListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			boolean hasDescription = !"".equals(getTextFrom(descriptionText));
			boolean hasUrl = !"".equals(getTextFrom(urlText));
			if(!hasDescription){
				setMessage("Brak opisu",IMessageProvider.ERROR);
			}
			if(!hasUrl){
				setMessage("Brak URL",IMessageProvider.ERROR);
			}
			if(hasDescription && hasUrl){
				setMessage("Dane OK",IMessageProvider.INFORMATION);
			}
			setPageComplete(hasDescription && hasUrl);
		}

	} 
}
