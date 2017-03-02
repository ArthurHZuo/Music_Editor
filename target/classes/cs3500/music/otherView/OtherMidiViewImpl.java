package cs3500.music.otherView;

import java.awt.event.KeyListener;
import java.util.Collection;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.otherModel.ANote;

/**
 * A skeleton for MIDI playback
 */
public class OtherMidiViewImpl implements OtherMusicEditorView {
  private final Synthesizer synth;
  private final Receiver receiver;
  private final ModelView model;
  private final Receiver mockR;
  private int curBeat = 0;

  public OtherMidiViewImpl(ModelView model) {
    Synthesizer s = null;
    Receiver r = null;
    try {
      s = MidiSystem.getSynthesizer();
      r = s.getReceiver();
      s.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = s;
    this.receiver = r;
    this.model = model;
    this.mockR = new OtherMidiViewImpl.MockReceiver();
  }

  public void playNote() throws InvalidMidiDataException {
//    for (int i = 0; i < this.model.getLastBeat(); i += 1) {
//      this.sendNote(this.model.getAtBeat(i));
//      this.curBeat = i;
//    }

    this.sendNote(this.model.getAtBeat(this.curBeat));
    this.curBeat += 1;
    if (this.model.getLastBeat() <= this.curBeat) {
      this.receiver.close();
    }
  }

  private void sendNote(Collection<ANote> c)
      throws InvalidMidiDataException {
    for (ANote n : c) {
      if (n.getStart() == this.curBeat) {
        ShortMessage start = new ShortMessage();

        start.setMessage(ShortMessage.NOTE_ON, n.getInstrument() - 1,
            n.midiIntValue(), n.getVolume());

        this.receiver.send(start, -1);
        this.mockR.send(start, -1);
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0,
            n.midiIntValue(), n.getVolume());
        this.receiver.send(stop, (n.getFinish() - n.getStart()) * this.model.tempo() +
            this.synth.getMicrosecondPosition());
        this.mockR.send(stop, (n.getFinish() - n.getStart()) * this.model.tempo() +
            this.synth.getMicrosecondPosition());
      }
    }
  }

  public void render() {
    try {
      this.playNote();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }

//    public getMockString() {
//      return this.MidiViewImple.MockReceiver.getNotes();
//    }
//  }
  }

  @Override
  public void addKeyListener(KeyListener k) {

  }


  public class MockReceiver implements Receiver {

    private StringBuilder notes;
    private int tempo = OtherMidiViewImpl.this.model.tempo();

    public MockReceiver() {
      this.notes = new StringBuilder("tempo" + this.tempo + "\n");
    }


    @Override
    public void send(MidiMessage message, long timeStamp) {
      ShortMessage msg = (ShortMessage) message;
      if (msg.getCommand() == ShortMessage.NOTE_ON) {
        notes.append(timeStamp / tempo + " ");
      } else {
        notes.append("" + timeStamp / tempo +
            (msg.getChannel() + 1) +
            msg.getData1() +
            msg.getData2() +
            "\n");
      }
    }

    @Override
    public void close() {
    }

    public StringBuilder getNotes() {
      return this.notes;
    }
  }

}
