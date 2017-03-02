package cs3500.music.view;


import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by Spencer on 11/19/15.
 *
 * An interface for view methods that are only relevant to the GUI view, such as scrolling and
 * adding/removing notes.
 */
public interface MusicEditorGuiView extends MusicEditorView {
  /**
   * Adds a {@link KeyListener} to be called when viewing this view.
   *
   * @param kl the {@link KeyListener} to be added to this viww
   */
  void addKeyListener(KeyListener kl);

  /**
   * Adds a {@link MouseListener} to be called when viewing this view.
   *
   * @param ml the {@link MouseListener} to be added to this viww
   */
  void addMouseListener(MouseListener ml);

  /**
   * Given an Y coordinate that is clicked by the mouse, return the pitch that it corresponds to on
   * the scale, taking into account any scrolling that was done with the vertical scrollbar.
   *
   * @param yCoord the Y coordinate clicked, represented as an int.
   * @return the int representation of the pitch clicked.
   */
  int getClickedPitch(int yCoord);

  /**
   * Given an X coordinate that is clicked by the mouse, return the beat that it corresponds to on
   * the scale, taking into account any scrolling that has previously taken place.
   *
   * @param xCoord the X coordinate clicked, represented as an int.
   * @return the int representing the beat clicked
   */
  int getClickedBeat(int xCoord);

  /**
   * Given a direction, move the scrollbar up or down by one increment.
   *
   * @param moveUp do we have to move the scrollbar up or down?
   */
  void reactToDirectionalKey(boolean moveUp);

  /**
   * Moves the gui view all the way to the top or bottom of the scrollbar, depending on the given
   * boolean.
   *
   * @param moveUp do we move the scrollbar up or down?
   */
  void reactCompletelyToDirectionalKey(boolean moveUp);

  /**
   * Change the view to the given beat.
   * @param
   * @throws IllegalArgumentException if beat < 0
   */

}
