package motion.model;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import motion.view.Animable;

public class TimerAnimator extends AbstractAnimator {

	private static final int SEC = 1000;
	private int max;
	private int value;
	private final Timer t;
	private EventListenerList listeners;
	private ChangeEvent event;
//	
//	public ChangeListener[] getChangeListeners() {
//		return listeners.getListeners(ChangeListener.class);
//	}
	
	public TimerAnimator(int max) {
		super(max);
		this.max = max;
		
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeValue();
				if (getSpeed() == 0) {
					t.stop();
				}
				fireTickOccured();
				System.out.println(4444);
			}
		};
		t = new Timer(SEC, al);
		t.setInitialDelay(0);
	}
	
//	@Override
//	public void addChangeListener(ChangeListener lst) {
//		listeners.add(ChangeListener.class, lst);
//	}
//	@Override
//	public void removeChangeListener(ChangeListener lst) {
//		listeners.remove(ChangeListener.class, lst);
//	}
//	// OUTILS
//	protected void fireStateChanged() {
//		Object[] lst = listeners.getListenerList();
//		for (int i = lst.length - 2; i >= 0; i -= 2) {
//			if (lst[i] == ChangeListener.class) {
//			if (event == null) {
//			event = new ChangeEvent(this);
//			}
//			((ChangeListener) lst[i + 1]).stateChanged(event);
//			}
//		}
//	}
	
	private void changeValue() {
		int oldValue = getSpeed();
		int newValue = oldValue == 0 ? max() : oldValue - 1;
		value = newValue;
		
	}
	
	int max() {
		return max;
	}
	
	@Override
	public int getSpeed() {
		return value;
	}

	@Override
	public boolean hasStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasStopped() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPaused() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isResumed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRunning() {
		return value > 0;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpeed(int d) {
		value = d;
		changeValue();
	}

	@Override
	public void start() {
		stop();
		t.start();
	}

	@Override
	public void stop() {
		t.stop();
	}

}
