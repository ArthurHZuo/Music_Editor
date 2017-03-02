package cs3500.music.otherView;

import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */

// TODO Controller Interface & Class
// TODO Composite Class combines values

public class OtherGuiViewFrame extends javax.swing.JFrame implements OtherGuiView {

  private OtherConcreteGuiViewPanel displayPanel;
  private JScrollPane scroll;

  /**
   * Creates new GuiView
   */
  //TODO Figure out why scroll isn't changing
  //TODO Why is the MouseListener not recognized?
  public OtherGuiViewFrame(ModelView m) {

    this.displayPanel = new OtherConcreteGuiViewPanel(m);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    scroll = new JScrollPane(displayPanel);
    scroll.requestFocusInWindow();

    this.add(scroll);
    this.pack();
    scroll.getHorizontalScrollBar().setValue(0);
  }

  public void initialize() {
    this.setVisible(true);
  }

  public void render() {
    this.initialize();
  }

  public void changeBlue() {
    displayPanel.changeBlue();
    displayPanel.repaint();
  }

  public void changeYellow() {
    displayPanel.changeYellow();
  }

  public void toEnd() {

    displayPanel.toEnd();

  }

  public void toBeginning() {
    displayPanel.toHome();
  }

  public void tickBeat() {
    displayPanel.tickBeat();

  }

  public void scrollRight() {
    displayPanel.scrollRight();
  }

  @Override
  public void scrollLeft() {
    displayPanel.scrollLeft();
  }

  public void addMouseListener(MouseListener m) {
    displayPanel.addMouseListener(m);
  }

  public void addPitchs() {
    displayPanel.addPitchs(1);
  }

  protected double pitchNum() {
    return displayPanel.getPreferredSize().getHeight();
  }

  @Override
  public int getClickedPitch(int yCoord) {
    return displayPanel.getClickedPitch(yCoord);
  }

  @Override
  public int getClickedBeat(int xCoord) {
    return displayPanel.getClickedBeat(xCoord);
  }

}