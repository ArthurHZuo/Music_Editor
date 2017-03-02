package cs3500.music.otherView;


import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Sub-View interface; contains important GUI-specific methods that are necessary for a functional
 * Gui View, but unrelated to other views
 */
public interface OtherGuiView extends OtherMusicEditorView {

  /**
   * Gives keyboard in controller access to the guiView, allowing user keyboard input to have an
   * impact on the gui.
   */
  void addKeyListener(KeyListener k);

  /**
   * Gives mouse in controller access to the guiView, allowing user to use mouse input to have a
   * meaningful impact on the gui.
   */
  void addMouseListener(MouseListener m);


  /**
   * Changes the color of the Note sustains to Cyan blue
   */
  void changeBlue();

  /**
   * Changes the color of the Note sustains to Yellow
   */
  void changeYellow();

  /**
   * Pushs the GUI view down to the very end
   */
  void toEnd();

  /**
   * Moves the Gui view beat counter by one
   */
  void tickBeat();

  /**
   * Adds a pitch on the top and bottom of the Gui View (Useful if the client wants to add Notes at
   * those locations)
   */
  void addPitchs();

  /**
   * Method to let the user scroll the board to the right
   */
  void scrollRight();

  /**
   * Method to let the user scroll the board to the left
   */
  void scrollLeft();

  /**
   * Calculates the Pitch at the given click
   *
   * @param yCoord represents mouse's y-coordinate
   * @return the pitch at the mouse y-coordinate
   */
  int getClickedPitch(int yCoord);

  /**
   * Calculates the beat at the given coordinate
   *
   * @param xCoord represents mouse's x-coordinate
   * @return the beat at the given x coordinate
   */
  int getClickedBeat(int xCoord);


}