/**
 * Monkey World is an environment where a monkey agent can stole a bunch of bananas and go home.
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package spa.simone.monkeyworld.gui;

import spa.simone.monkeyworld.core.environment.Laboratory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import static spa.simone.monkeyworld.Utils.getImage;

/**
 * @author Deep Blue Team
 */
public class BottomPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Image monkey;
    private Image box;
    private Image bananas;
    private Laboratory lab;

    private int size = 60;
    private int topPosition = 200;
    private int bottomPosition = 350;
    private boolean edit;
    private int choice;

    public BottomPanel(int c, final Laboratory l) {
        this.choice = c;
        lab = l;
        setEditable(true);

        this.setBackground(Color.WHITE);

        monkey = getImage("images/scimmia.jpg");
        box = getImage("images/box.png");
        bananas = getImage("images/bananas.jpg");
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(monkey, 0);
        mt.addImage(box, 1);
        mt.addImage(bananas, 2);
        try {
            mt.waitForID(0);
            mt.waitForID(1);
            mt.waitForID(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
                l.setInvisible(true);
            }
        });

        this.addMouseListener(new MouseListener() {

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int i = (x - 100) / 60;
                if (i >= 0 && i < 10) {
                    if (y > 200 && y < 260) {
                        if (isEditable() || choice == 2)
                            lab.setBananasBunch(i);
                    } else if (y > 350 && y < 410) {
                        if (isEditable())
                            lab.setBox(i);
                    } else if (y > 410 && y < 470) {
                        if (isEditable() && i != lab.getBox())
                            lab.setHome(i);
                    }
                    repaint();
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawString("Performance Measure: " + lab.getPerformanceMeasure(), 300, topPosition - 50);

        if (!lab.isGrabbed())
            graphics.drawImage(bananas, lab.getBananasBunch() * size + 105, topPosition + 5, null);

        for (int i = 0; i < 10; i++) {
            graphics.drawRect(i * size + 100, topPosition, size, size);
            if (i == lab.getBox() && (!lab.isInvisible() || lab.isGrabbed())) {
                graphics.fillRect(i * size + 100, bottomPosition, size, size);
            } else {
                graphics.drawRect(i * size + 100, bottomPosition, size, size);
            }
            if (i == lab.getHome() && lab.isMonkeyAtHome()) {
                graphics.drawRect(i * size + 100, bottomPosition + 60, size, size);
            }
        }
        if (lab.isOnTheBox()) {
            graphics.drawImage(monkey, lab.getMonkey() * size + 100, (bottomPosition + topPosition) / 2, null);
        } else {
            if (lab.isMonkeyAtHome()) {
                graphics.drawImage(monkey, lab.getMonkey() * size + 104, bottomPosition + 70, null);
            } else {
                if (lab.isGrabbed() || (!lab.isInvisible()))
                    graphics.drawImage(monkey, lab.getMonkey() * size + 102, bottomPosition + 10, null);
            }
        }
    }

    public void setEditable(boolean edit) {
        this.edit = edit;
    }

    public boolean isEditable() {
        return edit;
    }
}