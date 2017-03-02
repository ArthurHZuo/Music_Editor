package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.MusicEditorViewModel;

/**
 * MusicEditor View interface for viewing a MusicEditorModel.
 */
public interface MidiView {

  /**
   * Sends {@code receiver} messages to play notes based on notes starting at {@code vm} on {@code
   * beat}
   *
   * @param vm   the {@code MusicEditorViewModel} whose notes are to be played.
   * @param beat the integer value of the beat that notes who start on will be played.
   * @throws IllegalArgumentException {@code vm } is null or {@code beat} < 0
   */
  void play(MusicEditorViewModel vm, int beat) throws InvalidMidiDataException;
}
