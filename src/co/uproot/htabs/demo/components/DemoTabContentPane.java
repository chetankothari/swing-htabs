/*
   Copyright 2014 Uproot Labs India Pvt Ltd

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package co.uproot.htabs.demo.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import co.uproot.htabs.tabmanager.TabManager;
import co.uproot.htabs.tabmanager.TabManager.Tab;

public class DemoTabContentPane extends JPanel {

  final public static int CIRCLE = 0;
  final public static int SQUARE = 1;
  final public static int HEXAGON = 2;

  final public static int COLOR_1 = 0;
  final public static int COLOR_2 = 1;
  final public static int COLOR_3 = 2;
  final public static int COLOR_4 = 3;
  final public static int COLOR_5 = 4;
  final public static int COLOR_6 = 5;
  final public static int COLOR_7 = 6;
  final public static int COLOR_8 = 7;
  final public static int COLOR_9 = 8;

  final public static Color colors[] = new Color[] {
      new Color(10, 10, 10, 190),
      new Color(10, 10, 250, 190),
      new Color(10, 250, 10, 190),
      new Color(10, 250, 250, 190),
      new Color(250, 10, 10, 190),
      new Color(250, 10, 250, 190),
      new Color(250, 250, 10, 190),
      new Color(250, 250, 250, 190),
      new Color(90, 25, 250, 190),
  };

  final public static Icon icons[] = new Icon[] {
      new ColoredIcon(colors[0]),
      new ColoredIcon(colors[1]),
      new ColoredIcon(colors[2])
  };
  
  final private TabManager tabManager;
  
  public DemoTabContentPane(final String tabTitle, final int color, final int icon, final TabManager tabManager) {
    setLayout(new GridBagLayout());
    this.tabManager = tabManager;

    final GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.1;
    c.insets = new Insets(5, 100, 5, 100);
    
    final JLabel contentPaneTitle = new JLabel("<html><center>Content of tab<p><big><b>" + tabTitle + "</b></big></p></center></html>");
    c.gridx = 2;
    c.gridy = 1;
    c.gridwidth = 1;
    add(contentPaneTitle, c);
    
    final JPanel tabTitleEditorPanel = new JPanel();
    tabTitleEditorPanel.setLayout(new BorderLayout());
    tabTitleEditorPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), "Title"));
    final JLabel title = new JLabel("Edit title");
    final JTextField tabTitleEditor = new JTextField(tabTitle);
    final JButton changeTabTitle = new JButton("Change");
    tabTitleEditorPanel.add(title, BorderLayout.PAGE_START);
    tabTitleEditorPanel.add(tabTitleEditor, BorderLayout.CENTER);
    tabTitleEditorPanel.add(changeTabTitle, BorderLayout.LINE_END);
    c.gridx = 1;
    c.gridy = 2;
    c.gridwidth = 3;
    add(tabTitleEditorPanel, c);

    final JPanel tabIconPickerPanel = new JPanel();
    tabIconPickerPanel.setLayout(new BorderLayout());
    tabIconPickerPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), "Icon"));
    final JLabel iconTitle = new JLabel("Choose icon");
    tabIconPickerPanel.add(iconTitle, BorderLayout.PAGE_START);
    tabIconPickerPanel.add(createIconPicker(CIRCLE, tabManager), BorderLayout.CENTER);
    c.gridy = 3;
    add(tabIconPickerPanel, c);

    final JPanel tabColorPickerPanel = new JPanel();
    tabColorPickerPanel.setLayout(new BorderLayout());
    tabColorPickerPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.black), "Color"));
    final JLabel colorTitle = new JLabel("Choose colors");
    tabColorPickerPanel.add(colorTitle, BorderLayout.PAGE_START);
    tabColorPickerPanel.add(createColorPicker(COLOR_1, this.tabManager), BorderLayout.CENTER);
    c.gridy = 4;
    add(tabColorPickerPanel, c);

  }

  private static JPanel createColorPicker(final int color, final TabManager tabManager) {
    final ButtonGroup colorGroup = new ButtonGroup();
    final JPanel colorPicker = new JPanel();
    colorPicker.setLayout(new GridLayout(3, 3));
    for (int index = 0; index < colors.length; index++) {
      final JPanel colorRadioButton = new JPanel();
      colorRadioButton.setLayout(new BorderLayout());

      final JLabel iconLabel = new JLabel();
      iconLabel.setIcon(new ColoredIcon(colors[index]));

      final JRadioButton colorButton = new JRadioButton("" + index);
      if (index == color) {
        colorButton.setSelected(true);
      }
      colorButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
          final JRadioButton colorButton = (JRadioButton) e.getSource();
          final int colorIndex = Integer.parseInt(colorButton.getText());
          final Tab tab = tabManager.getActiveTab();
          tab.setTabIcon(new ColoredIcon(colors[colorIndex]));
        }
      });
      colorRadioButton.add(colorButton, BorderLayout.LINE_START);
      colorRadioButton.add(iconLabel, BorderLayout.CENTER);
      colorPicker.add(colorRadioButton);
      colorGroup.add(colorButton);
    }
    return colorPicker;
  }

  private static JPanel createIconPicker(final int icon, final TabManager tabManager) {
    final ButtonGroup iconGroup = new ButtonGroup();
    final JPanel iconPicker = new JPanel();
    iconPicker.setLayout(new GridLayout(0, 3));
    for (int index = 0; index < icons.length; index++) {
      final JPanel iconRadioButton = new JPanel();
      iconRadioButton.setLayout(new BorderLayout());

      final JLabel iconLabel = new JLabel();
      iconLabel.setIcon(icons[index]);
      final JRadioButton iconButton = new JRadioButton();
      if (index == icon) {
        iconButton.setSelected(true);
      }
      iconButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
        }
      });
      iconRadioButton.add(iconButton, BorderLayout.LINE_START);
      iconRadioButton.add(iconLabel, BorderLayout.CENTER);
      iconPicker.add(iconRadioButton);
      iconGroup.add(iconButton);
    }
    return iconPicker;
  }

}
