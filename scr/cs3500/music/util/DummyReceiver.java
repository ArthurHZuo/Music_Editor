package cs3500.music.util;

import java.io.IOException;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * Implementation of Receiver interface to provide output of messages to an {@link Appendable}.
 */
class DummyReceiver implements Receiver {
  private final Appendable log;

  /**
   * Constructor for DummyReceiver
   *
   * @param log the {@link Appendable} that messages will be output to.
   * @throws IllegalArgumentException if {@code log} is null.
   */
  DummyReceiver(Appendable log) {
    if (log == null) {
      throw new IllegalArgumentException("log must be non-null");
    }
    this.log = log;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    try {
      log.append("message: ");
      for (int i = 0; i < message.getLength(); i++) {
        log.append(Byte.toString(message.getMessage()[i])).append(" ");
      }
      log.append(Long.toString(timeStamp)).append("\n");
    } catch (IOException e) {
      throw new IllegalStateException();
    }

  }

  @Override
  public void close() {
  }
}
