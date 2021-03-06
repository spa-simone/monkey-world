/**
 * Monkey World is an environment where a monkey agent can stole a bunch of bananas and go home.
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package spa.simone.monkeyworld.gui;

import spa.simone.monkeyworld.core.agent.Monkey;
import spa.simone.monkeyworld.core.agent.SecondAgent;
import spa.simone.monkeyworld.core.agent.ThirdAgent;
import spa.simone.monkeyworld.core.environment.EnvType;
import spa.simone.monkeyworld.core.environment.Laboratory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Deep Blue Team
 */
public class FinalPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JFrame frame;
    private int choice;
    private BottomPanel bottomPanel;
    private Laboratory lab;

    public FinalPanel(JFrame f, int choice) {
        this.frame = f;
        this.choice = choice;
        frame.setSize(800, 800);
        createPanel();
    }

    private void createPanel() {
        this.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.CYAN);

        JLabel chooseTimeLabel = new JLabel("Time Banana Move: ");
        final JTextField chooseTime = new JTextField("5");
        chooseTime.setPreferredSize(new Dimension(50, 20));
        chooseTime.setToolTipText("Value: Min( 1 ), Max ( 50 )");
        if (choice == 1) {
            topPanel.add(chooseTimeLabel);
            topPanel.add(chooseTime);
        }
        switch (choice) {
            case 0:
                lab = new Laboratory(new Monkey());
                break;
            case 1:
                lab = new Laboratory(new SecondAgent(), EnvType.DYNAMIC);
                break;
            case 2:
                lab = new Laboratory(new ThirdAgent(), EnvType.USER_DEFINED);
                break;
        }
        bottomPanel = new BottomPanel(choice, lab);
        bottomPanel.repaint();

        final JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                if (choice == 1) {
                    int time = 1;
                    try {
                        String tmp = chooseTime.getText();
                        time = Integer.parseInt(tmp);
                        if (time == 0 || time > 50)
                            time = 5;
                    } catch (Exception e) {
                        // TODO: handle exception
                        time = 5;
                    }
                    lab.setIntervalTime(time);
                }
                bottomPanel.setEditable(false);
                start.setEnabled(false);
                LaboratoryThread t = new LaboratoryThread(lab, bottomPanel);
                t.start();
            }
        });
        topPanel.add(start);

        JSplitPane panel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPanel, bottomPanel);
        panel.setPreferredSize(new Dimension(800, 800));

        panel.setDividerLocation(70);
        panel.setDividerSize(2);
        panel.setEnabled(false);

        this.add(panel);
    }
}