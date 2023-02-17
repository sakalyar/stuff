package motion.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.EventListener;
import motion.model.Animator;
import motion.util.TickEvent;
import motion.util.TickListener;
import motion.view.Animable;

public class MotionAppli {
    
    private static final int MAJOR_TICK = 10;

    // ATTRIBUTS

    private JFrame frame;
    private Map<BKey, JButton> buttons;
    private JSlider slider;
    private Animable animable;
    private Animator animator;

    // CONSTRUCTEURS

    public MotionAppli(Animable v, Animator m) {
        createModel(m);
        createView(v);
        placeComponents();
        createController();
    }

    // COMMANDES

    public void display() {
        // initialisation de la vue en fonction de l'état du modèle
        updateButtons();
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // OUTILS

    private void createModel(Animator m) {
        animator = m;
    }
    
    private void createView(Animable v) {
        frame = new JFrame();
        slider = getConfiguredSlider();
        buttons = getButtonsMap();
        animable = v;
    }
    
    private JSlider getConfiguredSlider() {
        JSlider result = new JSlider(0, 0);
        result.setPaintTicks(true);
        result.setPaintLabels(true);
        result.setSnapToTicks(true);
        configTickSpacingFor(result);
        // il faut fixer les deux valeurs suivantes dans cet ordre pour être sûr
        // que animator.getSpeed() soit inférieure à slider.getMaximum()
        result.setMaximum(animator.getMaxSpeed());
        result.setValue(animator.getSpeed());
        return result;
    }
    
    private void configTickSpacingFor(JSlider js) {
        int max = animator.getMaxSpeed();
        int n = MAJOR_TICK;
        while (max / n < 2) {
            n = n / 2;
        }
        js.setMinorTickSpacing(1);
        js.setMajorTickSpacing(n);
    }
    
    private Map<BKey, JButton> getButtonsMap() {
        Map<BKey, JButton> result = new EnumMap<BKey, JButton>(BKey.class);
        for (BKey k : BKey.values()) {
            JButton b = new JButton(k.label);
            b.setName(k.name());
            result.put(k, b);
        }
        return result;
    }
    
    private void placeComponents() {
        JPanel p = new JPanel();
        { //--
            for (BKey k : BKey.values()) {
                p.add(buttons.get(k));
            }
        } //--
        frame.add(p, BorderLayout.NORTH);

        p = new JPanel();
        { //--
            p.add((Component) animable);
        } //--
        frame.add(p, BorderLayout.CENTER);

        frame.add(slider, BorderLayout.SOUTH);
    }

    private void createController() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pilotage de l'animation par les boutons
        /*****************/
        /** A COMPLETER **/
        
        /*****************/
        
        // Modification de la vitesse de l'animation
        /*****************/
        /** A COMPLETER **/
        
        slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				
				System.out.println("Current speed is: " + animator.getSpeed());
				animator.setSpeed(slider.getValue());
				System.out.println("New speed is: " + animator.getSpeed());
				System.out.println(slider.getValue());
			}
        });
        
        /*****************/

        // Observation des ticks
        /*****************/
        /** A COMPLETER **/
        
        buttons.get(BKey.START).addActionListener(
        		new ActionListener() {
            		@Override
            		public void actionPerformed(ActionEvent e) {
            			BKey.START.executeCommand(animator);
        				System.out.println(000);

        		}
    		});
        
        buttons.get(BKey.TERMINATE).addActionListener(
    		new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			BKey.TERMINATE.executeCommand(animator);
        			System.out.println("T");
    		}
		});
        
        /*****************/

        // Observation des changements d'état
        /*****************/
        /** A COMPLETER **/
        animator.addTickListener(new TickListener() {
			 
			@Override
			public void tickOccurred(TickEvent e) {
				animable.animate();
				System.out.println(123);				
			}
        });
        /*****************/
    }
    
    private void updateButtons() {
        /*****************/
        /** A COMPLETER **/
    	
    	buttons.get(BKey.PAUSE).setEnabled(animator.isRunning());
    	buttons.get(BKey.RESUME).setEnabled(animator.isPaused());
        /*****************/
    }

    // TYPES IMBRIQUES

    /**
     * Regroupement :
     * - des tests indiquant si les boutons doivent être actifs ou non,
     * - du code de pilotage de l'animation,
     * - de l'étiquette de chaque bouton.
     */
    private enum BKey {
        START("Start") {
            @Override boolean enabledValue(Animator anim) {
                return !anim.hasStarted();
            }
            @Override void executeCommand(Animator anim) {
                anim.start();
            }
        },
        PAUSE("Pause") {
            @Override boolean enabledValue(Animator anim) {
                return anim.isResumed();
            }
            @Override void executeCommand(Animator anim) {
                anim.pause();
            }
        },
        RESUME("Resume") {
            @Override boolean enabledValue(Animator anim) {
                return anim.isPaused();
            }
            @Override void executeCommand(Animator anim) {
                anim.resume();
            }
        },
        TERMINATE("Terminate") {
            @Override boolean enabledValue(Animator anim) {
                return anim.isRunning();
            }
            @Override void executeCommand(Animator anim) {
                anim.stop();
            }
        };

        private String label;

        BKey(String lbl) {
            label = lbl;
        }

        abstract boolean enabledValue(Animator anim);
        abstract void executeCommand(Animator anim);
    }
}
