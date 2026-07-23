package Main.Windows;

import Main.System.Input;
import Main.UI.Render.Renderer;
import Main.UI.Assets.AssetsManager;
import Main.Windows.Interface.Button;
import Main.Windows.Interface.Interactionable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Window {
    protected final Renderer render = new Renderer();
    protected final AssetsManager assets;

    protected boolean shouldQuit = false;
    protected List<Interactionable> interactionables = new ArrayList<>();
    protected Button<?> selectedBtn = Button.VOID;

    public Window(AssetsManager assets) {
        this.assets = assets;
    }

    public void display() {
        printInterface();
        printButtons();
        startControlling();
    }

    protected abstract void printInterface();

    protected void setInteractionableElements(Interactionable... interactionables) {
        for (Interactionable e : interactionables)
            addInteractionable(e);
    }

    public void prepareToQuite() {
        this.shouldQuit = true;
    }

    private void addInteractionable(Interactionable elem) {
        interactionables.add(elem);
    }

    public void setSelectedButton(Button<?> button) {
        selectedBtn.setStateVision(render, false);
        selectedBtn.onSelectionPassive();

        selectedBtn = button;

        selectedBtn.setStateVision(render, true);
        selectedBtn.onSelectionActive();
    }

// Функции класса
    private void printButtons() {
        for (Button<?> b : getButtons())
            render.overlay(b.getTxtrPassive(), false);

        selectedBtn.setStateVision(render, true);
    }

    private void startControlling() {
        setSelectedFirstButton();

        while (!shouldQuit) {
            render.printScreen();
            System.out.print("Команда: ");

            String input = Input.nextLine();

            if (!input.isEmpty()) {
                Interactionable focused = findByFocusKey(input);
                if (focused != null) {
                    focused.fokus();
                } else {
                    selectedBtn.getTxtrActive().clearSpace(render);
                    render.overlay(selectedBtn.getTxtrPassive(), false);
                    selectedBtn.onSelectionPassive();
                    selectedBtn = null;
                    printMessageNotFoundButtonFor(input);
                }
            }
            else clickToSelected();

        }
        shouldQuit = false;
    }

    private List<Button<?>> getButtons() {
        return interactionables.stream()
                .filter(x -> x instanceof Button<?>).map(x -> (Button<?>) x)
                .collect(Collectors.toList());
    }

    private void setSelectedFirstButton() {
        setSelectedButton(getButtons().get(0));
    }

    private void clickToSelected() { selectedBtn.click(); }

    private Interactionable findByFocusKey(String focusKey) {
        for (Interactionable e : interactionables)
            if (e.hasKey(focusKey))
                return e;

        printMessageNotFoundButtonFor(focusKey);
        return Button.VOID;
    }

    private void printMessageNotFoundButtonFor(String key) {
        String message = assets.getTexture("[Mes_NotFoundButton]")[0];
        render.messegeLine(message + " '" + key + "'");
    }

}
