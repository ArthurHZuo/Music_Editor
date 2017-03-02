package cs3500.music.util;

import java.util.List;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

/**
 * Dummy implementation of MidiDevice to give back a {@link DummyReceiver} for logging
 */
public class DummyMidiDevice implements MidiDevice {
  private final Appendable log;

  public DummyMidiDevice(Appendable log) {
    if (log == null) {
      throw new IllegalArgumentException("log must be non-null");
    }
    this.log = log;
  }

  @Override
  public Info getDeviceInfo() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void open() throws MidiUnavailableException {
  }

  @Override
  public void close() {
  }

  @Override
  public boolean isOpen() {
    throw new UnsupportedOperationException();
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public int getMaxReceivers() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getMaxTransmitters() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return new DummyReceiver(this.log);
  }

  @Override
  public List<Receiver> getReceivers() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Transmitter> getTransmitters() {
    throw new UnsupportedOperationException();
  }
}
