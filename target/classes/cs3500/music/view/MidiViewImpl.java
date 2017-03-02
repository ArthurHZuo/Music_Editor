package cs3500.music.view;

import cs3500.music.model.MusicEditorViewModel;
import cs3500.music.model.NoteModel;

import javax.sound.midi.*;

/**
 *
 */
public class MidiViewImpl implements MidiView {
  private final MidiDevice synth;
  private final Receiver receiver;
  private final boolean[] channelInstrument;


  public MidiViewImpl() throws MidiUnavailableException {
    this(MidiSystem.getSynthesizer());
  }

  /**
   * Constructor for MidiView
   *
   * @param synth the {@link MidiDevice} that MidiMessages will be output to.
   */
  public MidiViewImpl(MidiDevice synth) {
    if (synth == null) {
      throw new IllegalArgumentException("MidiDevice must not be null");
    }
    Receiver tryReceiver = null;
    channelInstrument = new boolean[15];
    try {
      tryReceiver = synth.getReceiver();
      synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = synth;
    this.receiver = tryReceiver;
  }
  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */

  /**
   * Sends {@code receiver} messages to start and stop playback of {@code note}
   *
   * @param note  the {@link NoteModel} to be played.
   * @param tempo the integer tempo for the note to be played at.
   * @throws IllegalArgumentException if {@code note} is null or if {@code tempo} is less than 1.
   */
  public void playNote(NoteModel note, int tempo) throws InvalidMidiDataException {
    if (note == null || tempo < 1) {
      throw new IllegalArgumentException("Arguments must be valid");
    }
    boolean changeC = false;
    if (!channelInstrument[note.getInstrument() - 1]) {
      changeC = true;
      channelInstrument[note.getInstrument() - 1] = true;
    }
    if (changeC) {
      this.receiver.send(
          new ShortMessage(ShortMessage.PROGRAM_CHANGE,
              note.getInstrument() - 1, note.getInstrument() - 1, note.getInstrument() - 1), -1);
    }
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument() - 1,
        note.getPitch().getMidiPitch(note.getOctave()),
        note.getVolume());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument() - 1,
        note.getPitch().getMidiPitch(note.getOctave()),
        note.getVolume());

    this.receiver.send(start, -1);
    this.receiver.send(stop, (note.getDuration() * tempo) + this.synth.getMicrosecondPosition());
  }

  /**
   * Only for midi testing. Will be deleted after this assignment
   */
  public void play(MusicEditorViewModel vm, int beat) throws InvalidMidiDataException {
    if (vm == null || beat < 0) {
      throw new IllegalArgumentException("Arguments must be valid");
    }
    for (NoteModel note : vm.getNotesStartingAt(beat)) {
      playNote(note, vm.getTempo());
    }
    if (beat == vm.lastBeat() + 2) {
      this.receiver.close();
    }
  }
}