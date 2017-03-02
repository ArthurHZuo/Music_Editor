package cs3500.music.model;

import cs3500.music.util.NoteBuilder;

/**
 * Builder For Notes
 */
public class NoteBuilderImp implements NoteBuilder {
  private int start;
  private int end;
  private int instrument;
  private int volume;
  private int pitch;

  /**
   * Default constructor for NoteBuilder with default values for fields.
   */
  public NoteBuilderImp() {
    this.start = 1;
    this.end = 2;
    this.instrument = 1;
    this.pitch = 60;
    this.volume = 127;
  }

  /**
   * Returns the {@link Pitch} value equivalent of the given int midi pitch
   *
   * @param pitch A valid midi pitch that is in the interval [24, 128)
   * @return the {@link Pitch} value of the given midi pitch value.
   * @throws IllegalArgumentException if {@code pitch} is outside the range [24, 128)
   */
  private static Pitch getPitch(int pitch) {
    if (pitch < 24 || pitch > 128) {
      throw new IllegalArgumentException("Pitch out of range");
    }
    int pitchI = (pitch - 24) % 12;
    int count = 0;
    Pitch out = null;
    for (Pitch tempPitch : Pitch.values()) {
      if (pitchI == count) {
        out = tempPitch;
      }
      count++;
    }
    return out;
  }

  /**
   * Returns the integer octave value equivalent of the given int midi pitch
   *
   * @param pitch A valid midi pitch that is in the interval [24, 128)
   * @return the octave that the given midipitch  is equivalent to.
   * @throws IllegalArgumentException if {@code pitch} is outside the range [24, 128)
   */
  private static int getOctave(int pitch) {
    if (pitch < 24 || pitch > 128) {
      throw new IllegalArgumentException("Pitch out of range");
    }
    return (int) Math.ceil((pitch - 23) / 12.0);
  }

  @Override
  public NoteModel build() {
    if (this.end < this.start) {
      throw new IllegalStateException("A note's start has to be before its end");
    }
    Pitch enumPitch = getPitch(pitch);
    int octave = getOctave(pitch);
    return new Note(end - start, enumPitch, octave, start, volume, instrument);
  }

  @Override
  public NoteBuilder setStart(int start) {
    if (start < 0) {
      throw new IllegalArgumentException("Start cannot be after start or be negative.");
    }
    this.start = start;
    return this;
  }

  @Override
  public NoteBuilder setEnd(int end) {
    if (end < 0) {
      throw new IllegalArgumentException("End cannot be before start or be negative.");
    }
    this.end = end;
    return this;
  }

  @Override
  public NoteBuilder setInstrument(int instrument) {
    if (instrument < 1 || instrument > 128) {
      throw new IllegalArgumentException("Instrument out of range" + instrument);
    }
    this.instrument = instrument;
    return this;
  }

  @Override
  public NoteBuilder setPitch(int pitch) {
    if (pitch < 24 || pitch > 128) {
      throw new IllegalArgumentException("Pitch out of range");
    }
    this.pitch = pitch;
    return this;
  }

  @Override
  public NoteBuilder setVolume(int volume) {
    if (volume < 0 || volume > 128) {
      throw new IllegalArgumentException("volume out of range");
    }
    this.volume = volume;
    return this;
  }
}