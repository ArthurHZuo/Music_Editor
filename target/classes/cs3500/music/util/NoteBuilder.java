package cs3500.music.util;

import cs3500.music.model.NoteModel;

/**
 * A builder of Notes.  So tests can be done outside of the package
 */
public interface NoteBuilder {
  /**
   * Constructs an actual Note, given the parameters that have been given.
   *
   * @return The new Note
   * @throws IllegalStateException if {@code start} is greater than {@code end}
   */
  NoteModel build();

  /**
   * Sets the start beat of the note.
   *
   * @param start The start time of the note, in beats
   * @return This builder
   * @throws IllegalArgumentException if {@code start} is negative
   */
  NoteBuilder setStart(int start);

  /**
   * Sets the end beat of the note.
   *
   * @param end The end time of the note, in beats
   * @return This builder
   * @throws IllegalArgumentException if {@code end} is negative
   */
  NoteBuilder setEnd(int end);

  /**
   * Sets the instrument of the note.
   *
   * @param instrument The instrument number (to be interpreted by MIDI)
   * @return This builder
   * @throws IllegalArgumentException if {@code instrument} is not in the interval [1 ,128)
   */
  NoteBuilder setInstrument(int instrument);

  /**
   * Sets the pitch of the note.
   *
   * @param pitch The pitch (in the range [24, 128), where 60 represents C4, the middle-C on a
   *              piano)
   * @return This builder
   * @throws IllegalArgumentException if {@code pitch} is not in the interval [24 ,128)
   */
  NoteBuilder setPitch(int pitch);

  /**
   * Sets the volume of the note.
   *
   * @param volume The volume (in the range [0, 128))
   * @return This builder
   * @throws IllegalArgumentException if {@code volume} is not in the interval [0 ,128)
   */
  NoteBuilder setVolume(int volume);
}
