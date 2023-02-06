package crazy.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import util.Contract;

/**
 * Implémentation standard de PodiumModel.
 */
public class StdPodiumModel<E> implements PodiumModel<E> {

    // ATTRIBUTS

    private List<E> data;
    private int capacity;
    private EventListenerList listeners;
    // CONSTRUCTEURS

    public StdPodiumModel(List<E> init, int capacity) {
        Contract.checkCondition(init != null);
        Contract.checkCondition(!init.contains(null));

        this.capacity = capacity;
        data = new ArrayList<E>(init);
        listeners = new EventListenerList();
    }

    public StdPodiumModel() {
        this(new ArrayList<E>(), 0);
    }

    // REQUETES

    @Override
    public E bottom() {
        Contract.checkCondition(size() > 0);

        return data.get(0);
    }

    @Override
    public E elementAt(int i) {
        Contract.checkCondition(0 <= i && i < capacity());

        return i < size() ? data.get(i) : null;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public boolean similar(PodiumModel<E> that) {
        Contract.checkCondition(that != null);

        int dataSize = data.size();
        boolean result = (that.capacity() == capacity)
                && (that.size() == dataSize);
        for (int i = 0; result && (i < dataSize); i++) {
            result = that.elementAt(i).equals(data.get(i));
        }
        return result;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public E top() {
        Contract.checkCondition(size() > 0);

        return data.get(data.size() - 1);
    }

    @Override
    public String toString() {
        String res = "[";
        int dataSize = data.size();
        for (int i = 0; i < dataSize; i++) {
            res += data.get(i) + "|";
        }
        return capacity + "/" + res + "]";
    }

    // COMMANDES

    @Override
    public void addTop(E elem) {
        Contract.checkCondition(elem != null);
        Contract.checkCondition(size() < capacity());

        data.add(elem);
    }

    @Override
    public void removeBottom() {
        Contract.checkCondition(size() > 0);

        data.remove(0);
    }

    @Override
    public void removeTop() {
        Contract.checkCondition(size() > 0);

        data.remove(size() - 1);
    }

	@Override
	public ChangeListener[] getChangeListeners() {
		return listeners.getListeners(ChangeListener.class);
	}

	@Override
	public void addChangeListener(ChangeListener cl) {
		listeners.add(ChangeListener.class, cl);
		
	}

	@Override
	public void removeChangeListener(ChangeListener cl) {
		listeners.remove(ChangeListener.class, cl);
	}
}
