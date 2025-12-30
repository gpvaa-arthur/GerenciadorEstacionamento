package view.util;

import java.awt.*;

public class AuxLayout
{
    public static void setup(GridBagConstraints c, int x, int y, int w, int h, double wx, double wy){
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = w;
        c.gridheight = h;
        c.weightx = wx;
        c.weighty = wy;
    }
    public static void reset(GridBagConstraints c){
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
    }
}
