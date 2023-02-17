package motion.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;

import javax.swing.JComponent;

import motion.model.Mobile;
import motion.model.StdMobile;
import util.Contract;

public class MobileComponent extends JComponent implements Animable {

    /**
     * La couleur (bleu) du rectangle statique du modèle.
     */
    private static final Color STAT_COLOR = Color.BLUE;

    /**
     * La couleur (rouge) du rectangle mobile du modèle.
     */
    private static final Color MOV_COLOR = Color.RED;

    // ATTRIBUTS

    // Le modèle du MobileComponent.
    private Mobile model;

    // CONSTRUCTEURS

    /**
     * @pre <pre>
     *     width > 0 && height > 0
     *     0 < ray <= min(width, height) / 2 </pre>
     * @post <pre>
     *     model.isMovable()
     *     model.getStaticRect() est un rectangle (0, 0, width, height)
     *     model.getMovingRect() est un rectangle (0, 0, 2 * ray, 2 * ray)
     *     model.getHorizontalShift() == 0
     *     model.getVerticalShift() == 0
     *     getPreferredSize().width == width
     *     getPreferredSize().height == height </pre>
     */
    public MobileComponent(int width, int height, int ray) {
        Contract.checkCondition(width > 0 && height > 0);
        Contract.checkCondition(ray > 0 && ray <= Math.min(width, height) / 2);

        createModel(width, height, ray);
        createView();
        createController();
    }

    // COMMANDES

    @Override
    public void animate() {
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }

    // OUTILS

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(STAT_COLOR);
        Rectangle sr = model.getStaticRect();
        g.fillRect(0, 0, sr.width, sr.height);
        g.setColor(MOV_COLOR);
        Rectangle mr = model.getMovingRect();
        Point tlc = mr.getLocation();
        g.fillOval(tlc.x, tlc.y, mr.width, mr.height);
        // Pour être certain du vidage immédiat du buffer d'affichage
        //  ce qui pose problème aux gestionnaires de fenêtres sous Linux et
        //  probablement aussi sous mac d'après ce que j'ai lu, mais pas sous
        //  Windows...
        Toolkit.getDefaultToolkit().sync();
    }

    private void createModel(int width, int height, int ray) {
        Dimension dim = new Dimension(width, height);
        Rectangle sr = new Rectangle(dim);
        Rectangle mr = new Rectangle(new Dimension(2 * ray, 2 * ray));
        model = new StdMobile(sr, mr);
        model.setHorizontalShift(ray / 2);
        model.setVerticalShift(ray / 2);
    }
    
    private void createView() {
        Rectangle r = model.getStaticRect();
        Dimension d = new Dimension(r.width, r.height);
        setPreferredSize(d);
    }

    private void createController() {
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }
    
    // TYPES IMBRIQUES

    private class MouseHandler extends MouseAdapter {
        /*****************/
        /** A COMPLETER **/
        /*****************/
    }
}
