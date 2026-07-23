package Main.Windows.Interface;

import Main.Windows.Window;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class InteractiveElement<W extends Window> implements Interactionable {
    protected final W RESPONSIBLE;
    protected Set<String> fokusKeys;

    protected InteractiveElement(W RESPONSIBLE) {
        this.RESPONSIBLE = RESPONSIBLE;
    }

    public void setFokusKeys(String... fokusKeys) {
        this.fokusKeys = new HashSet<>(List.of(fokusKeys));
    }

    @Override
    public boolean hasKey(String key) {
        return fokusKeys.contains(key); }

    @Override
    public abstract void fokus();


}
