package cs3500.music.model;

/**
 * An implementation of NoteModel with Notes ranging from from C1 to G9
 */
final class Note implements NoteModel {
  private final int duration;
  private final Pitch pitch;
  private final int octave;
  private final int startBeat;
  private final int instrument;
  private final int volume;

  /**
   * Constructor for Note with all fields taken. 
   *
   * @param duration  the number of beats for the note.
   * @param pitch     the letter valued pitch of a note.
   * @param octave    the integral octave of the note.
   * @param startBeat the integral beat number of the start of the note.
   * @throws IllegalArgumentException if: {@code duration} is less than or equal to 0 {@code octave}
   *                                  is less than 1 or greater than 8 {@code startBeat} is less
   *                                  than 0
   */
  public Note(int duration, Pitch pitch, int octave, int startBeat, int volume, int instrument) {
    if (duration <= 0 || octave < 1 || octave > 8 || startBeat < 0) {
      throw new IllegalArgumentException("Arguments must be valid for a Note" +
          duration + " " + octave + " " + startBeat);
    }
    this.duration = duration;
    this.pitch = pitch;
    this.octave = octave;
    this.startBeat = startBeat;
    this.volume = volume;
    this.instrument = instrument;
  }

  @Override
  public int getBeat() {
    return this.startBeat;
  }

  @Override
  public Pitch getPitch() {
    return this.pitch;
  }

  @Override
  public int getOctave() {
    return this.octave;
  }

  @Override
  public int getDuration() {
    return this.duration;
  }

  @Override
  public int getVolume() {
    return this.volume;
  }

  @Override
  public int getInstrument() {
    return this.instrument;
  }

  @Override
  public boolean equals(NoteModel other) {
    return this.getBeat() == other.getBeat()
        && this.getDuration() == other.getDuration()
        && this.getOctave() == other.getOctave()
        && this.getPitch() == other.getPitch()
        && this.getInstrument() == other.getInstrument()
        && this.getVolume() == other.getVolume();

  }

  @Override
  public boolean equals(Object other) {
    return other instanceof NoteModel && this.equals((NoteModel) other);
  }

  @Override
  public int comparePitch(NoteModel other) {
    if (other == null) {
      throw new IllegalArgumentException("argument cannot be null");
    }
    if (this.getOctave() == other.getOctave()) {
      return -1 * this.getPitch().compareTo(other.getPitch());
    } else {
      return other.getOctave() - this.getOctave();
    }
  }
}
