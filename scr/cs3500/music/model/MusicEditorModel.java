package cs3500.music.model;

/**
 * An interface for a music editor model.
 */
public interface MusicEditorModel {

  /**
   * Adds {@code note} to the editor.
   *
   * @param note a NoteModel that is to be added to the
   * @throws IllegalArgumentException if {@code note} is null.
   */
  void addNote(NoteModel note);

  /**
   * Removes the first {@link NoteModel} that are equivalent to {@code note}. Equality is based on
   * the start and end of a note as well as its pitch and octave.
   *
   * @param note the note to be removed from the editor
   * @throws IllegalArgumentException if {@code note} is null or if it is not in the editor.
   */
  void removeNote(NoteModel note);

  /**
   * Changes the first {@link NoteModel} in the editor equivalent to {@code oldNote} to {@code
   * newNote}.
   *
   * @param oldNote the note to be replaced by {@code newNote} in the editor.
   * @param newNote the note to be replace by {@code oldNote} in the editor.
   * @throws IllegalArgumentException if either {@code oldNote} or {@code newNote} are null or if
   *                                  {@code oldNote} is not a note that is in the editor.
   */
  void editNote(NoteModel oldNote, NoteModel newNote);

  /**
   * Sets the current beat of the MusicEditor Model to {@code beat}
   *
   * @param beat the integer valued beat that this model is at.
   * @throws IllegalArgumentException if {@code beat} is less than 0.
   */
  void setCurrentBeat(int beat);

  /**
   * Toggles if the MusicEditor is playing or not.
   */
  void togglePlaying();
}
