package cs3500.music.view;

import java.io.IOException;

import cs3500.music.model.MusicEditorViewModel;

/**
 * MusicEditor View interface for viewing a MusicEditorModel.
 */
public interface MusicEditorView {

  /**
   * Displays the state of the MusicEditor for the user.
   *
   * @param vm the contents of the grid
   * @throws IOException if writing the output throws
   */
  void view(MusicEditorViewModel vm) throws IOException;

}
