package cs3500.music.otherModel;

import java.util.Collection;

public interface OtherMusicEditorModel {

  /**
   * Adds Music to the music editor view
   *
   * @param n represents the note to be added (i.e. whole, half, or quarter)
   * @throws IllegalArgumentException if the given octave isn't within [0,9]
   * @throws IllegalArgumentException if the note's location isn't on the given editor
   */
  void addNote(ANote n);

  /**
   * Removes a note from the editor
   *
   * @param n represents the note to be removed
   * @throws IllegalArgumentException if there is no note at the given location
   * @throws IllegalArgumentException if the given octave isn't within [0,9]
   */
  void removeNote(ANote n);

  /**
   * Moves a note to a new pitch
   */
  void movePitch(ANote note, Pitch pitch, int octave);

  /**
   * Changes which beats a note is played on
   */
  void moveBeat(ANote note, int newStart, int newEnd);

  /**
   * Finds the highest note in the music
   *
   * @return note with the highest pitch and the highest octave
   */
  ANote getHighestNote();

  /**
   * Finds the lowest note in the music
   *
   * @return note with the lowest pitch and the lowest octave
   */
  ANote getLowestNote();

  /**
   * Finds the first note in the piece
   *
   * @return the beat of the first note
   */
  int firstBeat();


  /**
   * Finds the last note in the piece
   *
   * @return the last beat in the piece
   */
  int lastBeat();

  /**
   * Finds a note with a specific sound being played at a specific beat
   *
   * @param beat   the first beat of the note
   * @param p      the pitch of the note
   * @param octave the octave the pitch is in
   * @return the note that matches the given description
   * @throws IllegalArgumentException  when there is no note found
   * @throws IndexOutOfBoundsException when given improper octave or beat
   */
  ANote getNote(int beat, Pitch p, int octave);

  /**
   * Finds if there is a note being played at the given beat and pitch
   *
   * @param beat   the beat the note starts
   * @param pitch  the pitch of the note
   * @param octave the octave of the pitch
   * @return true if there is a note at this location
   * @throws IllegalArgumentException if not given proper data
   */
  boolean isNoteAt(int beat, Pitch pitch, int octave);

  /**
   * gives a string representation of the music
   */
  String drawScore();

  /**
   * Returns a collection of all the Notes at a given beat
   *
   * @param beat represents the beat to pull from
   * @return a collection all Notes at this beat
   */
  Collection<ANote> notesAtBeat(int beat);

  /**
   * Print Method that outputs a console rendering of the given piece
   */
  void print();

  /**
   * Determines the total number of notes in this model's score
   *
   * @return the number of notes in this model's score
   */
  int numNote();

  /**
   * Sets this model's tempo
   *
   * @param t value to set tempo to
   */
  void setTempo(int t);

  /**
   * Gets all distict notes in the model
   *
   * @return a collection of all distinct noets
   */
  Collection<ANote> allNotes();

  /**
   * Return the model's tempo
   *
   * @return model's tempo value
   */
  int getTempo();

  /**
   * Returns the nummber of beats in the score
   */
  int numBeats();

  /**
   * Adds ArrayLists to increase number of beats in score
   *
   * @param beats is how many beats to add
   */
  void addBeats(int beats);


  interface Builder {
    /**
     * Sets the how long the piece is.
     *
     * @param beats the number of beats i nthe piece
     * @return {@code this}, for method chaining
     * @throws IllegalArgumentException when beats < 0
     */
    Builder playLength(int beats);

    /**
     * Initializes the nested arraylist used to store the notes.
     */
    void buildScore();


    /**
     * Builds and returns the specified model.
     *
     * @return a new music sheet
     */
    OtherMusicEditorModel build();


  }

}
