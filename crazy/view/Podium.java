package crazy.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import crazy.model.PodiumModel;
import util.Contract;

/**
 * Composant graphique donnant une vue de PodiumModel.
 * Le paramètre de type du podium est un type implantant Drawable.
 * Un podium possède un modèle de type PodiumModel<E> qui est une propriété
 *  non liée accessible en lecture-écriture.
 * Un podium observe toujours son modèle pour se redessiner lorsque celui-ci
 *  change d'état.
 * Enfin, la taille préférée d'un podium est fonction de la capacité de son
 *  modèle.
 * @inv <pre>
 *     getModel() != null </pre>
 */
public class Podium<E extends Drawable> extends JComponent {

    // ATTRIBUTS

    private static final Color NO_ELEM = Color.LIGHT_GRAY;
    private static final Color PODIUM_COLOR = Color.BLACK;
    private static final Color NO_PODIUM_COLOR = Color.WHITE;
    
    // dimensions du podium
    private static final int BASE_HEIGHT = 5;
    private static final int PODIUM_HEIGHT = 2 * BASE_HEIGHT;
    private static final int LEG_WIDTH = 7;
    
    private static final int MARGIN = 2;
    
    // dimensions allouées à un E sur le podium
    private static final int ELEM_WIDTH = 40;
    private static final int ELEM_HEIGHT = 40;

    private ModelListener listener;
    private PodiumModel<E> model;

    // CONSTRUCTEURS

    /**
     * Un podium de modèle pm.
     * Le podium observe son modèle pour se redessiner lorsque ce dernier change
     *  d'état.
     * @pre <pre>
     *     pm != null </pre>
     * @post <pre>
     *     getModel() == pm </pre>
     */
    public Podium(PodiumModel<E> pm) {
        Contract.checkCondition(pm != null);

        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        fixModel(pm);
        listener = new ModelListener();
        // la taille préférée doit être ajustée
        resetPreferredSize();
    }

    // REQUETES

    /**
     * Le modèle de ce Podium.
     */
    public PodiumModel<E> getModel() {
        return model;
    }

    // COMMANDES

    /**
     * Fixe un nouveau modèle pour ce Podium.
     * Le podium observe son modèle pour se redessiner lorsque ce dernier change
     *  d'état.
     * @pre <pre>
     *     m != null </pre>
     * @post <pre>
     *     getModel() == m </pre>
     */
    public void setModel(PodiumModel<E> m) {
        Contract.checkCondition(m != null);

        fixModel(m);
        // la taille préférée doit être ajustée
        resetPreferredSize();
        // le composant doit se redessiner car son modèle vient de changer
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int capacity = model.capacity();
        int size = model.size();
        
        /* Rappel :
         * - l'origine est en (0,0),
         * - l'axe des X vers la droite,
         * - celui des Y vers le bas
         */
        
        // x repère l'abscisse à gauche de la colonne
        final int x = (getWidth() - ELEM_WIDTH) / 2;
        // y repère l'ordonnée variable du csg des rectangles internes
        int y = getHeight() - PODIUM_HEIGHT;
        
        /* Le support se dessine comme deux rectangles centrés horizontalement
         *   et alignés en bas.
         */
        // grand rectangle
        // largeur = largeur d'un élément
        // hauteur = hauteur du support
        g.setColor(PODIUM_COLOR);
        g.fillRect(x, y, ELEM_WIDTH, PODIUM_HEIGHT);
        // petit rectangle
        // largeur = largeur d'un élément - largeur des 2 pieds du support
        // hauteur = moitié de la hauteur du support
        g.setColor(NO_PODIUM_COLOR);
        g.fillRect(x + LEG_WIDTH, y + PODIUM_HEIGHT - BASE_HEIGHT,
                ELEM_WIDTH - 2 * LEG_WIDTH, BASE_HEIGHT);
        
        // tous les éléments
        for (int i = 0; i < size; i++) {
            y -= ELEM_HEIGHT;
            Graphics g2 = g.create(x, y, ELEM_WIDTH, ELEM_HEIGHT);
            try {
                model.elementAt(i).draw(g2);
            } finally {
                g2.dispose();
            }
        }
        
        // le vide au-dessus des éléments
        g.setColor(NO_ELEM);
        int h = ELEM_HEIGHT * (capacity - size);
        g.fillRect(x, y - h, ELEM_WIDTH, h);
    }

    // OUTILS

    /**
     * @pre
     *     pm != null
     * @post
     *     getModel() == pm
     *     Le podium observe son nouveau modèle pour se redessiner lorsque ce
     *     dernier change d'état
     */
    protected transient ChangeEvent changeEvent = null;
    protected void fireStateChanged() {
    	Object[] listeners = listenerList.getListenerList();
	    	for (int i = listeners.length - 2; i >= 0; i -= 2) {
		    	if (listeners[i] == ChangeListener.class) {
			    	if (changeEvent == null) {
			    	changeEvent = new ChangeEvent(this);
			    }
		    	((ChangeListener) listeners[i+1])
		    	.stateChanged(changeEvent);
	    	}
    	}
	}
    
    private class ModelListener implements ChangeListener {
    	public void stateChanged(ChangeEvent e) {
    		fireStateChanged();
    	}
	}
    
    
    private void fixModel(PodiumModel<E> pm) {
        /*****************/
        /** A COMPLETER **/
    	Contract.checkCondition(pm != null);
    	if (model == null) {
	    	model = pm;
	    	model.addChangeListener(listener);
    	} else {
    		model.removeChangeListener(listener);
    		pm.addChangeListener(listener);
    		model = pm;
    	}
        /*****************/
    }

    private void resetPreferredSize() {
        int maxNbOfElem = model.capacity();
        Dimension dim = new Dimension(
                ELEM_WIDTH + 2 * MARGIN,
                maxNbOfElem * ELEM_HEIGHT + PODIUM_HEIGHT);
        setPreferredSize(dim);
    }
}
