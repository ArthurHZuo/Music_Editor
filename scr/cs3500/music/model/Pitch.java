package cs3500.music.model;

/**
 * Enum of all pitches
 */
public enum Pitch {
  C, CSHARP, D, DSHARP, E, F, FSHARP, G, GSHARP, A, ASHARP, B;

  @Override
  public String toString() {
    switch (this) {
      case A:
        return "A";
      case B:
        return "B";
      case C:
        return "C";
      case D:
        return "D";
      case E:
        return "E";
      case F:
        return "F";
      case G:
        return "G";
      case ASHARP:
        return "A♯";
      case CSHARP:
        return "C♯";
      case DSHARP:
        return "D♯";
      case FSHARP:
        return "F♯";
      case GSHARP:
        return "G♯";
    }
    throw new IllegalStateException("Something bad happened.");
  }

  /**
   * Returns the Midi pitch value of this {@link Pitch} with consideration to {@code octave}
   *
   * @param octave the octave value from 1 to 8 of this note.
   * @return the Midi pitch value of this {@link Pitch} and {@code octave}.
   * @throws IllegalArgumentException if {@code octave} is outside the interval [1,8]
   */
  public int getMidiPitch(int octave) {
    if (octave < 0 || octave > 8) {
      throw new IllegalArgumentException("octave out of range");
    }
    int pitchI = 0;
    int count = 0;
    for (Pitch p : Pitch.values()) {
      if (p == this) {
        pitchI = count;
      }
      count++;
    }
    return pitchI + (12 * (octave + 1));
  }

}
