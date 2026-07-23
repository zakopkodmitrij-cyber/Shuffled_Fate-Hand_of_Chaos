package Main.Windows.Interface;

import Main.Windows.Window;

// [В РАЗРАБОТКЕ]
public abstract class Command<W extends Window> extends InteractiveElement<W> {

    protected Command(W RESTONSIBLE) {
        super(RESTONSIBLE);
    }

    @Override
    public void fokus() { execute(); }

    public abstract void execute();

}
