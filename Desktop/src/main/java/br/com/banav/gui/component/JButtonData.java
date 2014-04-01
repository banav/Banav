package br.com.banav.gui.component;

import javax.swing.*;

/**
 * Created by gilson on 4/1/14.
 */
public class JButtonData extends JButton {

    private Object data;

    public JButtonData(Icon icon) {
        super(icon);
    }

    public JButtonData(String text) {
        super(text);
    }

    public JButtonData(Action a) {
        super(a);
    }

    public JButtonData(String text, Icon icon) {
        super(text, icon);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
