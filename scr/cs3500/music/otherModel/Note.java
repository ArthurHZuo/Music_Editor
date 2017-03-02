package cs3500.music.otherModel;


public class Note extends ANote {


  /**
   * INVARIANTS
   * @inv start >= 0
   * @inv finish >= start
   * @inv 10 >= octave >= 1
   */
  /**
   * private constructor to protect data
   *
   * @param start  the starting beat
   * @param finish amount of beats the note is
   * @param pitch  pitch of the note
   * @param octave octave of note is in
   */
  protected Note(int start, int finish, Pitch pitch, int octave, int volume, int instrument) {
    super(start, finish, pitch, octave, volume, instrument);
  }


  /**
   * Factory method for Note. Makes sure all the data satisfies the invariants before creating the
   * note!
   *
   * @param p      pitch of the note
   * @param start  start of the note
   * @param finish end of the note
   * @param octave octave of the note
   * @param volume octave of the note
   */
  public static Note makeNote(Pitch p, int start, int finish,
                              int octave, int volume, int instrument) {
    if (octave > 10 || octave <= -2) {
      throw new IllegalArgumentException(Integer.toString(octave) + " is an invalid octave");
    }
    if (start < 0) {
      throw new IllegalArgumentException("Invalid start beat.");
    }
    if (finish < start) {
      throw new IllegalArgumentException("Invalid finish beat.");
    }
    return new Note(start, finish, p, octave, volume, instrument);
  }

  /**
   * Factory method for Note. Makes sure all the data satisfies the invariants before creating the
   * note! (Gives a default value of 64 for Volume (50%))
   *
   * @param p      pitch of the note
   * @param start  start of the note
   * @param finish end of the note
   * @param octave octave of the note
   */
  public static Note makeNote(Pitch p, int start, int finish,
                              int octave, int instrument) {
    if (octave > 10 || octave <= 0) {
      throw new IllegalArgumentException("Invalid octave.");
    }
    if (start < 0) {
      throw new IllegalArgumentException("Invalid start beat.");
    }
    if (finish < start) {
      throw new IllegalArgumentException("Invalid finish beat.");
    }
    return new Note(start, finish, p, octave, 64, instrument);
  }

}
