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
	private boolean started = false,
					stopped = true,
					paused = false;
	
//	
//	public ChangeListener[] getChangeListeners() {
//		return listeners.getListeners(ChangeListener.class);
//	}
	
	public TimerAnimator(int max) {
		super(max);
		this.max = max;
		value = 0;
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(t.getDelay());
				if (getSpeed() == 0 && !isRunning())
					stop();
				else if (getSpeed() == 0)
					pause();
				else {
					start();
				}
				fireTickOccured();
				System.out.println(4444);
			}
		};
		t = new Timer((int) Double.POSITIVE_INFINITY, al);
		t.setInitialDelay(0);
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
		return started;
	}

	@Override
	public boolean hasStopped() {
		return !t.isRunning();
	}

	@Override
	public boolean isPaused() {
		return paused;
	}

	@Override
	public boolean isResumed() {
		return !paused;
	}

	@Override
	public boolean isRunning() {
		System.out.println(t.isRunning());
		return t.isRunning();
	}

	@Override
	public void pause() {
		t.setDelay((int) Double.POSITIVE_INFINITY);
		System.out.println("yes i have executed");
		paused = true;
		fireStateChanged();
	}

	@Override
	public void resume() {
		paused = false;
		fireStateChanged();
	}

	@Override
	public void setSpeed(int d) {
		value = d;
		fireStateChanged();
	}

	@Override
	public void start() {
		stop();
		t.setDelay(SEC / value);
		t.start();
		started = true;
		fireStateChanged();
	}

	@Override
	public void stop() {
		t.stop();
		fireStateChanged();
	}

}
