package cs3500.music.view;

import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import cs3500.music.model.MusicEditorViewModel;

/**
 * Allows for the full playback of a {@link MusicEditorViewModel} under the interface of {@link
 * MusicEditorViewModel}
 */
public class MidiPlayView implements MusicEditorView {
  @Override
  public void view(MusicEditorViewModel vm) throws IOException {
    MidiView view;
    try {
      view = new MidiViewImpl();
    } catch (MidiUnavailableException e) {
      throw new IllegalStateException();
    }
    try {
      for (int i = 0; i < vm.lastBeat(); i++) {
        try {
          view.play(vm, i);
        } catch (InvalidMidiDataException e) {
          throw new IllegalStateException();
        }
        Thread.sleep(vm.getTempo() / 1000);
      }
    } catch (InterruptedException e) {
      throw new IllegalStateException();
    }
  }
}
