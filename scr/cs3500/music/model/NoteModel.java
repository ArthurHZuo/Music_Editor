package cs3500.music.model;

/**
 * Interface for notes in a music editor.
 */
public interface NoteModel {
  /**
   * Returns the beat that this Note starts on. This must be greater than 1
   *
   * @return An integer that represents the beat that the Note starts on.
   */
  int getBeat();

  /**
   * Returns the letter valued Pitch of this Note.
   *
   * @return The letter valued Pitch
   */
  Pitch getPitch();

  /**
   * Returns the octave of this note. This must be a integer betwen 1 and 8 inclusive
   *
   * @return The interger valued octave that this note's pitch is.
   */
  int getOctave();

  /**
   * Returns the duration of this note in beats. This must be a integer greater than 0.
   *
   * @return the integer number of beats that this note lasts.
   */
  int getDuration();

  /**
   * Returns the volume of the given note in the range [0, 128)
   *
   * @return the integer number that represents the volume from 0 to 127.
   */
  int getVolume();

  /**
   * Returns the instrument of the given note in the range [0, 128)
   *
   * @return the integer number that represents the instrument from 0 to 127.
   */
  int getInstrument();

  /**
   * Checks equality of the given note based on this note. Equality is based on beat the notes start
   * and end on as well as the pitch and octave.
   *
   * @param otherNote the note to compare this note to.
   * @return the equivalency of this note to {@code otherNote}.
   * @throws IllegalArgumentException if {@code otherNote} is null.
   */
  boolean equals(NoteModel otherNote);

  /**
   * Compares two NoteModels based on pitch where if this NoteModel is higher than the given
   * NoteModel then the answer is negative. If the two NoteModels are the same pitch then the answer
   * is 0. Otherwise the answer is positive.
   *
   * @return the integer comparison of this NoteModel with the given NoteModel based on pitch.
   * @throws IllegalArgumentException if {@code otherNote} is null.
   */
  int comparePitch(NoteModel other);
}
