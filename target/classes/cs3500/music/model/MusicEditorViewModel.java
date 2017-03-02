package cs3500.music.model;

import java.util.List;

/**
 * MusicEditor ViewModel interface for a view to use to get data from a model.
 */
public interface MusicEditorViewModel {

  /**
   * Returns a List of all the notes that are playing at {@code beat}.
   *
   * @param beat the integral value of the beat to check which notes are playing during.
   * @return a List of all the notes that are playing at {@code beat}.
   * @throws IllegalArgumentException if beat is less than 1.
   */
  List<NoteModel> getNotesPlayingAt(int beat);

  /**
   * Returns a List of all the notes that start playing at {@code beat}.
   *
   * @param beat the integral value of the beat to check which notes start playing at.
   * @return a List of all the notes that start playing at {@code beat}.
   * @throws IllegalArgumentException if beat is less than 1.
   */
  List<NoteModel> getNotesStartingAt(int beat);

  /**
   * Returns the last beat that any notes in the editor play on. If the editor has no notes then it
   * returns 0.
   *
   * @return the last beat that any notes play on or 0 if there are no notes in the editor.
   */
  int lastBeat();

  /**
   * Returns the lowest pitched note in this editor. When multiple notes are the same pitch it
   * returns the note that is earliest in the piece that was added earliest.1
   *
   * @return the NoteModel that is the lowest pitched in this editor.
   * @throws IllegalStateException if there are no notes in the editor.
   */
  NoteModel lowestNote();

  /**
   * Returns the highest pitched note in this editor. When multiple notes are the same pitch it
   * returns the note that is earliest in the piece that was added earliest.
   *
   * @return the NoteModel that is the highest pitched in this editor.
   * @throws IllegalStateException if there are no notes in the editor.
   */
  NoteModel highestNote();

  /**
   * Gets the tempo of this MusicEditorViewModel.
   *
   * @return the integer value tempo of this piece.
   */
  int getTempo();

  /**
   * Gets the current beat that this MusicEditorViewModel is at.
   *
   * @return the integer valued current beat of this MusicEditorViewModel.
   */
  int getCurrentBeat();

  /**
   * Is the MusicEditorViewModel currently playing?
   *
   * @return the boolean value which answers if the piece is playing or not.
   */
  boolean isPlaying();
}
