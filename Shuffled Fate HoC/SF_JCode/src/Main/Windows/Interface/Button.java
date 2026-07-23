package Main.Windows.Interface;

import Main.UI.Assets.AssetsManager;
import Main.UI.Render.Renderer;
import Main.UI.Render.Texture;
import Main.Windows.Window;

public abstract class Button<W extends Window> extends InteractiveElement<W> {
    public static final Button<?> VOID = new Button<>(null) {
        @Override public void click() {}
    };

    protected int coord_stroke, coord_column;
    protected Texture
            txtrActive  = new Texture(),
            txtrPassive = new Texture();

    public Button(W RESTONSIBLE) {
        super(RESTONSIBLE);
    }

    @Override
    public void fokus() {
        RESPONSIBLE.setSelectedButton(this);
    }

    protected void generateTextures(AssetsManager assets, String pseudoKey) {
        txtrActive.data  = assets.getTexture("[Btn_" + pseudoKey + "_A]");
        txtrPassive.data = assets.getTexture("[Btn_" + pseudoKey + "_P]");
    }

    public void setCoord(int stroke, int column) {
        setCoord_stroke(stroke);
        setCoord_column(column);
    }

    public int getCoord_stroke() { return coord_stroke; }
    public void setCoord_stroke(int coord_stroke) {
        int shift = coord_stroke - this.coord_stroke;

        this.coord_stroke += shift;
        this.txtrActive.coord_stroke += shift;
        this.txtrPassive.coord_stroke += shift;
    }

    public int getCoord_column() { return coord_column; }
    public void setCoord_column(int coord_column) {
        int shift = coord_column - this.coord_column;

        this.coord_column += shift;
        this.txtrActive.coord_column += shift;
        this.txtrPassive.coord_column += shift;
    }

    public Texture getTxtrActive() { return txtrActive; }
    public Texture getTxtrPassive() { return txtrPassive; }

    public void setStateVision(Renderer render, boolean isFocused) {
        if (isFocused) {
            txtrPassive.clearSpace(render);
            render.overlay(txtrActive, false);
        } else {
            txtrActive.clearSpace(render);
            render.overlay(txtrPassive, false);
        }
    }

    public void onSelectionActive() {}
    public void onSelectionPassive() {}
    public abstract void click();

}
