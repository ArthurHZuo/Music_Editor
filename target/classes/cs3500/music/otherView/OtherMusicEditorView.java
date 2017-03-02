package cs3500.music.otherView;

import java.awt.event.KeyListener;

/**
 * Created by Raj on 11/9/2015.
 */
public interface OtherMusicEditorView {

  /**
   * Allows the user to render their view (whichever view that may be)
   */
  void render();

  /**
   * Adds a KeyListener to the view to allow it to be controllable
   *
   * @param k represents the KeyListener to be added
   */
  void addKeyListener(KeyListener k);

}