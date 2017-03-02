package cs3500.music.view;

import cs3500.music.model.MusicEditorViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * A GuiViewFrame in Swing, ie. a window, that provides a canvas for the elements of a
 * MusicEditorViewModel to be rendered onto.
 */
public class GuiViewFrame extends javax.swing.JFrame implements MusicEditorGuiView {

  private final int SQUARE_SIZE = 20;
  private final int BOUNDARY_SIZE = 40;
  JScrollPane scrollPane;
  MusicEditorViewModel mevm;
  private JPanel displayPanel;

  /**
   * Constructor for a new GuiViewFrame, given a MusicEditorViewModel, returns the created
   * GuiViewFrame with the rendering done in the ConcreteGuiViewPanel,
   * which is inside a JScrollPane
   * to allow for scrolling in the window.
   *
   * @param mevm the MusicEditorViewModel to render
   */
  public GuiViewFrame(MusicEditorViewModel mevm) {
    this.displayPanel = new ConcreteGuiViewPanel(mevm);
    this.mevm = mevm;
    scrollPane = new JScrollPane(this.displayPanel,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.displayPanel.setMaximumSize(new Dimension(1000, 1000));
    scrollPane.setMaximumSize(new Dimension(1000, 1000));
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(scrollPane);
    this.pack();
  }

  /**
   * Initializes the view by allowing the rendered contents to be visible.
   */
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 1000);
  }

  @Override
  public void view(MusicEditorViewModel mv) throws IOException {
    this.displayPanel.repaint();
    this.initialize();
  }

  @Override
  public int getClickedPitch(int yCoord) {
    int maxPitch;
    if (this.mevm.lastBeat() == 0) {
      maxPitch = 107;
    } else {
      maxPitch = this.mevm.highestNote().getOctave() * 12 + 23;
    }
    int pitchesLess = (yCoord - BOUNDARY_SIZE) / SQUARE_SIZE;
    return maxPitch - pitchesLess;
  }


  @Override
  public int getClickedBeat(int xCoord) {
    int width = (int) this.scrollPane.getMaximumSize().getWidth();
    int currentBeat = this.mevm.getCurrentBeat();
    int leftSide = currentBeat - (currentBeat % ((width - BOUNDARY_SIZE) /
        SQUARE_SIZE));
    int displacement = (xCoord - BOUNDARY_SIZE) / SQUARE_SIZE;
    return leftSide + displacement;
  }

  @Override
  public void reactToDirectionalKey(boolean moveUp) {
    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
    if (moveUp) {
      verticalScrollBar.setValue(
          verticalScrollBar.getValue() - verticalScrollBar.getBlockIncrement());
    } else {
      verticalScrollBar.setValue(
          verticalScrollBar.getValue() + verticalScrollBar.getBlockIncrement());
    }
  }

  @Override
  public void reactCompletelyToDirectionalKey(boolean moveUp) {
    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
    if (moveUp) {
      verticalScrollBar.setValue(
          verticalScrollBar.getMinimum());
    } else {
      verticalScrollBar.setValue(
          verticalScrollBar.getMaximum());
    }
  }

  @Override
  public void addMouseListener(MouseListener ml) {
    this.displayPanel.addMouseListener(ml);
  }

}
