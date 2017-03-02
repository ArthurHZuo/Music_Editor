package cs3500.music.otherModel;


/**
 * Created by Raj on 11/10/2015.
 */
public abstract class ANote implements Comparable<ANote> {

  /**
   * where the note starts
   */
  private int start;
  /**
   * the beat the note will finish on
   */
  private int finish;
  /**
   * This note's pitch
   */
  private Pitch pitch;
  /**
   * This note's octave
   */
  private int octave;
  /**
   * This note's volume
   */
  private int volume;
  /**
   * This note's instrument
   */
  private int instrument;
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
   * @param volume volume of the note
   */

  public ANote(int start, int finish, Pitch pitch, int octave, int volume, int instrument) {
    this.start = start;
    this.finish = finish;
    this.pitch = pitch;
    this.octave = octave;
    this.instrument = instrument;
    this.volume = volume;
  }

  /**
   * Getter methods for Note fields.
   */
  public int getStart() {
    return this.start;
  }

  public int getFinish() {
    return this.finish;
  }

  public Pitch getPitch() {
    return this.pitch;
  }

  public int getOctave() {
    return this.octave;
  }

  public int getVolume() {
    return this.volume;
  }

  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Setter methods methods for Note fields.
   */
  public void setStart(int start) {
    this.start = start;
  }

  public void setFinish(int finish) {
    this.finish = finish;
  }

  public void setPitch(Pitch pitch) {
    this.pitch = pitch;
  }

  public void setVolume(int v) {
    if (0 <= v && v <= 127) {
      this.volume = v;
    } else {
      throw new IllegalArgumentException("Invalid Volume");
    }
  }

  /**
   * Checks if the pitch and octave of a note are the same.
   *
   * @param p      represents the pitch of the given Note
   * @param octave represents the octave of the given Note
   */
  public Boolean sameSound(Pitch p, int octave) {
    return this.pitch == p &&
        this.octave == octave;
  }

  /**
   * Checks for equality between this Note and the given Note
   *
   * @param that represents the Note to be compared to
   * @return whether the two Notes are the same
   */
  public Boolean sameNote(ANote that) {
    return this.sameSound(that.getPitch(), that.getOctave()) &&
        this.start == that.start &&
        this.finish == that.finish;
  }

  /**
   * Compares the pitch and octave of two notes.
   *
   * @return whether or not this note is higher than that note
   */
  public Boolean isHigher(ANote that) {
    return that == null ||
        (Pitch.getIntValue(this.getPitch()) + (this.octave * 11)) >
            (Pitch.getIntValue(that.getPitch()) + (that.octave * 11));

  }

  /**
   * Allows comparison between two notes (First Note comes first) (i.e. A Note that starts at beat 2
   * is before one that starts at beat 5)
   */
  public int compareTo(ANote other) {
    if (this.intValue() > other.intValue()) {
      return 1;
    } else if (this.intValue() < other.intValue()) {
      return -1;
    } else {
      return 0;
    }
  }

  /**
   * Compares the pitch and octave of two notes.
   *
   * @return whether or not this note is lower than that note
   */
  public Boolean isLower(ANote that) {
    return that == null ||
        (Pitch.getIntValue(this.getPitch()) + (this.octave * 11)) <
            (Pitch.getIntValue(that.getPitch()) + (that.octave * 11));
  }

  /**
   * Overridde the default toString to return the a string representation of the Note. Represented
   * by the pitch followed by the octave number
   *
   * @return string representation of this note.
   */
  @Override
  public String toString() {
    return this.octave + this.pitch.toString();
  }

  public int intValue() {

    return Pitch.getIntValue(pitch) + (octave * 11);
  }

  // Coverts octave back to midi value
  //(Needed because C0 starts at 24, and thus an offset was needed
  public int midiIntValue() {

    int oct = (12 * this.octave) + 12;
    return Pitch.getIntValue(pitch) + oct;
  }

  /**
   * Determines whether the given note is the head or a sustain
   *
   * @param beat represents the beat at which to add the note
   * @return a boolean, with true representing the node being a head
   */
  public boolean isHead(int beat) {
    return this.getStart() == beat;
  }


}
