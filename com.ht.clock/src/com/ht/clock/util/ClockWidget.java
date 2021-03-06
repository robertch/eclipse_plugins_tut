package com.ht.clock.util;

import java.time.LocalDateTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class ClockWidget extends Canvas{
	private final Color color;
	private int offset;

	public ClockWidget(Composite parent,int style,RGB rgb) {
		super(parent,style);
		this.color = new Color(parent.getDisplay(),rgb);
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				if(color!=null && !color.isDisposed()) color.dispose();
			}
		});
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				ClockWidget.this.painControl(e);
			}
		});
		new Thread("TikTak") {
			public void run() {
				while (!ClockWidget.this.isDisposed()) {
					ClockWidget.this.getDisplay().asyncExec(new Runnable() {
						public void run() {
							if (!ClockWidget.this.isDisposed())
								ClockWidget.this.redraw();
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						return;
					}
				}
			}
		}.start();
	}
	protected void painControl(PaintEvent e) {
		int seconds = LocalDateTime.now().getSecond(); 
		int arc = (15-seconds) * 6 % 360;
		Color blue = e.display.getSystemColor(SWT.COLOR_BLUE);
		e.gc.setBackground(this.color);
		e.gc.fillArc(e.x, e.y, e.width-1, e.height-1, arc-1, 2);
		
		e.gc.setBackground(e.display.getSystemColor(SWT.COLOR_BLACK));
		int hours = LocalDateTime.now().getHour() + this.offset;
		arc = (3 - hours) * 30 % 360;
		e.gc.fillArc(e.x, e.y, e.width-1, e.height-1, arc-5, 10);
	}
	
	public Point computeSize(int w, int h, boolean changed){
		int size;
		if(w == SWT.DEFAULT){
			size = w;
		}else if(h == SWT.DEFAULT){
			size = h;
		}else {
			size = Math.min(w, h);
		}
		if(size == SWT.DEFAULT) size = 50;
		return new Point(size, size);
		
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
