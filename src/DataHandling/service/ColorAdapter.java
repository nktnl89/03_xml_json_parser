package DataHandling.service;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.awt.*;

public class ColorAdapter extends XmlAdapter<String, Color> {

    @Override
    public Color unmarshal(String v) throws Exception {
        return Color.decode(v);
    }

    @Override
    public String marshal(Color v) throws Exception {
        String hexColour = Integer.toHexString(v.getRGB() & 0xffffff);
        if (hexColour.length() < 6) {
            hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
        }
        return "#" + hexColour;
    }
}
